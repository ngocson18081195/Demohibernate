package son.IMP;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import son.Model.empEntity;
import son.Service.empService;

import java.util.List;

/**
 * Created by ngocson on 30/09/2017.
 */
@Transactional
public class empIMP extends HibernateDaoSupport implements empService {

    public void save(empEntity empEntity) {
        getHibernateTemplate().saveOrUpdate(empEntity);
        System.out.println(empEntity.toString());
    }

    public List<empEntity> LIST() {
        List<empEntity> empEntities = getHibernateTemplate().loadAll(empEntity.class);
        System.out.println(empEntities.toString());
        return empEntities;
    }

    public empEntity tim(Integer ID) {
        return getHibernateTemplate().get(empEntity.class,ID);
    }

    public void remove(empEntity empEntity) {
        getHibernateTemplate().delete(empEntity);
    }


}
