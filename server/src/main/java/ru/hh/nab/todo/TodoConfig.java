package ru.hh.nab.todo;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.hibernate.transaction.DataSourceContextTransactionManager;
import ru.hh.nab.starter.NabProdConfig;

@Configuration
@Import({
    NabProdConfig.class,
    NabHibernateProdConfig.class
})
public class TodoConfig {

    @Bean
    MappingConfig mappingConfig() {
        MappingConfig mc = new MappingConfig();
        mc.addEntityClass(Todo.class);
        return mc;
    }

    @Bean
    DataSource dataSource(DataSourceFactory dsFactory, FileSettings fileSettings) {
        return dsFactory.create("dataSource", false, fileSettings);
    }

    @Bean
    TodoDao todoDao(SessionFactory sessionFactory, DataSourceContextTransactionManager transactionManager) {
        return new TodoDao(sessionFactory, transactionManager);
    }

    @Bean
    TodoService todoService(TodoDao todoDao) {
        return new TodoService(todoDao);
    }

}
