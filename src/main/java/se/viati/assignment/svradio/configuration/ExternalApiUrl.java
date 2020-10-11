package se.viati.assignment.svradio.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("external-api-url")
@Data
public class ExternalApiUrl {

    private String baseApiUrl;
    private String version;
    private String playlist;
    private String playlistByChannelEndpoint;
    private Parameters params;

    @Data
    static class Parameters {

        private String channelId;
        private String limit;
        private String startDateTime;
        private String endDateTime;
        private String format;
    }

    public String getP3PlaylistEndpoint() {
        return new StringBuilder(baseApiUrl)
                .append(version)
                .append(playlist)
                .append(playlistByChannelEndpoint)
                .append("?")
                .append(addParams(new String[]{params.getChannelId(), params.getLimit(), params.getStartDateTime(),
                        params.getEndDateTime(), params.getFormat()}))
                .toString();
    }

    private static String addParams(String[] params) {
        return Stream.of(params)
                .collect(Collectors.joining("&"));
    }
}
