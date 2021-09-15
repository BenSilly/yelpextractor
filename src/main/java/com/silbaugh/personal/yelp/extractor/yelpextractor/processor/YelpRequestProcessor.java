package com.silbaugh.personal.yelp.extractor.yelpextractor.processor;

import com.silbaugh.personal.yelp.extractor.yelpextractor.model.SearchRequestDTO;
import com.silbaugh.personal.yelp.extractor.yelpextractor.model.SearchResponseArtifact;
import com.silbaugh.personal.yelp.extractor.yelpextractor.model.SearchResponseDTO;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silbaugh.personal.yelp.extractor.yelpextractor.model.Business;
import com.silbaugh.personal.yelp.extractor.yelpextractor.model.ReviewResponseDTO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class will process and build the requests we send to yelp for the
 * following endpoints Search Ratings
 */
@Component
public class YelpRequestProcessor implements IYelpRequestProcessor {

    @Value("${yelp.api.search.url}")
    private String searchURL;

    @Value("${yelp.api.reviews.url}")
    private String reviewsURL;

    @Value("${yelp.api.client.id}")
    private String clientID;

    @Value("${yelp.api.key}")
    private String apiKey;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ReviewResponseDTO processYelpRequest(SearchRequestDTO request)
            throws JsonMappingException, JsonProcessingException {
        SearchResponseDTO response = searchYelp(request);
        ReviewResponseDTO reviewResponse = null;
        if (response != null && StringUtils.isNotBlank(response.getId())) {
            reviewResponse = getReviews(response.getId());
        }
        return reviewResponse;
    }

    private ReviewResponseDTO getReviews(String id) throws JsonMappingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(uriBuilderReviews(id).toUriString(), HttpMethod.GET, entity,
                String.class);
        String body = response.getBody();
        // ReviewResponseArtifact reviewArtifact = objectMapper.readValue(body, ReviewResponseArtifact.class);
        // List<Review> reviews = reviewArtifact.getReviews();
        // ReviewResponseDTO reviewResponse = new ReviewResponseDTO();
        // for(Review review : reviews){
        //     objectMapper.writeValueAsString(review.getText())
        // }
        ReviewResponseDTO reviewResponse = new ReviewResponseDTO();
        reviewResponse.setReviewsAsJSON(body);
        return reviewResponse;
    }

    HttpHeaders createHeaders() {
        return new HttpHeaders() {
            {
                String authHeader = "Bearer " + new String(apiKey);
                set("Authorization", authHeader);
            }
        };
    }

    private SearchResponseDTO searchYelp(SearchRequestDTO request)
            throws JsonMappingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(uriBuilderSearch(request).toUriString(), HttpMethod.GET, entity,
                String.class);
        String body = response.getBody();
        SearchResponseArtifact responseArtifact = objectMapper.readValue(body, SearchResponseArtifact.class);
        return buildResponse(getNearestBusiness(responseArtifact.getBusinesses()));
    }

    private Business getNearestBusiness(List<Business> businesses) {
        int indexOfNearestBusiness = -1;
        for (int i = 0; i < businesses.size() - 1; i++)
            for (int j = 0; j < businesses.size() - i - 1; j++) {
                if (businesses.get(j).getDistance() < businesses.get(j + 1).getDistance()) {
                    indexOfNearestBusiness = j;
                }
            }
        if (indexOfNearestBusiness >= 0) {
            return businesses.get(indexOfNearestBusiness);
        } else {
            // default to the first index for now
            return businesses.get(0);
        }
    }

    private SearchResponseDTO buildResponse(Business business) {
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        responseDTO.setAlias(business.getAlias());
        responseDTO.setId(business.getId());
        responseDTO.setDistance(business.getDistance());
        responseDTO.setReviewCount(business.getReviewCount());
        return responseDTO;
    }

    private UriComponentsBuilder uriBuilderSearch(SearchRequestDTO request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchURL);
        if (StringUtils.isNotBlank(request.getLocation())) {
            builder.queryParam("location", request.getLocation());
        } else if (StringUtils.isNotBlank(request.getLattitude()) && StringUtils.isNotBlank(request.getLongitude())) {
            builder.queryParam("longitude", request.getLongitude());
            builder.queryParam("latitude", request.getLattitude());
        } else {
            // TODO: Error out if one of the required (location or longitude and lattitude)
            // are not provided
        }
        if (StringUtils.isNotBlank(request.getLocation())) {
            builder.queryParam("radius", request.getRadius());
        }
        builder.queryParam("term", request.getTerm());
        return builder;
    }

    private UriComponentsBuilder uriBuilderReviews(String id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(reviewsURL.replace("{id}", id));
        return builder;
    }
}
