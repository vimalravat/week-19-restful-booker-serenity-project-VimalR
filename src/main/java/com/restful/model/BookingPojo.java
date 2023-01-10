package com.restful.model;

import java.util.Collections;
import java.util.List;


public class BookingPojo {

    private String firstName;
    private String lastName;
    private int totalPrice;
    private String depositPaid;
    private String bookingDates;
    private String additionalNeeds;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(String depositPaid) {
        this.depositPaid = depositPaid;
    }

    public List<String> getBookingDates() {
        return Collections.singletonList(bookingDates);
    }

    public void setBookingDates(List<String> bookingDates) {
        this.bookingDates = bookingDates.toString();
    }

    public List<String> getAdditionalNeeds() {
        return Collections.singletonList(additionalNeeds);
    }

    public void setAdditionalNeeds(List<String> additionalNeeds) {
        this.additionalNeeds = additionalNeeds.toString();
    }


    public static BookingPojo getBookingPojo(String firstName, String lastName, int totalPrice,
                                             String depositPaid, List<String> bookingDates,
                                             List<String> additionalNeeds){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(totalPrice);
        bookingPojo.setDepositPaid(depositPaid);
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalNeeds(additionalNeeds);
        return bookingPojo;
    }
}
