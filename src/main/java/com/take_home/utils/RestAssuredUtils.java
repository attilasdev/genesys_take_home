package com.take_home.utils;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class RestAssuredUtils {
    private static final Logger logger = LoggerFactory.getLogger(RestAssuredUtils.class);
    
    static {
        // Configure Rest Assured for logging
        RestAssured.config = RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));
    }
    

    public static Response sendGetRequest(String url) {
        logger.info("Sending GET request to: {}", url);
        
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(url);
    }
    

    public static Response sendGetRequest(String url, Map<String, String> queryParams) {
        logger.info("Sending GET request to: {} with params: {}", url, queryParams);
        
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
        
        // Add query parameters
        queryParams.forEach(request::queryParam);
        
        return request.when().get(url);
    }
    

    public static Response sendPostRequest(String url, Object jsonBody) {
        logger.info("Sending POST request to: {}", url);
        
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(url);
    }
    

    public static Response sendPutRequest(String url, Object jsonBody) {
        logger.info("Sending PUT request to: {}", url);
        
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put(url);
    }
    

    public static Response sendDeleteRequest(String url) {
        logger.info("Sending DELETE request to: {}", url);
        
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(url);
    }
} 