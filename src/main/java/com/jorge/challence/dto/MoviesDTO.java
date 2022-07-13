package com.jorge.challence.dto;

import java.util.Date;

public class MoviesDTO {
  private Long id;
  private String title;
  private String image;
  private Date created_at;

  public MoviesDTO(String title, String image, int qualification) {
    this.title = title;
    this.image = image;
  }

  public MoviesDTO(Long id, String title, String image, Date created_at) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.created_at = created_at;
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

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreated_at() {
    return created_at;
  }
  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }
}
