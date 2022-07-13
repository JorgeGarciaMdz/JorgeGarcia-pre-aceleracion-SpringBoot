package com.jorge.challence.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gender")
public class Gender {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Valid
  @Column(name = "name", nullable = false, length = 150)
  @Size(min = 3, max = 150)
  private String name;

  @Valid
  @Column(name = "image", nullable = false, length = 250)
  @Size(min = 10, max = 250)
  private String image;

  @Column(name = "created_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date updatedAt;

  @Column(name = "deleted_at", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date deletedAt;

  @OneToMany(mappedBy = "gender")
  private Set<Movie> movies;

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

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Set<Movie> getMovies() {
    return movies;
  }

  public void setMovies(Set<Movie> movies) {
    this.movies = movies;
  }

  public void addMovies(Movie movie) {
    movies.add(movie);
  }

  public void removeMovie(Movie movie) {
    movies.remove(movie);
  }

  @Override
  public String toString() {
    return "id: " + this.id + ", name: " + this.name + ", image: " + this.image;
  }
}
