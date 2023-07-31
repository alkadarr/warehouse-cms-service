package com.radev.basecode.service.implementation;

import com.radev.basecode.dao.ProductRepository;
import com.radev.basecode.dto.ProductHeaderDto;
import com.radev.basecode.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductHeaderDto> findAll() {
        return ProductHeaderDto.convert(productRepository.findAll());
    }
}
