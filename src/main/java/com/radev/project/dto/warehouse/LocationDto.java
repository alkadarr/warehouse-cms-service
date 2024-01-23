package com.radev.project.dto.warehouse;

import com.radev.project.entity.Location;
import lombok.Data;

@Data
public class LocationDto {
    private String city;
    private String state;
    private String country;
    private String address;
    private String zipCode;

    public Location convert(){
        return new Location(
                this.city,
                this.state,
                this.country,
                this.address,
                this.zipCode
        );
    }
}
