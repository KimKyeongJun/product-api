package com.example.api.controller.product;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public void getProductList() {

    }

    @PostMapping
    public void registerProduct() {

    }

    @PatchMapping("/{id}")
    public void modifyProduct(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

    }


}
