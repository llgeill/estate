package cn.stylefeng.guns.base;

import cn.stylefeng.guns.GunsApplication;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.modular.estate.entity.HouseResource;
import cn.stylefeng.guns.modular.estate.service.HouseResourceService;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 基础测试类
 *
 * @author stylefeng
 * @Date 2017/5/21 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GunsApplication.class)
@WebAppConfiguration
//@Transactional //打开的话测试之后数据可自动回滚
public class BaseJunit {

    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    HouseResourceService houseResourceService;
    @Autowired
    UserService userService;

    protected MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void initDatabase() {
    }

    @Test
    public void salePerformance(){
        //统计除了admin的所有用户租售數量
        QueryWrapper<User> queryWrappers=new QueryWrapper<>();
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
        try {
            Date date1 = sdf.parse("2018-07");
            Date date2 = sdf.parse("2019-08");
            queryWrappers.between("update_time",date1,date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<User> userList=userService.list(queryWrappers);
        List<Map<Object,Object>> mapList=new ArrayList<>();
        for(User user:userList){
            if(ConstantFactory.me().getRoleName(user.getRoleId()).equals("超级管理员"))continue;
            Map<Object,Object> map=new HashMap();
            map.put("name",user.getName());
            map.put("userId",user.getUserId());
            QueryWrapper<HouseResource> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("staff_id",user.getUserId());
            List<HouseResource> houseResourceList=houseResourceService.list(queryWrapper);
            for(HouseResource houseResource:houseResourceList){
                if(houseResource.getState().equals("我租")){
                    if(map.get("render")==null)map.put("render",1);
                    else map.put("render",(Integer)map.get("render")+1);
                }else if(houseResource.getState().equals("我售")){
                    if(map.get("sell")==null)map.put("sell",1);
                    else map.put("sell",(Integer)map.get("sell")+1);
                }
            }
            if(map.get("render")==null)map.put("render",0);
            if(map.get("sell")==null)map.put("sell",0);
            Integer render=(Integer)map.get("render");
            Integer sell=(Integer) map.get("sell");
            map.put("total",render+sell);
            mapList.add(map);
        }
        System.out.println(mapList);
    }
}
