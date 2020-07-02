package org.td_fl.youtube.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    public String publisher;
    public Images images;
    public String embed_html;
    public String description;
    public String title;
    public String duration;
    public String embed_url;
    public Statistics statistics;
    public String content;
    public Date published;
    public String provider;
    public String uploader;
}
