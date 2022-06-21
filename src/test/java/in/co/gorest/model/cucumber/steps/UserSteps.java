package in.co.gorest.model.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.model.goreststeps.UsersSteps;
import in.co.gorest.model.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class UserSteps {
    static String name = "name" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = "email@" + TestUtils.getRandomValue();
    static String status = "ACTIVE";
    static int usersId;
    static ValidatableResponse response;

    @Steps
    UsersSteps userSteps;


    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {response=userSteps.getAllUsers();}

    @Then("^User get back a valid status code (\\d+)$")
    public void userGetBackAValidStatusCode(int code) {

        response.log().all().statusCode(code);
    }

    @When("^User sends a Post request to list endpoint$")
    public void userSendsAPostRequestToListEndpoint() {
        response = userSteps.createUsers(name,gender,email,status);
        usersId = response.log().all().extract().path("id");
    }

    @And("^Verify that user created sucessfully$")
    public void verifyThatUserCreatedSucessfully() {
        HashMap<String, Object> UsersMap = userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(UsersMap, hasValue(name));
        System.out.println(usersId);
    }

    @When("^User sends a patch request to list endpoint$")
    public void userSendsAPatchRequestToListEndpoint() {
        name="name"+TestUtils.getRandomValue();
        response =userSteps.updateusers(usersId,name,gender,email,status);
        response.log().all().statusCode(200);
        HashMap<String, Object> usersMap =userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(usersMap,hasValue(name));
        System.out.println();
    }

    @When("^User sends a delete request to list endpoint$")
    public void userSendsADeleteRequestToListEndpoint() {
        response=userSteps.deleteUsers(usersId);
    }
}
