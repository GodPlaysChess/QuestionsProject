package examination.utils;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;


/**
 * author: a.savanovich
 * Date: 11.09.13
 * Time: 4:14
 */
public class TestImageUtils {
    @Test
    public void testCrop() throws Exception {
        OutputStream out = new FileOutputStream(new File("2.png"));
        ImageUtils.crop(new File("1.png"), 192, 240, out);


        byte[] bytes = IOUtils.toByteArray(new FileInputStream(new File("1.png")));
        OutputStream out2 = new FileOutputStream(new File("3.jpg"));
        ImageUtils.crop(bytes, 192, 240, out2);
    }
}
