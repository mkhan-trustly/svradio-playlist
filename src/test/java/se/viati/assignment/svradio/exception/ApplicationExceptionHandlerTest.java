package se.viati.assignment.svradio.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import se.viati.assignment.svradio.configuration.ExternalApiUrl;
import se.viati.assignment.svradio.exception.ApplicationExceptionHandler;
import se.viati.assignment.svradio.playlist.PlaylistController;
import se.viati.assignment.svradio.playlist.model.Playlist;
import se.viati.assignment.svradio.playlist.service.PlaylistService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Tests any REST service
 */

@ExtendWith(SpringExtension.class)
public class ApplicationExceptionHandlerTest {

    @Mock
    private ExternalApiUrl externalApiUrl;

    private MockMvc mvc;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PlaylistService playlistService;

    private PlaylistController playlistController;

    @BeforeEach
    public void setUp() {
        playlistController = new PlaylistController(playlistService);
        mvc = MockMvcBuilders.standaloneSetup(playlistController)
                .setControllerAdvice(new ApplicationExceptionHandler())
                .build();
    }

    @Test
    public void testGetGroupByRecordLabel_ShouldHandleClientErrorException() throws Exception {
        Mockito
                .when(restTemplate.getForObject(externalApiUrl.getP3PlaylistEndpoint(), Playlist.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        MockHttpServletResponse response = mvc.perform(get("/api/v1/channel/p3/playlist/recordlabels")).andReturn().getResponse();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testGetGroupByRecordLabel_ShouldHandleRuntimeException() throws Exception {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenThrow(new NullPointerException("x is null"));

        MockHttpServletResponse response = mvc.perform(get("/api/v1/channel/p3/playlist/recordlabels")).andReturn().getResponse();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
