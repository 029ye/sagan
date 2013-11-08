package sagan.projects;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ProjectRepository {

    private final String id;
    private final String name;
    private final String url;
    private final Boolean snapshotsEnabled;

    public static ProjectRepository get(String versionName) {
        if (versionName.contains("RELEASE")) {
            return null;
        }

        if (versionName.contains("SNAPSHOT")) {
            return new ProjectRepository("spring-snapshots", "Spring Snapshots", "http://repo.spring.io/snapshot", true);
        }

        return new ProjectRepository("spring-milestones", "Spring Milestones", "http://repo.spring.io/milestone", false);
    }

}
