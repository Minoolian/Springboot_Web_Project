package org.zerock.controller.domain.board;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.zerock.controller.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @Builder
    public Member(String name, Team team){
        this.name=name;
        this.team=team;
        team.getMembers().add(this);
    }
}
