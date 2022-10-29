import com.maple.myspring.ApplicationContext;
import com.maple.myspring.core.ClassPathXmlContext;
import com.maple.myspring.entity.User;
import org.junit.Test;

/**
 * @author 杨锋
 * @date 2022/10/29 14:24
 * desc:
 */

public class MySpringTest {

    @Test
    public void testRead(){
        ApplicationContext applicationContext = new ClassPathXmlContext("my_spring.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

    }
}
