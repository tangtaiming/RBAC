package com.system.core.parse;

import com.system.util.base.AppPathUtils;
import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private Map<String, Object> body;

    private Map<String, List<String>> head;

    public Main(String urlPath) throws ParsingException, IOException {
        System.out.println("Show url: " + urlPath);
//        AppPathUtils.getAppPageXmlPath() + AppPathUtils.SEPARATORCHAR + urlPath;
//        File file = new File(urlPath);
//        Builder builder = new Builder();
//        Document document = builder.build(file);
//        Element root = document.getRootElement();
//        parseXml(root);
    }

    public void parseXml(Element root) {
        Element headElement = root.getFirstChildElement("head");
        Element bodyElement = root.getFirstChildElement("body");
        Map<String, List<String>> headList = parseHeadXml(headElement);
        Map<String, Object> bodyList = parseBodyXml(bodyElement);
        setHead(headList);
        setBody(bodyList);
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

    private Map<String, List<String>> parseHeadXml(Element headElement) {
        Elements headChildElementList = headElement.getChildElements();
        int headChildTotalNumber = headChildElementList.size();
        List<String> jsList = new ArrayList<>();
        List<String> cssList = new ArrayList<>();
        for (int headX = 0; headX < headChildTotalNumber; headX++) {
            Element headChildElement = headChildElementList.get(headX);
            String headChileElementName = headChildElement.getLocalName();
            if (headChileElementName.equals("script")) {
                String srcAttribute = headChildElement.getAttributeValue("src");
                jsList.add(srcAttribute);
            } else if (headChileElementName.equals("link")) {
                String hrefAttribute = headChildElement.getAttributeValue("href");
                cssList.add(hrefAttribute);
            } else {
                System.out.println("Parse parseHeadXml fail element name : " + headChileElementName);
            }
        }
        //整合 script 与 css数据
        Map<String, List<String>> headMap = new HashMap<>();
        headMap.put("jsList", jsList);
        headMap.put("cssList", cssList);
        return headMap;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, List<String>> getHead() {
        return head;
    }

    public void setHead(Map<String, List<String>> head) {
        this.head = head;
    }
}
