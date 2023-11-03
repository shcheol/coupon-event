package com.hcs.member;

public record MemberDto(String id) {

	public static MemberDto convert(Member member){

		return new MemberDto(member.getMemberId().getId());
	}
}
