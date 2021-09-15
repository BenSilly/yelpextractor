package com.silbaugh.personal.yelp.extractor.yelpextractor.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"longitude",
"latitude"
})
@Generated("jsonschema2pojo")
public class Center {

@JsonProperty("longitude")
private double longitude;
@JsonProperty("latitude")
private double latitude;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("longitude")
public double getLongitude() {
return longitude;
}

@JsonProperty("longitude")
public void setLongitude(double longitude) {
this.longitude = longitude;
}

@JsonProperty("latitude")
public double getLatitude() {
return latitude;
}

@JsonProperty("latitude")
public void setLatitude(double latitude) {
this.latitude = latitude;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}