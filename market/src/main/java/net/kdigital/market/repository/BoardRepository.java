package net.kdigital.market.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.dto.SoldoutEnum;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.MemEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    /**
     * 카테고리 별 게시글 제목에 검색어가 포함된 것 중 soldout 값이 N(판매중)인 BoardEntity를 찾아 리스트로 반환하는 함수
     * 
     * @param category
     * @param searchWord
     * @param soldoutEnum
     * @param sort
     * @return
     */
    List<BoardEntity> findByCategoryAndTitleContainingAndSoldout(String category, String searchWord, SoldoutEnum n,Sort by);

    /**
     * 전체 게시글 제목에 검색어가 포함된 것 중 soldout값이 N(판매중)인 BoardEntity를 찾아 리스트로 반환하는 함수
     * 
     * @param searchWord
     * @param soldoutEnum
     * @param sort
     * @return
     */
    List<BoardEntity> findByTitleContainingAndSoldout(String searchWord, SoldoutEnum n, Sort by);

    /**
     * memberId에 해당하는 BoardEntity를 찾아 리스트로 반환하는 함수
     * 
     * @param memEntity
     * @return
     */
    List<BoardEntity> findAllByMemEntityOrderByBoardNumDesc(MemEntity memEntity);

    /**
     * 입력받은 memId와 buyerId가 일치하는 모든 게시글 리스트 반환
     * 
     * @param memId
     * @return
     */
    List<BoardEntity> findByBuyerId(String memId);

    

    

}
