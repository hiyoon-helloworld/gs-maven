package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.ServerInfo;
import type.ResourceFileType;

public class JsonUtils {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class.getCanonicalName());

    /**
     * 설정 파일을 읽어 해당 클래스에 매핑합니다.
     * @param type 설정파일 타입
     * @return 설정 정보 클래스
     * @throws Exception Exception
     */
    public static ServerInfo getJsonToServerInfo(ResourceFileType type) throws Exception {

        ServerInfo result = null;
        logger.info("Json Content. type: {}", type.getValue());
        String content = FileUtils.getFileContentAsStream(type.getValue());
        logger.info("Json Content. contnet: {}", content);
        if (content != null && content.length() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.readValue(content, new TypeReference<ServerInfo>() {});
        }

        return result;
    }
}
