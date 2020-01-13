package ru.temoteam.simsinfinity.data.models;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Goal implements Serializable {

    public static Goal fromMap(Map<String,Object> map){
        return new Goal()
                .withCompleted((double)map.get("completed"))
                .withTotal((double)map.get("total"))
                .withDeadline((long)map.get("deadline"))
                .withDescription((String) map.get("description"))
                .withReason((String) map.get("reason"))
                .withReward((String) map.get("reward"))
                .withTitle((String) map.get("title"))
                .withValue((String) map.get("value"))
                .withStartDate((long)map.get("startDate"));
    }

    private String title;
    private String description;
    private long startDate = new Date().getTime();
    private double total;
    private double completed;
    private List<String> tasks = new ArrayList<String>();
    private long deadline;
    private String value;
    private String reward;
    private String reason;
    @Exclude
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Goal withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goal withDescription(String description) {
        this.description = description;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Goal withTotal(double total) {
        this.total = total;
        return this;
    }

    public double getCompleted() {
        return completed;
    }

    public void setCompleted(double completed) {
        this.completed = completed;
    }

    public Goal withCompleted(double completed) {
        this.completed = completed;
        return this;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public Goal withTasks(List<String> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Goal withStartDate(Long startDate) {
        this.startDate = startDate;
        return this;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public Goal withDeadline(long deadline) {
        this.deadline = deadline;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Goal withValue(String value) {
        this.value = value;
        return this;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Goal withReward(String reward) {
        this.reward = reward;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Goal withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Goal withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getStartDate() {
        return startDate;
    }
}