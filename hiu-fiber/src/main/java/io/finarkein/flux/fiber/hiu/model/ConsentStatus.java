package io.finarkein.flux.fiber.hiu.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consent status enum values.
 */
public enum ConsentStatus {

    REQUESTED,
    GRANTED,
    DENIED,
    EXPIRED,
    REVOKED;

    public static Set<String> negatives() {
        return Stream.of(DENIED, EXPIRED, REVOKED)
                .map(Enum::toString).collect(Collectors.toSet());
    }
}
