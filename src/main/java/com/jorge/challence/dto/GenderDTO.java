package com.jorge.challence.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class GenderDTO {

  @NotEmpty
  @Length(min = 3, max = 150)
  private String name;
  
  @NotEmpty
  @Length(min = 3, max = 150)
  private String image;
  private Long id;

  public GenderDTO(String name, String image) {
    this.name = name;
    this.image = image;
  }

  public GenderDTO() {
  }

  public GenderDTO(Long id, String name, String image) {
    this.id = id;
    this.name = name;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Id: " + this.id + ", name: " + this.name + ", image: " + this.image;
  }
}
