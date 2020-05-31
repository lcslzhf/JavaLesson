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
    public void test1_insert() throws Exception {
        SqlSession sqlSession = getSession();

        //调用
        User user = new User();
        user.setId(7);
        user.setUsername("wuwuwuwu77777");


        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.insert(user);
    }

    @Test
    public void test2_update() throws Exception {

        SqlSession sqlSession = getSession();

        //调用
        User user = new User();
        user.setId(3);
        user.setUsername("update3");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.update(user);
    }


    @Test
    public void test3_delete() throws Exception {
        SqlSession sqlSession = getSession();

        User user = new User();
        user.setId(2);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.delete(user);

    }




}
