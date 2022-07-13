package com.jorge.challence.repository;

import java.util.List;
import java.util.Optional;
import com.jorge.challence.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

  public Optional<Gender> findByIdAndDeletedAtIsNull(Long id);

  public List<Gender> findByDeletedAtIsNull();

  public List<Gender> findByNameContainingAndDeletedAtIsNull(String name);
}
