package com.jorge.challence.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "figure") // character es palabra reservada de MySql
public class Character {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", length = 150, nullable = false)
  @Size(min = 3, max = 150)
  private String name;

  @Column(name = "image", length = 250, nullable = false)
  @Size(min = 5, max = 250)
  private String image;

  @Column(name = "age", nullable = false)
  @Min(0)
  @Max(200)
  private int age;

  @Column(name = "weight", nullable = false)
  @Min(0)
  private float weight;

  @Column(name = "history", nullable = false, length = 350)
  @Size(min = 5, max = 350)
  private String history;

  @Column(name = "created_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date updatedAt;

  @Column(name = "deleted_at", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date deletedAt;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "movie_figure", 
            joinColumns = { @JoinColumn(name = "figure_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "movie_id") })
  private Set<Movie> movies = new HashSet<>();

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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
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

  public Set<Movie> getMovies() {
    return movies;
  }

  public void setMovies(Set<Movie> movies) {
    this.movies = movies;
  }

  public void addMovie(Movie m) {
    movies.add(m);
  }

}
