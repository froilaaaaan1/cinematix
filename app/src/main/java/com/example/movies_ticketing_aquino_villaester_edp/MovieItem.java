package com.example.movies_ticketing_aquino_villaester_edp;

public class MovieItem {
    private String titleAndYear;
    private int imageId;

    public MovieItem(String titleAndYear, int imageID) {
        this.imageId = imageID;
        this.titleAndYear = titleAndYear;
    }

    public String getTitleAndYear() {
        return this.titleAndYear;
    }

    public int getImageId() {
        return this.imageId;
    }
}