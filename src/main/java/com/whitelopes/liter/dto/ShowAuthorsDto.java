package com.whitelopes.liter.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.whitelopes.liter.models.Book;

import java.util.List;

public record ShowAuthorsDto(String name, Integer birthYear, Integer deathYear, List<Book> books) {
}
