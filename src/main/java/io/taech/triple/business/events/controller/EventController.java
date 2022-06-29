package io.taech.triple.business.events.controller;

import io.taech.triple.business.events.constant.ActionType;
import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.service.EventService;
import io.taech.triple.common.dto.response.StandardResponse;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ServiceStatus;
import io.taech.triple.common.excpeted.ValidateException;
import io.taech.triple.common.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static io.taech.triple.business.events.controller.EventController.BEAN_NAME;

@Slf4j
@RestController(BEAN_NAME)
@RequestMapping("/events")
public class EventController {

    public static final String BEAN_NAME = "triple-event-consumer";
    private EventService service;

    public void dynamicInjection(final EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> handleEvent(@RequestBody @Valid final EventDto eventDto) throws Throwable {
        final String action = eventDto.getAction();
        final ActionType actionType = ActionType.get(action);

        if(Utils.isNull(actionType)) {

            log.info("There is no action for \"{}\"", action);
            throw new EventProcessingException(ServiceStatus.INVALID_EVENT);
        }

        log.info("Proceed event service for \"{}\" review.", action);
        StandardResponse response = null;
        switch (actionType) {
            case ADD:
                response = service.proceedAddEvent(eventDto);
                break;
            case MOD:
                response = service.proceedModifyEvent(eventDto);
                break;
            case DELETE:
                response = service.proceedDeleteEvent(eventDto);
                break;
            default:
                throw new ValidateException(ServiceStatus.NOT_SUPPORTED_ACTION);

        }

        return ResponseEntity.ok(response);
    }

}
