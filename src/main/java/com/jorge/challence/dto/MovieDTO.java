package com.jorge.challence.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class MovieDTO {
  private Long id;

  @Valid
  @Length(min = 3, max = 150)
  @NotNull
  private String title;

  @Valid
  @Length(min = 3, max = 250)
  @NotNull
  private String image;

  @Valid
  @NotNull
  @Min(value = 1)
  @Max(value = 5)
  private Integer qualification;

  @Valid
  @Min(value = 0)
  @NotNull
  private Long gender_id;

  private List<CharacterDTO> characters = new ArrayList<>();

  public MovieDTO() {
  }

  public MovieDTO(String title, String image, int qualification, Long gender_id) {
    this.title = title;
    this.image = image;
    this.qualification = qualification;
    this.gender_id = gender_id;
  }

  public MovieDTO(Long id, String title, String image, int qualification, Long gender_id) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.qualification = qualification;
    this.gender_id = gender_id;
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

  public Integer getQualification() {
    return qualification;
  }

  public void setQualification(Integer qualification) {
    this.qualification = qualification;
  }

  public Long getGender_id() {
    return gender_id;
  }

  public void setGender_id(Long gender_id) {
    this.gender_id = gender_id;
  }

  public List<CharacterDTO> getCharacters() {
    return characters;
  }

  public void setCharacters(List<CharacterDTO> characters) {
    this.characters = characters;
  }

  public void addCharacters(CharacterDTO characterDTO){
    this.characters.add(characterDTO);
  }
}
