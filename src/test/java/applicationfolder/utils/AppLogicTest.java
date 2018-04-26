package applicationfolder.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AppLogicTest {
    private AppLogic appLogic;

    @Before
    public void setUp() throws Exception {
        appLogic = new AppLogic();
    }

    @Test
    public void shouldBeNotNull() throws Exception {
        assertNotNull(appLogic.testing("/cpp_easy.txt", true));
        //TODO: Create external file file
    }

    @Test
    public void sizeOfArrayShouldBeEqual10() throws Exception {
        List list = appLogic.testing("/cpp_easy.txt", true);
        assertEquals(10, list.size());
    }

    @Test
    public void e() throws Exception {

    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException() throws Exception {
        appLogic.testing("not_found_file", false);
        appLogic.testing("not_found_file", true);
    }
}