package net.kdigital.market.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.MemEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    /**
     * 카테고리 별 게시글 제목에 검색어가 포함된 BoardEntity를 찾아 리스트로 반환하는 함수
     * @param category
     * @param searchWord
     * @param sort
     * @return
     */
    List<BoardEntity> findByCategoryAndTitleContaining(String category, String searchWord, Sort sort);
    
    /**
     * 전체 게시글 제목에 검색어가 포함된 BoardEntity를 찾아 리스트로 반환하는 함수
     * @param searchWord
     * @param sort
     * @return
     */
    List<BoardEntity> findByTitleContaining(String searchWord, Sort sort);

    /**
     * memberId에 해당하는 BoardEntity를 찾아 리스트로 반환하는 함수
     * @param memEntity
     * @return
     */
    List<BoardEntity> findAllByMemEntityOrderByBoardNumDesc(MemEntity memEntity);

}
