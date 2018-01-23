package joshsurette.resources;

import joshsurette.core.Project;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
public class ApplicationResource {
    private final List<Project> projects;

    public ApplicationResource(List<Project> projects) {
        this.projects = projects;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Josh Surette's Personal Site";
    }

    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectList() {
        return projects;
    }

}
