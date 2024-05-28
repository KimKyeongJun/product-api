package com.example.api.controller.product;


import com.example.api.controller.ApiResponse;
import com.example.api.controller.product.request.ProductModifyRequest;
import com.example.api.controller.product.request.ProductRegisterRequest;
import com.example.api.entity.product.Product;
import com.example.api.exception.BadParameterException;
import com.example.api.service.product.CategoryLowerPriceResponse;
import com.example.api.service.product.CategoryMinMaxProductResponse;
import com.example.api.service.product.MinPriceByBrandResponse;
import com.example.api.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "구현 4) 상품 등록", description = "상품을 등록한다.")
    @PostMapping
    public ApiResponse<Product> registerProduct(@Valid @RequestBody ProductRegisterRequest productRegisterRequest, BindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            throw new BadParameterException(bindingResult);
        }
        return ApiResponse.ok(productService.createProduct(productRegisterRequest));
    }

    @Operation(summary = "구현 4) 상품 수정", description = "상품을 수정한다.")
    @PatchMapping("/{id}")
    public ApiResponse<Product> modifyProduct(@PathVariable Long id, @Valid @RequestBody ProductModifyRequest productModifyRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadParameterException(bindingResult);
        }
        return ApiResponse.ok(productService.modifyProduct(id, productModifyRequest));
    }

    @Operation(summary = "구현 4) 상품 삭제", description = "상품을 삭제한다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Product> deleteProduct(@PathVariable Long id) {
        return ApiResponse.ok(productService.deleteProduct(id));
    }

    @Operation(summary = "구현 3) 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드 조회", description = "특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 조회한다.")
    @GetMapping("/min-max-by-category")
    public ApiResponse<CategoryMinMaxProductResponse> getMinMaxProductByCategoryName(@RequestParam String categoryName) {
        CategoryMinMaxProductResponse categoryMinMaxProductResponse = productService.getMinMaxProductByCategoryName(categoryName);
        return ApiResponse.ok(categoryMinMaxProductResponse);
    }

    @Operation(summary = "구현 1) 카테고리 별로 최저가격인 브랜드와 가격을 조회", description = "카테고리 별로 최저가격인 브랜드와 가격을 조회한다.")
    @GetMapping("/lowest-price-by-category")
    public ApiResponse<CategoryLowerPriceResponse> getLowestPriceByCategory() {
        CategoryLowerPriceResponse  categoryLowerPriceResponse = productService.getLowestPriceByCategory();
        return ApiResponse.ok(categoryLowerPriceResponse);
    }

    @Operation(summary = "구현 2) 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액 조회", description = "단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액 조회한다.")
    @GetMapping("/min-price-by-brand")
    public ApiResponse<MinPriceByBrandResponse> getMinPriceByBrand() {
        MinPriceByBrandResponse minPriceByBrandResponse = productService.getMinPriceByBrand();
        return ApiResponse.ok(minPriceByBrandResponse);
    }


}
