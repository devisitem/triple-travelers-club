package io.taech.triple.common.util;

import io.taech.triple.common.excpeted.ValidateException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidUtils {

    private final static String uuidRegex = "^([a-fA-F0-9]{8})([-]?)([a-fA-F0-9]{4})([-]?)(4[a-fA-F0-9]{3})([-]?)([a-fA-F0-9]{4})([-]?)([a-fA-F0-9]{12})$";

    public static boolean forUUID(final String uuid) {

        final Pattern compiled = Pattern.compile(uuidRegex);
        final Matcher matcher = compiled.matcher(uuid);

        return matcher.matches();
    }

    public static UUID getWithInspect(final String uuid) {
        if( ! forUUID(uuid))
            throw new ValidateException(String.format("Invalid UUID format. \"%s\"", uuid));

        return UUID.fromString(uuid);
    }
}
