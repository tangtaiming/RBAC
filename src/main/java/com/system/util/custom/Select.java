package com.system.util.custom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public final class Select {

    private static final String SELECT_PAGE_PATH = "com.system.core.page";

    private static final String METHOD_NAME = "getOption";

    /**
     * 模型类型文件路径名称
     */
    private String mode;

    /**
     * 类名
     */
    private String classesName;

    private Select(SelectBuilder builder) {
        this.classesName = builder.classesName;
        this.mode = builder.mode;
    }

    public Map<String, String> toOption() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException {
        String packagePath = SELECT_PAGE_PATH + "." + mode + "." + classesName;
        Class classes = Class.forName(packagePath);
        Method method = classes.getMethod(METHOD_NAME);
        Object obj = classes.newInstance();
        return (Map<String, String>) method.invoke(obj, null);
    }

    public static class SelectBuilder {

        /**
         * 模型类型文件路径名称
         */
        private String mode;

        /**
         * 类名
         */
        private String classesName;

        public SelectBuilder(String classesName, String model) {
            this.classesName = classesName;
            this.mode = model;
        }

        public Select build() {
            return new Select(this);
        }

    }


}
