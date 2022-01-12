import com.user.domain.Account;
import com.user.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void init() throws IOException {
        accountService = new AccountService();
    }

    @After
    public void destory() {
        accountService.destroy();
    }

    @Test
    public void testFindAll() throws IOException {
        List<Account> accountList = accountService.findAll();
        System.out.println(accountList.toString());
    }

    @Test
    public void testDeleteByPrimaryKey() {
        accountService.deleteByPrimaryKey("001");
    }

    @Test
    public void testInsert() throws IOException {
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        Account record = new Account("001", "李一", 150, sqlDate, sqlDate);
        accountService.insert(record);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Account ret = accountService.selectByPrimaryKey("1");
        System.out.println(ret);
    }

    @Test
    public void updateByPrimaryKey() {
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        Account record = new Account("001", "小明", 150, sqlDate, sqlDate);
        accountService.updateByPrimaryKey(record);
    }

    @Test
    public void transfer() {
        accountService.transfer("001", "002", 50);
    }
}
