package com.radev.basecode.controller;

import com.radev.basecode.common.RestResponse;
import com.radev.basecode.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        var rest = new RestResponse();
        try {
            rest.setMessage("success");
            rest.setData(productService.findAll());
            return ResponseEntity.ok().body(rest);
        }catch (Exception e){
            rest.setMessage(e.getMessage());
            rest.setSuccess(false);
            return new ResponseEntity<RestResponse>(rest, HttpStatusCode.valueOf(500));
        }
    }
}
