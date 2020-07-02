package org.td_fl.youtube.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    public List<Result> results;
    public String query;
    public String response_type;
    public String queryEncoded;
}

