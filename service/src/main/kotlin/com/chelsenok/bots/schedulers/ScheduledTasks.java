package com.chelsenok.bots.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        // TODO: 03/08/17 do something
    }
}