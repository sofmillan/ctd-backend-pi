package com.umbrella.dto.response;

public class InfoObject {
    private Long count;
    private Integer pages;

    public InfoObject(Long count, Integer pages) {
        this.count = count;
        this.pages = pages;
    }

    public InfoObject() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
