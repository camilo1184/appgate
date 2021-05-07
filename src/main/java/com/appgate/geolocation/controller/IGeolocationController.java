package com.appgate.geolocation.controller;

import com.appgate.geolocation.constants.ResourceEndpoint;
import com.appgate.geolocation.model.entity.dto.InfoIp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Validated
public interface IGeolocationController {

    @PostMapping(ResourceEndpoint.FILE)
    ResponseEntity<String> loadFile();

    @GetMapping(ResourceEndpoint.IP+"/{ip}")
    ResponseEntity<InfoIp> findInfoIp(@PathVariable String ip);

}
