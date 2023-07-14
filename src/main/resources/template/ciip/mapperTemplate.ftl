package ${mapperPackage};

import com.shinow.brick.ormplus.mybatis.annotaion.Dao;
import com.shinow.brick.ormplus.mybatis.sqlinjector.BaseDAO;
import org.springframework.stereotype.Repository;

/**
 * @author: ${author}
 * @date: ${date}
 */
@Dao
@Repository
public interface ${entity}DAO extends BaseDAO<${entity}> {

}