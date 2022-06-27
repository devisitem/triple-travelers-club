package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.EventStrategy;
import io.taech.triple.common.excpeted.ValidateException;
import io.taech.triple.common.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventServiceFactory {

    private final ApplicationContext context;

    public EventService retrieve(final String type) throws ValidateException {
        final EventStrategy eventStrategy = EventStrategy.find(type);

        if(Utils.isNull(eventStrategy))
            throw new ValidateException(String.format("\"%s\" is not declared.", type));

        final EventService toBeInjected = (EventService) context.getBean(eventStrategy.getBeanName());

        return toBeInjected;
    }

}
