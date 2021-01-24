package com.mkitchen.server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateManyToMany {

    public static void main(String[] args) {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//
//        SessionFactory factory = new MetadataSources(registry)
//                .buildMetadata()
//                .buildSessionFactory();
//
//        Session session = factory.openSession();
//
//        session.close();
//        factory.close();
        Configuration cfg = new Configuration();
        cfg.configure();
        //SessionFactory sf=cfg.configure().buildSessionFactory();//*过期方法*解析Hibernate.cfg.xml 然后返回一个已经拥有配置选项的Configuration
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        //SessionFactory类似数据库库的一个Connection
        SessionFactory sf =cfg.buildSessionFactory(sr);
        //打开一个连接
        Session session =sf.openSession();
        //开始一个事务
//        session.beginTransaction();
//        session.save(s);
//        //获得一个事务,并提交
//        session.getTransaction().commit();
//        //关闭连接
        session.close();
        //关闭工厂
        sf.close();
    }
}
