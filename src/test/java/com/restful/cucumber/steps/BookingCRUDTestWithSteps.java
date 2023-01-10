package com.restful.cucumber.steps;

import com.restful.studentinfo.BookingSteps;
import com.restful.testbase.TestBase;
import com.restful.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class BookingCRUDTestWithSteps extends TestBase {
    static String firstName = "Vim" + TestUtils.getRandomValue();
    static String lastName = "Rav";
    static int totalPrice = 4444;
    static String depositPaid = "True";

    static int bookingId;
    @Steps
    BookingSteps bookingSteps;

    @Title("This will create a new booking")
    @Test
    public void test001() {
        List<String> bookingDates = new ArrayList<>();
        bookingDates.add("checkin: 2023-01-01");
        bookingDates.add("checkout: 2023-02-01");
        List<String> additionalNeeds = new ArrayList<>();
        additionalNeeds.add("Breakfast");
        additionalNeeds.add("Wi-Fi");

        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid,
                bookingDates, additionalNeeds);
        response.log().all().statusCode(200);


    }

    @Title("Verify if the booking was added to the application")
    @Test
    public void test002() {


        HashMap<Integer, Object> bookingMap = (HashMap<Integer, Object>) bookingSteps.getBookingInfoByBookingId(bookingId);
        Assert.assertThat(bookingMap, hasValue(bookingId));
        bookingId = (int) bookingMap.get("bookingid");
    }

    @Title("Update the booking information and verify the updated information")
    @Test
    public void test003() {

        firstName = firstName + "_updated";

        List<String> bookingDates = new ArrayList<>();
        bookingDates.add("checkin: 2023-01-01");
        bookingDates.add("checkout: 2023-02-01");
        List<String> additionalNeeds = new ArrayList<>();
        additionalNeeds.add("Breakfast");
        additionalNeeds.add("Wi-Fi");

        bookingSteps.updateBooking(bookingId,firstName, lastName, totalPrice, depositPaid,
                        bookingDates, additionalNeeds)
                .log().all().statusCode(200);
        HashMap<String, Object> bookingMap = (HashMap<String, Object>) bookingSteps.getBookingInfoByBookingId(bookingId);
        Assert.assertThat(bookingMap, hasValue(firstName));
    }

    @Title("Delete the booking and verify if the booking is deleted!")
    @Test
    public void test004() {
        bookingSteps.deleteBooking(bookingId).statusCode(204);
        bookingSteps.getBookingById(bookingId).statusCode(404);
    }

}
