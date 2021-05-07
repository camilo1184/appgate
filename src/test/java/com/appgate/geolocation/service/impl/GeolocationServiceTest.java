package com.appgate.geolocation.service.impl;

import com.appgate.geolocation.model.entity.Geolocation;
import com.appgate.geolocation.repository.GeolocationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GeolocationServiceTest {

    @InjectMocks
    private GeolocationService geolocationService;

    @Mock
    private GeolocationRepository geolocationRepository;

    @Test
    public void given_validIp_then_returns_complementary_information() {
        List<Geolocation> list = new ArrayList<>();
        Geolocation geo = new Geolocation();
        geo.setCity("Bogota");
        list.add(geo);

        when(geolocationRepository.findByIp(anyString())).thenReturn(Optional.of(list));
        List result = geolocationService.findInfoIp(12212).get();
        Assert.assertEquals(list.size(), result.size());

    }
}