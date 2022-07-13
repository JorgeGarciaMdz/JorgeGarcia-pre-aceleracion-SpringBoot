package com.jorge.challence.repository;

import java.util.List;
import java.util.Optional;

import com.jorge.challence.domain.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom{

  public List<Movie> findByDeletedAtIsNull();
  public Optional<Movie> findByIdAndDeletedAtIsNull(Long id);
  
}
