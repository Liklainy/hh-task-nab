package ru.hh.nab.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.nab.testbase.hibernate.NabHibernateTestBaseConfig;

import java.util.function.Function;

@Configuration
@Import({
        NabTestConfig.class,
        NabHibernateCommonConfig.class,
        NabHibernateTestBaseConfig.class
})
public class TodoTestConfig {

    @Bean
    Function<String, String> serverPortAwareBean(String jettyBaseUrl) {
        return path -> jettyBaseUrl + path;
    }

}
