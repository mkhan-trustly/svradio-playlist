package se.viati.assignment.svradio.playlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Playlist(@JsonProperty("song") Song[] songs, String copyright, @JsonIgnore LocalDateTime lastFetched) implements Serializable {
}
