package com.appgate.geolocation.service;

import com.appgate.geolocation.model.entity.Geolocation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IGeolocationService {

    void processFile() throws IOException;

    Optional<List<Geolocation>> findInfoIp(int ip);

}
