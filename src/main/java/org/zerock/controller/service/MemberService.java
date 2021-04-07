package org.zerock.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.controller.domain.board.Member;
import org.zerock.controller.domain.board.MemberAuthRepo;
import org.zerock.controller.domain.board.MemberRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private MemberAuthRepo memberAuthRepo;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepo.findById(userid);
        Member memberEntity = memberEntityWrapper.orElse(null);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(memberEntity.getMemberAuth().getAuth()));

        return new User(memberEntity.getUserid(), memberEntity.getUserpw(), authorities);
    }

    public Member save(Member member, Long authid) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setUserpw(passwordEncoder.encode(member.getUserpw()));
        member.setMemberAuth(memberAuthRepo.findById(authid).get());
        return memberRepo.save(member);

    }
}
