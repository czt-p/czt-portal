package com.zjcds.czt;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * created date：2018-12-29
 *
 * @author niezhegang
 */
public class SpliteTest {
    @Test
    public void name() {
        String text = "fafafa|fa  afafa||fafafa";
//        String[] results = text.split(";,");
        String[] results = StringUtils.split(text,"||");
        Assert.assertTrue(results.length == 3);

    }
}
