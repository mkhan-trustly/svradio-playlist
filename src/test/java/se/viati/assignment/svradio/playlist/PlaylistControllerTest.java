package se.viati.assignment.svradio.playlist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.viati.assignment.svradio.playlist.model.Song;
import se.viati.assignment.svradio.playlist.service.PlaylistService;
import se.viati.assignment.svradio.util.PlaylistUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests various Playlist responses, like null, empty record labels also duplicate records.
 */

@ExtendWith(SpringExtension.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @InjectMocks
    private PlaylistController playlistController;

    @Test
    public void testGetGroupByRecordLabel_ShouldHandleEmptySongsInPlaylist() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.createNewPlaylist());
        Map<String, List<Song>> groupByRecordLabel = playlistController.getGroupByRecordLabel();
        assertThat(groupByRecordLabel).isNotNull();

        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getEmptyPlaylist());
        groupByRecordLabel = playlistController.getGroupByRecordLabel();
        assertThat(groupByRecordLabel.isEmpty()).isTrue();
    }

    @Test
    public void testGetGroupByRecordLabel_ShouldHandleRecordLabelWithNullEntries() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getPlaylistWithNullAsRecordLabel());
        Map<String, List<Song>> groupByRecordLabel = playlistController.getGroupByRecordLabel();
        assertThat(groupByRecordLabel).isNotNull();
        assertThat(groupByRecordLabel.size()).isEqualTo(2);
    }

    @Test
    public void testGetGroupByRecordLabel_WithWorkingPlaylist() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getPlaylistWithActualRecords());
        Map<String, List<Song>> groupByRecordLabel = playlistController.getGroupByRecordLabel();
        assertThat(groupByRecordLabel).isNotNull();
        assertThat(groupByRecordLabel.size()).isEqualTo(3);

        String[] labels = groupByRecordLabel.keySet().toArray(new String[0]);
        String[] expectedLabels = new String[]{"Columbia", "Joytime Collective", "Universal Music AB"};
        assertThat(Arrays.equals(labels, expectedLabels)).isTrue();
    }

    @Test
    public void testGetGroupByRecordLabel_WithWorkingPlaylist_ShouldReturnEmptySongs() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getPlaylistWithActualRecords());
        List<Song> songs = playlistController.getGroupByRecordLabel("UNKNOWN");
        assertThat(songs).isNotNull();
        assertThat(songs.isEmpty()).isTrue();
    }

    @Test
    public void testGetGroupByRecordLabel_WithWorkingPlaylist_ShouldReturnSongs() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getPlaylistWithActualRecords());
        List<Song> songs = playlistController.getGroupByRecordLabel("Columbia");
        assertThat(songs).isNotNull();
        assertThat(songs.size()).isEqualTo(2);
    }

    @Test
    public void testGetArtistsGroupedByRecordLabel_WithWorkingPlaylist() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getPlaylistWithActualRecords());
        Map<String, Set<String>> groupByRecordLabel = playlistController.getArtistsGroupedByRecordLabel();
        assertThat(groupByRecordLabel).isNotNull();
        assertThat(groupByRecordLabel.size()).isEqualTo(3);
        assertArtistsByRecordLabel(groupByRecordLabel);
    }

    @Test
    public void testGetArtistsGroupedByRecordLabel_WithDuplicates() {
        Mockito
                .when(playlistService.fetchPlaylistByChannel())
                .thenReturn(PlaylistUtil.getPlaylistWithDuplicateRecords());
        Map<String, Set<String>> groupByRecordLabel = playlistController.getArtistsGroupedByRecordLabel();
        assertThat(groupByRecordLabel).isNotNull();
        assertThat(groupByRecordLabel.size()).isEqualTo(2);
        assertArtistsByRecordLabel(groupByRecordLabel);
    }

    private void assertArtistsByRecordLabel(Map<String, Set<String>> groupByRecordLabel) {
        Set<String> artists = groupByRecordLabel.get("Columbia");
        assertThat(artists.size()).isEqualTo(2);
        assertThat(artists).contains("One Direction");
        assertThat(artists).contains("Daft Punk");
    }

}
