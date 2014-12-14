package com.aol.adtech.puzzle.matcher;

import java.util.Stack;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: BracketMatcher
 * Description: BracketMatcher implements core function from {@link MatcherInterface}.
 * <p> Using push down automaton theory, reference @see <a>http://en.wikipedia.org/wiki/Pushdown_automaton</a>
 * PDA (push down automaton) is a type of automaton that employs a stack.
 */
public class BracketMatcher extends MatcherInterfaceImpl {


    /**
     * stack of PDA
     */
    private Stack<Status> stack = new Stack<>();


    /**
     * status of PDA
     */
    private Status status = Status.DEFAULT;

    /**
     * @return validity of input value, core of this application
     */

    @Override
    public Boolean matches(String value) {
        this.revertStatus();
        this.setValue(value);
        return this.matches();
    }

    /**
     * @return validity of input value, when the value is already set.
     */

    @Override
    public Boolean matches() {
        Boolean isValid = false;
        if (this.isEmpty()) {
            return isValid;
        }
        for (Character value : this.chars) {
            switch (status) {
                /**
                 *  PDA DEFAULT status
                 */
                case DEFAULT:
                    initStatus(value);
                    if (status == null) {
                        return false;
                    }
                    break;
                /**
                 *  PDA PARENTHESES status
                 */
                case PARENTHESES:
                    if (')' == value && Status.PARENTHESES.equals(this.peek())) {
                        stack.pop();
                        if (stack.empty()) {
                            status = Status.DEFAULT;
                        } else if (Status.BRACKETS.equals(this.peek())) {
                            status = Status.BRACKETS;
                        }
                    } else if ('{' == value) {
                        stack.push(Status.BRACES);
                        status = Status.BRACES;
                    } else {
                        return false;
                    }
                    break;
                /**
                 *  PDA BRACKETS status
                 */
                case BRACKETS:
                    if (']' == value && Status.BRACKETS.equals(this.peek())) {
                        stack.pop();
                        if (stack.empty()) {
                            status = Status.DEFAULT;
                        } else if (Status.PARENTHESES.equals(this.peek())) {
                            status = Status.PARENTHESES;
                        } else if (Status.BRACES.equals(this.peek())) {
                            status = Status.BRACES;
                        }

                    } else {
                        initStatus(value);
                        if (status == null) {
                            return false;
                        }
                    }
                    break;
                /**
                 *  PDA BRACES status
                 */

                case BRACES:
                    if ('}' == value && Status.BRACES.equals(this.peek())) {
                        stack.pop();
                        if (stack.empty()) {
                            status = Status.DEFAULT;
                        } else if (Status.PARENTHESES.equals(this.peek())) {
                            status = Status.PARENTHESES;
                        } else if (Status.BRACKETS.equals(this.peek())) {
                            status = Status.BRACKETS;
                        }
                    } else if ('[' == value) {
                        stack.push(Status.BRACKETS);
                        status = Status.BRACKETS;
                    } else {
                        return false;
                    }
                    break;

                default:
                    break;

            }
        }

        if (this.stack.empty()) {
            isValid = true;
        }
        return isValid;
    }

    /***
     * check stack is empty and return value, {@link java.util.Stack#peek()}
     * @return the object at the top of automaton stack.
     */

    private Status peek() {
        Status peekValue = null;
        if (!stack.empty()) {
            peekValue = stack.peek();
        }
        return peekValue;
    }

    /***
     * initialize a automaton(PDA) status. If status null, Loop in {@link  BracketMatcher#matches(String) will return false at once}
     * @param value is char value form user input String
     */
    private void initStatus(char value) {
        if ('(' == value) {
            stack.push(Status.PARENTHESES);
            status = Status.PARENTHESES;
        } else if ('[' == value) {
            stack.push(Status.BRACKETS);
            status = Status.BRACKETS;
        } else if ('{' == value) {
            stack.push(Status.BRACES);
            status = Status.BRACES;
        } else {
            status = null;
        }
    }


    /**
     * revert PDA status
     */
    private void revertStatus() {
        stack.clear();
        status = Status.DEFAULT;
    }
}

/**
 * the status of automaton
 * PARENTHESES is ()
 * BRACKETS is []
 * BRACES is {}
 */

enum Status {
    DEFAULT, PARENTHESES, BRACKETS, BRACES
}

