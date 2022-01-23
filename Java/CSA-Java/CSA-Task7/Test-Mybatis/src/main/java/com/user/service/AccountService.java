package com.user.service;

import com.user.dao.AccountDao;
import com.user.domain.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class AccountService {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    // 初始化:
    public AccountService() throws IOException {
        inputStream = getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();

        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    // 释放资源:
    public void destroy() {
        try {
            sqlSession.commit();
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1.查询所有记录
    public List<Account> findAll() {
        // 方式1：getMapper
        AccountDao mapper = sqlSession.getMapper(AccountDao.class);
        return mapper.findAll();
    }

    // 2.通过id删除记录
    public void deleteByPrimaryKey(String id) {
        sqlSession.getMapper(AccountDao.class).deleteByPrimary(id);
    }

    // 3.插入记录
    public void insert(Account record) {
        sqlSession.getMapper(AccountDao.class).insert(record);
    }

    // 4.通过id查找对象
    public Account selectByPrimaryKey(String id) {
        return sqlSession.getMapper(AccountDao.class).selectByPrimaryKey(id);
    }

    // 5.更新Account
    public void updateByPrimaryKey(Account record) {
        sqlSession.getMapper(AccountDao.class).updateByPrimaryKey(record);
    }

    // 6. 转账功能——输入转出人id、转入人id、转账金额，实现转账
    public void transfer(String remitterId, String remitteeId, int money) {
        Account remitter = selectByPrimaryKey(remitterId);
        Account remittee = selectByPrimaryKey(remitteeId);
        if (remittee == null || remitter == null)
            return;

        // 前提:转出人金额>money
        if (selectByPrimaryKey(remitterId).getMoney() > money) {
            // 转出人余额-
            remitter.setMoney(remitter.getMoney() - money);
            updateByPrimaryKey(remitter);
            // 转入人余额+
            remittee.setMoney(remittee.getMoney() + money);
            updateByPrimaryKey(remittee);

            System.out.println("转账成功");
        }
    }
}
