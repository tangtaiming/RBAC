package com.rbac.applicatio;

import com.system.core.vo.NavigatorRsVo;
import com.system.util.base.JsonUtils;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @auther ttm
 * @date 2018/8/21
 */
public class ParseXmlTest {

    @Test
    public void xmlTest() {
        NavigatorRsVo navigatorRsVo = new NavigatorRsVo();
        LinkedList navigator = navigatorRsVo.getNavAll();
        System.out.println("Show: " + JsonUtils.toJson(navigator));

    }


}
