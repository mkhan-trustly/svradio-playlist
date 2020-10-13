package se.viati.assignment.svradio.playlist.service;

import se.viati.assignment.svradio.playlist.model.Playlist;

import java.util.Optional;

public interface PlaylistService {

    Optional<Playlist> fetchPlaylistByChannel();
}
