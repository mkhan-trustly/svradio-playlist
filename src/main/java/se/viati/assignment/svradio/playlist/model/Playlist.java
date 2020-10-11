package se.viati.assignment.svradio.playlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Playlist implements Serializable {

    @JsonProperty("song")
    private Song[] songs = new Song[] {};

    private String copyright;

    @JsonIgnore
    private LocalDateTime lastFetched = LocalDateTime.now();
}
