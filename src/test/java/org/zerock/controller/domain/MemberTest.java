package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MemberTest {
/*    @Autowired
    MemberRepo memberRepo;

    @Autowired
    TeamRepo teamRepo;

//    @Before
//    @Transactional
//    public void setup() {
//        Team team = new Team();
//        team.setName("team-1");
//        team = teamRepo.save(team);
//
//        Team team2 = new Team();
//        team2.setName("team-2");
//        team2 = teamRepo.save(team2);
//
//        Member member = new Member();
//        member.setName("member-1");
//        member.setTeam(team);
//        memberRepo.save(member);
//
//        Member member1 = new Member();
//        member1.setName("member-2");
//        member1.setTeam(team2);
//        memberRepo.save(member1);
//    }

    @Before
    @Transactional
    public void setup1(){
        Team team=teamRepo.save(
                Team.builder()
                .name("team-1")
                .build()
        );

        Team team2=teamRepo.save(
                Team.builder()
                        .name("team-2")
                        .build()
        );



        Member member=memberRepo.save(
            Member.builder()
                    .name("member-1")
                    .team(team)
                    .build()
        );


        Member member2=memberRepo.save(
                Member.builder()
                        .name("member-2")
                        .team(team2)
                        .build()
        );

    }

    @Test
    public void joinTest() {
        List<Member> all = memberRepo.findAll();
        for (Member member1 : all) {
            System.out.println(member1.getTeam().getName());
        }
    }*/
}

