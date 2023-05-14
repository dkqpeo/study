package com.chat.entity;

import com.chat.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true)
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberDTO md, PasswordEncoder pe){
        Member mem = new Member();
        mem.setName(mem.getName());
        String password = pe.encode(md.getPassword());
        mem.setPassword(password);
        mem.setRole(Role.USER);

        return mem;
    }
}
