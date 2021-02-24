package org.zerock.controller.domain.board;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.zerock.controller.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@Table(name = "tbl_board")
@DynamicUpdate
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String writer;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Reply> replies=new ArrayList<>();

    @Builder
    public Board(String title, String content, String writer){
        this.title=title;
        this.content=content;
        this.writer=writer;
    }

}
