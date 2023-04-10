package ${servicePackage};

import com.shinow.brick.ormplus.mybatis.service.BaseService;

/**
 * @author: ${author}
 * @date: ${date}
 */
public interface ${entity}Service extends BaseService<${entity}> {
    void update(${entity} entity);

    void logicDelete(String uuid);
}
