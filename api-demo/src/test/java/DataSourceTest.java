import com.demo.App;
import com.demo.entity.QueryVo;
import com.demo.entity.ResultVo;
import com.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @date: 2022/8/2 19:53
 * @author: LiHaoHan
 * @program: PACKAGE_NAME
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class DataSourceTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        QueryVo queryVo = new QueryVo();
        queryVo.setDate("2020-11-23");
        queryVo.setId(5);
        ResultVo pro = userMapper.getPro(queryVo);
        System.out.println("pro = " + pro);
    }

}
