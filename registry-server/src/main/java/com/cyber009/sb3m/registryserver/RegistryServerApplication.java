package com.cyber009.sb3m.registryserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.stream.StreamSupport;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class RegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryServerApplication.class, args);
	}

    @Bean
    public CommandLineRunner printEnvironment(Environment env) {
        return args -> {
            log.info("==================== Environment Properties ====================");

            // Log active profiles
            String[] activeProfiles = env.getActiveProfiles();
            log.info("Active Profiles: {}", Arrays.toString(activeProfiles));

            // Log default profiles
            String[] defaultProfiles = env.getDefaultProfiles();
            log.info("Default Profiles: {}", Arrays.toString(defaultProfiles));

            log.info("===============================================================");
            if (env instanceof ConfigurableEnvironment configurableEnv) {
                StreamSupport.stream(configurableEnv.getPropertySources().spliterator(), false)
                        .filter(ps -> ps instanceof EnumerablePropertySource<?>)
                        .map(ps -> (EnumerablePropertySource<?>) ps)
                        .forEach(ps -> {
                            log.info("PropertySource: {}", ps.getName());
                            Arrays.stream(ps.getPropertyNames())
                                    .sorted()
                                    .forEach(key -> {
                                        try {
                                            String value = env.getProperty(key);
                                            // Mask sensitive properties
                                            if (isSensitiveKey(key)) {
                                                log.info("SECRET:  {} = {}", key, value);
                                            } else {
                                                log.info("  {} = {}", key, value);
                                            }
                                        } catch (Exception e) {
                                            log.warn("  {} = <error reading value>", key);
                                        }
                                    });
                        });
            }

            log.info("===============================================================");

        };
    }

    private boolean isSensitiveKey(String key) {
        String lowerKey = key.toLowerCase();
        return lowerKey.contains("password") ||
                lowerKey.contains("secret") ||
                lowerKey.contains("token") ||
                lowerKey.contains("key") ||
                lowerKey.contains("credential") ||
                lowerKey.contains("auth");
    }
}
