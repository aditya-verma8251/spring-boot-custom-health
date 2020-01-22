package com.aditya.springbootactuator.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthIndicators implements HealthIndicator {

    public static final Logger LOGGER = LoggerFactory.getLogger(HealthIndicator.class);

    public static int COUNTER = 0;

    @Override
    public Health getHealth(boolean includeDetails) {
        return health();
    }

    @Override
    public Health health() {
        if(COUNTER % 2 == 0){
            LOGGER.info("Counter - {}, service considered as available", COUNTER);
            COUNTER++;
            return Health.up().withDetail("Counter", COUNTER).build();
        }
        LOGGER.error("Counter - {}, service considered as unavailable", COUNTER);
        COUNTER++;
        return Health.down().withDetail("Counter", COUNTER).build();
    }
}
