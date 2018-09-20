package com.site.joshsurette.projects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImmutableProject implements Project {

    private final String projectId;
    private final String title;
    private final long startedEpochDate;
    private final long endedEpochDate;
    private final String description;

    @JsonCreator
    public ImmutableProject(
            @JsonProperty("projectId") String projectId,
            @JsonProperty("title") String title,
            @JsonProperty("startedEpochDate") long startedEpochDate,
            @JsonProperty("endedEpochDate") long endedEpochDate,
            @JsonProperty("description") String description
    ) {
        this.projectId = projectId;
        this.title = title;
        this.startedEpochDate = startedEpochDate;
        this.endedEpochDate = endedEpochDate;
        this.description = description;
    }

    @JsonProperty("projectId")
    public String getProjectId() {
        return projectId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("startedEpochDate")
    public long getStartedEpochDate() {
        return startedEpochDate;
    }

    @JsonProperty("endedEpochDate")
    public long getEndedEpochDate() {
        return endedEpochDate;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }
}
