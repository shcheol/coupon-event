package com.hcs.promotion.infra.repository;

import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, PromotionId>, CustomPromotionRepository {
}
