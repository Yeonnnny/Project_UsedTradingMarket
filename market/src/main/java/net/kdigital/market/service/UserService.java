package net.kdigital.market.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.MemRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 전달받은 dto를 entity로 변환하여 DB에 저장
     * 
     * @param memDTO
     */
    public boolean insert(MemDTO memDTO) {
        boolean isExistUser = repository.existsById(memDTO.getMemId());

        // 이미 존재하는 아이디는 가입 못하도록
        if (isExistUser)
            return false;

        // 비밀번호 암호화
        memDTO.setMemPw(bCryptPasswordEncoder.encode(memDTO.getMemPw()));

        // dto -> entity
        MemEntity entity = MemEntity.toEntity(memDTO);

        // DB에 저장
        repository.save(entity);

        return true;

    }

}
