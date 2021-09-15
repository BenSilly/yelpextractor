package com.silbaugh.personal.yelp.extractor.yelpextractor.model;

import java.util.HashMap;
import java.util.List;
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
"reviews",
"total",
"possible_languages"
})
@Generated("jsonschema2pojo")
public class ReviewResponseArtifact {

@JsonProperty("reviews")
private List<Review> reviews = null;
@JsonProperty("total")
private Integer total;
@JsonProperty("possible_languages")
private List<String> possibleLanguages = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("reviews")
public List<Review> getReviews() {
return reviews;
}

@JsonProperty("reviews")
public void setReviews(List<Review> reviews) {
this.reviews = reviews;
}

@JsonProperty("total")
public Integer getTotal() {
return total;
}

@JsonProperty("total")
public void setTotal(Integer total) {
this.total = total;
}

@JsonProperty("possible_languages")
public List<String> getPossibleLanguages() {
return possibleLanguages;
}

@JsonProperty("possible_languages")
public void setPossibleLanguages(List<String> possibleLanguages) {
this.possibleLanguages = possibleLanguages;
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