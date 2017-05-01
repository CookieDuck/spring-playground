package com.example.utils;

import com.example.model.OmdbMovie;
import com.example.remote.OmdbSearchResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {
    public static List<OmdbMovie> mapFromResponse(OmdbSearchResponse omdbSearchResponse) {
        if (omdbSearchResponse == null) {
            return Collections.emptyList();
        }
        return omdbSearchResponse.getSearch().stream()
                .map(MapperUtils::mapFromEntry)
                .collect(Collectors.toList());
    }

    public static OmdbMovie mapFromEntry(OmdbSearchResponse.OmdbEntry entry) {
        return new OmdbMovie(
                entry.getTitle(),
                entry.getImdbId(),
                entry.getPoster(),
                Integer.valueOf(entry.getYear())
        );
    }
}
