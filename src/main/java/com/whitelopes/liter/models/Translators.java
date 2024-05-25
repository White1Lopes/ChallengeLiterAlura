package com.whitelopes.liter.models;

import com.whitelopes.liter.dto.AuthorsDto;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Translators {
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

    public Book getBooks() {
        return book;
    }

    public void setBooks(Book books) {
        this.book = books;
    }

    public static ArrayList<Translators> fromTranslatorDto(AuthorsDto authorsDto){
        var translators = new Translators();
        var arrayList = new ArrayList<Translators>();
        translators.setBirthYear(authorsDto.birthYear());
        translators.setName(authorsDto.name());
        translators.setDeathYear(authorsDto.deathYear());
        arrayList.add(translators);
        return arrayList;
    }

    @Override
    public String toString() {
        return "Translators{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
