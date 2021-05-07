package com.appgate.geolocation.model.entity.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoIp {

    private String countryCode;
    private String region;
    private String city;
    private String timezone;

}
