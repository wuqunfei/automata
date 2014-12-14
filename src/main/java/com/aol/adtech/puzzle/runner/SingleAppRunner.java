package com.aol.adtech.puzzle.runner;

import com.aol.adtech.puzzle.matcher.BracketMatcher;
import com.aol.adtech.puzzle.matcher.MatcherInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: SingleAppRunner
 * Description:
 */
public class SingleAppRunner {

    /**
     * For Puzzle Part I
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatcherInterface matcher = new BracketMatcher();
        helpMessage();
        List<String> lines = new ArrayList<>();
        String line;

        /**
         * stdin can use Q to Quit
         */

        while (scanner.hasNextLine() && !(line = scanner.nextLine()).equalsIgnoreCase("q")) {
            if ("".equals(line)) {
                for (String iLine : lines) {
                    System.out.println(iLine + ":" + matcher.matches(iLine));
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
        builder.append(System.getProperty("line.separator") + "        Bracket validation Application\n");
        builder.append("1.Enter 'q' or Ctrl+C to quit application\n2.Press Enter/Return Key twice to validate. \n3.Empty String is invalidated, so application ignores it!\n4.For Example,\n");
        builder.append("   Single line validate\n");
        builder.append("   ()             /* input () and Press Enter Key */ \n");
        builder.append("                  /* Press Enter Key */ \n");
        builder.append("   ():true        /* result message */ \n\n");
        builder.append("   Multi lines validate\n");
        builder.append("   ()             /* input ()  and Press Enter Key */ \n");
        builder.append("   (((            /* input ((( and Press Enter Key */ \n");
        builder.append("                  /* Press Enter Key */ \n");
        builder.append("   ():true        /* result message of first line */\n");
        builder.append("   (((:false      /* result message of second line*/\n\n");
        builder.append("Please enter your string to validate NOW!");
        System.out.println(builder.toString());
    }
}
