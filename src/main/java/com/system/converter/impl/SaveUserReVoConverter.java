package com.system.converter.impl;

import com.rbac.application.action.vo.SaveUserReVo;
import com.rbac.application.orm.User;
import com.system.converter.Converter;

/**
 * 保存用户
 */
public class SaveUserReVoConverter extends Converter<SaveUserReVo, User> {

    public SaveUserReVoConverter() {
        super(saveUserReVo -> {
            User user = new User();
            user.setId(saveUserReVo.getId());
            user.setName(saveUserReVo.getName());
            user.setPassword(saveUserReVo.getPassword());
            user.setEmail(saveUserReVo.getEmail());

            return user;
        }, user -> {
            SaveUserReVo saveUserReVo = new SaveUserReVo();
            saveUserReVo.setId(user.getId());
            saveUserReVo.setName(user.getName());
            saveUserReVo.setPassword(user.getPassword());
            saveUserReVo.setEmail(user.getEmail());

            return saveUserReVo;
        });
    }

}
