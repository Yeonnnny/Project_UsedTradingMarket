package net.kdigital.market.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    /**
     * 카테고리 별 게시글 제목에 검색어가 포함된 BoardEntity 찾는 쿼리
     * @param category
     * @param searchWord
     * @param sort
     * @return
     */
    List<BoardEntity> findByCategoryAndTitleContaining(String category, String searchWord, Sort sort);
    
    /**
     * 전체 게시글 제목에 검색어가 포함된 BoardEntity 찾는 쿼리
     * @param searchWord
     * @param sort
     * @return
     */
    List<BoardEntity> findByTitleContaining(String searchWord, Sort sort);

}
