package com.rebels.rebelsapi.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component("minhaPropriaMetrica")
public class CustomIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean condicao = condicao();
        if(condicao){
            return Health.up().withDetail("Minuto par", 113).build();
        } else return Health.down().withDetail("Minuto ímpar", 114).build();
    }

    private boolean condicao() {
        return LocalDateTime.now().getMinute() % 2 == 0;
    }

}
