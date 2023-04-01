package com.aiz.test;

import com.aiz.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZhangYao
 * @version 1.0
 * @className CacheTest
 * @description CacheTest
 * @date Create in 22:50 2023/4/1
 */
public class CacheTest {

  /**
   * 测试一级缓存
   */
  @Test
  public void firstLevelCacheTest() throws IOException {
    InputStream resourceAsStream = Resources.getResourceAsStream("com" + File.separator + "aiz" + File.separator + "mybatis-config.xml");

    // 2. (1)解析了配置文件，封装configuration对象 （2）创建了DefaultSqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    // 3.问题：openSession()执行逻辑是什么？
    // 3. (1)创建事务对象 （2）创建了执行器对象cachingExecutor (3)创建了DefaultSqlSession对象
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 发起第一次查询，查询ID为11的用户
    User user1 = sqlSession.selectOne("com.aiz.mapper.UserMapper.findByCondition", 11);

    // 更新操作
    User user = new User();
    user.setId(11);
    user.setUsername("夜不收");
    sqlSession.update("com.aiz.mapper.UserMapper.updateUser", user);
    sqlSession.commit();

    // 发起第二次查询，查询ID为11的用户
    User user2 = sqlSession.selectOne("com.aiz.mapper.UserMapper.findByCondition", 11);

    System.out.println(user1 == user2);
    System.out.println(user1);
    System.out.println(user2);

    sqlSession.close();
  }

  /**
   * 测试二级缓存
   */
  @Test
  public void secondLevelCacheTest() throws IOException {

    InputStream resourceAsStream = Resources.getResourceAsStream("com" + File.separator + "aiz" + File.separator + "mybatis-config.xml");

    // 2. (1)解析了配置文件，封装configuration对象 （2）创建了DefaultSqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    // 3.问题：openSession()执行逻辑是什么？
    // 3. (1)创建事务对象 （2）创建了执行器对象cachingExecutor (3)创建了DefaultSqlSession对象
    SqlSession sqlSession1 = sqlSessionFactory.openSession();
    SqlSession sqlSession2 = sqlSessionFactory.openSession();

    // 发起第一次查询，查询ID为11的用户
    User user1 = sqlSession1.selectOne("com.aiz.mapper.UserMapper.findByCondition", 11);

    // **必须要调用sqlSession的commit方法或者close方法，才能让二级缓存生效
    sqlSession1.commit();

    // 更新操作
    SqlSession sqlSession3 = sqlSessionFactory.openSession();
    User user = new User();
    user.setId(11);
    user.setUsername("夜不收");

    sqlSession3.update("com.aiz.mapper.UserMapper.updateUser", user);
    sqlSession3.commit();

    // 第二次查询
    User user2 = sqlSession2.selectOne("com.aiz.mapper.UserMapper.findByCondition", 11);

    System.out.println(user1 == user2);
    System.out.println(user1);
    System.out.println(user2);

    sqlSession1.close();
  }


}
