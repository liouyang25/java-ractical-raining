package dao;

import java.util.List;

/**
 * @author li
 */
public interface IDataAccess {
    /**
     * 公共获取实体列表方法
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> List<T> getList(Class<T> clazz);

    /**
     * 公共根据id获取实体方法
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getEntityById(Class<T> clazz, String id) throws Exception;
}
