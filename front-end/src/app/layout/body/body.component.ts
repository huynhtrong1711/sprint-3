import { Component, OnInit } from '@angular/core';
import {Book} from '../../book/model/book';
import {BooksService} from '../../book/books.service';

let WOW;

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  listBooks: Book[];

  constructor(private booksService : BooksService) { }

  ngOnInit(): void {
    this.getAllBook();
    console.log(this.listBooks);
    this.wow()
  }

  wow() {
    new WOW().init();
  }

  getAllBook() {
    this.booksService.getListBook().subscribe(data => {
      this.listBooks = data['content'];
      console.log(data);

    }, error => {
      console.log(error)
    }, () => {
      console.log(this.listBooks)
      console.log("Lấy list 8 tin mới nhất thành công")
      console.log("image: " + this.listBooks[0].img)
    })
  }
}
