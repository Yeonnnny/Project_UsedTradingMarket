package net.kdigital.market.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import net.kdigital.market.dto.BoardDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="market_board")
public class BoardEntity {

    @SequenceGenerator(
        name = "market_board_seq",
        sequenceName  = "market_board_seq",
        allocationSize = 1,
        initialValue = 1
    )
    @Id
    @Column(name = "board_num")
    @GeneratedValue(generator = "market_board_seq")
    private Long boardNum;
    
    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemEntity memEntity;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "contents", nullable = false)
    private String contents;
    
    @Column(name = "input_date")
    @CreationTimestamp
    private LocalDateTime inputDate;
    
    private String category;
    
    private boolean soldout;
    
    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private MemEntity buyerEntity;

    public static BoardEntity toEntity(BoardDTO boardDTO, MemEntity memEntity){
        return BoardEntity.builder()
                            .boardNum(boardDTO.getBoardNum())
                            .memEntity(memEntity)
                            .title(boardDTO.getTitle())
                            .contents(boardDTO.getContents())
                            .inputDate(boardDTO.getInputDate())
                            .category(boardDTO.getCategory())
                            .soldout(boardDTO.isSoldout())
                            .buyerEntity(memEntity)
                            .build();
    }

    

}
