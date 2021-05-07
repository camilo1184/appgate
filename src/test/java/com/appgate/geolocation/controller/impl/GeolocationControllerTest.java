package com.appgate.geolocation.controller.impl;

import com.appgate.geolocation.constants.ResourceEndpoint;
import com.appgate.geolocation.delegate.IGeolocationDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GeolocationController.class)
public class GeolocationControllerTest {

    @MockBean
    private IGeolocationDelegate geolocationDelegate;

    @Autowired
    private MockMvc mvc;

    @Test
    public void given_file_then_success() throws Exception {
        mvc.perform(post(ResourceEndpoint.GEO + ResourceEndpoint.FILE))
                .andExpect(status().isOk());
    }

    @Test
    public void given_ip_then_success() throws Exception {
        mvc.perform(get(ResourceEndpoint.GEO + ResourceEndpoint.IP + "/0.0.0.0"))
                .andExpect(status().isOk());
    }

}