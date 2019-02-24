import { Component, OnInit } from '@angular/core';
import { TodoService } from './services/todo.service';
import { Todo } from './shared/models/todo.model';

@Component({
  selector: 'td-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  todos: Todo[] = [];
  newTodo: Todo = new Todo();

  constructor(private todoService: TodoService) {
  }

  ngOnInit() {
    this.todoService.getAllTodos()
      .subscribe(x => this.todos = x);
  }

  addTodo() {
    this.todoService.addTodo(this.newTodo)
      .subscribe(x => {
        this.newTodo = new Todo();
        this.todos.push(x);
      });
  }

  toggleTodoComplete(todo: Todo) {
    let updatedTodo: Todo = Object.assign({}, todo);
    updatedTodo.complete = !updatedTodo.complete;
    this.todoService.updateTodo(updatedTodo)
      .subscribe(resultTodo => {
        Object.assign(todo, resultTodo);
      });
  }

  removeTodo(todo: Todo) {
    this.todoService.deleteTodoById(todo.id)
      .subscribe(() => {
        const index: number = this.todos.indexOf(todo);
        if (index > -1)
          this.todos.splice(index, 1);
      })
  }
}
