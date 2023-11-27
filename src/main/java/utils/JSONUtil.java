package utils;

import com.alibaba.fastjson.JSON;

import java.util.List;


/**
 * JSON工具类
 * 处理和JSON相关的所有内容
 */
public class JSONUtil {

    /**
     * 把对象转换成JSON格式的字符串
     *
     * @param entity 指定对象
     * @return 返回JSON格式的字符串
     */
    public static String entity2JSON(Object entity) {
        return JSON.toJSONString(entity);
    }

    /**
     * 把对象列表转换成JSON格式的字符串
     *
     * @param entityList 指定对象列表
     * @return 返回JSON格式的字符串
     */
    public static String entityList2JSON(List<?> entityList) {
        return entity2JSON(entityList);
    }

    /**
     * 把JSON字符串转换成指定类型的对象
     *
     * ? 泛型的通配符，代表的是未知的任意类型，或者说是Object
     * @param json 要转换的数据
     * @param clazz 指定的类型
     * @return 返回Object对象
     */
//    public static Object JSON2Entity(String json, Class<?> clazz) {
//       Object obj = JSON.parseObject(json, clazz);
//       return obj;
//    }

    public static <T> T JSON2Entity(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将JSON数组转换成指定类型的对象列表
     *
     * @param json 数据
     * @param clazz 指定的类型Class对象
     * @param <T> 指定的类型
     * @return 对象列表
     */
    public static <T> List<T> JSONArray2List(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

//    public static List<?> JSONArray2List(String json, Class<?> clazz) {
//        return JSON.parseArray(json, clazz);
//    }

}
