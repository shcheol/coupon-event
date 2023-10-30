package com.hcs.promotion.infra.repository;

import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("classpath:test-init.sql")
class CustomPromotionRepositoryImplTest {

	@Autowired
	private PromotionRepository repository;

	@Test
	void proceedingPromotions() {

		SearchCondition condition = new SearchCondition(true, "title1", LocalDateTime.of(2023, 10, 31, 0, 0));
		PageRequest of = PageRequest.of(0, 3);
		List<PromotionDto> promotions = repository.findPromotionsByCondition(condition, of).getContent();
		Assertions.assertThat(promotions.size()).isEqualTo(1);
	}

	@Test
	void notProceedingPromotions() {

		SearchCondition condition = new SearchCondition(false, "title1", LocalDateTime.of(2023, 10, 31, 0, 0));
		PageRequest of = PageRequest.of(0, 3);
		Page<PromotionDto> promotions = repository.findPromotionsByCondition(condition, of);

		Assertions.assertThat(promotions.getContent().size()).isEqualTo(2);
	}
}