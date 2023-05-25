package com.example.reminisce_ticketing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDetailsModel {

@SerializedName("code")
@Expose
public Integer code;
@SerializedName("status")
@Expose
public String status;
@SerializedName("message")
@Expose
public String message;
@SerializedName("data")
@Expose
public Data data;

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

    public class Data {

        @SerializedName("event_id")
        @Expose
        public String eventId;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("slug")
        @Expose
        public String slug;
        @SerializedName("location")
        @Expose
        public String location;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("event_start_date")
        @Expose
        public String eventStartDate;
        @SerializedName("event_end_date")
        @Expose
        public String eventEndDate;
        @SerializedName("event_start_time")
        @Expose
        public String eventStartTime;
        @SerializedName("event_end_time")
        @Expose
        public String eventEndTime;
        @SerializedName("latitude")
        @Expose
        public String latitude;
        @SerializedName("longitude")
        @Expose
        public String longitude;
        @SerializedName("VIP_Balcony")
        @Expose
        public VIPBalcony vIPBalcony;


        @SerializedName("Second_Release")
        @Expose
        public SecondRelease secondRelease;

        @SerializedName("First_Release")
        @Expose
        public FirstRelease firstRelease;

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEventStartDate() {
            return eventStartDate;
        }

        public void setEventStartDate(String eventStartDate) {
            this.eventStartDate = eventStartDate;
        }

        public String getEventEndDate() {
            return eventEndDate;
        }

        public void setEventEndDate(String eventEndDate) {
            this.eventEndDate = eventEndDate;
        }

        public String getEventStartTime() {
            return eventStartTime;
        }

        public void setEventStartTime(String eventStartTime) {
            this.eventStartTime = eventStartTime;
        }

        public String getEventEndTime() {
            return eventEndTime;
        }

        public void setEventEndTime(String eventEndTime) {
            this.eventEndTime = eventEndTime;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public VIPBalcony getVIPBalcony() {
            return vIPBalcony;
        }

        public void setVIPBalcony(VIPBalcony vIPBalcony) {
            this.vIPBalcony = vIPBalcony;
        }

        public SecondRelease getSecondRelease() {
            return secondRelease;
        }

        public void setSecondRelease(SecondRelease secondRelease) {
            this.secondRelease = secondRelease;
        }

        public FirstRelease getFirstRelease() {
            return firstRelease;
        }

        public void setFirstRelease(FirstRelease firstRelease) {
            this.firstRelease = firstRelease;
        }
        public class VIPBalcony {

            @SerializedName("seat_type_id")
            @Expose
            public String seatTypeId;
            @SerializedName("seat_type")
            @Expose
            public String seatType;
            @SerializedName("total_seat")
            @Expose
            public String totalSeat;
            @SerializedName("ticket_price")
            @Expose
            public String ticketPrice;
            @SerializedName("maximum_booking_seat")
            @Expose
            public String maximumBookingSeat;
            @SerializedName("total_sale_ticket")
            @Expose
            public Integer totalSaleTicket;

            public String getSeatTypeId() {
                return seatTypeId;
            }

            public void setSeatTypeId(String seatTypeId) {
                this.seatTypeId = seatTypeId;
            }

            public String getSeatType() {
                return seatType;
            }

            public void setSeatType(String seatType) {
                this.seatType = seatType;
            }

            public String getTotalSeat() {
                return totalSeat;
            }

            public void setTotalSeat(String totalSeat) {
                this.totalSeat = totalSeat;
            }

            public String getTicketPrice() {
                return ticketPrice;
            }

            public void setTicketPrice(String ticketPrice) {
                this.ticketPrice = ticketPrice;
            }

            public String getMaximumBookingSeat() {
                return maximumBookingSeat;
            }

            public void setMaximumBookingSeat(String maximumBookingSeat) {
                this.maximumBookingSeat = maximumBookingSeat;
            }

            public Integer getTotalSaleTicket() {
                return totalSaleTicket;
            }

            public void setTotalSaleTicket(Integer totalSaleTicket) {
                this.totalSaleTicket = totalSaleTicket;
            }

        }
        public class FirstRelease {

            @SerializedName("seat_type_id")
            @Expose
            public String seatTypeId;
            @SerializedName("seat_type")
            @Expose
            public String seatType;
            @SerializedName("total_seat")
            @Expose
            public String totalSeat;
            @SerializedName("ticket_price")
            @Expose
            public String ticketPrice;
            @SerializedName("maximum_booking_seat")
            @Expose
            public String maximumBookingSeat;
            @SerializedName("total_sale_ticket")
            @Expose
            public Integer totalSaleTicket;

            public String getSeatTypeId() {
                return seatTypeId;
            }

            public void setSeatTypeId(String seatTypeId) {
                this.seatTypeId = seatTypeId;
            }

            public String getSeatType() {
                return seatType;
            }

            public void setSeatType(String seatType) {
                this.seatType = seatType;
            }

            public String getTotalSeat() {
                return totalSeat;
            }

            public void setTotalSeat(String totalSeat) {
                this.totalSeat = totalSeat;
            }

            public String getTicketPrice() {
                return ticketPrice;
            }

            public void setTicketPrice(String ticketPrice) {
                this.ticketPrice = ticketPrice;
            }

            public String getMaximumBookingSeat() {
                return maximumBookingSeat;
            }

            public void setMaximumBookingSeat(String maximumBookingSeat) {
                this.maximumBookingSeat = maximumBookingSeat;
            }

            public Integer getTotalSaleTicket() {
                return totalSaleTicket;
            }

            public void setTotalSaleTicket(Integer totalSaleTicket) {
                this.totalSaleTicket = totalSaleTicket;
            }

        }
        public class SecondRelease {

            @SerializedName("seat_type_id")
            @Expose
            public String seatTypeId;
            @SerializedName("seat_type")
            @Expose
            public String seatType;
            @SerializedName("total_seat")
            @Expose
            public String totalSeat;
            @SerializedName("ticket_price")
            @Expose
            public String ticketPrice;
            @SerializedName("maximum_booking_seat")
            @Expose
            public String maximumBookingSeat;
            @SerializedName("total_sale_ticket")
            @Expose
            public Integer totalSaleTicket;

            public String getSeatTypeId() {
                return seatTypeId;
            }

            public void setSeatTypeId(String seatTypeId) {
                this.seatTypeId = seatTypeId;
            }

            public String getSeatType() {
                return seatType;
            }

            public void setSeatType(String seatType) {
                this.seatType = seatType;
            }

            public String getTotalSeat() {
                return totalSeat;
            }

            public void setTotalSeat(String totalSeat) {
                this.totalSeat = totalSeat;
            }

            public String getTicketPrice() {
                return ticketPrice;
            }

            public void setTicketPrice(String ticketPrice) {
                this.ticketPrice = ticketPrice;
            }

            public String getMaximumBookingSeat() {
                return maximumBookingSeat;
            }

            public void setMaximumBookingSeat(String maximumBookingSeat) {
                this.maximumBookingSeat = maximumBookingSeat;
            }

            public Integer getTotalSaleTicket() {
                return totalSaleTicket;
            }

            public void setTotalSaleTicket(Integer totalSaleTicket) {
                this.totalSaleTicket = totalSaleTicket;
            }

        }
    }
}
