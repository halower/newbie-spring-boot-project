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

package com.newbie.autoconfigure;


import com.newbie.context.NewbieBootContext;
import com.newbie.core.config.ApplicationWebConfigurer;
import com.newbie.core.config.NewbieBootBasicProperties;
import com.newbie.core.datasource.crypt.DssDecryptService;
import com.newbie.core.exception.GlobalExceptionHandler;
import com.newbie.core.log.LogbackProperties;
import com.newbie.core.monitor.p6spy.P6spyProperties;
import com.newbie.core.schedule.CleanTomcatTmpFileTask;
import com.newbie.endpoint.NewbieBootVersionEndpoint;
import com.newbie.swagger.Knife4jConfiguration;
import com.newbie.swagger.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author halower
 */
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
@EnableConfigurationProperties({
        NewbieBootBasicProperties.class,
        P6spyProperties.class,
        SwaggerProperties.class,
        LogbackProperties.class
  }
)
@Import({
        GlobalExceptionHandler.class,
        ApplicationWebConfigurer.class,
        NewbieBootVersionEndpoint.class,
        CleanTomcatTmpFileTask.class,
        DssDecryptService.class,
        NewbieBootContext.class,
        Knife4jConfiguration.class
})
public class NewBieApplicationConfiguration {
}