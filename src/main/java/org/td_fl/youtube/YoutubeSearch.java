package org.td_fl.youtube;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.td_fl.youtube.json.SearchResult;
import org.td_fl.youtube.models.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeSearch {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String DUCKDUCKGO_URL = "https://duckduckgo.com/";

    @NotNull
    public static List<Video> search(@NotNull String query) throws IOException, InterruptedException {
        List<Video> videos = new ArrayList<>();

        SearchResult result = queryDuckDuckGo(query, getVQD(query));

        result.results.forEach(res -> {
            videos.add(Video.builder()
                    .title(res.title)
                    .description(res.description)
                    .link(res.content)
                    .imgUri(res.images.medium)
                    .views(res.statistics.viewCount)
                    .channel(res.uploader)
                    .uploaded(res.published)
                    .duration(res.duration)
                    .build()
            );
        });

        return videos;
    }

    private static String getVQD(String query) throws IOException {
        String htmlRes;
        Pattern VQDPattern = Pattern.compile("vqd=([\\d-]+)\\&", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        Request VQDReq = new Request.Builder()
                .url(DUCKDUCKGO_URL)
                .post(new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("q", query)
                        .build())
                .build();

        try (Response response = client.newCall(VQDReq).execute()) {
            htmlRes = response.body().string();
        }

        Matcher m = VQDPattern.matcher(htmlRes);

        if (m.find()) {
            return m.group(1);
        }

        return "";
    }

    private static SearchResult queryDuckDuckGo(String query, String VQD) throws IOException {
        String jsonRes;
        ObjectMapper mapper = new ObjectMapper();

        Request VQDReq = new Request.Builder()
                .url(DUCKDUCKGO_URL + "v.js?l=us-en&o=json&q=" + query + "&vqd=" + VQD)
                .build();

        try (Response response = client.newCall(VQDReq).execute()) {
            jsonRes = response.body().string();
        }

        return mapper.readValue(jsonRes, SearchResult.class);
    }
}
