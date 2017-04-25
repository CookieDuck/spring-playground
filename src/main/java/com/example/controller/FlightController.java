package com.example.controller;

import com.example.model.Flight;
import com.example.model.TicketList;
import com.example.model.TicketSum;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @GetMapping("/flight")
    public Flight getFlight() {
        return Flight.builder()
                .departs(createDate("2017-04-21 14:34"))
                .tickets(asList(
                        Flight.Ticket.builder()
                                .passenger(
                                        Flight.Passenger.builder().firstName("Some name").lastName("Some other name").build()
                                )
                                .price(200)
                                .build()
                ))
                .build();
    }

    @GetMapping
    public List<Flight> getFlights() {
        Flight flight1 = Flight.builder()
                .departs(createDate("2017-04-21 14:34"))
                .tickets(asList(
                        Flight.Ticket.builder()
                                .passenger(
                                        Flight.Passenger.builder().firstName("Some name").lastName(null).build()
                                )
                                .price(200)
                                .build()
                ))
                .build();

        return(asList(flight1));
    }

    @PostMapping("/tickets/total")
    public TicketSum calculateTicketTotal(@RequestBody TicketList ticketList) {
        return new TicketSum(ticketList.getTickets().stream().mapToInt(t -> t.getPrice()).sum());
    }

    private static Date createDate(String string) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date;
        try {
            date = formatter.parse(string);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }
}
