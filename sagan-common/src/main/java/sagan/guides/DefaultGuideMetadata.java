package sagan.guides;

import lombok.Data;

import static java.lang.String.format;

@Data
public class DefaultGuideMetadata implements GuideMetadata {

    private final static String REPO_ZIP_URL = "https://github.com/%s/%s/archive/master.zip";
    private final static String REPO_HTTPS_URL = "https://github.com/%s/%s.git";
    private final static String GITHUB_HTTPS_URL = "https://github.com/%s/%s";
    private final static String REPO_SSH_URL = "git@github.com:%s/%s.git";
    private final static String REPO_SUBVERSION_URL = "https://github.com/%s/%s";
    private final static String CI_STATUS_IMAGE_URL = "https://drone.io/github.com/%s/%s/status.png";
    private final static String CI_LATEST_URL = "https://drone.io/github.com/%s/%s/latest";

    private final String ghOrgName;
    private final String guideId;
    private final String repoName;
    private final String description;

    private final String title;
    private final String subtitle;

    private final String gitRepoHttpsUrl;
    private final String githubHttpsUrl;
    private final String zipUrl;
    private final String gitRepoSshUrl;
    private final String gitRepoSubversionUrl;
    private final String ciStatusImageUrl;
    private final String ciLatestUrl;

    public DefaultGuideMetadata(String ghOrgName, String guideId, String repoName, String description) {
        this.ghOrgName = ghOrgName;
        this.guideId = guideId;
        this.repoName = repoName;
        this.description = description;

        String[] split = description.split("::", 2);
        title = split[0].trim();
        subtitle = (split.length > 1) ? split[1].trim() : "";

        gitRepoHttpsUrl = format(REPO_HTTPS_URL, ghOrgName, repoName);
        githubHttpsUrl = format(GITHUB_HTTPS_URL, ghOrgName, repoName);
        zipUrl = format(REPO_ZIP_URL, ghOrgName, repoName);
        gitRepoSshUrl = format(REPO_SSH_URL, ghOrgName, repoName);
        gitRepoSubversionUrl = format(REPO_SUBVERSION_URL, ghOrgName, repoName);
        ciStatusImageUrl = format(CI_STATUS_IMAGE_URL, ghOrgName, repoName);
        ciLatestUrl = format(CI_LATEST_URL, ghOrgName, repoName);
    }


}
