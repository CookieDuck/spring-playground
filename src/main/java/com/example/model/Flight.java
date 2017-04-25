package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@Builder
public class Flight {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departs;

    private List<Ticket> tickets;

    @JsonGetter(value = "Departs")
    public Date getDeparts() {
        return this.departs;
    }

    @JsonGetter(value = "Tickets")
    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Builder
    @EqualsAndHashCode
    public static class Ticket {
        private Passenger passenger;
        private Integer price;

        @JsonGetter(value = "Passenger")
        public Passenger getPassenger() {
            return this.passenger;
        }

        @JsonGetter(value = "Price")
        public Integer getPrice() {
            return this.price;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }

    @EqualsAndHashCode
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Passenger {
        private String firstName;
        private String lastName;

        @JsonGetter(value = "FirstName")
        public String getFirstName() {
            return this.firstName;
        }

        @JsonGetter(value = "LastName")
        public String getLastName() {
            return this.lastName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
