package net.kdigital.market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final BoardRepository repository;

    /**
     * 검색 카테고리에 해당하는 게시글들을 리스트로 반환하는 함수
     * 
     * @param category
     * @return
     */
    public List<BoardDTO> selectAllBySearching(String category, String searchWord) {

        List<BoardEntity> entityList = repository.findByCategoryAndTitleContaining(category, searchWord,Sort.by(Sort.Direction.DESC,"boardNum"));

        List<BoardDTO> dtoList = new ArrayList<>();

        entityList.forEach((entity) -> {
            dtoList.add(BoardDTO.toDTO(entity, entity.getMemEntity().getMemId()));
        });

        return dtoList;

    }

    /**
     * 게시글 번호에 해당하는 게시글 반환하는 함수
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
}
