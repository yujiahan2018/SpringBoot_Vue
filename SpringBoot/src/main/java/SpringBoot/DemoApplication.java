package SpringBoot;

import SpringBoot.logger.MyLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        MyLogger.loggerInfo("程序启动...");
        SpringApplication.run(DemoApplication.class, args);
    }

}
