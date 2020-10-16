package com.rolandohuber.minesweeper.controller;

import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.service.GameService;
import com.rolandohuber.minesweeper.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private GameService gameService;
    private Mapper mapper;

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
}
