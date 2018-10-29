package com.system.core.session;

import java.util.Map;

/**
 * @auther ttm
 * @date 2018/10/27 0027
 **/
public class RbacSession extends ActionSession {

    private Map<String, Object> session;

    public RbacSession() {
        session = getAction().getSession();
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Object get(String key) {
        return session.get(key);
    }

    public void put(String key, Object object) {
        session.put(key, object);
    }

    /**
     * 根据key删除session
     * @param key
     */
    public void remove(String key) {
        session.remove(key);
    }

    /**
     * 清除所有session
     */
    public void removeAll() {
        session.clear();
    }

}
