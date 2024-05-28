package com.example.api.controller.brand;


import com.example.api.controller.ApiResponse;
import com.example.api.controller.brand.request.BrandModifyRequest;
import com.example.api.controller.brand.request.BrandRegisterRequest;
import com.example.api.entity.brand.Brand;
import com.example.api.exception.BadParameterException;
import com.example.api.service.brand.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @Operation(summary = "구현 4) 브랜드 등록", description = "브랜드를 등록한다.")
    @PostMapping
    public ApiResponse<Brand> registerBrand(@Valid @RequestBody BrandRegisterRequest brandRegisterRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadParameterException(bindingResult);
        }

        return ApiResponse.ok(brandService.createBrand(brandRegisterRequest));
    }

    @Operation(summary = "구현 4) 브랜드 수정", description = "브랜드를 수정한다.")
    @PatchMapping("/{id}")
    public ApiResponse<Brand> modifyBrand(@PathVariable Long id, @Valid @RequestBody BrandModifyRequest brandModifyRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadParameterException(bindingResult);
        }
       return ApiResponse.ok(brandService.modifyBrand(id, brandModifyRequest));
    }

    @Operation(summary = "구현 4) 브랜드 삭제", description = "브랜드를 삭제한다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Brand> deleteBrand(@PathVariable Long id) {

        return ApiResponse.ok(brandService.deleteBrand(id));

    }
}
