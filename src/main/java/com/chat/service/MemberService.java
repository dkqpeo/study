package com.chat.service;

import com.chat.entity.Member;
import com.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional  // 디비 실행 중 예외 발생 시 콜백 역할을 함
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByName(member.getName());
        if(findMember != null)
            throw new IllegalStateException("이미 가입된 회원입니다.");
    }
}
