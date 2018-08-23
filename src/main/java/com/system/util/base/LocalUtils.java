package com.system.util.base;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class LocalUtils {

    private final static String packageName = "com/package";

    private Locale locale;

    private ResourceBundle resourceBundle;

    public LocalUtils(Locale locale) {
        this.setLocale(locale);
        this.setResourceBundle(ResourceBundle.getBundle(this.packageName, locale));
    }

    public LocalUtils(String language, String country) {
        this.setLocale(new Locale(language, country));
        this.setResourceBundle(ResourceBundle.getBundle(this.packageName, this.locale));
    }

    public String getText(String key) {
        key = this.convert(key);
        String text = key;
        try {
            if (resourceBundle.containsKey(key)) {
                text = this.resourceBundle.getString(key);
            }
        } catch (Exception arg5) {
            throw arg5;
        }
        return text;
    }

    public String convert(String key) {
        return key.replaceAll("_", ".").toLowerCase();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
