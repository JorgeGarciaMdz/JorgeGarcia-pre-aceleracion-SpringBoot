package com.jorge.challence.serviceImplement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.jorge.challence.domain.Character;
import com.jorge.challence.domain.Movie;
import com.jorge.challence.dto.CharacterDTO;
import com.jorge.challence.dto.CharactersDTO;
import com.jorge.challence.repository.CharacterRepository;
import com.jorge.challence.service.CharacterService;
import com.jorge.challence.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v1_mysql")
public class CharacterServiceI implements CharacterService {

  @Autowired
  @Qualifier("v1_mysql")
  private MovieService ms;

  @Autowired
  private CharacterRepository cr;

  @Override
  public void createCharacter(CharacterDTO c_dto) {
    Movie m = null;
    if (c_dto.getMovie_id() != null) {
      m = ms.findByIdMovie(c_dto.getMovie_id());
    }

    Character c = new Character();
    c.setName(c_dto.getName());
    c.setImage(c_dto.getImage());
    c.setAge(c_dto.getAge());
    c.setWeight(c_dto.getWeight());
    c.setHistory(c_dto.getHistory());
    c.setCreatedAt(new Date());
    c.setUpdatedAt(new Date());
    if ( m != null)
      c.addMovie(m);

    cr.saveAndFlush(c);

    for (Movie e : c.getMovies())
      c_dto.addMovie(e.getTitle());

    c_dto.setMovie_id(null);
    c_dto.setId(c.getId());

  }

  public void createCharacter(Character character) {
    character.setCreatedAt(new Date());
    character.setUpdatedAt(new Date());
    cr.saveAndFlush(character);
  }

  @Override
  public void updateCharacter(Character c) {
    cr.saveAndFlush(c);
  }

  @Override
  public void updateCharacter(CharacterDTO c_dto) {
    Optional<Character> c = cr.findByIdAndDeletedAtIsNull(c_dto.getId());
    if (c.isPresent()) {
      c.get().setName(c_dto.getName());
      c.get().setImage(c_dto.getImage());
      c.get().setAge(c_dto.getAge());
      c.get().setWeight(c_dto.getWeight());
      c.get().setHistory(c_dto.getHistory());
      c.get().setUpdatedAt(new Date());
      c.get().getMovies().forEach( m -> {
        c_dto.addMovie(m.getTitle());
      });
      c_dto.setMovie_id(null);
      cr.saveAndFlush(c.get());
    }
  }

  @Override
  public void deleteCharacter(Long id) {
    Optional<Character> c = cr.findByIdAndDeletedAtIsNull(id);
    if (c.isPresent()) {
      c.get().setDeletedAt(new Date());
      c.get().setMovies(new HashSet<>());
      cr.saveAndFlush(c.get());
    }
  }

  @Override
  public CharacterDTO findByIdDTO(Long id) {
    Optional<Character> c = cr.findByIdAndDeletedAtIsNull(id);
    if (c.isPresent()) {
      CharacterDTO c_dto = new CharacterDTO();
      c_dto.setId(c.get().getId());
      c_dto.setName(c.get().getName());
      c_dto.setImage(c.get().getImage());
      c_dto.setAge(c.get().getAge());
      c_dto.setWeight(c.get().getWeight());
      c_dto.setHistory(c.get().getHistory());

      for (Movie e : c.get().getMovies())
        c_dto.addMovie(e.getTitle());
      return c_dto;
    }
    return null;
  }

  @Override
  public List<CharactersDTO> findAll() {
    List<Character> c = cr.findByDeletedAtIsNull();
    List<CharactersDTO> cs_dto = new ArrayList<>();

    for (Character e : c)
      cs_dto.add(new CharactersDTO(e.getId(), e.getName(), e.getImage()));

    return cs_dto;
  }

  @Override
  public List<CharactersDTO> findByParams(String name, Integer age, Float weight, Long movie_id) {
    HashMap<String, Object> h = new HashMap<>();
    if (name != null)
      h.put("name", name);
    if (age != null)
      h.put("age", age);
    if (weight != null)
      h.put("weight", weight);
    if (movie_id != null) {
      h.put("movie", movie_id);
    }

    List<CharactersDTO> c_dto = new ArrayList<>();
    if (movie_id == null)
      for (Character c : cr.findByParamsOutMovieId(h))
        c_dto.add(new CharactersDTO(c.getId(), c.getName(), c.getImage()));
    else
      for (Character c : cr.findByParamsWithMovieId(h))
        c_dto.add(new CharactersDTO(c.getId(), c.getName(), c.getImage()));

    return c_dto;
  }

  @Override
  public Character findById(Long id) {
    Optional<Character> c = cr.findByIdAndDeletedAtIsNull(id);
    if (c.isPresent())
      return c.get();
    return null;
  }
}
