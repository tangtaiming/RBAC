package com.rbac.application.service;

import com.rbac.application.action.dto.AccessManagementRsDto;
import com.rbac.application.action.dto.SaveAccessRsDto;
import com.rbac.application.dao.AccessDao;
import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Access;
import com.system.util.base.JsonUtils;
import com.system.util.base.PageUtils;
import com.system.util.enumerate.Status;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class AccessService extends SimpleCoreService<Access> {

    private Logger LOG = LoggerFactory.getLogger(AccessService.class);

    private AccessDao accessDao = new AccessDao(Access.class);

    private RoleDao roleDao = new RoleDao();

    public List<Access> findAccessAllList() {
        return accessDao.findAllList();
    }

    public Access findAccessOne(Integer aid) {
        return accessDao.findOne(aid);
    }

    public List<AccessManagementRsDto> findAccessRsDto() {
        List<AccessManagementRsDto> accessRsDto = new ArrayList<>();
        List<Access> accessList = findAccessAllList();
        if (CollectionUtils.isNotEmpty(accessList)) {
            for (Access row : accessList) {
                AccessManagementRsDto createAccessRsDto = new AccessManagementRsDto();
                createAccessRsDto.setId(row.getId());
                createAccessRsDto.setTitle(row.getTitle());
                createAccessRsDto.setUrls(row.getUrls());
                accessRsDto.add(createAccessRsDto);
            }
        }

        return accessRsDto;
    }

    public boolean saveAccess(SaveAccessRsDto accessRsDto) {
        boolean saveFalg = false;
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        Integer accessId = accessRsDto.getId();
        String[] urls = StringUtils.split(accessRsDto.getUrls(), ",");

        if (null != accessId) {
            Access findAccess = accessDao.findOne(accessId);
            if (null != findAccess) {
                findAccess.setUrls(JsonUtils.toJson(urls));
                findAccess.setTitle(accessRsDto.getTitle());
                findAccess.setUpdateDate(time);
                accessDao.update(findAccess);
                saveFalg = true;
            }
        } else {
            Access createAccess = new Access();
            createAccess.setCreateDate(time);
            createAccess.setUpdateDate(time);
            createAccess.setStatus(Status.OPEN.getStatus());
            createAccess.setTitle(accessRsDto.getTitle());
            createAccess.setUrls(JsonUtils.toJson(urls));
            Integer createAccessId = (Integer) accessDao.save(createAccess);
            saveFalg = (null != createAccessId ? true : false);
        }

        return saveFalg;
    }

    public SaveAccessRsDto findAccessRsDtoOne(Serializable aid) {
        Access access = findAccessOne(Integer.valueOf(aid.toString()));
        if (null != access) {
            SaveAccessRsDto accessRsDto = new SaveAccessRsDto();
            accessRsDto.setId(access.getId());
            accessRsDto.setTitle(access.getTitle());
            accessRsDto.setUrls(parseUrls(access.getUrls()));
            return accessRsDto;
        }

        return null;
    }

    private String parseUrls(String urls) {
        List<String> urlList = (List<String>) JsonUtils.fromJson(urls, List.class);
        String url = "";
        for (int x = 0; x < urlList.size(); x++) {
            if (urlList.size() == (x + 1)) {
                url += urlList.get(x);
            } else {
                url += urlList.get(x) + ",";
            }
        }

        return url;
    }

    public Access findAccessByTitle(String title) {
        return accessDao.findAccessByTitle(title);
    }

    public int findAccessAllListCount() {
        return accessDao.findAllListCount();
    }

    @Override
    public List<Access> getDataList() {
        return accessDao.findDataList();
    }

    @Override
    public PageUtils getPage() {
        return accessDao.findPage();
    }
}
