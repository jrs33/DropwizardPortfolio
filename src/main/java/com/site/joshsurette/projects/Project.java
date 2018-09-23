package com.site.joshsurette.projects;

public interface Project {
    String PROJECT_ID_FIELD = "projectId";
    String ENDED_DATE_FIELD = "endedDate";
    String DESCRIPTION_FIELD = "description";
    String STARTED_DATE_FIELD = "startedDate";
    String TITLE_FIELD = "title";

    String getProjectId();
    String getTitle();
    long getStartedEpochDate();
    long getEndedEpochDate();
    String getDescription();
}
