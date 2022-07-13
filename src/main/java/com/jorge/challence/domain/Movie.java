package com.jorge.challence.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title", nullable = false, length = 150)
  @Size(min = 3, max = 150)
  private String title;

  @Column(name = "image", nullable = false, length = 250)
  @Size(min = 10, max = 250)
  private String image;

  @Column(name = "qualification", nullable = false)
  @Min(1)
  @Max(5)
  private int qualification;

  @Column(name = "created_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Column(name = "updated_at", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date updatedAt;

  @Column(name = "deleted_at", nullable = true)
  @Temporal(TemporalType.DATE)
  private Date deletedAt;

  @ManyToOne
  @JoinColumn(name = "gender_id", nullable = false)
  private Gender gender;

  @ManyToMany(mappedBy = "movies")
  private Set<Character> characters = new HashSet<>();

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

  public Set<Character> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<Character> characters) {
    this.characters = characters;
  }

  public void addCharacters(Character c) {
    this.characters.add(c);
  }

  public void removeCharacterById(Long idCharacter) {
    this.characters.removeIf((Character c) -> c.getId() == idCharacter);  
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Id: " + this.id + ", title: " + this.title + ", image: " + this.image + ", qualification: " +
        this.qualification + "gender_id: " + gender.getId();
  }
}
