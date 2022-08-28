package SpringBoot.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//将log4j2的相关逻辑一起封装到一个类中，方便调用
public class MyLogger {
    private static final Logger logger = LogManager.getLogger();

    public static void loggerInfo(Object msg) {
        logger.info(msg);
    }

    public static void loggerDebug(String msg) {
        logger.debug(msg);
    }
}
