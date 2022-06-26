package io.taech.triple.business.events.controller;

import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.dto.response.StandardResponse;
import io.taech.triple.business.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    @PostMapping
    public ResponseEntity<StandardResponse> handleEvent(@RequestBody @Valid final EventDto eventDto) throws Exception {

        final StandardResponse response = service.consumeEvent(eventDto);

        return ResponseEntity.ok(response);
    }
}
