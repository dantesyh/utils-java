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

        assertEquals(person, parsedPerson);
    }

    @Test
    void testJsonArray() {
        // 示例 JSON 数据
        Person[] people = {
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 22)
        };
        String peopleStr = JsonUtils.toJson(people);

        // 测试反序列化数组
        Person[] peopleArray = JsonUtils.fromJson(peopleStr, Person[].class);
        logger.info("Array Deserialization:");
        logger.info(Arrays.toString(peopleArray));
        assertEquals(Arrays.toString(people), Arrays.toString(peopleArray));

        // 测试反序列化列表
        List<Person> peopleList = JsonUtils.fromJsonList(peopleStr, Person.class);
        logger.info("List Deserialization:");
        peopleList.forEach(person -> logger.info(person.toString()));
        assertEquals(Arrays.toString(people), Arrays.toString(peopleList.toArray()));

        // 测试反序列化数组
        Person[] people1 = JsonUtils.fromJson(peopleStr, TypeToken.get(Person[].class));
        logger.info("Array Deserialization:");
        logger.info(Arrays.toString(people1));
        assertEquals(Arrays.toString(people), Arrays.toString(people1));

        // 测试反序列化数组
        Person[] people2 = JsonUtils.fromJson(peopleStr, TypeToken.get(Person[].class).getType());
        logger.info("Array Deserialization:");
        logger.info(Arrays.toString(people2));
        assertEquals(Arrays.toString(people), Arrays.toString(people2));

    }
}