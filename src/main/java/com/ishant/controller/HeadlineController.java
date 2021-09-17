package com.ishant.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.sse.Event;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

@Controller("/headlines")
public class HeadlineController {

    @ExecuteOn(TaskExecutors.IO)
    @Get(produces = MediaType.TEXT_EVENT_STREAM)
    public Publisher<Event<String>> index() {

        String[] news = {
                "India Won the T20 World Cup",
                "Indain Gdp rises in first quarter",
                "Taliban captures the Afganistan",
                "Coronovirus continue to increase in India",
                "Njokovic loses in the USA final"
        };

        return Flux.generate(() -> 0, (i, emitter) -> {
                emitter.next(Event.of(news[(int)(Math.random() * 4) + 0]));
                emitter.complete();
                return ++i;
        });
    }
}
