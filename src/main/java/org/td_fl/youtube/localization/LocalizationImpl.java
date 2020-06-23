package org.td_fl.youtube.localization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

public class LocalizationImpl {
    public static Integer getViewCount(String s, Locale locale) {
        try {
            return NumberFormat.getInstance(locale).parse(s).intValue();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static Duration getDuration(String str) {
        String out = str.replaceAll("[^\\d.:,]", "");
        out = out.replace(".", ":");
        out = out.replace(",", ":");

        if (out.equals("")) {
            return Duration.ofSeconds(0);
        }

        if (out.charAt(0) == ':') {
            out = out.substring(1);
        }

        if (out.charAt(out.length() - 1) == ':') {
            out = out.substring(0, out.length() - 1);
        }

        if (out.length() <= 5) {
            out = "00:" + out;
        }

        int[] vals = Arrays.stream(out.split(":")).mapToInt(Integer::parseInt).toArray();
        int seconds = vals[0] * 3600 + vals[1] * 60 + vals[2];

        return Duration.ofSeconds(seconds);
    }
}
