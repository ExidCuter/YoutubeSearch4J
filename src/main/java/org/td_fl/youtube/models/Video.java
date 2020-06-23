package org.td_fl.youtube.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;

@Getter
@Builder
@ToString
public class Video {
    private String link;
    private String title;
    private Integer views;
    private String imgUri;
    private String uploaded;
    private Duration duration;
    private String description;
    private boolean playlist;

    private Channel channel;
}
