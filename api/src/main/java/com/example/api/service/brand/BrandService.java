package com.example.api.service.brand;


import com.example.api.controller.brand.request.BrandModifyRequest;
import com.example.api.controller.brand.request.BrandRegisterRequest;
import com.example.api.entity.brand.Brand;
import com.example.api.entity.brand.BrandStatus;
import com.example.api.exception.BusinessException;
import com.example.api.repository.brand.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 브랜드가 존재하지 않습니다."));
        brand.updateStatus(BrandStatus.INACTIVE);
    }

    @Transactional
    public void createBrand(BrandRegisterRequest brandRegisterRequest) {
        Optional<Brand> brand = brandRepository.findByName(brandRegisterRequest.getName());

        if (brand.isPresent()) {
            throw new BusinessException(HttpStatus.CONFLICT, "이미 존재하는 브랜드입니다.");
        }

        Brand newBrand = brandRegisterRequest.toEntity();
        brandRepository.save(newBrand);
    }

    @Transactional
    public void modifyBrand(Long id, BrandModifyRequest brandModifyRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 브랜드가 존재하지 않습니다."));

        Optional<Brand> existBrand = brandRepository.findByName(brandModifyRequest.getName());

        if (existBrand.isPresent()) {
            throw new BusinessException(HttpStatus.CONFLICT, "이미 존재하는 브랜드입니다.");
        }

        brand.updateName(brandModifyRequest.getName());
    }
}
