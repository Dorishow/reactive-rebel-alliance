package com.rebels.rebelsapi.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("minhaPropriaMetrica")
public class CustomIndicator implements HealthIndicator {

    @Override
    public Health health() {
            return Health.up().withDetail("true", 113).build();
    }

}
