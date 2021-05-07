package com.appgate.geolocation.repository;

import com.appgate.geolocation.model.entity.Geolocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeolocationRepository extends CrudRepository<Geolocation, Integer> {

    @Query("select geo from Geolocation geo where geo.ipFrom = :ip or geo.ipTo = :ip")
    Optional<List<Geolocation>> findByIp(@Param("ip") String ip);

}