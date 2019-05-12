package net.quadratic.dao;

import net.quadratic.entity.Params;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParamsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public long save(Params params) {
        return (Long) sessionFactory.getCurrentSession().save(params);
    }
}
