package com.hcs.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class MemberId implements Serializable {

    @Column(name = "member_id")
    private String id;

    public static MemberId newId() {
        MemberId id = new MemberId();
        id.id = UUID.randomUUID().toString();
        return id;
    }

	public static MemberId of(String id){
        if (!StringUtils.hasText(id)){
            throw new IllegalArgumentException();
        }
		MemberId memberId = new MemberId();
        memberId.id = id;
		return memberId;
	}

}
