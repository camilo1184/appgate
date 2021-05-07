package com.appgate.geolocation.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="geolocation")
public class Geolocation implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private int id;

  @Column(name="ip_from")
  private String ipFrom;

  @Column(name="ip_to")
  private String ipTo;

  @Column(name="countryCode")
  private String countryCode;

  @Column(name="country")
  private String country;

  @Column(name="region")
  private String region;

  @Column(name="city")
  private String city;

  @Column(name="latitude")
  private String latitude;

  @Column(name="timezone")
  private String timezone;

  @Version
  private Short version;
}