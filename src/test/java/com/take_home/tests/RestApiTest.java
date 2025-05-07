package com.take_home.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.take_home.models.User;
import com.take_home.utils.ApiUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestApiTest extends BaseTest {

    @Test
    @DisplayName("Case 5 - REST API testing")
    public void testRestApi() throws IOException {
        logStep("Sending GET request to users endpoint");
        String url = "https://jsonplaceholder.typicode.com/users";
        String response = ApiUtils.sendGetRequest(url);

        logStep("Parsing response to JSON format");
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(response, new TypeReference<List<User>>() {
        });

        logStep("Logging user names and emails");
        for (User user : users) {
            logger.info("{} | {}", user.getName(), user.getEmail());
        }

        logStep("Validating email format");
        logValidation("First user's email contains @ symbol");
        assertTrue(users.get(0).getEmail().contains("@"), "Email should contain @ symbol.");
    }
}
