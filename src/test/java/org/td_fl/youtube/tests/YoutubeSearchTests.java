package org.td_fl.youtube.tests;

import org.junit.Test;
import org.td_fl.youtube.YoutubeSearch;
import org.td_fl.youtube.models.Video;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class YoutubeSearchTests {
    @Test
    public void test_search() throws IOException {
        List<Video> videos = YoutubeSearch.search("pewdiepie bitch lasagna");

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=6Dh-RL__uN4", "bitch lasagna", "PewDiePie")
                );
    }
}
