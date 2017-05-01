package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@JsonPropertyOrder({ "title", "imdbId", "poster", "year" })
public class OmdbMovie {
    private final String title;
    private final String imdbId;
    @JsonProperty(value = "poster")
    private final String posterURL;
    private final Integer year;

    @JsonCreator
    public OmdbMovie(@JsonProperty(value = "title") String title,
                     @JsonProperty(value = "imdbID") String imdbId,
                     @JsonProperty(value = "poster") String poster,
                     @JsonProperty(value = "year") Integer year) {
        this.title = title;
        this.imdbId = imdbId;
        this.posterURL = poster;
        this.year = year;
    }
}
