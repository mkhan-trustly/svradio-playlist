package se.viati.assignment.svradio.playlist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.viati.assignment.svradio.playlist.model.Playlist;
import se.viati.assignment.svradio.playlist.model.Song;
import se.viati.assignment.svradio.playlist.service.PlaylistServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/channel/p3")
public class PlaylistRestController {

    private PlaylistServiceImpl playlistService;

    public PlaylistRestController(PlaylistServiceImpl playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlist/recordlabels")
    public Map<String, List<Song>> getGroupByRecordLabel() {
        Optional<Playlist> playlist = playlistService.fetchPlaylistByChannel();
        if (playlist.isEmpty()) {
            return Map.of();
        }

        return Arrays.stream(playlist.get().songs())
                .filter(Song::hasRecordLabel)
                .collect(Collectors.groupingBy(Song::recordLabel, TreeMap::new, Collectors.toList()));
    }

    @GetMapping("/playlist/recordlabels/{recordLabel}")
    public List<Song> getGroupByRecordLabel(@PathVariable("recordLabel") String recordLabel) {
        List<Song> songs = this.getGroupByRecordLabel().get(recordLabel);
        return Objects.nonNull(songs) ? songs : Collections.emptyList();
    }

    @GetMapping("/playlist/recordlabels/artist")
    public Map<String, Set<String>> getArtistsGroupedByRecordLabel() {
        Optional<Playlist> playlist = playlistService.fetchPlaylistByChannel();
        if (playlist.isEmpty()) {
            return Map.of();
        }

        return Arrays.stream(playlist.get().songs())
                .filter(Song::hasRecordLabel)
                .collect(Collectors.groupingBy(Song::recordLabel, TreeMap::new,
                        Collectors.mapping(Song::artist, Collectors.toSet())));
    }

}
