package applicationfolder.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static org.apache.commons.io.IOUtils.*;

import static org.junit.Assert.*;

public class DataBaseDriverTest {
    private DataBaseDriver dataBaseDriver;

    @Before
    public void setUp() throws Exception {
        dataBaseDriver = new DataBaseDriver();
    }

    @After
    public void tearDown() throws Exception {
        dataBaseDriver = null;
    }

    @Test
    public void registration() throws Exception {
        dataBaseDriver.registration("foo", "login", "password");
        dataBaseDriver.registration("bar", "login", "12345");

    }

    @Test
    public void updateStatistic() throws Exception {
        final String xmlName = "database/users.xml";
        List<String> xmlBeforeTransform = readLines(new FileInputStream(xmlName), "UTF-8");

        dataBaseDriver.updateStatistic(10);
        int expected = 100;
        int actual = Integer.parseInt(dataBaseDriver.getPercent());
        assertEquals(expected, actual);

        expected = 1;
        actual = Integer.parseInt(dataBaseDriver.getNumber());
        assertEquals(expected, actual);

        List<String> xmlAfterTransform = readLines(new FileInputStream(xmlName), "UTF-8");
        assertNotEquals(xmlAfterTransform, xmlBeforeTransform);
    }

    @Test
    public void searchPerson() throws Exception {
        assertFalse(dataBaseDriver.searchPerson("foo"));
        assertTrue(dataBaseDriver.searchPerson("login"));
    }
}