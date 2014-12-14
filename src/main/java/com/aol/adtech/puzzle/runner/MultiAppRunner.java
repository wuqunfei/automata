package com.aol.adtech.puzzle.runner;

import com.aol.adtech.puzzle.task.AllMatcherTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: MultiAppRunner
 * Description: validate String with multi
 */
public class MultiAppRunner {

    /**
     * For Puzzle Part II
     *
     * @param args
     * @throws InterruptedException multi thread on multi core by ForkJoinPool(processors)
     *                              submit function start pool and needn't wait to return
     */

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        int processors = Runtime.getRuntime().availableProcessors();
        AllMatcherTask task;
        ForkJoinPool pool;
        helpMessage();
        String line;

        /**
         * stdin can use Q to Quit
         */

        while (scanner.hasNextLine() && !(line = scanner.nextLine()).equalsIgnoreCase("q")) {
            if ("".equals(line)) {
                if (lines.size() > 0) {
                    task = new AllMatcherTask(lines);
                    pool = new ForkJoinPool(processors);
                    pool.submit(task);
                    pool.shutdown();
                    pool.awaitTermination(60, TimeUnit.SECONDS);
                }

                lines.clear();
            } else {
                lines.add(line);
            }
        }
    }

    /**
     * Print help message when start main application
     */
    public static void helpMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(System.getProperty("line.separator") + "        Bracket validation multi-thread Application\n");
        builder.append("1.Enter 'q' or Ctrl+C to quit application\n2.Press Enter/Return Key twice to validate. \n3.Empty String is invalidated, so application ignores it!\n4.For Example,\n");
        builder.append("   Single line validate\n");
        builder.append("   ()             /* input () and Press Enter Key */ \n");
        builder.append("                  /* Press Enter Key */ \n");
        builder.append("   0:true         /* result message */ \n\n");
        builder.append("   Multi lines validate\n");
        builder.append("   ()             /* input ()  and Press Enter Key */ \n");
        builder.append("   (((            /* input ((( and Press Enter Key */ \n");
        builder.append("                  /* Press Enter Key */ \n");
        builder.append("   0:true         /* result message of first line */\n");
        builder.append("   1:false        /* result message of second line*/\n\n");
        builder.append("Please enter your string to validate NOW!");
        System.out.println(builder.toString());
    }
}
