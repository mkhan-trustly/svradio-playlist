package se.viati.assignment.svradio.playlist.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import se.viati.assignment.svradio.configuration.ExternalApiUrl;
import se.viati.assignment.svradio.playlist.model.Playlist;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlaylistServiceTest {

    @Mock
    private ExternalApiUrl externalApiUrl;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PlaylistService playlistService;

    @Test
    public void testFetchPlaylistByChannel_ShouldWorkWithEmptyPlaylist() {
        Mockito
                .when(restTemplate.getForObject(externalApiUrl.getP3PlaylistEndpoint(), Playlist.class))
                .thenReturn(new Playlist());

        Optional<Playlist> playlist = playlistService.fetchPlaylistByChannel();
        assertThat(playlist.isPresent()).isTrue();
        assertThat(playlist.get().getSongs()).isEmpty();
    }

    @Test
    public void testFetchPlaylistByChannel_ShouldHandleNullAsResponse() {
        Mockito
                .when(restTemplate.getForEntity(externalApiUrl.getP3PlaylistEndpoint(), Playlist.class))
                .thenReturn(null);

        Optional<Playlist> playlist = playlistService.fetchPlaylistByChannel();
        assertThat(playlist.isPresent()).isFalse();
    }


}
