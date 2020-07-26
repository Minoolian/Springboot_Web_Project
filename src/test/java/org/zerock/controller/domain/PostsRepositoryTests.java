package org.zerock.controller.domain;


import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.posts.Posts;
import org.zerock.controller.domain.posts.PostsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTests {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll(); // 테스트 이후 repo 전체를 비운다.
    }


    public void 게시글저장_불러오기(){

        //given : 테스트 기반환경 구축
        postsRepository.save(Posts.builder()
        .title("테스트게시글")
        .content("테스트본문")
        .author("minoolian")
        .build());

        //when : 테스트할 행위위
        List<Posts> postsList = postsRepository.findAll();


        //then : 테스트 결과 검증
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트게시글"));
        assertThat(posts.getContent(), is("테스트본문"));
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now=LocalDateTime.now();
        postsRepository.save(Posts.builder()
        .title("테스트게시글")
        .content("테스트본문")
        .author("minoolian")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts=postsList.get(0); // 테스트가 여럿일 경우 gradlew 시 게시글 번호를 주의
        assertTrue(posts.getCreateDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
