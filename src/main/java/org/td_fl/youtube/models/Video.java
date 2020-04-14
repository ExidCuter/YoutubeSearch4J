package org.td_fl.youtube.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Video {
    private String link;
    private String title;
    private String views;
    private String uploaded;
    private String duration;
    private String description;
    private boolean playlist;

    private Channel channel;
}
