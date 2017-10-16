package utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.ServerInfo;
import type.ResourceFileType;

import java.io.IOException;
import java.util.Map;

public class JsonUtilsTest {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtilsTest.class.getCanonicalName());

    @Test
    public void getResources() {
        try {
            ServerInfo serverInfo = JsonUtils.getJsonToServerInfo(ResourceFileType.SERVER);
            Assert.assertEquals(serverInfo.getPort(), 9090);
            Assert.assertNotNull(serverInfo.getHosts());
        } catch (IOException ioe) {
            logger.error(String.valueOf(ioe.getStackTrace()));
        } catch (Exception ex) {
            logger.error(String.valueOf(ex.getStackTrace()));
        }
    }
}
