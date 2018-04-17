package applicationfolder.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AppLogicTest {
    @Test
    public void testing() throws Exception {
        AppLogic appLogic = new AppLogic();
        List actual = appLogic.testing("/cpp_easy.txt", true);
        assertNotNull(actual);
    }

}