package com.site.joshsurette.projects;

public interface Project {
    String getProjectId();
    String getTitle();
    long getStartedEpochDate();
    long getEndedEpochDate();
    String getDescription();
}
