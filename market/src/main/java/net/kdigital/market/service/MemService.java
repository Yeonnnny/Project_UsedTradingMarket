package net.kdigital.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.BoardRepository;
import net.kdigital.market.repository.MemRepository;

@Service
@RequiredArgsConstructor
public class MemService {
    private final MemRepository repository;
    private final BoardRepository boardRepository;
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

        // enabled(계정상태) true(사용가능)로 변경
        memDTO.setEnabled(true);

        // 비밀번호 암호화
        memDTO.setMemPw(bCryptPasswordEncoder.encode(memDTO.getMemPw()));

        // dto -> entity
        MemEntity entity = MemEntity.toEntity(memDTO);

        // DB에 저장
        repository.save(entity);

        return true;

    }

    /**
     * 전달 받은 멤버 아이디에 해당하는 회원이 판매중인 상품을 리스트로 반환하는 함수
     * @param memId
     * @return
     */
    public List<BoardDTO> myBoardList(String memId) {
        Optional<MemEntity> entity = repository.findById(memId);
        List<BoardDTO> boardDTOList = new ArrayList<>();

        if (entity.isPresent()) {
            MemEntity memEntity= entity.get();
            List<BoardEntity> boardEntityList = boardRepository.findAllByMemEntityOrderByBoardNumDesc(memEntity);
            
            boardEntityList.forEach((e)->{
                boardDTOList.add(BoardDTO.toDTO(e, memId));
            });

        }

        return boardDTOList;
    }

}
