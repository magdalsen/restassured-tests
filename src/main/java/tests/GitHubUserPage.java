package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GitHubUserPage {
    private String baseUrl;
    
    public GitHubUserPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public Response getUserDetails(String username) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/users/" + username)
                .get();
    }
}
