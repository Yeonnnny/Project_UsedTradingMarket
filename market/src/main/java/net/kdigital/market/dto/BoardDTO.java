package net.kdigital.market.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.entity.BoardEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDTO {

    private Long boardNum;
    private String memId;
    private String title;
    private String contents;
    private LocalDateTime inputDate;
    private String category;
    private boolean soldout;
    private String buyerId;

    public static BoardDTO toDTO(BoardEntity boardEntity, String memId, String buyerId){
        return BoardDTO.builder()
                        .boardNum(boardEntity.getBoardNum())
                        .memId(memId)
                        .title(boardEntity.getTitle())
                        .contents(boardEntity.getContents())
                        .inputDate(boardEntity.getInputDate())
                        .category(boardEntity.getCategory())
                        .soldout(boardEntity.isSoldout())
                        .buyerId(buyerId)
                        .build();
    }
}
