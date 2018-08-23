package com.rbac.application.action.vo;

import com.rbac.application.orm.Jstree;
import com.rbac.application.orm.jstree.JstreeState;
import com.system.core.vo.NavigatorRsVo;
import com.system.util.base.JsonUtils;
import com.system.util.base.LocalUtils;
import nu.xom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 菜单权限
 * @auther ttm
 * @date 2018/8/23
 */
public class CreateAccessRsVo {

    private static final Logger LOG = LoggerFactory.getLogger(NavigatorRsVo.class);

    private static final String SYSTEM_NAVAGATOR_RBAC_PATH = "D://RBAC-NAVIGATOR.xml";

    private final String NAVIGATOR = "navigator";

    private LocalUtils local = new LocalUtils(Locale.SIMPLIFIED_CHINESE);

    public CreateAccessRsVo() {}

    /**
     * 获取所有导航菜单
     * @return
     */
    public String getNavAll() {
        List<Jstree> allNav = new ArrayList<>();
        try {
            allNav = navigator(true);
            return JsonUtils.toJson(allNav);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 导航菜单
     * @param all
     * @return
     */
    private List<Jstree> navigator(boolean all) throws ParsingException, IOException {
        List<Jstree> jstreeList = new ArrayList<>();
        Builder builder = new Builder();
        File file = new File(SYSTEM_NAVAGATOR_RBAC_PATH);
        Document rootDocument = builder.build(file);
        Element rootElement = rootDocument.getRootElement();
        if (existRootNavigator(rootElement)) {
            jstreeList = parseXml(rootElement, all);
        }
        return jstreeList;
    }

    /**
     * 解析菜单树
     * @param root
     * @param all
     * @return
     */
    private List<Jstree> parseXml(Element root, boolean all) {
        List<Jstree> jstreeList = new ArrayList<>();
        //frist navigator elments
        Elements fristNavElements = root.getChildElements();
        for (int x = 0; x < fristNavElements.size(); x++) {
            Element firstNav = fristNavElements.get(x);
            //second navigator elments
            Elements secondNavElements = firstNav.getChildElements();
            String firstNavName = firstNav.getLocalName().toLowerCase();
            LOG.info("First nav name: " + firstNavName);

            Jstree rootMenu = new Jstree();
            List<Jstree> children = new ArrayList<>();
            for (int y = 0; y < secondNavElements.size(); y++) {
                Element secondNav = secondNavElements.get(y);
                //third navigator element
                Elements thirdNavElements = secondNav.getChildElements();
                Jstree secondMenu = new Jstree();
                List<Jstree> secondChildren = new ArrayList<>();
                for (int z = 0; z < thirdNavElements.size(); z++) {
                    Element thirdNav = thirdNavElements.get(z);
                    String thirdId = thirdNav.getAttributeValue("link");
                    String thirdText = thirdNav.getLocalName().toLowerCase();
                    Jstree thirdMenu = new Jstree();
                    thirdMenu.setId(thirdId);
                    thirdMenu.setText(local.getText(thirdText));
                    secondChildren.add(thirdMenu);
                }
                String secondId = secondNav.getAttributeValue("link");
                String secondText = secondNav.getLocalName().toLowerCase();
                secondMenu.setId(secondId);
                secondMenu.setText(local.getText(secondText));
                secondMenu.setChildren(secondChildren);
                children.add(secondMenu);
            }
            rootMenu.setId("-1");
            rootMenu.setText(local.getText("root.menu"));
            JstreeState open = new JstreeState();
            open.setOpened(true);
            rootMenu.setState(open);
            rootMenu.setChildren(children);
            jstreeList.add(rootMenu);
        }

        return jstreeList;
    }

    /**
     * 是否存在导航
     * @param root
     * @return
     */
    private boolean existRootNavigator(Element root) {
        return NAVIGATOR.equals(root.getLocalName());
    }

}
