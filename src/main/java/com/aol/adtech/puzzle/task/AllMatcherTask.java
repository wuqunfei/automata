package com.aol.adtech.puzzle.task;

import com.aol.adtech.puzzle.matcher.BracketMatcher;
import com.aol.adtech.puzzle.matcher.MatcherInterface;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: AllMatchTask
 * Description:
 */
public class AllMatcherTask extends RecursiveAction {
    private final List<String> lines;
    private MatcherInterface matcher;

    /**
     * @param lines construct all lines which is read from System.in
     */
    public AllMatcherTask(List<String> lines) {
        super();
        this.lines = lines;
    }

    /**
     * Start subtask in {@link LineMatcherTask} by fork() function {@link java.util.concurrent.RecursiveAction#fork()}.
     * Because the order is not necessarily, it does not expect and wait to return.
     */

    @Override
    protected void compute() {
        for (int i = 0; i < this.lines.size(); i++) {
            String line = this.lines.get(i);
            this.matcher = new BracketMatcher();
            this.matcher.setValue(line);
            LineMatcherTask task = new LineMatcherTask(i, this.matcher);
            task.fork();
        }
    }
}
