package com.silbaugh.personal.yelp.extractor.yelpextractor.model;

public class SearchRequestDTO {
    private String location;
    private String longitude;
    private String lattitude;
    private String term;
    private String radius;

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLattitude() {
        return lattitude;
    }
    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getRadius() {
        return radius;
    }
    public void setRadius(String radius) {
        this.radius = radius;
    }
}
