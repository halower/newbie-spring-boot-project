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

package com.newbie.initializer;


import com.newbie.context.NewbieBootContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

@Log4j2
public class NewbieBootContextInitializer implements  ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext)  {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        if(!NewbieBootContext.appIsStarted) {
            if (NewbieBootContext.isSpringCloudBootstrapEnvironment(environment)) {
                log.info("\033[32;4m" + "当前接入SpringCloud环境!" + "\033[0m");
            }
            log.info("\033[32;4m" + "TFStarter基础设施已经准备就绪!" + "\033[0m");
        }
    }

    @Override
    public int getOrder() {
        //设置为最高优先级
        return HIGHEST_PRECEDENCE;
    }
}
