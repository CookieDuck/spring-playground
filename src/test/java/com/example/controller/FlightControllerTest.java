package com.example.controller;

import com.example.model.Flight;
import com.example.model.TicketList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.core.IsNull;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
@AutoConfigureMockMvc(secure=false)
public class FlightControllerTest {
    @Autowired
    private MockMvc mvc;

    private Gson gson = new GsonBuilder().create();

    @Test
    public void testGetFlight() throws Exception {
        mvc.perform(get("/flights/flight"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }

    @Ignore
    public void testGetFlightsOriginal() throws Exception {
        mvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
                .andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.lastName").value(IsNull.nullValue()))
                .andExpect(jsonPath("$[1].tickets[0].price", is(400)));
    }

    @Ignore
    public void testGetFlightsCapitalizedJSON() throws Exception {
        mvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName").doesNotExist())
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)));
    }

    @Test
    public void testTicketTotallerStringLiteral() throws Exception {
        String jsonString = "{\"tickets\": [" +
                "  {" +
                "    \"passenger\": {" +
                "      \"firstName\": \"Some name\"," +
                "      \"lastName\": \"Some other name\"" +
                "    }," +
                "    \"price\": 200" +
                "  }," +
                "  {" +
                "    \"passenger\": {" +
                "      \"firstName\": \"Name B\"," +
                "      \"lastName\": \"Name C\"" +
                "    }," +
                "    \"price\": 150" +
                "  }" +
                "]}";

        mvc.perform(post("/flights/tickets/total").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testTicketTotallerGSON() throws Exception {
        String jsonRequest = gson.toJson(
                new TicketList(asList(
                makeTicket("Some name", "Some other name", 200),
                makeTicket("Name B", "Name C",  150)
        )));

        mvc.perform(post("/flights/tickets/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testTicketTotallerJsonFromFile() throws Exception {
        String jsonRequest = getJSON("/data.json");

        mvc.perform(post("/flights/tickets/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

    private static Flight.Ticket makeTicket(String first, String last, Integer price) {
        return Flight.Ticket.builder()
                .passenger(
                        Flight.Passenger.builder().firstName(first).lastName(last).build())
                .price(price).build();
    }
}
