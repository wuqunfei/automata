package com.aol.adtech.puzzle.matcher;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * version: 1.0
 * author: Qunfei Wu
 * Date: 13/12/14 01:43
 * Email: qunfei.wu@gmail.com
 * Copyright (c) 2014 All rights reserved.
 * ClassName: BracketMatcherTest
 * Description: Test for {@link BracketMatcher}
 */
public class BracketMatcherTest {
    MatcherInterface matcher;


    @Before
    public void setUp() throws Exception {
        matcher = new BracketMatcher();
    }

    @After
    public void tearDown() throws Exception {
        matcher = null;
    }

    @Test
    public void testOpenedWithClosed() throws Exception {
        assertTrue(matcher.matches("()") && matcher.matches("[]") && matcher.matches("{}"));
        assertFalse(matcher.matches("(()") && matcher.matches("{{}") && matcher.matches("]]]"));
    }


    @Test
    public void testOnlyCloseLastOpened() throws Exception {
        assertTrue(matcher.matches("({})"));
        assertFalse(matcher.matches("({)}") && matcher.matches("[}]"));
    }


    @Test
    public void testBracesInsideParenthesis() throws Exception {
        assertTrue(matcher.matches("({})"));
        assertFalse(matcher.matches("({)}") && matcher.matches("[}]"));
    }


    @Test
    public void testBracketsInsideBraces() throws Exception {
        assertTrue(matcher.matches("{[]}"));
        assertFalse(matcher.matches("{()}") && matcher.matches("{{}}"));
    }

    @Test
    public void testAnyBracketInsideSquare() throws Exception {
        assertTrue(matcher.matches("[()]") && matcher.matches("[{}]") && matcher.matches("[[]]") && matcher.matches("[[[]]]"));
        assertFalse(matcher.matches("[([])]"));
    }


    @Test
    public void testListBraces() throws Exception {
        assertTrue(matcher.matches("[()()]") && matcher.matches("{[][()()]}") && matcher.matches("()()"));
    }

    @Test
    public void testEmpty() throws Exception {
        assertFalse(matcher.matches("") && matcher.matches(null));
    }

    /***
     * Give Any Other Random String to validate
     * @throws Exception
     */
    @Test
    public void testOtherCharacterInside() throws Exception {
        assertFalse(matcher.matches(RandomStringUtils.randomAlphabetic(10)));
    }
}
