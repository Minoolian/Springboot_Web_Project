package org.zerock.controller.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.zerock.controller.domain.board.Reply;

import java.util.List;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
    private Long replyCnt;
    private List<Reply> list;
}
