package com.restful.cucumber.steps;

import com.restful.studentinfo.BookingSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.Collections;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


public class MyStepdefs {

    static ValidatableResponse response;

    static String firstName;

    static String lastName;
    static int totalPrice;
    static String depositPaid;
    static String bookingDates;
    static String additionalNeeds;


    static int bookingID;

    @Steps
    BookingSteps bookingSteps;
    @When("^User sends a GET Booking request to list endpoint$")
    public void userSendsAGETBookingRequestToListEndpoint() {
        response = bookingSteps.getAllBookingInfo();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^I create a new booking by providing the information firstName \"([^\"]*)\" lastName \"([^\"]*)\" totalPrice \"([^\"]*)\" depositPaid \"([^\"]*)\" bookingDates \"([^\"]*)\" additionalNeeds \"([^\"]*)\"$")
    public void iCreateANewBookingByProvidingTheInformationFirstNameLastNameTotalPriceDepositPaidBookingDatesAdditionalNeeds
            (String firstName, String lastName, int totalPrice, String depositPaid, String bookingDates, String additionalNeeds){
 response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid, Collections.singletonList(bookingDates), Collections.singletonList(additionalNeeds));

    }

    @Then("^I verify that the booking with \"([^\"]*)\" is created$")
    public void iVerifyThatTheBookingWithIsCreated() {
        response.statusCode(201);
        HashMap<String, Object> bookingMap = bookingSteps.getBookingInfoByFirstName(firstName);
        bookingID = (int) bookingMap.get("id");
        Assert.assertThat(bookingMap, hasValue(firstName));
    }


    @And("^I update the booking with information firstName \"([^\"]*)\" lastName \"([^\"]*)\" totalPrice \"([^\"]*)\" depositPaid \"([^\"]*)\" bookingDates \"([^\"]*)\" additionalNeeds \"([^\"]*)\"$")
    public void iUpdateTheBookingWithInformationFirstNameLastNameTotalPriceDepositPaidBookingDatesAdditionalNeeds(int bookingID,String firstName, String lastName, int totalPrice, String depositPaid, String bookingDates, String additionalNeeds) {

        firstName = firstName +"_updated";
        response = bookingSteps.updateBooking(bookingID, firstName, lastName, totalPrice, depositPaid, Collections.singletonList(bookingDates), Collections.singletonList(additionalNeeds));

    }

    @And("^I delete the booking that created with name \"([^\"]*)\"$")
    public void iDeleteTheBookingThatCreatedWithName()  {
        response = bookingSteps.deleteBooking(bookingID);

    }

    @Then("^The booking deleted successfully from the list$")
    public void theBookingDeletedSuccessfullyFromTheList() {
        response.statusCode(204);
        bookingSteps.getBookingById(bookingID).statusCode(404);
    }
}
