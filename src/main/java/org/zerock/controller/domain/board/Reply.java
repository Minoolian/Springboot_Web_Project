package org.zerock.controller.domain.board;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.zerock.controller.domain.BaseTimeEntity;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="tbl_reply")
@Getter @Setter
@DynamicUpdate
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne
    @JoinColumn(name="bno")
    private Board board;

    private String reply;
    private String replyer;

    @Builder
    public Reply(String reply, String replyer){
        this.reply=reply;
        this.replyer=replyer;
    }

}
