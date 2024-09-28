/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.seata.util;

import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jingliu_xiong@foxmail.com
 */
public class LogUtils {
    public static void printProcessLog(Logger LOGGER, Process process) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> printStream(LOGGER, process.getInputStream(), false));
//        executor.submit(() -> printStream(LOGGER, process.getErrorStream(), true));
        executor.shutdown();
    }

    private static void printStream(Logger LOGGER, InputStream inputStream, boolean isError) {
        new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(line -> {
            if (isError) {
                LOGGER.warn(line);
            } else {
                LOGGER.info(line);
            }
        });
    }
}
