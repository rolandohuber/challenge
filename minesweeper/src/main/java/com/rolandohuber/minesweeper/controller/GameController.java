package com.rolandohuber.minesweeper.controller;

import com.rolandohuber.minesweeper.dto.CellDto;
import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.service.CellService;
import com.rolandohuber.minesweeper.service.GameService;
import com.rolandohuber.minesweeper.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final Mapper mapper;
    private final CellService cellService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Game game) {
        this.gameService.create(game);
        return ResponseEntity.ok(mapper.mapGameToGameDto(game));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Game game) {
        this.gameService.update(game);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(this.gameService.list().stream().map(game -> mapper.mapGameToGameDto(game)));
    }

    @PostMapping("/cell/markAsFlag")
    public ResponseEntity markAsFlag(@RequestBody CellDto cell) {
        try {
            cellService.markAsFlag(cell);
            return ResponseEntity.ok(mapper.mapGameToGameDto(gameService.read(cell.getGame())));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cell/discover")
    public ResponseEntity discover(@RequestBody CellDto cell) {
        try {
            return ResponseEntity.ok(mapper.mapGameToGameDto(cellService.discover(cell)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
