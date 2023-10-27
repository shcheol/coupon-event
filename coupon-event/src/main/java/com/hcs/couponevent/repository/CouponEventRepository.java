package com.hcs.couponevent.repository;

import com.hcs.couponevent.domain.CouponEvent;
import com.hcs.couponevent.domain.CouponEventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponEventRepository extends JpaRepository<CouponEvent, CouponEventId> {
}
