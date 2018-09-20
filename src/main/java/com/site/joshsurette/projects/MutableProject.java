package com.site.joshsurette.projects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MutableProject implements Project {

    private String projectId;
    private String title;
    private long startedEpochDate;
    private long endedEpochDate;
    private String description;

    public MutableProject(
            String projectId,
            String title,
            long startedEpochDate,
            long endedEpochDate,
            String description
    ) {
        this.projectId = projectId;
        this.title = title;
        this.startedEpochDate = startedEpochDate;
        this.endedEpochDate = endedEpochDate;
        this.description = description;
    }

    @JsonProperty
    public String getProjectId() {
        return projectId;
    }

    @JsonProperty
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public long getStartedEpochDate() {
        return startedEpochDate;
    }

    @JsonProperty
    public void setStartedEpochDate(long startedEpochDate) {
        this.startedEpochDate = startedEpochDate;
    }

    @JsonProperty
    public long getEndedEpochDate() {
        return endedEpochDate;
    }

    @JsonProperty
    public void setEndedEpochDate(long endedEpochDate) {
        this.endedEpochDate = endedEpochDate;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }
}
