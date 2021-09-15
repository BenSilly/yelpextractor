package com.silbaugh.personal.yelp.extractor.yelpextractor.ws.controller;

import com.silbaugh.personal.yelp.extractor.yelpextractor.model.SearchRequestDTO;
import com.silbaugh.personal.yelp.extractor.yelpextractor.processor.YelpRequestProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YelpSearchController {
    
    @Autowired
    private YelpRequestProcessor yelpRequestProcessor;

    @GetMapping("/getYelpReviews")
    public String yelpRequest(
        @RequestParam(value="restaurantName",required=true)String restaurantName,
        @RequestParam(value="radius",required=false)String radius,
        @RequestParam(value="longitude",required=false)String longitude,
        @RequestParam(value="lattitude",required=false)String lattitude, 
        @RequestParam(value="location",required=false)String location) {
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        //TODO: sanitize input
        searchRequestDTO.setTerm(restaurantName);
        searchRequestDTO.setRadius(radius);
        searchRequestDTO.setLongitude(longitude);
        searchRequestDTO.setLattitude(lattitude);
        searchRequestDTO.setLocation(location);
        String jsonResponse = "";
        try {
            jsonResponse = yelpRequestProcessor.processYelpRequest(searchRequestDTO).getReviewsAsJSON();
        } catch(Exception e){
            //TODO: filter down to a try and multi catch for expected exceptions
            System.out.println(e.getMessage());
        }
        return jsonResponse;
    }
}
