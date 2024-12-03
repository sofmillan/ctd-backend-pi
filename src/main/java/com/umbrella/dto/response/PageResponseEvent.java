package com.umbrella.dto.response;

import java.util.List;

public class PageResponseEvent {
    private InfoObject info;
    private List<EventResponseDto> results;

    public PageResponseEvent(Long count, Integer pages, List<EventResponseDto> results) {
        this.info = new InfoObject(count, pages);
        this.results = results;
    }

    public InfoObject getInfo() {
        return info;
    }

    public void setInfo(InfoObject info) {
        this.info = info;
    }

    public List<EventResponseDto> getResults() {
        return results;
    }

    public void setResults(List<EventResponseDto> results) {
        this.results = results;
    }
}
