package org.zerock.controller.domain.board;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.zerock.controller.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name="tbl_member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userid;

    private String userpw;
    private String username;

}
