import com.alibaba.fastjson2.JSON;
import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.Rational;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.study.service.impl.PriceSyncVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.Collections;

/**
 * @description:
 * @date: 2021/11/4 17:01
 * @author: LiHaoHan
 * @program: PACKAGE_NAME
 */
@Slf4j
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = App.class)
public class JsonTest {
    @org.junit.Test
    public void test() {
        PriceSyncVo syncVo = new PriceSyncVo();
        syncVo.setIV_PRSDT("1");
        PriceSyncVo.ITITEMDTO ititemdto = new PriceSyncVo.ITITEMDTO();
        ititemdto.setMATNR("1");

        syncVo.setIT_ITEM(Collections.singletonList(ititemdto));
        String jsonString = JSON.toJSONString(syncVo);
        System.out.println("jsonString = " + jsonString);
    }




    @org.junit.Test
    public void pai() {

    }

    @Test
    public void test1() {
        try {
            File jpegFile = new File("C:\\Users\\plums\\Desktop\\a1dd7267-795f-42df-9487-79f58ed9d8c4.jpg"); // 替换为你的图片路径
            Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
            GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

            if (directory.containsTag(GpsDirectory.TAG_LATITUDE)) {
                String latitudeRef = directory.getString(GpsDirectory.TAG_LATITUDE);
                Rational[] latitude = directory.getRationalArray(GpsDirectory.TAG_LATITUDE);
                String longitudeRef = directory.getString(GpsDirectory.TAG_LONGITUDE);
                Rational[] longitude = directory.getRationalArray(GpsDirectory.TAG_LONGITUDE);

                if (latitude != null && longitude != null) {
                    double lat = convertRationalToDegree(latitudeRef, latitude);
                    double lon = convertRationalToDegree(longitudeRef, longitude);

                    System.out.println("Latitude: " + lat);
                    System.out.println("Longitude: " + lon);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double convertRationalToDegree(String ref, Rational[] coordinates) {
        double degree = coordinates[0].doubleValue() + (coordinates[1].doubleValue() / coordinates[2].doubleValue()) / 60.0;
        if ("S".equals(ref) || "W".equals(ref)) {
            degree = degree * -1;
        }
        return degree;
    }


}