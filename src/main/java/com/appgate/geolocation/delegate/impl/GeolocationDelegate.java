package com.appgate.geolocation.delegate.impl;

import com.appgate.geolocation.delegate.IGeolocationDelegate;
import com.appgate.geolocation.mapper.IpInfoMapper;
import com.appgate.geolocation.model.entity.dto.InfoIp;
import com.appgate.geolocation.service.IGeolocationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class GeolocationDelegate implements IGeolocationDelegate {

    @Value("${ip.octet1}")
    private int octet1;

    @Value("${ip.octet2}")
    private int octet2;

    @Value("${ip.octet3}")
    private int octet3;

    private IGeolocationService geolocationService;

    private IpInfoMapper ipInfoMapper;

    public GeolocationDelegate(IGeolocationService geolocationService,
                               IpInfoMapper ipInfoMapper) {
        this.geolocationService = geolocationService;
        this.ipInfoMapper = ipInfoMapper;
    }

    @Override
    public ResponseEntity<String> processFile() {
        try {
            geolocationService.processFile();
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity findInfoIp(String ip) {

        InetAddressValidator validator = InetAddressValidator.getInstance();
        if (!validator.isValidInet4Address(ip)) {
            return new ResponseEntity("The ip " + ip + " is not valid", HttpStatus.BAD_REQUEST);
        }

        return geolocationService.findInfoIp(transformToDecimal(ip))
                .map(geo -> {
                    List<InfoIp> infoIps = ipInfoMapper.entityListToDtoList(geo);
                    if (infoIps.isEmpty()) {
                        return new ResponseEntity("Not data found for ip " + ip, HttpStatus.OK);
                    }
                    return new ResponseEntity(infoIps, HttpStatus.OK);
                }).get();

    }

    private int transformToDecimal(String ip) {
        String[] octets = ip.split("\\.");
        return Integer.parseInt(octets[0]) * octet1 +
                Integer.parseInt(octets[1]) * octet2 +
                Integer.parseInt(octets[2]) * octet3 + Integer.parseInt(octets[3]);
    }

}
