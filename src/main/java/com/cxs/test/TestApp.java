package com.cxs.test;

import com.cxs.model.PageBean;
import com.cxs.test.domain.User;
import com.cxs.test.mapper.UserMapper;
import com.cxs.test.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 16:11
 */
public class TestApp {

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-dao.xml", "classpath:spring-service.xml", "classpath:spring-aspect.xml");
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(2);
        System.out.println(user.getName());
        System.out.println("----------------");
        UserService userService = ctx.getBean(UserService.class);
       /* User u = new User();
        u.setName("ssss");
        u.setAddress("aaaaa");
        User user1 = userService.add(u);
        System.out.println(user1);*/
        System.out.println("------------------");

        PageBean<User> pageBean = userService.findAll(1, 3);
        System.out.println(pageBean.getTotal());
        pageBean.getData().forEach((x) ->
            System.out.println(x)
        );
    }
}
