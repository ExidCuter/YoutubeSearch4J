package org.td_fl.youtube.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@Builder
@ToString
public class Video {
    private String link;
    private String title;
    private Integer views;
    private String imgUri;
    private Date uploaded;
    private String duration;
    private String description;
    private String channel;
}
