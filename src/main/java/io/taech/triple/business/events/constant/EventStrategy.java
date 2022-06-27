package io.taech.triple.business.events.constant;

import io.taech.triple.business.events.service.ReviewEventService;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EventStrategy {
    REVIEW(ReviewEventService.BEAN_NAME)
    //== Some other events ==//
    ;

    private String beanName;

    EventStrategy(final String beanName) {
        this.beanName = beanName;
    }

    private static final Map<String, EventStrategy> serviceTicket = Arrays.stream(values())
            .collect(Collectors.toMap(Enum::name, Function.identity()));

    public static EventStrategy find(final String type) {

        return serviceTicket.get(type);
    }

    public String getBeanName() {
        return this.beanName;
    }
}
