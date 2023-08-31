package dantesyh.utils;

import org.junit.jupiter.api.Test;

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
}