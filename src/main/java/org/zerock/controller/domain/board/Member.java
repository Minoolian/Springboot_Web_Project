package org.zerock.controller.domain.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name="tbl_member")
public class Member{

    @Id
    private String userid;

    private String userpw;
    private String username;

    @OneToOne
    @JoinColumn(name="authid")
    private MemberAuth memberAuth;

}
