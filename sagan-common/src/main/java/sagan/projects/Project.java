package sagan.projects;

import lombok.Data;

import java.util.List;

@Data
public class Project {

    private final String id;
    private final String name;
    private final String repoUrl;
    private final String siteUrl;
    private final List<ProjectRelease> projectReleases;
    private final boolean isAggregator;

    public boolean hasSite() {
        return !siteUrl.isEmpty();
    }

}
