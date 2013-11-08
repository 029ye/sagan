package sagan.projects.service;

import sagan.projects.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;

import static lombok.AccessLevel.NONE;

@Data
public class ProjectMetadataService {

    private final String ghPagesBaseUrl;
    private final List<Project> projects;
    private final @Getter(NONE) Map<String, List<Project>> projectCategoryMap;

    public ProjectMetadataService(Map<String, List<Project>> projectCategoryMap, String ghPagesBaseUrl) {
        this.projectCategoryMap = projectCategoryMap;
        this.ghPagesBaseUrl = ghPagesBaseUrl;
        projects = new ArrayList<>();
        for (Map.Entry<String, List<Project>> projectCategory : projectCategoryMap.entrySet()) {
            projects.addAll(projectCategory.getValue());
        }
    }

    public List<Project> getProjectsForCategory(String category) {
        return projectCategoryMap.get(category);
    }

    public Project getProject(String id) {
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        return null;
    }

}
