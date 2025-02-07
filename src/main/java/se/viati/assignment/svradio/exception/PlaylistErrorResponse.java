package se.viati.assignment.svradio.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public record PlaylistErrorResponse(LocalDateTime timestamp,
                                    int status,
                                    String error,
                                    String message,
                                    String path
) {
}

