package com.whitelopes.liter.dto;

import java.util.List;

public record GutendexBooksResponseDto(Integer count, String next, String previous, List<BookDto> results) {
}
