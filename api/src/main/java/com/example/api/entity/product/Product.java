package com.example.api.entity.product;


import com.example.api.entity.BaseEntity;
import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @Comment("브랜드 아이디")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Comment("카테고리 아이디")
    private Category category;

//    @Column(length = 100, nullable = false)
//    @Comment("상품명")
//    private String name;

    @Column(nullable = false)
    @Comment("상품 가격")
    private Integer price;

//    @Column
//    @Comment("전시 여부")
//    private boolean isDisplay;

}
