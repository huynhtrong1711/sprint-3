import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BodyComponent} from './layout/body/body.component';
import {BookRoutingModule} from './book/book-routing.module';


const routes: Routes = [
  {
    path: "", component:BodyComponent
  },
  {
    path:'book',loadChildren: () => import ('./book/book.module').then(module => module.BookModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes), BookRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
