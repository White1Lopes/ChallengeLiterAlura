package com.whitelopes.liter.models;

import com.whitelopes.liter.dto.BookDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer idGutendex;
    private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors = new ArrayList<>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Translators> translators = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> subjects;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> bookshelves;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;
    private boolean copyright;
    private String media_type;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> formats;
    Integer downloadCount;

    public Book(BookDto bookDto) {
        setIdGutendex(bookDto.id());
        setTitle(bookDto.title());
        setAuthors(Authors.fromAuthorDto(bookDto.authors()));
        setTranslators(!bookDto.translators().isEmpty()
                ? Translators.fromTranslatorDto(bookDto.translators().get(0))
                : new ArrayList<>());
        setSubjects(bookDto.subjects());
        setBookshelves(bookDto.bookshelves());
        setLanguages(bookDto.languages());
        setCopyright(bookDto.copyright());
        setMedia_type(bookDto.media_type());
        setDownloadCount(bookDto.downloadCount());
        setFormats(bookDto.formats());
    }

    public Book() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getIdGutendex() {
        return idGutendex;
    }

    public void setIdGutendex(Integer idGutendex) {
        this.idGutendex = idGutendex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        authors.forEach(e -> e.setBook(this));
        this.authors = authors;
    }

    public List<Translators> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Translators> translators) {
        translators.forEach(t -> t.setBooks(this));
        this.translators = translators;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", idGutendex=" + idGutendex +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", translators=" + translators +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", languages=" + languages +
                ", copyright=" + copyright +
                ", media_type='" + media_type + '\'' +
                ", formats=" + formats +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
