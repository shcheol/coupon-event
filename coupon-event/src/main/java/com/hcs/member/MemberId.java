package com.hcs.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class MemberId implements Serializable {

    @Column(name = "member_id")
    private String id;

    public static MemberId newId() {
        MemberId id = new MemberId();
        id.id = UUID.randomUUID().toString();
        return id;
    }

}
