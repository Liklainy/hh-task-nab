package ru.hh.nab.todo;

import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.WebApplicationContext;

import ru.hh.nab.starter.NabApplication;

public class TodoMain {
  public static void main(String[] args) {
    NabApplication.builder()
      .configureWebapp(TodoMain::servletContextHandlerConfigurer)
      .configureJersey(TodoJerseyConfig.class)
      .bindTo("/api/*")
      .build()
      .run(TodoConfig.class);
  }

  public static void servletContextHandlerConfigurer(WebAppContext webContext, WebApplicationContext appContext)
  {
    // Client static resources
    ServletHolder clientHolder = new ServletHolder("client", DefaultServlet.class);
    clientHolder.setInitParameter("resourceBase", "../client/dist/todomvc");
    clientHolder.setInitParameter("dirAllowed", "true");
    clientHolder.setInitParameter("pathInfoOnly", "true");
    webContext.addServlet(clientHolder, "/*");
  }
}
