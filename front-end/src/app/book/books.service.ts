import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from './model/book';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private URL = 'http://localhost:8080/api/book';

  constructor(private httpClient : HttpClient) { }

  getListBook() {
    return this.httpClient.get<Book[]>(this.URL + '/list');
  }
}
