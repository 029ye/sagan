package sagan.projects;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ProjectReleaseTests {

    private ProjectRelease release1;
    private ProjectRelease release2;
    private ProjectRelease release3;

    @Before
    public void setUp() {
        release1 = new ProjectRelease("1.0.0.RELEASE", ProjectRelease.ReleaseStatus.GENERAL_AVAILABILITY, true,
                "http://example.com/x/refdocs", "http://example.com/x/apidocs", "com.example", "x");

        release2 = new ProjectRelease("1.0.0.RELEASE", ProjectRelease.ReleaseStatus.GENERAL_AVAILABILITY, false,
                "http://example.com/x/refdocs", "http://example.com/x/apidocs", "com.example", "x");

        release3 = new ProjectRelease("1.0.1.BUILD-SNAPSHOT", ProjectRelease.ReleaseStatus.SNAPSHOT, false,
                "http://example.com/x/refdocs", "http://example.com/x/apidocs", "com.example", "x");
    }

    @Test
    public void testEquals() throws Exception {
        assertThat(release1, equalTo(release2));
        assertThat(release1, not(equalTo(release3)));
    }

}
