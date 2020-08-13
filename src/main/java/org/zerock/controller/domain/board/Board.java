package org.zerock.controller.domain.board;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.zerock.controller.domain.BaseTimeEntity;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_board")
@DynamicUpdate
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bno;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String writer;

    @Builder
    public Board(String title, String content, String writer){
        this.title=title;
        this.content=content;
        this.writer=writer;
    }

    public void updateBoard(Board board){
        this.title=board.getTitle();
        this.content=board.getContent();
    }

}
