package com.rolandohuber.minesweeper.service;

import com.rolandohuber.minesweeper.dto.CellDto;
import com.rolandohuber.minesweeper.entity.Cell;
import com.rolandohuber.minesweeper.entity.CellType;
import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.repository.CellRepository;
import com.rolandohuber.minesweeper.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CellService {

    @Autowired
    private CellRepository repository;
    @Autowired
    private GameRepository gameRepository;

    public void markAsFlag(CellDto cell) throws Exception {
        Cell entity = repository.findById(cell.getId()).orElseThrow(() -> new Exception());
        entity.setFlag(true);
        repository.save(entity);
    }

    public Game discover(CellDto cell) throws Exception {
        Cell entity = repository.findById(cell.getId()).orElseThrow(() -> new Exception());
        if (CellType.BOMB.equals(entity.getType())) {
            Game game = entity.getGame();
            game.setFinished(true);
            gameRepository.save(game);
            throw new Exception("BUMMMMMM!!!");
        } else {
            entity.setDiscovered(true);
            repository.save(entity);

            Cell[][] board = new Cell[entity.getGame().getWidth()][entity.getGame().getHeight()];

            entity.getGame().getCells().forEach(c -> {
                if (!c.getType().equals(CellType.BOMB) && !c.getDiscovered())
                    board[c.getPositionX()][c.getPositionY()] = c;
            });
            discoverEmptyCells(entity, new HashMap<>(), board);
        }
        return gameRepository.findById(entity.getGame().getId()).orElseThrow(() -> new Exception());
    }

    private void discoverEmptyCells(Cell entity, Map<String, Boolean> cache, Cell[][] board) {
        int xi = entity.getPositionX();
        int yi = entity.getPositionY();

        if (xi < 0 || xi > entity.getGame().getWidth())
            return;
        if (yi < 0 || yi > entity.getGame().getHeight())
            return;


        if ((yi + 1) > -1
                && (yi + 1) < entity.getGame().getHeight()
                && cache.get(xi + "_" + (yi + 1)) == null
                && board[xi][yi + 1] != null
                && board[xi][yi + 1].getValue() == 0
        ) {
            Cell aux = board[xi][yi + 1];
            aux.setDiscovered(true);
            repository.save(aux);
            cache.put(xi + "_" + (yi + 1), true);
            board[xi][yi + 1] = null;
            discoverEmptyCells(aux, cache, board);
        }

        if ((yi - 1) > -1
                && (yi - 1) < entity.getGame().getHeight()
                && cache.get(xi + "_" + (yi - 1)) == null
                && board[xi][yi - 1] != null
                && board[xi][yi - 1].getValue() == 0
        ) {
            Cell aux = board[xi][yi - 1];
            aux.setDiscovered(true);
            repository.save(aux);
            cache.put(xi + "_" + (yi - 1), true);
            discoverEmptyCells(aux, cache, board);
        }

        if ((xi + 1) > -1
                && (xi + 1) < entity.getGame().getWidth()
                && cache.get((xi + 1) + "_" + yi) == null
                && board[xi + 1][yi] != null
                && board[xi + 1][yi].getValue() == 0
        ) {
            Cell aux = board[xi + 1][yi];
            aux.setDiscovered(true);
            repository.save(aux);
            cache.put((xi + 1) + "_" + yi, true);
            discoverEmptyCells(aux, cache, board);
        }

        if ((xi - 1) > -1 && (xi - 1) < entity.getGame().getWidth()
                && cache.get((xi - 1) + "_" + yi) == null
                && board[xi - 1][yi] != null
                && board[xi - 1][yi].getValue() == 0
        ) {
            Cell aux = board[xi - 1][yi];
            aux.setDiscovered(true);
            repository.save(aux);
            cache.put((xi - 1) + "_" + yi, true);
            discoverEmptyCells(aux, cache, board);
        }
    }
}
