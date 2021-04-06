package org.zerock.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.controller.domain.board.Member;
import org.zerock.controller.domain.board.MemberRepo;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepo.
    }

    public int save(Member member) {

    }
}
