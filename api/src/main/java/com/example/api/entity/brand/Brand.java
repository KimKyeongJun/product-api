package com.example.api.entity.brand;


import com.example.api.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "brand")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("브랜드명")
    private String name;

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


}
