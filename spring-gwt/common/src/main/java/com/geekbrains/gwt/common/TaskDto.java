package com.geekbrains.gwt.common;

public class TaskDto {
    private Long id;
    private String name;
    private String owner;
    private String executer;
    private String summary;

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getExecuter() {
        return executer;
    }

    public String getSummary() {
        return summary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskDto() {
    }

    public TaskDto(Long id, String name, String owner, String executer, String summary) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.executer = executer;
        this.summary = summary;
    }
}
