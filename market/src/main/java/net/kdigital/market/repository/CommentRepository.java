package net.kdigital.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
