package com.user.dao;

import com.user.domain.Account;

import java.util.List;

public interface AccountDao {
    // 1.查询所有记录
    List<Account> findAll();

    // 2.通过id删除记录
    int deleteByPrimary(String id);

    // 3.插入记录
    int insert(Account record);

    // 4.通过id查找对象
    Account selectByPrimaryKey(String id);

    // 5.更新Account
    int updateByPrimaryKey(Account record);
}
