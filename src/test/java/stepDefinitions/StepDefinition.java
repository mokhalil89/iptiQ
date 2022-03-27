package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.request.UserRequest;
import resources.APIResources;
import resources.Constants;
import resources.RestApiClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static resources.Utils.getJsonPath;

public class StepDefinition {
    private String endpointUrl;
    private Response response;
    UserRequest userRequest = new UserRequest();

    @When("calling Get API and the response received")
    public void callingGetAPIAndTheResponseReceived() {
        response = new RestApiClient().get(endpointUrl);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedResponseCode) {
        assertEquals(expectedResponseCode, response.getStatusCode());
    }

    @Given("id number {string} in the path parameter")
    public void idNumberInThePathParameter(String id) {
        endpointUrl = Constants.BASE_URL + APIResources.userPath.getResource() + id;

    }

    @Given("set User Name {string} and Job title {string} in a json body")
    public void setUserNameAndJobTitleInAJsonBody(String name, String jobTitle) {
        endpointUrl = Constants.BASE_URL + APIResources.userPath.getResource();
        userRequest.setName(name);
        userRequest.setJob(jobTitle);
    }

    @When("calling POST API and the response received")
    public void callingPOSTAPIAndTheResponseReceived() {

        response = new RestApiClient().post(endpointUrl, userRequest);
    }

    @When("calling PUT API and the response received")
    public void callingPUTAPIAndTheResponseReceived() {
        response = new RestApiClient().put(endpointUrl, userRequest);
    }


    @When("calling DELETE API and the response received")
    public void callingDELETEAPIAndTheResponseReceived() {
        response = new RestApiClient().delete(endpointUrl);
    }

    @Given("invalid id number {string} in the path parameter")
    public void invalidIdNumberInThePathParameter(String id) {
        endpointUrl = Constants.BASE_URL + APIResources.userPath.getResource() + id;
    }

    @Then("Validate name{string} in the response body")
    public void validateNameInTheResponseBody(String expName) {
        String name = getJsonPath(response, "name");
        Assert.assertEquals(expName, name);
    }

    @Given("update User Name {string} and Job title {string} in a json body for specific ID{string}")
    public void updateUserNameAndJobTitleInAJsonBodyForSpecificId(String name, String id, String jobTitle) {
        endpointUrl = Constants.BASE_URL + APIResources.userPath.getResource() + id;
        userRequest.setName(name);
        userRequest.setJob(jobTitle);
    }

    @Given("set id number {string} in the path parameter")
    public void setIdNumberInThePathParameter(String id) {
        endpointUrl = Constants.BASE_URL + APIResources.userPath.getResource() + id;
    }

    @And("validate id {string}")
    public void validateId(String ExpID) {
        String id = getJsonPath(response, "data.id");
        Assert.assertEquals(ExpID, id);
    }

    @And("validate first name{string}")
    public void validateFirstName(String firstName) {
        String name = getJsonPath(response, "data.first_name");
        Assert.assertEquals(firstName, name);
    }

    @And("the updatedAt is valid")
    public void theUpdatedAtIsValid() {
        String updateAt = getJsonPath(response,"updatedAt");
        Assert.assertNotNull(updateAt);
        Assert.assertNotNull(LocalDateTime.parse(updateAt, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
    }
}