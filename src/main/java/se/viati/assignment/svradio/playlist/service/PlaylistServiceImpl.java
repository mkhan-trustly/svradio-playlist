package se.viati.assignment.svradio.playlist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.viati.assignment.svradio.configuration.ExternalApiUrl;
import se.viati.assignment.svradio.playlist.model.Playlist;

import java.util.Optional;

@Service
@Slf4j
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExternalApiUrl externalApiUrl;

    public Optional<Playlist> fetchPlaylistByChannel() {
        Playlist playlist = restTemplate.getForObject(externalApiUrl.getP3PlaylistEndpoint(), Playlist.class);
        return Optional.ofNullable(playlist);
    }
}
