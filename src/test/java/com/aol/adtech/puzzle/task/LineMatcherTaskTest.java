package com.aol.adtech.puzzle.task;

import com.aol.adtech.puzzle.matcher.BracketMatcher;
import com.aol.adtech.puzzle.matcher.MatcherInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14 01:53
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: LineMatcherTaskTest
 * Description: Test for {@link LineMatcherTask}
 */
public class LineMatcherTaskTest {

    LineMatcherTask task;
    MatcherInterface matcher;

    @Before
    public void setUp() throws Exception {
        matcher = new BracketMatcher();
    }

    @After
    public void tearDown() throws Exception {
        matcher = null;


    }

    /**
     * start to test, return boolean to validate logic.
     *
     * @throws Exception
     */

    @Test
    public void testCompute() throws Exception {
        int processors = Runtime.getRuntime().availableProcessors();
        String line = "()";
        matcher.setValue(line);
        task = new LineMatcherTask(0, this.matcher);
        ForkJoinPool pool = new ForkJoinPool(processors);
        Boolean isValid = pool.invoke(task);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);
        assertTrue(isValid);
    }
}
