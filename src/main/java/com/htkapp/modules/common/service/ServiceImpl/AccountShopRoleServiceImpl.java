package com.htkapp.modules.common.service.ServiceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.dao.AccountShopRoleMapper;
import com.htkapp.modules.common.entity.AccountShopRole;
import com.htkapp.modules.common.service.AccountShopRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountShopRoleServiceImpl implements AccountShopRoleService {

    @Resource
    private AccountShopRoleMapper accountShopRoleDao;

    //插入商户和角色信息
    @Override
    public void insertAccountShopRole(AccountShopRole accountShopRole) {
        int row = accountShopRoleDao.insertAccountShopRoleDAO(accountShopRole);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
}
