package com.jorge.challence.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.jorge.challence.buildError.HandlerError;
import com.jorge.challence.dto.MovieDTO;
import com.jorge.challence.dto.MoviesDTO;
import com.jorge.challence.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin("*")
public class MovieController {

  @Autowired
  @Qualifier("v1_mysql")
  private MovieService ms;

  @Autowired
  private HandlerError handlerError;

  // @GetMapping
  // public ResponseEntity<?> findAll(){
  // HashMap<String, List<MoviesDTO>> h = new HashMap<>();
  // h.put("movies", ms.findAll());
  // return new ResponseEntity<>(h ,HttpStatus.OK);
  // }
  @GetMapping
  public ResponseEntity<?> findByParams(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "genre", required = false) Long genre,
      @RequestParam(value = "order", required = false) String order) {
    HashMap<String, List<MoviesDTO>> h = new HashMap<>();
    h.put("movies", ms.findByParams(name, genre, order));
    return new ResponseEntity<>(h, HttpStatus.OK);
  }

  @GetMapping(params = "id")
  public ResponseEntity<?> findById(@RequestParam Long id) {
    HashMap<String, Object> h = new HashMap<>();
    h.put("movie", ms.findById(id));
    return new ResponseEntity<>(h, HttpStatus.OK);
  }

  @PostMapping("/{idMovie}/characters/{idCharacter}")
  public ResponseEntity<?> addCharacterToMovie(
      @PathVariable(name = "idMovie", required = true) Long idMovie,
      @PathVariable(name = "idCharacter", required = true) Long idCharacter) {
        System.out.println("movie: " + idMovie + "  character: " + idCharacter);
    ms.addCharacterToMovie(idCharacter, idMovie);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @PostMapping()
  public ResponseEntity<?> createMovie(@Valid @RequestBody MovieDTO m_dto, BindingResult result){
    if(result.hasErrors())
      return new ResponseEntity<>(handlerError.getErrors(result), HttpStatus.UNPROCESSABLE_ENTITY);
    ms.createMovie(m_dto);
    if( m_dto.getId() != null){
      HashMap<String,MovieDTO> h = new HashMap<>();
      h.put("movie", m_dto);
      return new ResponseEntity<>(h, HttpStatus.ACCEPTED);
    } else{
      HashMap<String, String> not_movie = new HashMap<>();
      not_movie.put("error", "no hay Genero con #ID: " + m_dto.getGender_id() );
      return new ResponseEntity<>(not_movie ,HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  @PutMapping
  public ResponseEntity<?> updateMovie(@Valid @RequestBody MovieDTO m_dto, BindingResult result ,@RequestParam(name = "id", required = false) Long id) {
    if(result.hasErrors())
      return new ResponseEntity<>(handlerError.getErrors(result), HttpStatus.UNPROCESSABLE_ENTITY);
    if(id != null)
      m_dto.setId(id);
    ms.updateMovie(m_dto);
    HashMap<String, MovieDTO> h = new HashMap<>();
    h.put("movie", m_dto);
    return new ResponseEntity<>(h, HttpStatus.ACCEPTED);
  }

  @DeleteMapping(params = "id")
  public ResponseEntity<?> deleteMovie(@RequestParam Long id) {
    ms.deleteMovie(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{idMovie}/characters/{idCharacter}")
  public ResponseEntity<?> deleteCharacter(@PathVariable(name = "idMovie", required = true) Long idMovie, @PathVariable(name = "idCharacter", required = true) Long idCharacter ){
    ms.deleteCharacter(idCharacter, idMovie);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

}
