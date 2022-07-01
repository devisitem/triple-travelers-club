package io.taech.triple.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {


    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(final Object target) {
        if (Utils.isNull(target))
            return null;

        return gson.toJson(target);
    }
}
