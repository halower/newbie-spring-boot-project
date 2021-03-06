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

package com.newbie.core.datasource.aop;

import com.newbie.constants.NewbieBootInfraConstants;
import com.newbie.core.datasource.DynamicDataSourceContextHolder;
import org.apache.dubbo.rpc.*;

/**
 * @Author: halower
 * @Date: 2019/5/22 14:06
 */
public class DataSourceForDubboFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation){
        Result result;
        String WRITE_READ_DB_TYPE = RpcContext.getContext().getAttachment(NewbieBootInfraConstants.READ_WRITE_DB_TYPE);
        try {
            if (RpcContext.getContext().isProviderSide()) {
                Binder.bindChainParameters(WRITE_READ_DB_TYPE);
            }
            result = invoker.invoke(invocation);
            return result;
        } finally {
            if (RpcContext.getContext().isProviderSide()) {
                DynamicDataSourceContextHolder.clearDataSourceType();
            }
        }
    }

}



