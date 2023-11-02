package com.hcs.promotion.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@Getter
@ToString
public class PromotionId implements Serializable {

    @Column(name = "promotion_id")
    private String id;

    public static PromotionId newId() {
        PromotionId id = new PromotionId();
        id.id = UUID.randomUUID().toString();
        return id;
    }

	public static PromotionId of(String of) {
		PromotionId id = new PromotionId();
		id.id = of;
		return id;
	}
}
