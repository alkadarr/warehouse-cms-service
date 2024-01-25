package com.radev.project.dto.warehouse;

import lombok.Data;

@Data
public class WarehouseRegister {
    private String name;
    private LocationDto location;
    private String category;
    private Integer capacity;
}
