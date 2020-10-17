import {Component, Input, OnInit} from '@angular/core';
import {Game} from "../model/game";
import {Cell} from "../model/cell";

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent {
  @Input() game: Game;

  constructor() {
  }

  clickCell(cell: Cell) {
  }

  markCell(cell: Cell) {
  }

}
