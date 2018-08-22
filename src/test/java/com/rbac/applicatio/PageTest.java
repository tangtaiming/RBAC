package com.rbac.applicatio;

import com.system.util.base.PageUtils;
import org.junit.Test;

/**
 * @auther ttm
 * @date 2018/8/21
 */
public class PageTest {

    @Test
    public void pageTest() {
        PageUtils pageUtils = new PageUtils(1, 20, 1000);
        System.out.println("Show: " + pageUtils.toString());
    }

}
