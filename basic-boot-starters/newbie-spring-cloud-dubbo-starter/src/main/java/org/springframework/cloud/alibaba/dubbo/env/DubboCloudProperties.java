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

package org.springframework.cloud.alibaba.dubbo.env;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.util.StringUtils.*;

/**
 * Dubbo Cloud {@link ConfigurationProperties Properties}
 *
 * @Author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 */
@ConfigurationProperties(prefix = "services.cloud")
public class DubboCloudProperties {

    /**
     * All services of Dubbo
     */
    public static final String ALL_DUBBO_SERVICES = "*";

    /**
     * The subscribed services, the default value is "*". The multiple value will use comma(",") as the separator.
     *
     * @see #ALL_DUBBO_SERVICES
     */
    private String subscribedServices = ALL_DUBBO_SERVICES;

    public String getSubscribedServices() {
        return subscribedServices;
    }

    public void setSubscribedServices(String subscribedServices) {
        this.subscribedServices = subscribedServices;
    }

    /**
     * Get the subscribed services as a {@link Set} with configuration order.
     *
     * @return non-null Read-only {@link Set}
     */
    public Set<String> subscribedServices() {

        String[] services = commaDelimitedListToStringArray(getSubscribedServices());

        if (services.length < 1) {
            return Collections.emptySet();
        }

        Set<String> subscribedServices = new LinkedHashSet<>();

        for (String service : services) {
            if (hasText(service)) {  // filter blank service name
                // remove all whitespace
                subscribedServices.add(trimAllWhitespace(service));
            }
        }

        return Collections.unmodifiableSet(subscribedServices);
    }
}
