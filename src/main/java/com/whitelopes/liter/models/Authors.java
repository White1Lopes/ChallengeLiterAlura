package com.whitelopes.liter.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.whitelopes.liter.dto.AuthorsDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToOne
    private Book book;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYea(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public static ArrayList<Authors> fromAuthorDto(List<AuthorsDto> authorsDto){
        var arrayList = new ArrayList<Authors>();
        authorsDto.forEach(a -> {
        var authors = new Authors();
            authors.setBirthYear(a.birthYear());
            authors.setName(a.name());
            authors.setDeathYear(a.deathYear());
            arrayList.add(authors);
        });
        return arrayList;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", id=" + id +
                '}';
    }
}
