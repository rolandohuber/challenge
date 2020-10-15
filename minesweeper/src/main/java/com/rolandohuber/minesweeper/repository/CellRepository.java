package com.rolandohuber.minesweeper.repository;

import com.rolandohuber.minesweeper.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {
}