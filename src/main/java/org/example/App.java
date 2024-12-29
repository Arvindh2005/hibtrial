package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Laptop laptop = new Laptop();
        laptop.setLid(106);
        laptop.setLname("Hp");


        Student s = new Student();
        s.setName("Indira");
        s.setRollno(4);
        s.setMarks(100);

        List<Laptop> great = s.getLaptop();

        great.add(laptop);
        s.setLaptop(great);

        laptop.setStudent(s);

//        AlienName an = new AlienName();
//        an.setFname("R");
//        an.setLname("Lakshya");
//        an.setMname("Shri");
//
//        Alien alien = new Alien();
//        alien.setAid(103);
//        alien.setAname(an);
//        alien.setColor("White");

        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        //session.save(laptop);
        //session.save(s);
//        session.flush();
        
        Student s1 = session.get(Student.class, s.getRollno());
        System.out.println(s1.getRollno());

        //alien = (Alien)session.get(Alien.class, 102);

        tx.commit();

        //System.out.println(alien);
    }
}
