package com.example.api.service.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {

    private final String categoryName;

    private final Integer price;


}
