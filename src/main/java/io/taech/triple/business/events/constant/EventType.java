package io.taech.triple.business.events.constant;

import io.taech.triple.common.util.Utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EventType {
    REVIEW(ActionType.ADD, ActionType.MOD, ActionType.DELETE)
    //== Some other events ==//
    ;

    private ActionType [] actionTypes;

    EventType (final ActionType... actionTypes) {
        this.actionTypes = actionTypes;
    }

    private static final Map<String, EventType> typeMap = Arrays.stream(values()).collect(Collectors.toMap(Enum::name, Function.identity()));

    public static EventType find(final String typeName) {

        return typeMap.get(typeName);
    }

    public Optional<ActionType> getAction(final String actionType) {

        return Arrays.stream(this.actionTypes)
                .filter(at -> at.name().equals(actionType))
                .findFirst();
    }
}
