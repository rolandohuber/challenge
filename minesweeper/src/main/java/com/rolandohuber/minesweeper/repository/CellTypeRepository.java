package com.rolandohuber.minesweeper.repository;

import com.rolandohuber.minesweeper.entity.CellType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellTypeRepository extends JpaRepository<CellType, Long> {
}