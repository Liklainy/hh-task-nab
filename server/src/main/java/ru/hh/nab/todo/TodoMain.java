package ru.hh.nab.todo;

import ru.hh.nab.starter.NabApplication;

public class TodoMain {
  public static void main(String[] args) {
    NabApplication.builder()
      .configureJersey(TodoJerseyConfig.class)
      .bindToRoot()
      .build()
      .run(TodoConfig.class);
  }
}
