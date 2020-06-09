import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.factory.AnnotationBeanFactory;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import com.lagou.edu.service.impl.TransferServiceImpl;
import org.junit.Test;

/**
 * 测试自定义注解实现的ioc和aop
 */
public class SpringTest {

    @Test
    public void testIoc() throws Exception {
        Object obj = AnnotationBeanFactory.beans.get("transferServiceImpl");
        TransferService transferService = (TransferService)obj;
        transferService.transfer("6029621011001", "6029621011000", 1);

    }
}
