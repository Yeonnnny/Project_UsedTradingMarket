package net.kdigital.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.dto.MemDTO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "market_member")
public class MemEntity {
    
    @Id
    @Column(name = "member_id")
    private String memId;

    @Column(name = "member_pw", nullable = false)
    private String memPw;

    @Column(name = "member_name", nullable = false)
    private String memName;

    @Column(name = "phone", nullable = false)
    private String phone;

    private boolean enabled;

    private String rolename;

    // 자식
    @OneToMany(mappedBy = "memEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("board_num")
    List<BoardEntity> boardEntity = new ArrayList<>();
    
    @OneToMany(mappedBy = "memEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("comment_num")
    List<CommentEntity> commentEntity = new ArrayList<>();

    public static MemEntity toEntity(MemDTO memDTO){
        return MemEntity.builder()
                        .memId(memDTO.getMemId())
                        .memPw(memDTO.getMemPw())
                        .memName(memDTO.getMemName())
                        .phone(memDTO.getPhone())
                        .enabled(memDTO.isEnabled())
                        .rolename(memDTO.getRolename())
                        .build();
    }
}
