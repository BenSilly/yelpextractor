package com.silbaugh.personal.yelp.extractor.yelpextractor.model;

public class SearchResponseDTO {
    private String id;
    private String alias;
    private int reviewCount;
    private double distance;
        public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public int getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double d) {
        this.distance = d;
    }
}
