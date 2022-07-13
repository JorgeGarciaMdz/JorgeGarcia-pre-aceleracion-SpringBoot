package com.jorge.challence.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.jorge.challence.buildError.HandlerError;
import com.jorge.challence.dto.CharacterDTO;
import com.jorge.challence.dto.CharactersDTO;
import com.jorge.challence.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/characters")
@CrossOrigin("*")
public class CharacterController {

  @Autowired
  @Qualifier("v1_mysql")
  private CharacterService cs;

  @Autowired
  private HandlerError handlerError;

  @PostMapping
  public ResponseEntity<?> createCharacter(@Valid @RequestBody CharacterDTO c_dto, BindingResult result){
    if(result.hasErrors())
      return new ResponseEntity<>(handlerError.getErrors(result), HttpStatus.UNPROCESSABLE_ENTITY);

    cs.createCharacter(c_dto);
    if (c_dto.getId() != null) {
      HashMap<String, CharacterDTO> h = new HashMap<>();
      h.put("character", c_dto);
      return new ResponseEntity<>(h, HttpStatus.ACCEPTED);
    } else
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @PutMapping
  public ResponseEntity<?> updateCharacter(@Valid @RequestBody CharacterDTO c_dto, BindingResult result, 
  @RequestParam(name = "id", required = false) Long id){
    if( result.hasErrors())
      return new ResponseEntity<>(handlerError.getErrors(result), HttpStatus.UNPROCESSABLE_ENTITY);
    if( id != null)
    c_dto.setId(id);
    cs.updateCharacter(c_dto);
    HashMap<String, CharacterDTO> h = new HashMap<>();
    h.put("character", c_dto);
    return new ResponseEntity<>(h, HttpStatus.ACCEPTED);
  }

  @DeleteMapping( params = "id")
  public ResponseEntity<?> deleteCharacter(@RequestParam Long id){
    cs.deleteCharacter(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping(params = "id")
  public ResponseEntity<?> findById(@RequestParam Long id){
    CharacterDTO c_dto = cs.findByIdDTO(id);
    if( c_dto != null){
      HashMap<String, CharacterDTO> h = new HashMap<>();
      h.put("character", c_dto);
      return new ResponseEntity<>(h, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping
  public ResponseEntity<?> findByParams(
      @RequestParam(value="name", required = false) String name,
      @RequestParam(value = "age", required = false) Integer age, 
      @RequestParam(value = "weight", required = false) Float weight,
      @RequestParam(value = "movies", required = false) Long movies) {
      HashMap<String, List<CharactersDTO>> h = new HashMap<>();
      h.put("characters", cs.findByParams(name, age, weight, movies));
    return new ResponseEntity<>(h, HttpStatus.ACCEPTED);
  }

  // @GetMapping
  // public ResponseEntity<?> findAll(){
  //   HashMap<String, List<CharactersDTO>> h = new HashMap<>();
  //   h.put("characters", cs.findAll());
  //   return new ResponseEntity<>(h, HttpStatus.ACCEPTED);
  // }
}
