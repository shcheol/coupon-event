package com.hcs.promotion.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public record SearchCondition(Boolean proceeding, String title, LocalDateTime now) {

	public SearchCondition {
		if (proceeding == null) {
			proceeding = false;
		}
		if (now == null) {
			now = LocalDateTime.now();
		}
	}
}
