package net.kdigital.market.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.dto.CommentDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "market_comment")
public class CommentEntity {

    @SequenceGenerator(name = "market_comment_seq", sequenceName = "market_comment_seq", allocationSize = 1, initialValue = 1)
    @Id
    @Column(name = "comment_num")
    @GeneratedValue(generator = "market_comment_seq")
    private Long commentNum;

    // FK
    @ManyToOne
    @JoinColumn(name = "board_num")
    private BoardEntity boardEntity;

    // FK
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemEntity memEntity;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "input_date")
    @CreationTimestamp
    private LocalDateTime inputDate;

    public static CommentEntity toEntity(CommentDTO commentDTO, BoardEntity boardEntity, MemEntity memEntity) {
        return CommentEntity.builder()
                .commentNum(commentDTO.getCommentNum())
                .boardEntity(boardEntity)
                .memEntity(memEntity)
                .commentText(commentDTO.getCommentText())
                .inputDate(commentDTO.getInputDate())
                .build();
    }

}
