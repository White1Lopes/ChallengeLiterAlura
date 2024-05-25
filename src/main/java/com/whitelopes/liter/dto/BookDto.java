package com.whitelopes.liter.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Map;

public record BookDto(Integer id, String title, List<AuthorsDto> authors,
                      List<AuthorsDto> translators, List<String> subjects,
                      List<String> bookshelves, List<String> languages,
                      boolean copyright, String media_type, Map<String, String> formats,
                      @JsonAlias("download_count") Integer downloadCount) {
}
