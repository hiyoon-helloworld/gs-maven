package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.ServerInfo;
import type.ResourceFileType;

import java.io.IOException;

public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static ServerInfo getJsonToMap(ResourceFileType type) throws IOException, Exception {

        ServerInfo result = null;
        String content = FileUtils.getFileContents(type.getValue());
        logger.info("Json Content. contnet: {}", content);
        if (content != null && content.length() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.readValue(content, new TypeReference<ServerInfo>() {});
        }

        return result;
    }
}
