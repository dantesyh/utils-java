package dantesyh.utils;

import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author dante
 * @since 2023/8/31
 */
class JsonUtilsTest {
    private static final Logger logger = Logger.getLogger(SecureUtils.class.getName());

    record Person(String name, int age) {
    }

    @Test
    void testJson() {
        Person person = new Person("John", 30);
        String json = JsonUtils.toJson(person);
        logger.info("JSON: " + json);

        Person parsedPerson = JsonUtils.fromJson(json, Person.class);
        logger.info("Parsed Person: " + parsedPerson);

        // 示例 JSON 数据
        String jsonArray = "[{\"name\":\"Tag1\",\"aliasName\":1.0,\"price\":\"Price1\",\"aliasPrice\":2.0}, {\"name\":\"Tag2\",\"aliasName\":3.0,\"price\":\"Price2\",\"aliasPrice\":4.0}]";
        String jsonList = "[{\"name\":\"Tag3\",\"aliasName\":5.0,\"price\":\"Price3\",\"aliasPrice\":6.0}, {\"name\":\"Tag4\",\"aliasName\":7.0,\"price\":\"Price4\",\"aliasPrice\":8.0}]";

        // 测试反序列化数组
        CustomizedTag[] tagArray = JsonUtils.fromJsonArray(jsonArray, CustomizedTag[].class);
        logger.info("Array Deserialization:");
        logger.info(Arrays.toString(tagArray));

        // 测试反序列化列表
        List<CustomizedTag> tagList = JsonUtils.fromJsonList(jsonList, CustomizedTag.class);
        logger.info("\nList Deserialization:");
        for (CustomizedTag tag : tagList) {
            logger.info(tag.toString());
        }
        assertEquals(person, parsedPerson);
    }

    static class CustomizedTag {
        private String name;
        private double aliasName;
        private String price;
        private double aliasPrice;

        @Override
        public String toString() {
            return "CustomizedTag{" +
                    "name='" + name + '\'' +
                    ", aliasName=" + aliasName +
                    ", price='" + price + '\'' +
                    ", aliasPrice=" + aliasPrice +
                    '}';
        }
    }
}