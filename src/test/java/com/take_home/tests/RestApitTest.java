package com.take_home.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.take_home.models.User;
import com.take_home.utils.ApiUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestApitTest extends BaseTest {
    
    @Test
    public void testRestApi() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users";
        String response = ApiUtils.sendGetRequest(url);

        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(response, new TypeReference<List<User>>() {});

        for (User user : users) {
            logger.info("{} | {}", user.getName(), user.getEmail());
        }

        assertTrue(users.get(0).getEmail().contains("@"), "Email should contain @ symbol.");
    }
}
