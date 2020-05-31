package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    private static SqlSession getSession() throws PropertyVetoException, DocumentException {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        return sqlSessionFactory.openSession();
    }
    @Test
    public void test0_selectCheck() throws Exception {
        SqlSession sqlSession = getSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        for (User user1 : users) {
            System.out.println(user1);
        }


    }
    @Test
    public void test1_selecConditionCheck() throws Exception {
        SqlSession sqlSession = getSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1);
        user.setUsername("wuwuwuwu77777");
        User users = userDao.findByCondition(user);
        System.out.println(users);

    }
    @Test
    public void test2_insert() throws Exception {
        SqlSession sqlSession = getSession();
        User user = new User();
        user.setId(2);
        user.setUsername("wuwuwuwu2");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.insert(user);
    }

    @Test
    public void test3_update() throws Exception {

        SqlSession sqlSession = getSession();
        User user = new User();
        user.setId(2);
        user.setUsername("update2");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.update(user);
    }


    @Test
    public void test4_delete() throws Exception {
        SqlSession sqlSession = getSession();
        User user = new User();
        user.setId(2);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.delete(user);

    }
    @Test
    public void test5_selectResultCheck() throws Exception {
        SqlSession sqlSession = getSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        for (User user1 : users) {
            System.out.println(user1);
        }


    }




}
