import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartComponent} from './cart/cart.component';
import {DetailComponent} from './detail/detail.component';


const routes: Routes = [
  {
    path: "cart", component : CartComponent
  },
  {
    path:"detail", component: DetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
