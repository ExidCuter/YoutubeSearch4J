package org.td_fl.youtube;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.td_fl.youtube.models.Channel;
import org.td_fl.youtube.models.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YoutubeSearch {
    private static final OkHttpClient client = new OkHttpClient();
    private static String ytUrl = "https://www.youtube.com";
    private static final String resultsQuery = YoutubeSearch.ytUrl + "/results?search_query=";
    private static int MAX_ATTEMPTS = 3;

    @NotNull
    public static List<Video> search(@NotNull String query) throws IOException {
        int attempt = 0;
        List<Video> videos = new ArrayList<>();

        Elements htmlVideos = parseHTML(makeRequest(query));

        while(htmlVideos.size() == 0 && attempt < MAX_ATTEMPTS) {
            htmlVideos = parseHTML(makeRequest(query));
            attempt++;
        }

        htmlVideos.forEach(htmlVideo -> {
            Video.VideoBuilder videoBuilder = Video.builder();
            Elements links = htmlVideo.select("a");

            if (links.size() > 1) {
                Element videoData = links.get(0);
                Element channelData = links.get(1);

                Channel channel = Channel.builder()
                        .link(YoutubeSearch.ytUrl + channelData.attr("href"))
                        .name(channelData.text())
                        .verified(htmlVideo.select(".yt-channel-title-icon-verified").size() == 1)
                        .build();

                videoBuilder.link(YoutubeSearch.ytUrl + videoData.attr("href"))
                        .title(videoData.attr("title"))
                        .channel(channel);

                videoBuilder.playlist(videoData.attr("href").contains("list="));
            }

            Elements videoInfo = htmlVideo.select(".yt-lockup-meta-info").select("li");

            if (videoInfo.size() > 1) {
                videoBuilder
                        .uploaded(videoInfo.get(0).text())
                        .views(videoInfo.get(1).text());
            }

            videoBuilder
                    .duration(htmlVideo.select(".accessible-description").text())
                    .description(htmlVideo.select(".yt-lockup-description").text());

            Video video = videoBuilder.build();

            if (video.getLink() != null) {
                videos.add(video);
            }

        });

        return videos;
    }

    private static String makeRequest(String query) throws IOException {
        Request request = new Request.Builder()
                .url(resultsQuery + query)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private static Elements parseHTML(String html) {
        Document doc = Jsoup.parse(html);

        return doc.select(".yt-lockup-content");
    }

    public static void setMaxFailedAttempts(int maxFailedAttempts) {
        MAX_ATTEMPTS = maxFailedAttempts;
    }
}
