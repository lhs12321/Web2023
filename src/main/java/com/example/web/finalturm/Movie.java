package com.example.web.finalturm;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Movie {
    int movieId;
    String title;
    String director;

    public String getTitleEncoded() {
        return Encode.forHtml(title);
    }

    public String getDirectorEncoded() {
        return Encode.forHtml(director);
    }

    @Override
    public String toString() {
        return String.format("\nMovie{movieId=%d, title=%s, director=%s}", movieId,
                title, director);
    }
}