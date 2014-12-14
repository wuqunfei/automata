package com.aol.adtech.puzzle.task;

import com.aol.adtech.puzzle.matcher.MatcherInterface;

import java.util.concurrent.RecursiveTask;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: LineMatchTask
 * Description:
 */
public class LineMatcherTask extends RecursiveTask<Boolean> {

    /**
     * line number/index of many lines
     */

    private final Integer index;

    /**
     * matcher {@link com.aol.adtech.puzzle.matcher.BracketMatcher}
     */
    private final MatcherInterface matcher;

    /**
     * Each LineMatcherTask is for each line to match
     *
     * @param index
     * @param matcher
     */

    LineMatcherTask(Integer index, MatcherInterface matcher) {
        super();
        this.index = index;
        this.matcher = matcher;
    }

    /**
     * validate
     *
     * @return boolean
     */

    //TODO  in fact, this class needn't return value, but for unit test in LineMatcherTaskTest to assert it can be work in fork/join mode.

    /**
     * It can be replace to void function
     * <code>
     * public class LineMatcherTask extends RecursiveAction {...}
     * <p/>
     * protected void compute() {
     * System.out.println(this.index + ":" + this.matcher.matches());
     * }
     * </code>
     */
    @Override
    protected Boolean compute() {
        boolean flag = this.matcher.matches();
        System.out.println(this.index + ":" + flag);
        return flag;
    }
}
