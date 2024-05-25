package com.whitelopes.liter.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorsDto(String name, @JsonAlias("birth_year") Integer birthYear, @JsonAlias("death_year") Integer deathYear) {
}
