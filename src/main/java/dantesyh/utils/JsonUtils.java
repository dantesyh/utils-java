package dantesyh.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * JSON 工具类
 *
 * @author dante
 * @since 2023/8/31
 */
public class JsonUtils {
    private JsonUtils() {
    }

    private static final Gson gson = new GsonBuilder().create();

    /**
     * 将JSON字符串转换为指定类型的对象。
     *
     * @param json  JSON字符串
     * @param clazz 目标类的Class对象
     * @param <T>   目标类的类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将JSON字符串转换为指定类型的对象。
     *
     * @param json JSON字符串
     * @param type 目标类型的Type对象
     * @param <T>  目标类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将JSON字符串转换为指定类型的对象。
     *
     * @param json      JSON字符串
     * @param typeToken TypeToken对象，包含目标类型信息
     * @param <T>       目标类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 将JSON字符串转换为包含指定类型元素的List。
     *
     * @param json  JSON字符串
     * @param clazz 目标元素的Class对象
     * @param <T>   目标元素的类型
     * @return 转换后的List
     */
    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, listType);
    }

    /**
     * 将对象转换为JSON字符串。
     *
     * @param object 要转换为JSON的对象
     * @return JSON字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}
