package com.example.reminisce_ticketing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScannerData {

    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("event_start_date")
    @Expose
    private String eventStartDate;
    @SerializedName("event_start_time")
    @Expose
    private String eventStartTime;
    @SerializedName("ticket_number")
    @Expose
    private String ticketNumber;

    @SerializedName("event_end_date")
    @Expose
    private String eventEndDate;
    @SerializedName("event_end_time")
    @Expose
    private String eventEndTime;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
