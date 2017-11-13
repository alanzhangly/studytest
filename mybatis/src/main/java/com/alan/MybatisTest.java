package com.alan;

import com.alan.todo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by Ke Zhang on 2017/9/22.
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception{
//        test1();
        int[] n=new int[]{1,2,3};
//        test2(n);
    }

    public String test2(int[] n){
        for(int i=n.length-1;i>=0;i--){
            System.out.print(n[i]+" ");
        }
        return "";
    }

    private static void test1()throws Exception{
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = ssf.openSession();

        try {
            Student user = session.selectOne("Student.selectStudent", "1");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
