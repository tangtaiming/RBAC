package com.system.core.parse;

import com.system.util.base.AppPathUtils;
import com.system.util.custom.Select;
import nu.xom.*;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/11/3 0003
 **/
public class Edit {

    private static final String EDIT_PACKAGE = "edit";

    /**
     * 头部
     */
    private Map<String, Object> headList;

    /**
     * 数据面板
     */
    private Map<String, Object> tabsList;

    /**
     * 标题
     */
    private String title;

    /**
     * 类名称
     */
    private String classesName;

    public Edit(String urlPath) throws ParsingException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String[] urlPathArr = StringUtils.split(urlPath, "/");
        String path =
                AppPathUtils.getAppPageXmlPath()
                        + EDIT_PACKAGE + "\\" + urlPathArr[0] + "\\" + urlPathArr[1] + ".xml";
        System.out.println("Show url: " + path);
        File file = new File(path);
        Builder builder = new Builder();
        Document document = builder.build(file);
        Element root = document.getRootElement();
        parseXml(root);
    }

    public void parseXml(Element root) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Element headElement = root.getFirstChildElement("head");
        Element tabsElement = root.getFirstChildElement("tabs");
        headList = parseHeadXml(headElement);
        tabsList = parseTabsXml(tabsElement);
        title = (String) headList.get("titleOne");
        classesName = root.getAttributeValue("class");
    }

    private Map<String, Object> parseTabsXml(Element tabsElement) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Elements tabElementList = tabsElement.getChildElements();

        Map<String, Object> tabRowNameMap = new HashMap<>();
        List<Object> tabList = new ArrayList<>();
        for (int x = 0; x < tabElementList.size(); x++) {
            List<Map<String, Object>> columnList = new ArrayList<>();
            Element tabElementRow = tabElementList.get(x);
            String tabRowTitle = tabElementRow.getAttributeValue("title");
            String tabRowName = tabElementRow.getAttributeValue("name");
            Elements tabColumnList = tabElementRow.getChildElements();
            for (int y = 0; y < tabColumnList.size(); y++) {
                Map<String, Object> columnMap = new HashMap<>();
                Element columnRow = tabColumnList.get(y);
                String tabType = columnRow.getAttributeValue("type");
                String tabTitle = columnRow.getAttributeValue("title");
                String tabName = columnRow.getAttributeValue("name");
                columnMap.put("tabType", tabType);
                columnMap.put("tabTitle", tabTitle);
                columnMap.put("tabName", tabName);
                if (tabType.equals("ftl")) {
                    String tabFtl = columnRow.getAttributeValue("ftl");
                    columnMap.put("tabFtl", tabFtl);
                } else if (tabType.equals("checkbox")) {
                    buildSelect(columnMap, columnRow);
                }
                columnList.add(columnMap);
            }
            Map<String, Object> b = new HashMap<>();
            b.put("tab", columnList);
            b.put("title", tabRowTitle);
            tabRowNameMap.put(tabRowName, b);
            tabList.add(columnList);
        }

        Map<String, Object> tabsMap = new HashMap<>();
        tabsMap.put("tabs", tabList);
        return tabsMap;
    }

    private void buildSelect(Map<String, Object> dataMap, Element element) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String type = element.getAttributeValue("type");
        String name = element.getAttributeValue("name");
        String title = element.getAttributeValue("title");
        String model = element.getAttributeValue("model");
        String classes = element.getAttributeValue("option");
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(name) || StringUtils.isEmpty(classes) || StringUtils.isEmpty(title)) {
            throw new IllegalArgumentException("type , name, option, and title can not be null");
        }
        if (StringUtils.isEmpty(model)) {
            model = type;
        }
        Map<String, String> optionMap = new Select.SelectBuilder(classes, model).build().toOption();
        dataMap.put("model", model);
        dataMap.put("option", optionMap);
    }

    private Map<String, Object> parseHeadXml(Element headElement) {
        //js script
        List<String> jsList = new ArrayList<>();
        Element headChildScriptElement = headElement.getFirstChildElement("script");
        String srcAttribute = headChildScriptElement.getAttributeValue("src");
        jsList.add(srcAttribute);
        //css link
        List<String> cssList = new ArrayList<>();
        Element headChildCssElement = headElement.getFirstChildElement("link");
        String hrefAttribute = headChildCssElement.getAttributeValue("href");
        cssList.add(hrefAttribute);
        //title
        Element headChildTitleElement = headElement.getFirstChildElement("title");
        String headTitle = headChildTitleElement.getValue();

        //整合 script 与 css数据
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("jsList", jsList);
        headMap.put("cssList", cssList);
        headMap.put("titleOne", headTitle);
        return headMap;
    }

    public Map<String, Object> getHeadList() {
        return headList;
    }

    public void setHeadList(Map<String, Object> headList) {
        this.headList = headList;
    }

    public Map<String, Object> getTabsList() {
        return tabsList;
    }

    public void setTabsList(Map<String, Object> tabsList) {
        this.tabsList = tabsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

}
