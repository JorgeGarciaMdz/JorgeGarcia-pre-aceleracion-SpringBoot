package com.jorge.challence.repository;

import java.util.List;
import java.util.Optional;

import com.jorge.challence.domain.Character;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long>, CharacterRepositoryCustom{

  Optional<Character> findByIdAndDeletedAtIsNull(Long id);
  List<Character> findByDeletedAtIsNull();
}
