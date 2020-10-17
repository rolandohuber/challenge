import {Component, OnInit} from '@angular/core';
import {GameService} from "../service/game.service";
import {Game} from "../model/game";

@Component({
  selector: 'app-minesweeper',
  templateUrl: './minesweeper.component.html',
  styleUrls: ['./minesweeper.component.scss']
})
export class MinesweeperComponent implements OnInit {

  games: Game[];

  constructor(private service: GameService) {
  }

  ngOnInit(): void {
    this.service.games().subscribe((res) => {
      this.games = res;
    })
  }

}
