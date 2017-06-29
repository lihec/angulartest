import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
// import {HeroManagerRoutingModule} from "./heroes/hero-manager-routing.module";
import {HeroManagerModule} from "./heroes/hero-manager.module";


import { AppComponent } from './app.component';
// import { HeroManagerComponent } from './heroes/hero-manager.component';
import { AppRoutingModule }     from './app-routing.module';


@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    HeroManagerModule,
    // HeroManagerRoutingModule
  ],
  declarations: [
    AppComponent,
    // HeroManagerComponent,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
