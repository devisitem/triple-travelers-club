package io.taech.triple.common.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static boolean isNull(Object o) {
        return (o == null);
    }

    public static boolean isNotNull(Object o) {
        return (o != null);
    }

    public static boolean isEmpty(String s) {
        if (isNull(s))
            return true;

        return "".equals(s);
    }

}
