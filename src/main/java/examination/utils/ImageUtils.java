package examination.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
//
//<dependency>
//    <groupId>net.coobird</groupId>
//    <artifactId>thumbnailator</artifactId>
//    <version>[0.4, 0.5)</version>
//</dependency>

/**
 * author: a.savanovich
 * Date: 11.09.13
 * Time: 4:08
 */
public class ImageUtils {
    public static void crop(File image, int w, int h, OutputStream out) throws IOException {
        Thumbnails.of(image)
            .sourceRegion(Positions.TOP_LEFT, w, h).scale(1.0).outputFormat("png").toOutputStream(out);
    }

    public static void crop(byte[] image, int w, int h, OutputStream out) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        Thumbnails.of(bImageFromConvert)
            .sourceRegion(Positions.TOP_LEFT, w, h).scale(1.0).outputFormat("jpg").toOutputStream(out);
    }
}
