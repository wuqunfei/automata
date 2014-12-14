package com.aol.adtech.puzzle.matcher;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: BracketMatcherInterface
 */
public interface MatcherInterface {

    public abstract String getValue();

    public abstract void setValue(String value);

    /**
     * matches is core function of this application
     *
     * @return validity of input value
     */
    public abstract Boolean matches();

    public abstract Boolean matches(String value);

}


