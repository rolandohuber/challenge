import {Cell} from "./cell";

export interface Game {
  id: number;
  name: string;
  date: Date;
  timer: number;
  flags: boolean;
  bombs: number;
  width: number;
  height: number;
  finished: boolean;
  cells: Cell[][];
}
