package com.example.web.finalturm;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Song {
    private int songId;
    private String title;
    private String name;

    public String getTitleEncoded() {
        return Encode.forHtml(title);
    }

    public String getNameEncoded() {
        return Encode.forHtml(name);
    }

    @Override
    public String toString() {
        return String.format("\nSong{songId=%d, title=%s, name=%s}", songId,
                title, name);
    }
}
