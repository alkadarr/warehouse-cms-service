package com.radev.basecode.dto;

import com.radev.basecode.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHeaderDto {
    private String productName;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime updatedDate;
    private String updatedBy;

    public static List<ProductHeaderDto> convert(List<Product> products) {
        return products.stream().map(
                product -> new ProductHeaderDto(
                        product.getProductName(),
                        product.getCreatedDate(),
                        product.getCreatedBy(),
                        product.getUpdatedDate(),
                        product.getUpdatedBy()
                )).collect(Collectors.toList());
    }
}
