package com.jorge.challence.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CharacterDTO {
  private Long id;

  @Valid
  @NotBlank
  @Length(min = 3, max = 150)
  private String name;

  @Valid
  @NotBlank
  @Length(min = 3, max = 250)
  private String image;

  @NotNull
  @Min(value = 0)
  @Max(value = 300)
  private Integer age;

  @NotNull
  @Min(value = 0)
  private Float weight;

  @NotBlank
  @Length(min = 5, max = 250)
  private String history;

  private Set<String> movies = new HashSet<>();
  private Long movie_id;

  public CharacterDTO() {
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Float getWeight() {
    return weight;
  }

  public void setWeight(Float weight) {
    this.weight = weight;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  public void addMovie(String movie){
    movies.add(movie);
  }

  public Set<String> getMovies() {
    return movies;
  }

  public Long getMovie_id() {
    return movie_id;
  }

  public void setMovie_id(Long movie_id) {
    this.movie_id = movie_id;
  }
}
