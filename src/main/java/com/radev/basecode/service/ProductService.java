package com.radev.basecode.service;

import com.radev.basecode.dto.ProductHeaderDto;

import java.util.List;

public interface ProductService {
    List<ProductHeaderDto> findAll();
}
