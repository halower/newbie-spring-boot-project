/*
 * Apache License
 *
 * Copyright (c) 2019  halower (halower@foxmail.com).
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springframework.cloud.alibaba.dubbo.autoconfigure.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.cloud.alibaba.dubbo.registry.SpringCloudRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.apache.dubbo.config.spring.util.PropertySourcesUtils.getSubProperties;
import static org.springframework.cloud.alibaba.dubbo.registry.SpringCloudRegistryFactory.PROTOCOL;

/**
 * Missing {@link SpringCloudRegistry} Property {@link Condition}
 *
 * @see SpringCloudRegistry
 * @see Condition
 */
public class MissingSpringCloudRegistryConfigPropertyCondition extends SpringBootCondition {


    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableEnvironment environment = (ConfigurableEnvironment) context.getEnvironment();

        String protocol = environment.getProperty("services.registry.protocol");

        if (PROTOCOL.equals(protocol)) {
            return ConditionOutcome.noMatch("'spring-cloud' protocol was found from 'services.registry.protocol'");
        }

        String address = environment.getProperty("services.registry.address");

        if (StringUtils.startsWithIgnoreCase(address, PROTOCOL)) {
            return ConditionOutcome.noMatch("'spring-cloud' protocol was found from 'services.registry.address'");
        }

        Map<String, Object> properties = getSubProperties(environment, "services.registries.");

        boolean found = properties.entrySet().stream().anyMatch(entry -> {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            return (key.endsWith(".address") && value.startsWith(PROTOCOL)) ||
                    (key.endsWith(".protocol") && PROTOCOL.equals(value));

        });

        return found ? ConditionOutcome.noMatch("'spring-cloud' protocol was found in 'services.registries.*'") : ConditionOutcome.match();
    }
}
