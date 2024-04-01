package net.kdigital.market.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter; 
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.entity.CommentEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentDTO {
    private Long commentNum;
    private Long boardNum;
    private String memId;
    private String commentText;
    private LocalDateTime inputDate;

    public CommentDTO(Long boardNum, String memId, String commentText) {
        this.boardNum = boardNum;
        this.memId = memId;
        this.commentText = commentText;
    }

    public static CommentDTO toDTO(CommentEntity commentEntity, Long boardNum, String memId) {
        return CommentDTO.builder()
                .commentNum(commentEntity.getCommentNum())
                .boardNum(boardNum)
                .memId(memId)
                .commentText(commentEntity.getCommentText())
                .inputDate(commentEntity.getInputDate())
                .build();
    }

}
