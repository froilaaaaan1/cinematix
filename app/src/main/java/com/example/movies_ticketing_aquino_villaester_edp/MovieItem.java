package com.example.movies_ticketing_aquino_villaester_edp;

public class MovieItem {
    private final String titleAndYear;
    private final int imageId;
    private final int blurImageId;
    private final String title;
    private final String year;
    private final String director;
    private final String runtime;

    public MovieItem(
            String titleAndYear,
            int imageID,
            int blurImageId,
            String title,
            String year,
            String director,
            String runtime) {
        this.imageId = imageID;
        this.titleAndYear = titleAndYear;
        this.blurImageId = blurImageId;
        this.title = title;
        this.year = year;
        this.director = director;
        this.runtime = runtime;
    }

    public String getTitleAndYear() {
        return this.titleAndYear;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getBlurImageId() { return this.blurImageId; }

    public String getTitle() { return this.title; }

    public String getYear() { return this.year; }

    public String getDirector() { return this.director; }

    public String getRuntime() { return this.runtime; }
}