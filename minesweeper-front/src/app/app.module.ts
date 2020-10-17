import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CellComponent} from './cell/cell.component';
import {BoardComponent} from './board/board.component';
import {MinesweeperComponent} from './minesweeper/minesweeper.component';
import {GameService} from "./service/game.service";
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    CellComponent,
    BoardComponent,
    MinesweeperComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
