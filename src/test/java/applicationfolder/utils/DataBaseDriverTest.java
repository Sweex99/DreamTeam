package applicationfolder.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static org.apache.commons.io.IOUtils.readLines;
import static org.junit.Assert.*;

public class DataBaseDriverTest {
    private DataBaseDriver dataBaseDriver;

    @Before
    public void setUp() throws Exception {
        dataBaseDriver = new DataBaseDriver();
    }

    @Test
    public void shouldBeFalseWhenNotCreateNewUserWithExistingLogin() throws Exception {
        assertTrue(dataBaseDriver.registration("foo", "login", "password"));
        assertFalse(dataBaseDriver.registration("bar", "login", "12345"));

    }

    @Test
    public void shouldEditXmlAndChangeNumberAndPercent() throws Exception {
        final String xmlName = "database/users.xml";
        List<String> xmlBeforeTransform = readLines(new FileInputStream(xmlName), "UTF-8");
        int beforeUpdateNumber = Integer.parseInt(dataBaseDriver.getNumber());

        dataBaseDriver.updateStatistic(10);
        assertEquals(100, Integer.parseInt(dataBaseDriver.getPercent()));

        assertEquals(beforeUpdateNumber + 1, Integer.parseInt(dataBaseDriver.getNumber()));

        List<String> xmlAfterTransform = readLines(new FileInputStream(xmlName), "UTF-8");
        assertNotEquals(xmlAfterTransform, xmlBeforeTransform);
    }

    @Test
    public void shouldBeFalseWhenNotFoundPerson() throws Exception {
        assertFalse(dataBaseDriver.searchPerson("foo"));
        assertTrue(dataBaseDriver.searchPerson("login"));
    }
}