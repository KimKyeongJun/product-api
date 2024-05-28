package com.example.api.entity.brand;


import com.example.api.entity.BaseEntity;
import com.example.api.entity.product.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "brand")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("브랜드명")
    private String name;

    @Column
    @Comment("상태")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BrandStatus status = BrandStatus.ACTIVE;

//    @Column(length = 12, nullable = false)
//    @Comment("사업자 등록번호")
//    private String companyNo;
//
//    @Column(length = 30)
//    @Comment("회사명")
//    private String companyName;
//
//    @Column(length = 20)
//    @Comment("대표자명")
//    private String ceoName;
//
//    @Column(length = 35)
//    @Comment("이메일")
//    private String email;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateStatus(BrandStatus brandStatus) {
        this.status = brandStatus;
    }
}
