package com.hcs.promotion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SearchCondition {

	boolean proceeding = true;

	String title;

	LocalDateTime now = LocalDateTime.now();
}
