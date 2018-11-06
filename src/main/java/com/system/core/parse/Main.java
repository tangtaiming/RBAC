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

public class Main {

    /**
     * 集合实体
     */
    private String classesName;

    /**
     * 管理功能 title
     */
    private String title;

    /**
     * 管理页面搜索与数据集合
     */
    private Map<String, Object> body;

    /**
     * css样式与js
     */
    private Map<String, Object> head;

    public Main(String urlPath) throws ParsingException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String[] urlPathArr = StringUtils.split(urlPath, "/");
        String path =
                AppPathUtils.getAppPageXmlPath()
                + "main\\" + urlPathArr[0] + "\\" + urlPathArr[1] + ".xml";
        System.out.println("Show url: " + path);
        File file = new File(path);
        Builder builder = new Builder();
        Document document = builder.build(file);
        Element root = document.getRootElement();
        parseXml(root);
    }

    public void parseXml(Element root) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Element headElement = root.getFirstChildElement("head");
        Element bodyElement = root.getFirstChildElement("body");
        head = parseHeadXml(headElement);
        body = parseBodyXml(bodyElement);
        title = (String) head.get("titleOne");
        classesName = root.getAttributeValue("class");
    }

    private Map<String, Object> parseBodyXml(Element bodyElement) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Element headContainer = bodyElement.getChildElements("head-container").get(0);
        Elements headTitleElementList = headContainer.getChildElements();
        List<Map<String, String>> titleList = new ArrayList<>();
        //获取对应列的管理页面标题
        for (int titleX = 0; titleX < headTitleElementList.size(); titleX++) {
            Map<String, String> headTitleMap = new HashMap<>();
            Element headTitleElement = headTitleElementList.get(titleX);
            String title = headTitleElement.getAttributeValue("title");
            String width = headTitleElement.getAttributeValue("width");
            headTitleMap.put("title", title);
            if (!StringUtils.isEmpty(width)) {
                headTitleMap.put("width", width);
            }

            titleList.add(headTitleMap);
        }

        Element searchContainer = bodyElement.getChildElements("search-container").get(0);
        Elements searchElementList = searchContainer.getChildElements();
        List<Map<String, Object>> searchList = new ArrayList<>();
        for (int searchX = 0; searchX < searchElementList.size(); searchX++) {
            Map<String, Object> searchMap = new HashMap<>();
            Element searchElement = searchElementList.get(searchX);
            String type = searchElement.getAttributeValue("type");
            String name = searchElement.getAttributeValue("name");
            searchMap.put("type", type);
            switch (type) {
                case "text":
                    searchMap.put("name", name);
                    break;
                case "select":
                    buildSelect(searchMap, searchElement);
                    break;
                case "action":
                    searchMap.put("name", "");
                    break;
                default:
                    searchMap.put("name", "");
                    break;
            }
            searchList.add(searchMap);
        }

        Element dataContainer = bodyElement.getChildElements("data-container").get(0);
        Elements dataElementList = dataContainer.getChildElements();
        List<Map<String, String>> dataList = new ArrayList<>();
        for (int dataX = 0; dataX < dataElementList.size(); dataX++) {
            Map<String, String> dataMap = new HashMap<>();
            Element dataElement = dataElementList.get(dataX);
            String name = dataElement.getAttributeValue("name");
            String type = dataElement.getAttributeValue("type");
            if (!StringUtils.isEmpty(type) && type.equals("action")) {
                dataMap.put("type", "action");
            }
            dataMap.put("name", name);
            dataList.add(dataMap);
        }

        Map<String, Object> allBodyMap = new HashMap<>();
        allBodyMap.put("title", titleList);
        allBodyMap.put("search", searchList);
        allBodyMap.put("data", dataList);

        return allBodyMap;
    }

    private void buildSelect(Map<String, Object> dataMap, Element element) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String type = element.getAttributeValue("type");
        String name = element.getAttributeValue("name");
        String model = element.getAttributeValue("model");
        String classes = element.getAttributeValue("option");
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(name) || StringUtils.isEmpty(classes)) {
            throw new IllegalArgumentException("type , name and option can not be null");
        }
        if (StringUtils.isEmpty(model)) {
            model = type;
        }
        Map<String, String> optionMap = new Select.SelectBuilder(classes, model).build().toOption();
        dataMap.put("name", name);
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
        //create button
        Element headChildCreateElement = headElement.getFirstChildElement("create");
        String createLink = headChildCreateElement.getAttributeValue("link");

        //整合 script 与 css数据
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("jsList", jsList);
        headMap.put("cssList", cssList);
        headMap.put("titleOne", headTitle);
        headMap.put("createLink", createLink);
        return headMap;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getHead() {
        return head;
    }

    public void setHead(Map<String, Object> head) {
        this.head = head;
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
