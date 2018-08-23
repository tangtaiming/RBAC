package com.rbac.applicatio;

import com.rbac.application.action.vo.CreateAccessRsVo;
import com.system.util.base.JsonUtils;
import org.junit.Test;

/**
 * @auther ttm
 * @date 2018/8/21
 */
public class ParseXmlTest {

    @Test
    public void xmlTest() {
//        NavigatorRsVo navigatorRsVo = new NavigatorRsVo();
//        LinkedList navigator = navigatorRsVo.getNavAll();
//        System.out.println("Show: " + JsonUtils.toJson(navigator));
        CreateAccessRsVo menuAccessRsVo = new CreateAccessRsVo();
        System.out.println("Show: " + JsonUtils.toJson(menuAccessRsVo.getNavAll()));
    }


}
