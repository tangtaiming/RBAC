package com.system.core.vo;

import nu.xom.*;
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

    public NavigatorRsVo() {}

    /**
     * 获取所有导航菜单
     * @return
     */
    public LinkedList getNavAll() {
        try {
            return navigator(true);
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
//        TreeMap<Integer, ArrayList> navigatorTree = new TreeMap<>();

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

    private void parseSecondXml(LinkedHashMap<String, LinkedHashMap<String, LinkedHashSet<HashMap>>> firstNavData, Element firstElement, boolean all) {
        Elements secondNavElements = firstElement.getChildElements();
        String firstNavName = firstElement.getLocalName().toLowerCase();
        LOG.info("First nav name: " + firstNavName);
        //second navigator data
        LinkedHashMap<String, LinkedHashSet<HashMap>> secondNavData = new LinkedHashMap<>();
        for (int y = 0; y < secondNavElements.size(); y++) {
            Element secondNav = secondNavElements.get(y);
            //third navigator elements
//            parseThirdXml(secondNavData , secondNav, all);
        }
    }

//    private void parseThirdXml(LinkedHashMap<String, LinkedHashSet<HashMap>> secondNavData, Element secondNav, boolean all) {
//        String secondNavName = secondNav.getLocalName();
//        Elements thirdNavElements = secondNav.getChildElements();
//        String secondLink = secondNav.getAttributeValue("link");
//        LOG.info("Second nav: " + secondNavName + " link: " + secondLink);
//
//        //third navigator data
//        LinkedHashSet<HashMap> thirdNavData = new LinkedHashSet<>();
//        for (int z = 0; z < thirdNavElements.size(); z++) {
//            HashMap<String, String> linkHashMap = new HashMap<>();
//            Element thirdNav = thirdNavElements.get(z);
//            String thirdNavName = thirdNav.getLocalName();
//            String link = thirdNav.getAttributeValue("link");
//            if (!StringUtils.isEmpty(link)) {
//                link = link.trim();
//            }
//            LOG.info("Link: " + link);
//            linkHashMap.put("title", thirdNavName);
//            linkHashMap.put("link", link);
//            thirdNavData.add(linkHashMap);
//        }
//        secondNavData.put(secondNavName, thirdNavData);
//    }

    /**
     * 是否存在导航
     * @param root
     * @return
     */
    private boolean existRootNavigator(Element root) {
        return NAVIGATOR.equals(root.getLocalName());
    }

}
