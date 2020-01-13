package ru.temoteam.simsinfinity.data.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Task implements Serializable {

    private String title;
    private String description;
    private long deadline;
    private double progress;
    private boolean completed;
    @Exclude
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public static Task fromMap(Map<String, Object> map) {
        Task task = new Task();
        if (map.containsKey("title"))
        task.title = (String) map.get("title");
        if (map.containsKey("deadline"))
        task.deadline = (long) map.get("deadline");
        if (map.containsKey("description"))
        task.description = (String) map.get("description");
        if (map.containsKey("progress"))
        task.progress = (double) map.get("progress");
        if (map.containsKey("completed"))
        task.completed = (boolean) map.get("completed");
        return task;
    }

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

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Task withCompleted(boolean completed) {
        this.completed = completed;
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