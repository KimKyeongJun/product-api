package com.example.api.repository.category;

import com.example.api.entity.category.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("등록된 카테고리의 갯수를 확인한다")
    @Test
    void selectCategoryCount() {
        // given
        // when
        List<Category> categories = categoryRepository.findAll();
        // then
        assertThat(categories).hasSize(8);
    }

}