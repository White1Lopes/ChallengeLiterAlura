package com.whitelopes.liter.repository;

import com.whitelopes.liter.models.Authors;
import com.whitelopes.liter.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {
    Book findByIdGutendex(Integer id);
    List<Book> findAllByLanguagesContainingIgnoreCase(String language);
    List<Book> findAllByAuthorsBirthYearLessThanAndAuthorsDeathYearGreaterThan(Integer year, Integer year2);
    Integer countAllByLanguagesContainingIgnoreCase(String language);
}
