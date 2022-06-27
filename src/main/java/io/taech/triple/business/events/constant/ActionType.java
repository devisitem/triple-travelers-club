package io.taech.triple.business.events.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ActionType {

    ADD,
    MOD,
    DELETE
    ;

    private static final Map<String, ActionType> actionTicket = Arrays.stream(values())
            .collect(Collectors.toMap(Enum::name, Function.identity()));

    public static ActionType get(final String name) {
        return actionTicket.get(name);
    }
}
