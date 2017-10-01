package son.Service;

import org.springframework.transaction.annotation.Transactional;
import son.Model.empEntity;

import java.util.List;

/**
 * Created by ngocson on 30/09/2017.
 */
public interface empService {
    void save(empEntity empEntity);
    List<empEntity> LIST();
    empEntity tim(Integer ID);
    void remove(empEntity empEntity);
}
