package com.jorge.challence.repository;

import java.util.HashMap;
import java.util.List;

import com.jorge.challence.domain.Movie;

public interface MovieRepositoryCustom {

  public List<Movie> findByParams(HashMap<String, Object> params);
  
}
