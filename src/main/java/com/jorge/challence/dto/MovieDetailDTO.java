package com.jorge.challence.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MovieDetailDTO {

  private Long id;
  private String title;
  private String image;
  private int qualification;
  private Date created_at;
  private Set<CharactersDTO> characters;

  public MovieDetailDTO() {
    this.characters = new HashSet<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getQualification() {
    return qualification;
  }

  public void setQualification(int qualification) {
    this.qualification = qualification;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public void addCharacters(CharactersDTO c) {
    characters.add(c);
  }

  public Set<CharactersDTO> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<CharactersDTO> characters) {
    this.characters = characters;
  }

}
