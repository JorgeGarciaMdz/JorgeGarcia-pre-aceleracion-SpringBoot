package com.jorge.challence.serviceImplement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import com.jorge.challence.domain.Gender;
import com.jorge.challence.dto.GenderDTO;
import com.jorge.challence.repository.GenderRepository;
import com.jorge.challence.service.GenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v1_mysql")
public class GenderServiceI implements GenderService {

  private final GenderRepository gr;

  @Autowired
  public GenderServiceI(GenderRepository gr) {
    this.gr = gr;
  }

  @Override
  @Transactional
  public GenderDTO saveGender(GenderDTO g_dto) {
    Gender g = new Gender();
    g.setName(g_dto.getName());
    g.setImage(g_dto.getImage());
    g.setCreatedAt(new Date());
    g.setUpdatedAt(new Date());
    try {
      gr.save(g);
      g_dto.setId(g.getId());
      return g_dto;
    } catch (RollbackException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public List<GenderDTO> findall() {
    List<GenderDTO> gs_dto = new ArrayList<>();

    for (Gender g : gr.findByDeletedAtIsNull())
      gs_dto.add(new GenderDTO(g.getId(), g.getName(), g.getImage()));

    return gs_dto;
  }

  public GenderDTO findById(Long id) {
    Optional<Gender> o = gr.findByIdAndDeletedAtIsNull(id);
    if (!o.isPresent())
      return null;
    else {
      return new GenderDTO(o.get().getId(), o.get().getName(), o.get().getImage());
    }
  }

  @Override
  public GenderDTO updateGender(GenderDTO g_dto) {
    if (g_dto.getId() == null)
      return null;
    else {
      Optional<Gender> o_g = gr.findByIdAndDeletedAtIsNull(g_dto.getId());
      if (!o_g.isPresent())
        return null;
      else {
        Gender g = o_g.get();
        g.setName(g_dto.getName());
        g.setImage(g_dto.getImage());
        g.setUpdatedAt(new Date());
        gr.saveAndFlush(g);
        return g_dto;
      }
    }
  }

  @Override
  public List<GenderDTO> findByName(String name) {
    List<GenderDTO> gs_dto = new ArrayList<>();

    for (Gender g : gr.findByNameContainingAndDeletedAtIsNull(name))
      gs_dto.add(new GenderDTO(g.getId(), g.getName(), g.getImage()));

    return gs_dto;
  }

  @Override
  public void deleteGender(Long id) {
    Optional<Gender> g = gr.findByIdAndDeletedAtIsNull(id);
    if(g.isPresent()){
      g.get().setDeletedAt(new Date());
      gr.saveAndFlush(g.get());
    }
    
  }

  @Override
  public Gender findbyId(Long id) {
    Optional<Gender> g = gr.findByIdAndDeletedAtIsNull(id);
    if( g.isPresent())
      return g.get();
    return null;
  }

}
