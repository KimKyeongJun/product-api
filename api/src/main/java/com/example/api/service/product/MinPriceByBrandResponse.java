package com.example.api.service.product;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MinPriceByBrandResponse {

    private String brandName;

    private List<ProductResponse> categories;

    private int price;
}
