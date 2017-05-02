package com.example.service;

import com.example.config.OmdbConfig;
import com.example.remote.OmdbSearchResponse;
import com.example.remote.OmdbSearchResponse.OmdbEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class OmdbServiceTest {
    private final RestTemplate restTemplate = new RestTemplate();

    private OmdbService service;

    private MockRestServiceServer mockServer;

    @Mock
    private OmdbConfig config;

    @Before
    public void init() {
        when(config.getBaseUrl()).thenReturn("http://abc123");
        service = new OmdbService(config, restTemplate);
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testOmdbQuery() throws Exception {
        mockServer
                .expect(requestTo("http://abc123/?s=oink"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getJSON("/oink.json"), MediaType.APPLICATION_JSON));

        OmdbSearchResponse response = service.searchOmdb("oink");
        List<OmdbEntry> entries = response.getSearch();
        assertEquals(5, entries.size());

        List<String> titles = entries.stream()
                .map(OmdbEntry::getTitle).collect(Collectors.toList());
        assertTrue(titles.containsAll(asList("Oink", "Oink 2: Folsom 4-Way", "Oink")));

        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
