package com.walmartlabs.components.scheduler.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.walmart.platform.kernel.exception.error.Error;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Created by smalik3 on 6/22/16
 */
@JsonInclude(NON_EMPTY)
public class EventResponse extends EventRequest {

    private List<Error> errors;
    private String processedAt;
    private String eventId;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public static EventResponse fromRequest(EventRequest eventRequest) {
        final EventResponse eventResponse = new EventResponse();
        eventResponse.setTenant(eventRequest.getTenant());
        eventResponse.setUtc(eventRequest.getUtc());
        eventResponse.setId(eventRequest.getId());
        eventResponse.setPayload(eventRequest.getPayload());
        return eventResponse;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "errors=" + errors +
                ", processedAt=" + processedAt +
                ", eventId='" + eventId + '\'' +
                "} " + super.toString();
    }

    public static EventResponse toResponse(Event e) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(e.getXrefId());
        eventResponse.setEventId(e.id().getEventId());
        eventResponse.setProcessedAt(e.getProcessedAt() == null ? null : e.getProcessedAt().toString());
        eventResponse.setTenant(e.getTenant());
        eventResponse.setUtc(e.id().getEventTime().toInstant().toEpochMilli());
        eventResponse.setPayload(e.getPayload());
        return eventResponse;
    }
}