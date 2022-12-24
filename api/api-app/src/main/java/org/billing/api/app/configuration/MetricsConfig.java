package org.billing.api.app.configuration;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MetricsConfig {
    @Bean
    public MeterFilter myMeterFilter() {
        return new MeterFilter() {
            private static final String TAG_TO_REPLACE = "name";
            private static final String NEW_TAG_NAME = "beanName";
            @Override
            public Meter.Id map(Meter.Id id) {
                    List<Tag> tags = new ArrayList<>();
                    for (Tag tag : id.getTagsAsIterable()) {
                        if (tag.getKey().equals(TAG_TO_REPLACE))
                            tags.add(Tag.of(NEW_TAG_NAME, tag.getValue()));
                        else
                            tags.add(tag);
                    }

                    return id.replaceTags(tags);
            }
        };
    }
}

