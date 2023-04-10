package ${mapperPackage};

import org.apache.ibatis.annotations.Mapper;
import ${entityPackage}.${entity}Entity;
import java.util.List;

/**
 * 描述: ${table.comment}
 * author: ${author}
 * date: ${date}
 */
@Mapper
public interface ${entity}Mapper{
    /**
	 * 获取单条数据
	 *
	 * @param id 主键
	 */
     ${entity}Entity get(Integer id);

	/**
	 * 查询数据集
	 * @param baseContentManager
	 * @return
	 */
	List<${entity}Entity> findList(${entity}Entity baseContentManager);

    /**
    * 添加
    * @param baseContentManager
    * @return
    */
    int insert(${entity}Entity baseContentManager);

    /**
    * 更新
    * @param baseContentManager
    * @return
    */
    int update(${entity}Entity baseContentManager);

    /**
    * 删除
    * @param id
    * @return
    */
    int deleteById(Integer id);
}