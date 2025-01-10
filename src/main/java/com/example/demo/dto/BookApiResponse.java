package com.example.demo.dto;

import java.util.List;

public class BookApiResponse {
    private Integer gutendexId;
    private String title;
    private String authorName;
    private Integer authorBirthYear;
    private Integer authorDeathYear;
    private List<String> languages;

    public Integer getGutendexId() {
        return gutendexId;
    }

    public void setGutendexId(Integer gutendexId) {
        this.gutendexId = gutendexId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAuthorBirthYear() {
        return authorBirthYear;
    }

    public void setAuthorBirthYear(Integer authorBirthYear) {
        this.authorBirthYear = authorBirthYear;
    }

    public Integer getAuthorDeathYear() {
        return authorDeathYear;
    }

    public void setAuthorDeathYear(Integer authorDeathYear) {
        this.authorDeathYear = authorDeathYear;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
