package ru.temoteam.simsinfinity.data.models;

import java.util.HashMap;
import java.util.Map;

public class Task {

    private String title;
    private String description;
    private long deadline;
    private double progress;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Task withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task withDescription(String description) {
        this.description = description;
        return this;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public Task withDeadline(long deadline) {
        this.deadline = deadline;
        return this;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public Task withProgress(double progress) {
        this.progress = progress;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Task withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}