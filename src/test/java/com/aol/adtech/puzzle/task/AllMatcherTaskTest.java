package com.aol.adtech.puzzle.task;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14 01:53
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: AllMatcherTaskTest
 * Description: Test for {@link AllMatcherTask}
 */
public class AllMatcherTaskTest {
    AllMatcherTask task;


    /**
     * Start to test, no return value, no exception is pass
     *
     * @throws Exception
     */

    @Test
    public void testCompute() throws Exception {
        int processors = Runtime.getRuntime().availableProcessors();
        List<String> lines = Arrays.asList("()", "abc");
        task = new AllMatcherTask(lines);
        ForkJoinPool pool = new ForkJoinPool(processors);
        pool.submit(task);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);
    }
}
