import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Todo } from '../shared/models/todo.model';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  readonly API_URL = '/api';

  constructor(private http: HttpClient) { }

  addTodo(todo: Todo): Observable<Todo> {
    return this.http
      .put<Todo>(this.API_URL + `/todo`, todo)
      .pipe(catchError(this.handleError));
  }

  deleteTodoById(id: number): Observable<Object> {
    return this.http
      .delete(this.API_URL + `/todo/${id}`)
      .pipe(catchError(this.handleError));
  }

  updateTodo(todo: Todo): Observable<Todo> {
    return this.http
      .post<Todo>(this.API_URL + `/todo/${todo.id}`, todo)
      .pipe(catchError(this.handleError));
  }

  getAllTodos(): Observable<Todo[]> {
    return this.http
      .get<Todo[]>(this.API_URL + '/todo')
      .pipe(catchError(this.handleError));
  }

  getTodoById(id: number): Observable<Todo> {
    return this.http
      .get<Todo>(this.API_URL + `/todo/${id}`)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
