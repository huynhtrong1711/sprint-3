import {Category} from './category';

export interface Book {
  id? : number,
  name? : string,
  img? : string,
  author? : string,
  category? : Category
}
