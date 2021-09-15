package com.silbaugh.personal.yelp.extractor.yelpextractor.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.silbaugh.personal.yelp.extractor.yelpextractor.model.ReviewResponseDTO;
import com.silbaugh.personal.yelp.extractor.yelpextractor.model.SearchRequestDTO;

public interface IYelpRequestProcessor {
    public abstract ReviewResponseDTO processYelpRequest(SearchRequestDTO request) throws JsonMappingException, JsonProcessingException;
}
