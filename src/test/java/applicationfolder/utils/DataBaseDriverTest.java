package applicationfolder.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
        dataBaseDriver.updateStatistic(10);
        int expected = 100;
        int actual = Integer.parseInt(dataBaseDriver.getPercent());
        assertEquals(expected, actual);
    }

    @Test
    public void searchPerson() throws Exception {
        assertFalse(dataBaseDriver.searchPerson("foo"));
        assertTrue(dataBaseDriver.searchPerson("login"));
    }
}