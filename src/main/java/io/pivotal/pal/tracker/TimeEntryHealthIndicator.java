package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    private final int max_entries = 5;
    private static TimeEntryRepository timeEntryRepository;

    public TimeEntryHealthIndicator (TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @Override
    public Health health() {

        Health.Builder builder = new Health.Builder();

        if(timeEntryRepository.list().size() < max_entries){
            builder.up();
        }
        else{
            builder.down();
        }
        return builder.build();
    }
}
