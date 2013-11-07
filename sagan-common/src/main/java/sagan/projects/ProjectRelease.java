package sagan.projects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class ProjectRelease implements Comparable<ProjectRelease> {

    private static final Pattern VERSION_DISPLAY_REGEX = Pattern.compile("([0-9.]+)\\.(RC\\d+|M\\d+)?");
    private static final Pattern PREREALSE_PATTERN = Pattern.compile("[0-9.]+(M|RC)\\d+");
    private static final Pattern SNAPSHOT_PATTERN = Pattern.compile("[0-9.].*(SNAPSHOT)");

    public enum ReleaseStatus {
        SNAPSHOT, PRERELEASE, GENERAL_AVAILABILITY;

        public static ReleaseStatus getFromVersion(String version) {
            Assert.notNull(version, "Version must not be null");
            if (PREREALSE_PATTERN.matcher(version).matches()) {
                return PRERELEASE;
            }
            if (SNAPSHOT_PATTERN.matcher(version).matches()) {
                return SNAPSHOT;
            }
            return GENERAL_AVAILABILITY;
        }
    }

    private final String version;
    private final ReleaseStatus releaseStatus;
    private final String refDocUrl;
    private final String apiDocUrl;
    private final String groupId;
    private final String artifactId;

    private final transient boolean current;
    private final transient ProjectRepository repository;

    public ProjectRelease(String version, ReleaseStatus releaseStatus, boolean current, String refDocUrl,
                          String apiDocUrl, String groupId, String artifactId) {
        this.version = version;
        this.releaseStatus = releaseStatus;
        this.refDocUrl = refDocUrl;
        this.apiDocUrl = apiDocUrl;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.current = current;
        repository = ProjectRepository.get(version);
    }

    public boolean isGeneralAvailability() {
        return releaseStatus == ReleaseStatus.GENERAL_AVAILABILITY;
    }

    public boolean isPreRelease() {
        return releaseStatus == ReleaseStatus.PRERELEASE;
    }

    public boolean isSnapshot() {
        return releaseStatus == ReleaseStatus.SNAPSHOT;
    }

    public String getVersionDisplayName() {
        return getVersionDisplayName(true);
    }

    public String getVersionDisplayName(boolean includePreReleaseDescription) {
        Matcher matcher = VERSION_DISPLAY_REGEX.matcher(version);
        matcher.find();
        String versionNumber = matcher.group(1);
        String preReleaseDescription = matcher.group(2);

        if (preReleaseDescription != null && includePreReleaseDescription) {
            return versionNumber + " " + preReleaseDescription;
        }
        return versionNumber;
    }

    public boolean hasRefDocUrl() {
        return !refDocUrl.isEmpty();
    }

    public boolean hasApiDocUrl() {
        return !apiDocUrl.isEmpty();
    }

    @Override
    public int compareTo(ProjectRelease other) {
        return version.compareTo(other.version);
    }

}
