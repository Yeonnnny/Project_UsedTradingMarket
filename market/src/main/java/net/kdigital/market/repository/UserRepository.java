package net.kdigital.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.MemEntity;

public interface UserRepository extends JpaRepository<MemEntity, String> {

}