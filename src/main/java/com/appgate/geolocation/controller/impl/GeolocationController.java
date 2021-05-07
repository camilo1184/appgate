package com.appgate.geolocation.controller.impl;

import com.appgate.geolocation.constants.ResourceEndpoint;
import com.appgate.geolocation.controller.IGeolocationController;
import com.appgate.geolocation.delegate.IGeolocationDelegate;
import com.appgate.geolocation.model.entity.dto.InfoIp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceEndpoint.GEO)
@Slf4j
public class GeolocationController implements IGeolocationController {

    private IGeolocationDelegate locationDelegate;

    public GeolocationController(IGeolocationDelegate locationDelegate) {
        this.locationDelegate = locationDelegate;
    }

    @Override
    public ResponseEntity<String> loadFile() {
        return locationDelegate.processFile();
    }

    @Override
    public ResponseEntity<InfoIp> findInfoIp(@PathVariable String ip) {
        return locationDelegate.findInfoIp(ip);
    }
}
