package org.td_fl.youtube.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    public String motion;
    public String large;
    public String medium;
    public String small;
}
