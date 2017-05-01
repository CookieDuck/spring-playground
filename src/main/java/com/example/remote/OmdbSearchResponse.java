package com.example.remote;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class OmdbSearchResponse {
    private List<OmdbEntry> search;

    @JsonCreator
    public OmdbSearchResponse(@JsonProperty(value = "Search") List<OmdbEntry> search) {
        this.search = search;
    }

    @Value
    public static class OmdbEntry {
        private final String title;
        private final String imdbId;
        private final String poster;
        private final String year;

        @JsonCreator
        public OmdbEntry(@JsonProperty(value = "Title") String title,
                         @JsonProperty(value = "imdbID") String imdbId,
                         @JsonProperty(value = "Poster") String poster,
                         @JsonProperty(value = "Year") String year) {
            this.title = title;
            this.imdbId = imdbId;
            this.poster = poster;
            this.year = year;
        }
    }
}
