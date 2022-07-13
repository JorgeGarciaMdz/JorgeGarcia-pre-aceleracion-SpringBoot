package com.jorge.challence.serviceImplement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import com.jorge.challence.domain.Character;
import com.jorge.challence.domain.Gender;
import com.jorge.challence.domain.Movie;
import com.jorge.challence.dto.CharacterDTO;
import com.jorge.challence.dto.CharactersDTO;
import com.jorge.challence.dto.MovieDTO;
import com.jorge.challence.dto.MovieDetailDTO;
import com.jorge.challence.dto.MoviesDTO;
import com.jorge.challence.repository.MovieRepository;
import com.jorge.challence.service.CharacterService;
import com.jorge.challence.service.GenderService;
import com.jorge.challence.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v1_mysql")
public class MovieServiceI implements MovieService {

  @Autowired
  private MovieRepository mr;

  @Autowired
  @Qualifier("v1_mysql")
  private GenderService gs;

  @Autowired
  private CharacterService cs;

  @Override
  public List<MoviesDTO> findAll() {
    List<Movie> m = mr.findByDeletedAtIsNull();
    List<MoviesDTO> m_dto = new ArrayList<>();
    for (Movie i : m) {
      m_dto.add(new MoviesDTO(i.getId(), i.getTitle(), i.getImage(), i.getCreatedAt()));
    }
    return m_dto;
  }

  @Override
  public MovieDetailDTO findById(Long id) {
    Optional<Movie> m = mr.findByIdAndDeletedAtIsNull(id);
    if (m.isPresent()) {
      MovieDetailDTO md_dto = new MovieDetailDTO();
      md_dto.setId(m.get().getId());
      md_dto.setTitle(m.get().getTitle());
      md_dto.setImage(m.get().getImage());
      md_dto.setQualification(m.get().getQualification());
      md_dto.setCreated_at(m.get().getCreatedAt());
      for (Character c : m.get().getCharacters()) {
        md_dto.addCharacters(new CharactersDTO(c.getId(), c.getName(), c.getImage()));
      }
      return md_dto;
    }
    return null;
  }

  @Override
  public Movie findByIdMovie(Long id) {
    Optional<Movie> m = mr.findByIdAndDeletedAtIsNull(id);
    if (m.isPresent())
      return m.get();
    return null;
  }

  @Override
  public void createMovie(MovieDTO m_dto) {
    Gender g = gs.findbyId(m_dto.getGender_id());
    if (g != null) {
      Movie m = new Movie();
      m.setTitle(m_dto.getTitle());
      m.setImage(m_dto.getImage());
      m.setQualification(m_dto.getQualification());
      m.setCreatedAt(new Date());
      m.setUpdatedAt(new Date());
      m.setGender(g);
      mr.saveAndFlush(m);
      m_dto.setId(m.getId());
      m_dto.getCharacters().forEach(c -> {
        Character ch = new Character();
        try {
          ch.setName(c.getName());
          ch.setImage(c.getImage());
          ch.setAge(c.getAge());
          ch.setWeight(c.getWeight());
          ch.setHistory(c.getHistory());
          ch.addMovie(m);
          cs.createCharacter(ch);
          c.setId(ch.getId());
        } catch (Exception e) {
          System.out.println(e.hashCode() + e.getMessage() + e.getCause());
        }
      });
    }
  }

  @Override
  public void updateMovie(MovieDTO m_dto) {
    Optional<Movie> m = mr.findByIdAndDeletedAtIsNull(m_dto.getId());
    if (m.isPresent()) {
      Gender g = gs.findbyId(m_dto.getGender_id());
      if (g != null) {
        m.get().setTitle(m_dto.getTitle());
        m.get().setImage(m_dto.getImage());
        m.get().setQualification(m_dto.getQualification());
        m.get().setUpdatedAt(new Date());
        m.get().setGender(g);
        mr.saveAndFlush(m.get());
        m.get().getCharacters().forEach(c -> {
          CharacterDTO cd = new CharacterDTO();
          cd.setId(c.getId());
          cd.setName(c.getName());
          cd.setImage(c.getImage());
          cd.setHistory(c.getHistory());
          cd.setWeight(c.getWeight());
          cd.setHistory(c.getHistory());
          m_dto.addCharacters(cd);
        });
      }
    }
  }

  @Override
  public void deleteMovie(Long id) {
    Optional<Movie> m = mr.findByIdAndDeletedAtIsNull(id);
    if (m.isPresent()) {
      m.get().setDeletedAt(new Date());
      m.get().getCharacters().clear();
      mr.saveAndFlush(m.get());
    }
  }

  @Override
  public void deleteCharacter(Long idCharacter, Long idMovie) {
    Optional<Movie> m = mr.findByIdAndDeletedAtIsNull(idMovie);
    if (m.isPresent()) {
      m.get().removeCharacterById(idCharacter);
      mr.save(m.get());
    }
  }

  @Override
  public List<MoviesDTO> findByParams(String name, Long gender_id, String order) {
    HashMap<String, Object> params = new HashMap<>();
    if (name != null)
      if (name.length() > 0)
        params.put("title", name);

    if (gender_id != null) {
      Gender g = gs.findbyId(gender_id);
      if (g != null)
        params.put("gender", g);
    }

    if (order != null)
      if (order.toUpperCase().equals("DESC"))
        params.put("order", "DESC");
      else
        params.put("order", "ASC");
    else
      params.put("order", "ASC");

    List<MoviesDTO> m_dto = new ArrayList<>();
    for (Movie m : mr.findByParams(params))
      m_dto.add(new MoviesDTO(m.getId(), m.getTitle(), m.getImage(), m.getCreatedAt()));

    return m_dto;
  }

  @Override
  public void addCharacterToMovie(Long character_id, Long movie_id) {
    Optional<Movie> m = mr.findByIdAndDeletedAtIsNull(movie_id);
    if (m.isPresent()) {
      Character c = cs.findById(character_id);
      if (c != null) {
        c.addMovie(m.get());
        cs.updateCharacter(c);
      }
    }

  }
}
