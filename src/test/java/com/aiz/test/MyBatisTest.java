package com.aiz.test;

import com.aiz.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZhangYao
 * @version 1.0
 * @className MyBatisTest
 * @description 编写MyBatis测试类
 * @date Create in 23:46 2023/3/27
 */
public class MyBatisTest {

  @Test
  public void test1() throws IOException {
    InputStream resourceAsStream = Resources.getResourceAsStream("com" + File.separator + "aiz" + File.separator + "mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    User queryUser = new User();
    queryUser.setId(11);
    User user = sqlSession.selectOne("user.findUserById", queryUser);
    System.out.println(user);
    System.out.println("MyBatis源码环境搭建成功...");
    sqlSession.close();
  }
}