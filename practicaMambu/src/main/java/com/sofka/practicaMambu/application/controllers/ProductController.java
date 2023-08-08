package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.ProductResponseDTO;
import com.sofka.practicaMambu.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productDeposit")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{idProduct}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> product(@PathVariable String idProduct){
        System.out.println(idProduct);

        ProductResponseDTO responseDTO = productService.product(idProduct);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getName());
        return responseEntity;
    }

}
