package ru.hh.nab.todo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TodoResource.class)
public class TodoJerseyConfig {
}
