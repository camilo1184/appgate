package com.appgate.geolocation.mapper;

import com.appgate.geolocation.model.entity.Geolocation;
import com.appgate.geolocation.model.entity.dto.InfoIp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IpInfoMapper {


    InfoIp entityToDto(Geolocation geolocation);

    List<InfoIp> entityListToDtoList(List<Geolocation> geolocations);

}
