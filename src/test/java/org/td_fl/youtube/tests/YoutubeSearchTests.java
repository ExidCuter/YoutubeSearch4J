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

    @Test
    public void test_search2() throws IOException {
        List<Video> videos = YoutubeSearch.search("I hate everything about you");

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=d8ekz_CSBVg", "Three Days Grace - I Hate Everything About You (Official Video)", "Three Days Grace")
                );
    }

    @Test
    public void test_search3() throws IOException {
        List<Video> videos = YoutubeSearch.search("na balkanu");

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=lYKENqLKK-Y", "Neda Ukraden - Na Balkanu - (Official Video 2012) HD", "Neda Ukraden")
                );
    }

    @Test
    public void test_search4() throws IOException {
        List<Video> videos = YoutubeSearch.search("kes picke");

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=SJfUYTfdoR4", "Klemen Klemen - Keš pičke (official video HD)", "Klemen Klemen")
                );
    }
}
