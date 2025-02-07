package se.viati.assignment.svradio.util;

import se.viati.assignment.svradio.playlist.model.Playlist;

import java.util.Optional;

public class PlaylistUtil {

    public static Optional<Playlist> createNewPlaylist() {
        return Optional.empty();
    }

    public static Optional<Playlist> getPlaylistWithNullAsRecordLabel() {
        return Optional.ofNullable(FileUtil.parseJSONToGivenResponseType("getplaylistbychannelid_p3_null_as_record_label.json", Playlist.class));
    }

    public static Optional<Playlist> getEmptyPlaylist() {
        return Optional.ofNullable(FileUtil.parseJSONToGivenResponseType("getplaylistbychannelid_p3_empty_playlist.json", Playlist.class));
    }

    public static Optional<Playlist> getPlaylistWithActualRecords() {
        return Optional.ofNullable(FileUtil.parseJSONToGivenResponseType("getplaylistbychannelid_p3_full_playlist.json", Playlist.class));
    }

    public static Optional<Playlist> getPlaylistWithDuplicateRecords() {
        return Optional.ofNullable(FileUtil.parseJSONToGivenResponseType("getplaylistbychannelid_p3_duplicate_artists.json", Playlist.class));
    }
}
