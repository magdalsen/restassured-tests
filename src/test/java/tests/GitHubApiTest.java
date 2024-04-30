package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GitHubApiTest {
    private GitHubUserPage gitHubUserPage;
    private String baseUrl = "https://api.github.com";
    private String username = "octocat";
    
    @BeforeClass
    public void setUp() {
        gitHubUserPage = new GitHubUserPage(baseUrl);
    }
    
    @Test
    public void testGetUserDetails() {
        Response response = gitHubUserPage.getUserDetails(username);
        
        // Asercje
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK");
        
        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, "The Octocat", "Incorrect user name");
        
        String company = response.jsonPath().getString("company");
        Assert.assertEquals(company, "@github", "Incorrect company name");
        
        String location = response.jsonPath().getString("location");
        Assert.assertEquals(location, "San Francisco", "Incorrect location");
        
        int followers = response.jsonPath().getInt("followers");
        Assert.assertTrue(followers > 0, "Followers count should be greater than 0");
    }
}
