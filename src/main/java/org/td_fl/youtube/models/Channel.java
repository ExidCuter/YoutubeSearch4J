package org.td_fl.youtube.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Channel {
    private String name;
    private String link;
    private boolean verified;
}
