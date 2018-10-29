package com.rbac.applicatio;

import com.rbac.application.action.core.MainAction;
import com.system.util.base.DumperUtils;
import nu.xom.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/8/21
 */
public class ParseXmlTest {

    @Test
    public void contextText() throws ParsingException, IOException {
        String fileDir = "E:\\Project\\learngit\\RBAC\\src\\main\\resources\\com\\page\\main\\";
        String path = fileDir + "userManager.xml";
        File file = new File(path);
        Builder builder = new Builder();
        Document document = builder.build(file);
        System.out.println("root : " + document.getRootElement().getLocalName());
        Element root = document.getRootElement();
        String rootClassName = root.getAttributeValue("class");
        Elements childElementList = root.getChildElements();
        int childElementSize = childElementList.size();
        Map<String, Object> allMapData = new HashMap<>();
        allMapData.put("entity", rootClassName);
        for (int x = 0; x < childElementSize; x++) {
            Element childElement = childElementList.get(x);
            System.out.println("Show " + childElement.getLocalName());
            String childLocalName = childElement.getLocalName();
            if (childLocalName.equals("head")) {
                Map<String, Object> headData = parseHeadXml(childElement);
                allMapData.put("head", headData);
            } else if (childLocalName.equals("body")) {
                Map<String, Object> bodyData = parseBodyXml(childElement);
                allMapData.put("body", bodyData);
            } else {
                System.out.println("Parse fail element name : " + childLocalName);
            }
        }
        DumperUtils.dump(allMapData);
    }

    private Map<String, Object> parseBodyXml(Element bodyElement) {
        Element headContainer = bodyElement.getChildElements("head-container").get(0);
        Elements headTitleElementList = headContainer.getChildElements();
        List<Map<String, String>> titleList = new ArrayList<>();
        //获取对应列的管理页面标题
        for (int titleX = 0; titleX < headTitleElementList.size(); titleX++) {
            Map<String, String> headTitleMap = new HashMap<>();
            Element headTitleElement = headTitleElementList.get(titleX);
            String title = headTitleElement.getAttributeValue("title");
            headTitleMap.put("title", title);
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
            searchMap.put("name", name);
            searchList.add(searchMap);
        }

        Element dataContainer = bodyElement.getChildElements("data-container").get(0);
        Elements dataElementList = dataContainer.getChildElements();
        List<Map<String, String>> dataList = new ArrayList<>();
        for (int dataX = 0; dataX < dataElementList.size(); dataX++) {
            Map<String, String> dataMap = new HashMap<>();
            Element dataElement = dataElementList.get(dataX);
            String name = dataElement.getAttributeValue("name");
            dataMap.put("name", name);
            dataList.add(dataMap);
        }

        Map<String, Object> allBodyMap = new HashMap<>();
        allBodyMap.put("title", titleList);
        allBodyMap.put("search", searchList);
        allBodyMap.put("data", dataList);

        return allBodyMap;
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


}
