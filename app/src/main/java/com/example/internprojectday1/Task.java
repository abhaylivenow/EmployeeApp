package com.example.internprojectday1;

public class Task {
    private String taskId;
    private String heading;
    private String description;
    private String priority;
    private String startTime;
    private String endTime;
    private String currentStatus;
    private String rating;

    public Task(){

    }

    public Task(String taskId, String heading, String description, String priority, String startTime, String endTime, String currentStatus, String rating) {
        this.taskId = taskId;
        this.heading = heading;
        this.description = description;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentStatus = currentStatus;
        this.rating = rating;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
