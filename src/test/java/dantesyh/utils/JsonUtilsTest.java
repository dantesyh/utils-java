package dantesyh.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author dante
 * @since 2023/8/31
 */
class JsonUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(SecureUtils.class);

    record Person(String name, int age) {
    }

    @Test
    void testJson() {
        Person person = new Person("John", 30);
        String json = JsonUtils.toJson(person);
        logger.debug("JSON: " + json);

        Person parsedPerson = JsonUtils.fromJson(json, Person.class);
        logger.debug("Parsed Person: " + parsedPerson);

        assertEquals(person, parsedPerson);
    }
}