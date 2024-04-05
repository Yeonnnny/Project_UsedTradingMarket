package net.kdigital.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.dto.SoldoutEnum;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.MemEntity;
import net.kdigital.market.repository.BoardRepository;
import net.kdigital.market.repository.MemRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;
    private final MemRepository memRepository;

    /**
     * 최신 날짜순으로 현재 판매중(soldout=false)인 상품들을 리스트로 반환하는 함수
     * 
     * @return
     */
    public List<BoardDTO> selectAll() {
        List<BoardEntity> entiyList = repository.findAll(Sort.by(Direction.DESC, "inputDate"));

        List<BoardDTO> dtoList = new ArrayList<>();

        entiyList.forEach((entity) -> {
            // 판매 가능한 상품만 반환 리스트에 담기
            if (entity.getSoldout() == SoldoutEnum.N) {
                dtoList.add(
                        BoardDTO.toDTO(entity, entity.getMemEntity().getMemId()));
            }
        });

        return dtoList;
    }

    /**
     * 전달받은 dto를 entity로 변경하여 DB에 등록하는 함수
     * 
     * @param boardDTO
     */
    public void insert(BoardDTO boardDTO) {
        // soldout 값 넣기
        boardDTO.setSoldout(SoldoutEnum.N);

        Optional<MemEntity> memEntity = memRepository.findById(boardDTO.getMemId());
        if (memEntity.isPresent()) {
            MemEntity mem = memEntity.get();
            BoardEntity boardEntity = BoardEntity.toEntity(boardDTO, mem);

            repository.save(boardEntity);
        }
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
            return BoardDTO.toDTO(boardEntity, boardEntity.getMemEntity().getMemId());
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
    @Transactional
    public void purchase(Long boardNum, String buyerId) {
        log.info("==== 구매 요청 in service");

        Optional<BoardEntity> entity = repository.findById(boardNum);

        if (entity.isPresent()) {
            BoardEntity boardEntity = entity.get();
            // 1) 상품 soldout 값 변경
            boardEntity.setSoldout(SoldoutEnum.Y);
            // 2) 상품 구매 회원 아이디 등록
            boardEntity.setBuyerId(buyerId);
            log.info("변경 후 : {}", boardEntity.getSoldout());
        }
    }

}
