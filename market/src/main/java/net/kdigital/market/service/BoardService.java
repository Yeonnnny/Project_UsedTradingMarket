package net.kdigital.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.BoardRepository;
import net.kdigital.market.repository.MemRepository;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;
    private final MemRepository memRepository;

    public List<BoardDTO> selectAll() {
        List<BoardEntity> entiyList = repository.findAll();

        List<BoardDTO> dtoList = new ArrayList<>();

        entiyList.forEach((entity) -> {
            dtoList.add(BoardDTO.toDTO(entity, entity.getMemEntity().getMemId(), entity.getBuyerEntity().getMemId()));
        });

        return dtoList;
    }

    /**
     * 전달받은 dto를 entity로 변경하여 DB에 등록하는 함수
     * 
     * @param boardDTO
     */
    public void insert(BoardDTO boardDTO) {
        BoardEntity entity = BoardEntity.toEntity(boardDTO, null);
        repository.save(entity);
    }

    /**
     * 전달받은 boardNum에 대한 dto반환하는 함수
     * 
     * @param boardNum
     * @return
     */
    public BoardDTO selectOne(Long boardNum) {

        Optional<BoardEntity> entity = repository.findById(boardNum);

        if (entity.isPresent()) {
            BoardEntity boardEntity = entity.get();
            return BoardDTO.toDTO(boardEntity, boardEntity.getMemEntity().getMemId(),
                    boardEntity.getBuyerEntity().getMemId());
        }

        return null;
    }

    /**
     * 전달받은 boardNum에 해당하는 게시글 삭제
     * 
     * @param boardNum
     */
    public void delete(Long boardNum) {
        repository.deleteById(boardNum);
    }

    // 근데 진짜 페이지에서는 여러명의 사용자가 로그인을 한 상태이기도하고
    // 해당 페이지를 여러명이 보고 있다면.. 어쩧게 처리하지?
    /**
     * 전달받은 boardNum에 해당하는 게시글의 판매여부 변경하고 구매아이디 등록하는 함수
     * 
     * @param boardNum
     * @param loginId
     */
    public void purchase(Long boardNum, String loginId) {
        Optional<BoardEntity> entity = repository.findById(boardNum);

        if (entity.isPresent()) {
            BoardEntity boardEntity = entity.get();
            // 1) 상품 soldout 값 변경
            boardEntity.setSoldout(true);
            // 2) 상품 구매 회원 정보 등록
            Optional<MemEntity> memEntity = memRepository.findById(loginId);
            if (memEntity.isPresent()) {
                MemEntity mem = memEntity.get();
                boardEntity.setBuyerEntity(mem);
            }
        }
    }

}
