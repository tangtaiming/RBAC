package com.system.core.vo;

import com.rbac.application.orm.Menu;
import com.rbac.application.orm.RoleMenu;
import com.rbac.application.service.MenuService;
import com.rbac.application.service.RoleMenuService;
import com.rbac.application.service.RoleService;
import com.rbac.application.service.UserService;
import com.system.core.session.RbacSession;
import com.system.util.base.JsonUtils;
import com.system.util.enumerate.MenuType;
import nu.xom.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 导航菜单
 * @auther ttm
 * @date 2018/8/20
 */
public class NavigatorRsVo {

    private static final Logger LOG = LoggerFactory.getLogger(NavigatorRsVo.class);

    private static final String SYSTEM_NAVAGATOR_RBAC_PATH = "D://RBAC-NAVIGATOR.xml";

    private final String NAVIGATOR = "navigator";

    private MenuService menuService = new MenuService();

    private UserService userService = new UserService();

    private RoleMenuService roleMenuService = new RoleMenuService();

    private static List<Menu> navigator;

    public NavigatorRsVo() {

    }

    /**
     * 获取所有导航菜单
     * @return
     */
    public List<Menu> getNavAll() {
//        try {
//            return navigator(true);
//        } catch (ParsingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//        if (null == navigatorTree) {
//            navigatorTree = navigatorByMysql(true);
//        }
//        return navigatorTree;
        if (CollectionUtils.isEmpty(navigator)) {
            navigator = navigatorMenuByMysql(true);
        }
        return navigator;
    }

    public void cleanNav() {
        navigator = null;
    }

    public List<Menu> navigatorMenuByMysql(boolean all) {
        List<Long> privilegeMenu = new ArrayList<>();
        if (CollectionUtils.isEmpty(navigator)) {
            RbacSession session = new RbacSession();
            String secretKey = (String) session.get("secretKey");
            if (!StringUtils.isEmpty(secretKey)) {
                String[] secretKeyArr = StringUtils.split(secretKey, "#");
                String userId = secretKeyArr[1];
                List<Menu> menuList = menuService.findUserMenu(Integer.valueOf(userId));
                if (!CollectionUtils.isEmpty(menuList)) {
                    for (Menu menu : menuList) {
                        privilegeMenu.add(menu.getId());
                    }
                    navigator = menuList;
                }
            }
        }
        return navigator;
    }

    /**
     * 查询数据库获取导航
     * @param all
     * @return
     */
    public LinkedList navigatorByMysql(boolean all) {
        List<Menu> menuList = menuService.fetchMenuAllList(new ArrayList<>());
        if (!CollectionUtils.isEmpty(menuList)) {
            //查询一级菜单
            for (Menu first : menuList) {
                String firstNavName = first.getName();
                List<Menu> secondNav = (List<Menu>) first.getList();
                if (!CollectionUtils.isEmpty(secondNav)) {
                    //second navigator data
                    LinkedHashMap<String, LinkedHashSet<HashMap>> secondNavData = new LinkedHashMap<>();
                    for (Menu second : secondNav) {
                        String secondNavName = second.getName();
                        String secondLink = second.getUrl();
                        HashMap<String, Object> linkHashMap = new HashMap<>();
                        linkHashMap.put("title", secondNavName);
                        linkHashMap.put("link", secondLink);
                        //third navigator data
                        LinkedHashSet<HashMap> thirdNavData = new LinkedHashSet<>();
                        thirdNavData.add(linkHashMap);
                        secondNavData.put(secondNavName, thirdNavData);
                    }
                    LinkedHashMap<String, LinkedHashMap<String, LinkedHashSet<HashMap>>> firstNavData = new LinkedHashMap<>();
                    firstNavData.put(firstNavName, secondNavData);
//                    navigatorTree.add(firstNavData);
                }
            }
        }
        return null;
    }

    /**
     * 导航菜单
     * @param all
     * @return
     */
    private LinkedList navigator(boolean all) throws ParsingException, IOException {
        LinkedList navigatorTree = new LinkedList<>();
        Builder builder = new Builder();
        File file = new File(SYSTEM_NAVAGATOR_RBAC_PATH);
        Document rootDocument = builder.build(file);
        Element rootElement = rootDocument.getRootElement();
        if (existRootNavigator(rootElement)) {
            navigatorTree = parseXml(rootElement, all);
        }
        return navigatorTree;
    }

    /**
     * 解析XML
     * @param root
     * @param all
     * @return
     */
    private LinkedList parseXml(Element root, boolean all) {
        LinkedList navigatorTree = new LinkedList<>();
        //frist navigator elments
        Elements fristNavElements = root.getChildElements();
        for (int x = 0; x < fristNavElements.size(); x++) {
            Element firstNav = fristNavElements.get(x);
            //second navigator elments
            Elements secondNavElements = firstNav.getChildElements();
            String firstNavName = firstNav.getLocalName().toLowerCase();
            LOG.info("First nav name: " + firstNavName);
            //second navigator data
            LinkedHashMap<String, LinkedHashSet<HashMap>> secondNavData = new LinkedHashMap<>();
            for (int y = 0; y < secondNavElements.size(); y++) {
                Element secondNav = secondNavElements.get(y);
                //third navigator elements
                String secondNavName = secondNav.getLocalName();
                Elements thirdNavElements = secondNav.getChildElements();
                String secondLink = secondNav.getAttributeValue("link");
                LOG.info("Second nav: " + secondNavName + " link: " + secondLink);

                //third navigator data
                LinkedHashSet<HashMap> thirdNavData = new LinkedHashSet<>();
                HashMap<String, Object> linkHashMap = new HashMap<>();
                linkHashMap.put("title", secondNavName);
                linkHashMap.put("link", secondLink);
                HashMap<String, Object> aclLink = new HashMap<>();
                for (int z = 0; z < thirdNavElements.size(); z++) {
                    Element thirdNav = thirdNavElements.get(z);
                    String thirdNavName = thirdNav.getLocalName();
                    String link = thirdNav.getAttributeValue("link");
                    if (!StringUtils.isEmpty(link)) {
                        link = link.trim();
                    }
                    aclLink.put(thirdNavName, link);
                }
                linkHashMap.put("acl", aclLink);
                thirdNavData.add(linkHashMap);
                secondNavData.put(secondNavName, thirdNavData);
            }
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashSet<HashMap>>> firstNavData = new LinkedHashMap<>();
            firstNavData.put(firstNavName, secondNavData);
            navigatorTree.add(firstNavData);
        }

        return navigatorTree;
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
