package pl.cieszk.todoapp.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.cieszk.todoapp.controllers.RequestCountController;
import pl.cieszk.todoapp.utils.RequestCounterFilter;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class RequestCountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RequestCounterFilter requestCounterFilter;

    @InjectMocks
    private RequestCountController requestCountController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(requestCountController)
                .build();
    }

    @Test
    public void testGetRequestCount() throws Exception {
        int count = 10;

        given(requestCounterFilter.getRequestCount()).willReturn(count);

        mockMvc.perform(get("/api/utils/request-count")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(count)));
    }
}