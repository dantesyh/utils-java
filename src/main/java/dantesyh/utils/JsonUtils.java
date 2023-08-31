package dantesyh.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
