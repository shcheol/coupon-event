package com.hcs.promotion.dto;

import java.time.LocalDateTime;

public record SearchCondition(Boolean proceeding, String title, LocalDateTime now) {

	public SearchCondition {
		if (proceeding == null) {
			proceeding = true;
		}
		if (now == null) {
			now = LocalDateTime.now();
		}
	}
}
