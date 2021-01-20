package ${package_service};
import ${package_pojo}.${Table};
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:gxh
 * @Description:${Table}业务层接口
 * @Date 2021/1/12
 *****/
public interface ${Table}Service {

    /***
     * ${Table}多条件分页查询
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    List<${Table}> findPage(${Table} ${table}, int page, int size);

    /***
     * ${Table}分页查询
     * @param page
     * @param size
     * @return
     */
    List<${Table}> findPage(int page, int size);

    /***
     * ${Table}多条件搜索方法
     * @param ${table}
     * @return
     */
    List<${Table}> findList(${Table} ${table});

    /***
     * 删除${Table}
     * @param id
     */
    int delete(${keyType} id);

    /***
     * 修改${Table}数据
     * @param ${table}
     */
    int update(${Table} ${table});

    /***
     * 新增${Table}
     * @param ${table}
     */
    int add(${Table} ${table});

    /**
     * 根据ID查询${Table}
     * @param id
     * @return
     */
     ${Table} findById(${keyType} id);

    /***
     * 查询所有${Table}
     * @return
     */
    List<${Table}> findAll();
}
