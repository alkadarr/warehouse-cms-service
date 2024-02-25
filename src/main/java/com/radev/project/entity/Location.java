package com.radev.project.entity;

import com.radev.project.entity.general.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Locations")
@Getter
@Setter
@NoArgsConstructor
public class Location extends BaseEntity {
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "address")
    private String address;
    @Column(name = "zip_code")
    private String zipCode;

    public Location(String city, String state, String country, String address, String zipCode) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.zipCode = zipCode;
    }
}
