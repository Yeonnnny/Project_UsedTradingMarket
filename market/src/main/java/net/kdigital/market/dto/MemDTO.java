package net.kdigital.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.entity.MemEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemDTO {
    private String memId;
    private String memPw;
    private String memName;
    private String phone;
    private boolean enabled;
    private String rolename;

    public static MemDTO toDTO(MemEntity memEntity){
        return MemDTO.builder()
                        .memId(memEntity.getMemId())
                        .memPw(memEntity.getMemPw())
                        .memName(memEntity.getMemName())
                        .phone(memEntity.getPhone())
                        .enabled(memEntity.isEnabled())
                        .rolename(memEntity.getRolename())
                        .build();
    }

}
