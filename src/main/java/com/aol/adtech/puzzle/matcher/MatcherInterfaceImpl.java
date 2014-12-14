package com.aol.adtech.puzzle.matcher;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: MatcherInterfaceImpl
 */

public abstract class MatcherInterfaceImpl implements MatcherInterface {


    /**
     * value is the input String value
     */
    protected String value;

    /**
     * chars are the charArray of input value, used by {@link BracketMatcher}.
     */
    protected char[] chars;


    public String getValue() {
        return value;
    }

    /**
     * @param value is input string value
     */
    public void setValue(String value) {
        this.value = value;
        if (this.value != null) {
            chars = this.value.toCharArray();
        }
    }

    /**
     * @return value is empty or not
     */

    public boolean isEmpty() {
        Boolean flag = false;
        if (this.chars == null) {
            flag = true;
        } else if (this.chars.length == 0) {
            flag = true;
        }
        return flag;
    }
}
