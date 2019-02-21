package ru.hh.nab.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import org.springframework.context.annotation.Import;
import ru.hh.nab.testbase.NabTestConfig;

@Configuration
@Import(NabTestConfig.class)
public class TodoTestConfig {

  @Bean
  Function<String, String> serverPortAwareBean(String jettyBaseUrl) {
    return path -> jettyBaseUrl + path;
  }
}
