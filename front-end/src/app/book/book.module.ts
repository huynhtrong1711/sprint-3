import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { CartComponent } from './cart/cart.component';
import { DetailComponent } from './detail/detail.component';


@NgModule({
  declarations: [CartComponent, DetailComponent],
  imports: [
    CommonModule,
    BookRoutingModule
  ]
})
export class BookModule { }
