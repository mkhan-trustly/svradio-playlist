package se.viati.assignment.svradio.playlist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Song(String title,
                   String description,

                   String artist,
                   String composer,

                   @JsonProperty("albumname")
                   String albumName,

                   @JsonProperty("recordlabel")
                   String recordLabel,

                   @JsonProperty("lyricist")
                   String lyricist
) implements Serializable {

    public boolean hasRecordLabel() {
        return Objects.nonNull(this.recordLabel);
    }
}
