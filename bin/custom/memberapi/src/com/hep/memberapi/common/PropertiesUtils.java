package com.hep.memberapi.common;

import com.hep.memberapi.manager.impl.ProductManagerImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 15:22
 */
@Component
public class PropertiesUtils {
    private static final Log LOGGER = LogFactory.getLog(ProductManagerImpl.class);

    public static Properties getProperties(String path) {
        InputStream is = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        final String propertiesPath = PropertiesUtils.class.getClassLoader().getResource(path).getPath();
        try {
            properties.load(is);
            is.close();//是否加载完后就可以关闭
        } catch (IOException e) {
            LOGGER.error("can not load the properties file in the path:"
                    + propertiesPath);
            e.printStackTrace();
        }
        return properties;
    }

    public static boolean store(Properties properties, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            properties.store(fos, "写入配置文件!");
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.error("写入配置文件错误，配置文件未找到。");
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("写入配置文件错误，IO流错误。");
            e.printStackTrace();
        }
        return false;
    }
}
