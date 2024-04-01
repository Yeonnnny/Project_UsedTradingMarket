package net.kdigital.market.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findByCategoryAndTitleContaining(String category, String searchWord, Sort sort);

}
