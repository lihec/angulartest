import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HeroManagerComponent }         from './hero-manager.component';
import { DashboardComponent }   from './dashboard.component';
import { HeroDetailComponent }  from './hero-detail.component';
import { HeroesComponent }      from './heroes.component';
import { HeroService }          from './hero.service';
import { HeroManagerRoutingModule }     from './hero-manager-routing.module';
@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HeroManagerRoutingModule
  ],
  declarations: [
    HeroManagerComponent,
    DashboardComponent,
    HeroDetailComponent,
    HeroesComponent
  ],
  providers: [ HeroService ],
})
export class HeroManagerModule { }
