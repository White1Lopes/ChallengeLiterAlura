package com.whitelopes.liter.service;

import com.whitelopes.liter.dto.ShowAuthorsDto;
import com.whitelopes.liter.models.Authors;
import com.whitelopes.liter.models.Book;
import com.whitelopes.liter.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository _booksRepository;

    public List<Book> findAll() {
        return _booksRepository.findAll();
    }

    public Book findByGutendexId(Integer id) {
        return _booksRepository.findByIdGutendex(id);
    }

    public Book add(Book book) {

        if (findByGutendexId(book.getIdGutendex()) != null) return null;

        return _booksRepository.save(book);
    }

    public List<Book> findAllByLanguage(String language) {
        return _booksRepository.findAllByLanguagesContainingIgnoreCase(language);
    }

    public List<ShowAuthorsDto> FindAllAuthors() {
        var books = _booksRepository.findAll();
        var authorsList = new ArrayList<ShowAuthorsDto>();
        books.forEach(b -> {
            b.getAuthors().forEach(a -> {
                var teste = new ArrayList<Book>();
                teste.add(b);
                var author = new ShowAuthorsDto(a.getName(), a.getBirthYear(), a.getDeathYear(), teste);
                authorsList.add(author);
            });
        });

        return authorsList;
    }

    public List<ShowAuthorsDto> FindALlAuthorsByLivingyear(Integer year, Integer year2)
    {
        var books =  _booksRepository.findAllByAuthorsBirthYearLessThanAndAuthorsDeathYearGreaterThan(year, year2);
        var authorsList = new ArrayList<ShowAuthorsDto>();
        books.forEach(b -> {
            b.getAuthors().forEach(a -> {
                var teste = new ArrayList<Book>();
                teste.add(b);
                var author = new ShowAuthorsDto(a.getName(), a.getBirthYear(), a.getDeathYear(), teste);
                authorsList.add(author);
            });
        });

        return authorsList;
    }

    public Integer CountBooksByLanguage(String language){
        return _booksRepository.countAllByLanguagesContainingIgnoreCase(language);
    }
}
