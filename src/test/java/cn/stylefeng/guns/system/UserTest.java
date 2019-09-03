package cn.stylefeng.guns.system;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.core.util.IsNumberUtil;
import cn.stylefeng.guns.modular.estate.entity.City;
import cn.stylefeng.guns.modular.estate.mapper.CityMapper;
import cn.stylefeng.guns.modular.system.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMapper userMapper;

    @Resource
    CityMapper cityMapper;

    @Test
    public void userTest() throws Exception {
        /*获取执行JavaScript的执行引擎*/
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        /*为文件注入全局变量*/
        Bindings bindings = engine.createBindings();
        /*设置绑定参数的作用域*/
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        // 获得js文件
        try {
            engine.eval(new FileReader("assets/modular/estate/data.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(bindings.get("threeSelectData"));
        Map<Object,Object> maps = (Map) bindings.get("threeSelectData");
    }

    @Test
    public void ss(){
        String var ="13711919653";
        boolean s1=var.length()==11;
        boolean ss=IsNumberUtil.isNumeric(var);
         List<City> cities = cityMapper.selectList(null);
    }

}
