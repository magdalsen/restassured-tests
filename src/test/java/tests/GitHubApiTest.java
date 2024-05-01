package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static utils.TestConstants.*;


public class GitHubApiTest {
    private GitHubUserPage gitHubUserPage;
    
    @BeforeClass
    public void setUp() {
        gitHubUserPage = new GitHubUserPage(BASE_URL);
    }
    
    @Test(dataProvider = "usernames")
    public void testGetUserDetails(String username, String expectedName) {
        Response response = gitHubUserPage.getUserDetails(username);
        
        // Asercje
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK");
        
        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, expectedName, "Incorrect user name");
        
        String company = response.jsonPath().getString("company");
        Assert.assertEquals(company, EXPECTED_COMPANY_NAME, "Incorrect company name");
        
        String location = response.jsonPath().getString("location");
        Assert.assertEquals(location, EXPECTED_LOCATION, "Incorrect location");
        
        int followers = response.jsonPath().getInt("followers");
        Assert.assertTrue(followers > 0, "Followers count should be greater than 0");
    }
    
    @DataProvider(name = "usernames")
    public Object[][] getUsernames() {
        return new Object[][] {
            {"octocat", "The Octocat"},
        };
    }
}
