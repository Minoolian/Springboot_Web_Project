package org.zerock.controller.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.controller.domain.board.Reply;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;
    private Long bno;
    private String reply;
    private String replyer;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public ReplyDTO(Reply reply){
        rno=reply.getRno();
        bno=reply.getBoard().getBno();
        this.reply=reply.getReply();
        replyer=reply.getReplyer();
        createDate=reply.getCreateDate();
        modifiedDate=reply.getModifiedDate();
    }
}
