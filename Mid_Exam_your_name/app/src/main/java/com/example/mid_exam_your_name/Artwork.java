package com.example.mid_exam_your_name;

public class Artwork {
    private final int imageResId;
    private final String title;
    private final String artist;
    private final String year;
    private final String url;

    public Artwork(int imageResId, String title, String artist, String year, String url) {
        this.imageResId = imageResId;
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.url = url;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getYear() {
        return year;
    }

    public String getUrl() {
        return url;
    }
}
