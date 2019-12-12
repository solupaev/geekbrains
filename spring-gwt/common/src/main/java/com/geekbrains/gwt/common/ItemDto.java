package com.geekbrains.gwt.common;

public class ItemDto {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemDto() {
    }

    public ItemDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
