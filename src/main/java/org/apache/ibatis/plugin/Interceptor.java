/*
 *    Copyright 2009-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * @author Clinton Begin
 */
public interface Interceptor {

  /**
   * 真正方法被拦截执行的逻辑
   * @param invocation  主要目的是将多个参数进行封装
   */
  Object intercept(Invocation invocation) throws Throwable;

  // 生成目标对象的代理对象
  default Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  // 可以拦截器设置一些属性
  default void setProperties(Properties properties) {
    // NOP
  }

}
