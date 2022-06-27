package io.taech.triple.common.aop;

import io.taech.triple.business.events.controller.EventController;
import io.taech.triple.business.events.service.EventService;
import io.taech.triple.business.events.service.EventServiceFactory;
import io.taech.triple.common.dto.StandardEventDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class DynamicServiceInjector {

    private final EventServiceFactory factory;
    private final ApplicationContext context;

    @Before("execution(* io.taech.triple.business.events.controller.EventController.handleEvent(..)) and args(eventDto)")
    public void prepareEventService(final JoinPoint joinPoint, final StandardEventDto eventDto) throws Throwable {

        final String type = eventDto.getType();
        final EventService eventService = factory.retrieve(type);
        final EventController eventController = (EventController) context.getBean(EventController.BEAN_NAME);

        eventController.dynamicInjection(eventService);

    }
}
