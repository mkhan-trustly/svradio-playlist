package se.viati.assignment.svradio.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExceptionUtils {

    public static String getShortStackTrace(Throwable e) {
        return Stream.of(e.getStackTrace())
                .limit(4)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
    }
}
