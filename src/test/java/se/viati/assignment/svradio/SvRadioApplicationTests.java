package se.viati.assignment.svradio;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.viati.assignment.svradio.playlist.PlaylistController;

@SpringBootTest
class SvRadioApplicationTests {

	@Autowired
	private PlaylistController playlistController;

	@Test
	void testIfContextLoads() {
		assertThat(playlistController).isNotNull();
	}

}
