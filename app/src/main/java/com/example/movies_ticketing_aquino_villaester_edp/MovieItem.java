package com.example.movies_ticketing_aquino_villaester_edp;

public class MovieItem {
    private String titleAndYear;
    private int imageId;
    private int blurImageId;

    public MovieItem(String titleAndYear, int imageID, int blurImageId) {
        this.imageId = imageID;
        this.titleAndYear = titleAndYear;
        this.blurImageId = blurImageId;
    }

    public String getTitleAndYear() {
        return this.titleAndYear;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getBlurImageId() { return this.blurImageId; }
}