package se.viati.assignment.svradio.playlist.service;

import se.viati.assignment.svradio.playlist.model.Playlist;

import java.util.Optional;

public interface IPlaylistService {

    Optional<Playlist> fetchPlaylistByChannel();
}
