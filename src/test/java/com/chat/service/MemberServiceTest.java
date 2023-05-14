package com.chat.service;

import com.chat.dto.MemberDTO;
import com.chat.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Mock
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberDTO dto = new MemberDTO();
        dto.setName("a");
        dto.setPassword("a");
        return Member.createMember(dto, passwordEncoder);
    }

    @Test
    @DisplayName("join test")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        Assertions.assertEquals(member.getName(), savedMember.getName());
        Assertions.assertEquals(member.getPassword(), savedMember.getPassword());
        Assertions.assertEquals(member.getRole(), savedMember.getRole());
    }
    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMember() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e =assertThrows(IllegalStateException.class, () ->{
            memberService.saveMember(member2);
        });
        assertEquals("이미 가입된 회원입니다.", e.getMessage());

    }

}
