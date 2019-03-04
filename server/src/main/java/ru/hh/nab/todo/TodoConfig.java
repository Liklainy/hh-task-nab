package ru.hh.nab.todo;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.export.MBeanExporter;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

import javax.sql.DataSource;

@Configuration
@Import({
        NabHibernateProdConfig.class,
        NabProdConfig.class
})
public class TodoConfig {

    @Bean
    MappingConfig mappingConfig() {
        MappingConfig mc = new MappingConfig();
        mc.addEntityClass(Todo.class);
        return mc;
    }

    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, fileSettings);
    }

    @Bean
    TodoDao todoDao(SessionFactory sessionFactory) {
        return new TodoDao(sessionFactory);
    }

    @Bean
    TodoService todoService(TodoDao todoDao) {
        return new TodoService(todoDao);
    }

}
