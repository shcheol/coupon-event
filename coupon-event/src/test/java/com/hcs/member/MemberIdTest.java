package com.hcs.member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberIdTest {

    @Test
    void of() {
        String id = "id";
        MemberId of = MemberId.of(id);

        assertEquals(of.getId(), id);
    }

    @Test
    void ofEmptyString() {
        String id = "";
        assertThrows(IllegalArgumentException.class, () -> MemberId.of(id));
    }


    @Test
    void ofNullValue() {
        String id = null;
        assertThrows(IllegalArgumentException.class, () -> MemberId.of(id));
    }
}