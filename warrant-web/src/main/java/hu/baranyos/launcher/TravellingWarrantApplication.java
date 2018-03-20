package hu.baranyos.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("hu.baranyos")
@EnableJpaRepositories("hu.baranyos")
@EntityScan("hu.baranyos")
public class TravellingWarrantApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TravellingWarrantApplication.class, args);
    }

/*
 * @SpringBootApplication public class TrafficWarrantApplication extends
 * SpringBootServletInitializer {
 * @Override protected SpringApplicationBuilder configure(final SpringApplicationBuilder
 * application) { return application.sources(TrafficWarrantApplication.class); } public static void
 * main(final String[] args) { SpringApplication.run(TrafficWarrantApplication.class, args); }
 */
}
