package org.td_fl.youtube.tests;

import org.junit.Before;
import org.junit.Test;
import org.td_fl.youtube.YoutubeSearch;
import org.td_fl.youtube.models.Video;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class YoutubeSearchTests {
    @Before
    public void init() {
        YoutubeSearch.setMaxFailedAttempts(10);
    }

    @Test
    public void test_search() throws IOException, InterruptedException {
        List<Video> videos = YoutubeSearch.search("pewdiepie bitch lasagna", Locale.GERMAN);

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=6Dh-RL__uN4", "bitch lasagna", "PewDiePie")
                );
    }

    @Test
    public void test_search2() throws IOException, InterruptedException {
        List<Video> videos = YoutubeSearch.search("I hate everything about you", Locale.GERMAN);

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=d8ekz_CSBVg", "Three Days Grace - I Hate Everything About You (Official Video)", "Three Days Grace")
                );
    }

    @Test
    public void test_search3() throws IOException, InterruptedException {
        List<Video> videos = YoutubeSearch.search("na balkanu", Locale.GERMAN);

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=lYKENqLKK-Y", "Neda Ukraden - Na Balkanu - (Official Video 2012) HD", "Neda Ukraden")
                );
    }

    @Test
    public void test_search4() throws IOException, InterruptedException {
        List<Video> videos = YoutubeSearch.search("kes picke", Locale.GERMAN);

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=SJfUYTfdoR4", "Klemen Klemen - Keš pičke (official video HD)", "Klemen Klemen")
                );
    }

    @Test
    public void test_search5() throws IOException, InterruptedException {
        List<Video> videos = YoutubeSearch.search("nyan cat 10 hour", Locale.GERMAN);

        assertThat(videos).isNotNull()
                .extracting("link", "title", "channel.name")
                .contains(
                        tuple("https://www.youtube.com/watch?v=SkgTxQm9DWM", "Nyan Cat - 10 HOURS [ BEST SOUND QUALITY ] 4K UHD ULTRA HD", "Bufu Sounds")
                );
    }
}
