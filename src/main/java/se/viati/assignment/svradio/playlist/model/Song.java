package se.viati.assignment.svradio.playlist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song implements Serializable {

    private String title;
    private String description;

    private String artist;
    private String composer;

    @JsonProperty("albumname")
    private String albumName;

    @JsonProperty("recordlabel")
    private String recordLabel;

    @JsonProperty("lyricist")
    private String lyricist;

    public boolean hasRecordLabel() {
        return Objects.nonNull(this.recordLabel);
    }
}
