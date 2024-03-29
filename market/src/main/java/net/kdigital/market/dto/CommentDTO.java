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
    private String commentNum;
    private Long boardNum;
    private String memId;
    private String commentText;
    private  LocalDateTime inputDate;

    public static CommentDTO toEntity (CommentEntity commentEntity, Long boardNum, String memId){
        return CommentDTO.builder()
                            .commentNum(commentEntity.getCommentNum())
                            .boardNum(boardNum)
                            .memId(memId)
                            .commentText(commentEntity.getCommentText())
                            .inputDate(commentEntity.getInputDate())
                            .build();
    }

}
