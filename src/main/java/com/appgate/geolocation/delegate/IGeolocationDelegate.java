package com.appgate.geolocation.delegate;

import org.springframework.http.ResponseEntity;

public interface IGeolocationDelegate {

    ResponseEntity<String> processFile();

    ResponseEntity findInfoIp(String ip);

}
