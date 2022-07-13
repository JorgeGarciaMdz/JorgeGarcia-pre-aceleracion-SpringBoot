package com.jorge.challence.repository;

import java.util.HashMap;
import java.util.List;

import com.jorge.challence.domain.Character;

public interface CharacterRepositoryCustom {
  public List<Character> findByParamsOutMovieId(HashMap<String, Object> params);
  public List<Character> findByParamsWithMovieId(HashMap<String, Object> params);
}

