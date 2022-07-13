package com.jorge.challence.service;

import java.util.List;

import com.jorge.challence.domain.Gender;
import com.jorge.challence.dto.GenderDTO;

public interface GenderService {
  public GenderDTO saveGender( GenderDTO g_dto);
  public List<GenderDTO> findall();
  public GenderDTO findById(Long id);
  public List<GenderDTO> findByName(String name);
  public GenderDTO updateGender( GenderDTO g_dto);
  public void deleteGender(Long id);
  public Gender findbyId(Long id);
}
