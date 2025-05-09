package com.take_home.tests;

import com.take_home.models.User;
import com.take_home.utils.RestAssuredUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

public class RestApiTest extends BaseTest {

    @Test
    @Retry(2) 
    @DisplayName("Case 5 - REST API testing")
    public void testRestApi() {
        logStep("Sending GET request to users endpoint");
        String url = "https://jsonplaceholder.typicode.com/users";
        
        Response response = RestAssuredUtils.sendGetRequest(url);
        
        logStep("Validating response status code");
        logValidation("Response status code is 200");
        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        
        logStep("Parsing response to User objects");
        List<User> users = response.jsonPath().getList("", User.class);
        
        logStep("Logging user names and emails");
        for (User user : users) {
            logger.info("{} | {}", user.getName(), user.getEmail());
        }
        
        logStep("Validating user count");
        logValidation("Response contains 10 users");
        assertEquals(10, users.size(), "Expected 10 users in response");
        
        logStep("Validating email format");
        logValidation("All users have valid email format");
        assertTrue(users.stream().allMatch(user -> user.getEmail().contains("@")), 
                "All emails should contain @ symbol");
        
        logStep("Validating specific user data");
        User firstUser = users.get(0);
        logValidation("First user has correct name");
        assertEquals("Leanne Graham", firstUser.getName(), "First user should be Leanne Graham");
    }
    
    @Test
    @DisplayName("Advanced REST API testing with RestAssured fluent API")
    public void advancedRestApiTest() {
        logStep("Testing RestAssured fluent API directly");
        
        logStep("GET request to fetch a single user");
        Response userResponse = given()
            .contentType("application/json")
            .when()
            .get("https://jsonplaceholder.typicode.com/users/1")
            .then()
            .statusCode(200)
            .extract().response();
            
        User user = userResponse.as(User.class);
        logger.info("User details: {} ({})", user.getName(), user.getEmail());
        
        logStep("POST request to create a user");
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");
        
        Response createResponse = RestAssuredUtils.sendPostRequest(
            "https://jsonplaceholder.typicode.com/users", 
            newUser
        );
        
        logValidation("Create user response has status code 201");
        assertEquals(201, createResponse.getStatusCode(), "Expected status code 201");
        
        // Extract the created user ID
        int userId = createResponse.jsonPath().getInt("id");
        logger.info("Created user with ID: {}", userId);
        
        logStep("Demonstrating response validation with fluent API");
        given()
            .contentType("application/json")
            .when()
            .get("https://jsonplaceholder.typicode.com/users")
            .then()
            .statusCode(200)
            .assertThat()
            .body("size()", org.hamcrest.Matchers.equalTo(10));
    }
}
