package com.zz.learning.channel;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import c.z.zcommon.channel.Filter;
import c.z.zcommon.channel.FixedFilterChain;

/**
 * @author Administrator
 *
 */
@Service
public class UserService2Impl implements UserService {

    /**
     * 这有两类实现Filter接口的实现类，一类是Filter<User>,一类是Filter<Dept>， 在spring3中有bug，体现在两点，
     * 1.对于本示例　会注入Filter<Dept>类型的实现类进来，
     * ２.对于filter的执行顺序问题，对于没有实现order接口的，默认是最优先的;
     * 所以在spring3的情况下，应用这个设计模式，只能将Filter接口及FilterChain 接口具体化
     * 
     * 
     * 这两个问题，在spring4不存在了即spring4解决了这个问题
     */
    @Autowired
    private List<Filter<User>> filters;

    @PostConstruct
    public void init() {

        Collections.sort(filters, AnnotationAwareOrderComparator.INSTANCE);

    }
/**
 * 从注入的users,depts,filters,可以发现spring3只能识别第一层范型式，而对于Filter<User>与Filter<Dept>在进
 * 行List注入时是无法识别的
 */
    @Autowired
    private List<IUser> users;
    @Autowired
    private List<IDept> depts;

    @Override
    public User dealUser(User u) {

        try {
            new FixedFilterChain<User>(filters).go(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

}
