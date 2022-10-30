import com.maple.transaction.AccountService;
import com.maple.transaction.config.SpringTransactionConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/10/30 15:50
 * desc:
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringTransactionConfig.class, locations = "使用配置文件的地址:classpath:xxx.xml")
public class SpringJunit4Test {


    @Resource
    private AccountService accountService;

    @Test
    public void test(){
        accountService.tract("from", "to", 10);
    }
}
