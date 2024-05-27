package com.example.api.service.brand;


import com.example.api.controller.brand.request.BrandModifyRequest;
import com.example.api.controller.brand.request.BrandRegisterRequest;
import com.example.api.entity.brand.Brand;
import com.example.api.repository.brand.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public void deleteBrand(Long id) {
    }

    public void createBrand() {

        // 중복 체크

        // 저장
    }

    public void modifyBrand() {

    }

    public void createBrand(BrandRegisterRequest brandRegisterRequest) {
        Brand brand = brandRegisterRequest.toEntity();
        brandRepository.save(brand);
    }

    public void modifyBrand(Long id, BrandModifyRequest brandModifyRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 존재하지 않습니다."));
        brand.updateName(brandModifyRequest.getName());
    }
}
