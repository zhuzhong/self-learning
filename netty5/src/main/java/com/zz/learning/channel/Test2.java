package com.zz.learning.channel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class Test2 {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext c=new ClassPathXmlApplicationContext("/com/zz/learning/channel/spring.xml");
        UserService userSerice=c.getBean("userService2Impl",UserService.class);
        User u = new User();
        u.setPass("2222");
        u.setUserName("zzzz");
        userSerice.dealUser(u);
       /* User u = new User();
        u.setPass("2222");
        u.setUserName("zzzz");
        List<Filter<User> >  filters=new ArrayList<Filter<User>>();
        Set<Filter<User>>
        filters = new LinkedHashSet<Filter<User>>();
        Filter<User> fu = new Filter1Impl();
        filters.add(fu);
        filters.add(fu);
        filters.add(fu);
        filters.add(fu);
        new FixedFilterChain<User>(filters).go(u);*/
    }

}
