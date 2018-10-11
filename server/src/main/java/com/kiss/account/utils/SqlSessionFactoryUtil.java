package com.kiss.account.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSession 工具类
 */
public class SqlSessionFactoryUtil {

    private static String RESOURCE = "mybatis-config.xml";

    private static SqlSessionFactory sqlSessionFactory;

    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

    /**
     * 初始化 SqlSession 工厂对象
     */
    public static void initSqlSessionFactory() {
        try {

            InputStream is = Resources.getResourceAsStream(RESOURCE);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取 SqlSession 工厂对象
     *
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /**
     * 关闭 SqlSession 对象
     */
    public static void close() {

        SqlSession session = threadLocal.get();

        if (session != null) {
            session.close();
            threadLocal.set(null);
        }
    }
}
