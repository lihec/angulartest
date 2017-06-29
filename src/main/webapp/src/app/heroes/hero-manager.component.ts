import { Component } from '@angular/core';
@Component({
  selector: 'hero-manager',
  template: `
    <h1>{{title}}</h1>
    <nav>
      <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
      <a routerLink="/heroes" routerLinkActive="active">Heroes</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./hero-manager.component.css'],
})
export class HeroManagerComponent {
  title = 'Tour of Heroes';
}
