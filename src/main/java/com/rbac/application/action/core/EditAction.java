package com.rbac.application.action.core;

import com.system.core.parse.Edit;
import com.system.util.base.DumperUtils;
import nu.xom.ParsingException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @auther ttm
 * @date 2018/11/3 0003
 **/
public class EditAction<E> extends RbacAction<E> {

    private E entity;

    private Edit edit;

    public void _execute() throws Exception {
        super._execute();
        initEditXml(getUrl());
    }

    public void initEditXml(String url) throws NoSuchMethodException, IOException, InstantiationException, ParsingException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        if (StringUtils.isEmpty(url)) {
            edit = new Edit(getSubRequestUri());
        } else {
            edit = new Edit(url);
        }
        DumperUtils.dump(edit);
        setTitle(edit.getTitle());
    }

    public Edit getEdit() {
        return edit;
    }

    public void setEdit(Edit edit) {
        this.edit = edit;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }
}
