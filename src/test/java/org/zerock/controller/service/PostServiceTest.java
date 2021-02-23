package org.zerock.controller.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.posts.Posts;
import org.zerock.controller.domain.posts.PostsRepository;
import org.zerock.controller.dto.posts.PostsSaveRequestDto;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void Dto_Data_to_Entity(){

        PostsSaveRequestDto dto=PostsSaveRequestDto.builder()
                .author("minoolian")
                .content("Test")
                .title("Test Title")
                .build();

        postsService.save(dto);

        Posts posts=postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
