package net.kdigital.market.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * 전달받은 dto를 entity로 변환하여 DB에 저장
     * 
     * @param memDTO
     */
    public void insert(MemDTO memDTO) {
        MemEntity entity = MemEntity.toEntity(memDTO);
        repository.save(entity);

    }

}
