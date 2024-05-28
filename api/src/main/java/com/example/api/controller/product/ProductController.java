package com.example.api.controller.product;


import com.example.api.controller.ApiResponse;
import com.example.api.controller.product.request.ProductModifyRequest;
import com.example.api.controller.product.request.ProductRegisterRequest;
import com.example.api.entity.product.Product;
import com.example.api.service.product.CategoryLowerPriceResponse;
import com.example.api.service.product.CategoryMinMaxProductResponse;
import com.example.api.service.product.MinPriceByBrandResponse;
import com.example.api.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ApiResponse<Product> registerProduct(@RequestBody ProductRegisterRequest productRegisterRequest ){
        return ApiResponse.ok(productService.createProduct(productRegisterRequest));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Product> modifyProduct(@PathVariable Long id, @RequestBody ProductModifyRequest productModifyRequest) {
        return ApiResponse.ok(productService.modifyProduct(id, productModifyRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Product> deleteProduct(@PathVariable Long id) {
        return ApiResponse.ok(productService.deleteProduct(id));
    }

    @GetMapping("/min-max-by-category")
    public ApiResponse<CategoryMinMaxProductResponse> getMinMaxProductByCategoryName(@RequestParam String categoryName) {
        CategoryMinMaxProductResponse categoryMinMaxProductResponse = productService.getMinMaxProductByCategoryName(categoryName);
        return ApiResponse.ok(categoryMinMaxProductResponse);
    }

    @GetMapping("/lowest-price-by-category")
    public ApiResponse<CategoryLowerPriceResponse> getLowestPriceByCategory() {
        CategoryLowerPriceResponse  categoryLowerPriceResponse = productService.getLowestPriceByCategory();
        return ApiResponse.ok(categoryLowerPriceResponse);
    }

    @GetMapping("/min-price-by-brand")
    public ApiResponse<MinPriceByBrandResponse> getMinPriceByBrand() {
        MinPriceByBrandResponse minPriceByBrandResponse = productService.getMinPriceByBrand();
        return ApiResponse.ok(minPriceByBrandResponse);
    }


}
