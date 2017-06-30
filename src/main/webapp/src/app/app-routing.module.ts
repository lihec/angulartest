import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroManagerComponent }  from './heroes/hero-manager.component';
const routes: Routes = [
  // { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'heroManager',     component: HeroManagerComponent },
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}