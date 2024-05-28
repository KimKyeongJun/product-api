# product-api

# 구현 범위

- Java Version - 17
- Spring Boot Version - 3.2.5

# 실행 방법

- API 모듈 밑에 있는 ApiApplication 파일 실행
- http://localhost:8080/swagger-ui/index.html 접속
- API 테스트
    - 구현 1) 카테고리 별로 최저가격인 브랜드와 가격을 조회
        - GET
        - [**/api/products/lowest-price-by-category**](http://localhost:8080/swagger-ui/index.html#/product-controller/getLowestPriceByCategory)
    - 구현 2) 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액 조회
        - **GET**
        - [**/api/products/min-price-by-brand**](http://localhost:8080/swagger-ui/index.html#/product-controller/getMinPriceByBrand)
    - 구현 3) 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드 조회
        - **GET**
        - [**/api/products/min-max-by-category**](http://localhost:8080/swagger-ui/index.html#/product-controller/getMinMaxProductByCategoryName)
    - 구현 4) 상품, 브랜드 등록, 수정, 삭제 API 구현

# 구현 항목

- [x]  카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
- [x]  단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
조회하는 API
- [x]  카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 **API**
- [x]  브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
- [x]  ProductService Test Code 작성

# 요구사항 정의
## 보유 카테고리
* 상의, 아우터, 바지, 스니커즈, 가방, 모자, 양말, 액세서리
구매 가격 외의 추가적인 비용(예, 배송비 등)은 고려하지 않고, 브랜드의 카테고리에는 1개의 상품은 존재하고, 구매를 고려하는 모든 쇼핑몰에 상품 품절은 없다고 가정한다.

## 브랜드
* A, B, C, D, E, F, G, H, I

## 구현 사항
1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 한다.
2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 한다.
3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 한다.
4. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 한다.


