package com.example.api.controller.brand;


import com.example.api.controller.brand.request.BrandModifyRequest;
import com.example.api.controller.brand.request.BrandRegisterRequest;
import com.example.api.service.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @PostMapping
    public void registerBrand(@RequestBody BrandRegisterRequest brandRegisterRequest) {
        brandService.createBrand(brandRegisterRequest);

    }

    @PatchMapping("/{id}")
    public void modifyBrand(@PathVariable Long id, @RequestBody BrandModifyRequest brandModifyRequest) {
        brandService.modifyBrand(id, brandModifyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);

    }
}
