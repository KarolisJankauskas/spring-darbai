package com.example.query.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String genre;
  private int year;

  public Movie(int id, String title, String genre, int year) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.year = year;
  }

  public Movie() {

  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getGenre() {
    return genre;
  }

  public int getYear() {
    return year;
  }

}
