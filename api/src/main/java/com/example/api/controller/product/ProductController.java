package com.example.api.controller.product;


import com.example.api.controller.product.request.ProductModifyRequest;
import com.example.api.controller.product.request.ProductRegisterRequest;
import com.example.api.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public void registerProduct(@RequestBody ProductRegisterRequest productRegisterRequest ){
        productService.createProduct(productRegisterRequest);

    }

    @PatchMapping("/{id}")
    public void modifyProduct(@PathVariable Long id, @RequestBody ProductModifyRequest productModifyRequest) {
        productService.modifyProduct(id, productModifyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
