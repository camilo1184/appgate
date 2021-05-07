package com.appgate.geolocation.service.impl;

import com.appgate.geolocation.model.entity.Geolocation;
import com.appgate.geolocation.repository.GeolocationRepository;
import com.appgate.geolocation.service.IGeolocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GeolocationService implements IGeolocationService {

    private GeolocationRepository geolocationRepository;

    public GeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Value("${file.path}")
    private String filePath;

    @Value("${database.num.records}")
    private int numRecords;

    @Override
    public void processFile() throws IOException {
        log.info("Start upload file ...");
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        LinkedList geolocations = new LinkedList();
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            geolocations.add(loadEntity(line, ++i));
            if (i % numRecords == 0) {
                geolocationRepository.saveAll(geolocations);
                geolocations.clear();
            }
        }

        geolocations.stream().findAny().map(list -> {
            return geolocationRepository.saveAll(geolocations);
        });
        log.info("End upload file");
    }

    @Override
    public Optional<List<Geolocation>> findInfoIp(int ip) {
        return geolocationRepository.findByIp(String.valueOf(ip));
    }

    private Geolocation loadEntity(String line, int count) {
        String[] splited = line.split(",");
        Geolocation geo = new Geolocation();
        geo.setId(count);
        geo.setIpFrom(splited[0].replace("\"", ""));
        geo.setIpTo(splited[1].replace("\"", ""));
        geo.setCountryCode(splited[2].replace("\"", ""));
        geo.setCountry(splited[3].replace("\"", ""));
        geo.setRegion(splited[4].replace("\"", ""));
        geo.setCity(splited[5].replace("\"", ""));
        geo.setLatitude(splited[6].replace("\"", ""));
        geo.setTimezone(splited[7].replace("\"", ""));
        return geo;
    }
}
