package com.AE.sgmis.runner;

import com.AE.sgmis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisConnectionRunner implements ApplicationRunner {

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 最大尝试连接数
     */
    private final int max = 5;

    /**
     * 测试redis连接
     */
    @Override
    public void run(ApplicationArguments args) {
        boolean connect = false;
        for (int i = 0; i < max; i++) {
            boolean ping = redisUtil.PING();
            if (ping) {
                connect = true;
                break;
            }
        }
        if (!connect) {
            log.error("redis连接失败");

            //连接失败退出程序
            System.exit(1);
        }

        log.info("redis连接成功");
        System.out.println("""
                                                                 \s
                                                                 \s
                                                                 \s
                                       ,---,  ,--,               \s
                  __  ,-.            ,---.'|,--.'|               \s
                ,' ,'/ /|            |   | :|  |,      .--.--.   \s
                '  | |' | ,---.      |   | |`--'_     /  /    '  \s
                |  |   ,'/     \\   ,--.__| |,' ,'|   |  :  /`./  \s
                '  :  / /    /  | /   ,'   |'  | |   |  :  ;_    \s
                |  | ' .    ' / |.   '  /  ||  | :    \\  \\    `. \s
                ;  : | '   ;   /|'   ; |:  |'  : |__   `----.   \\\s
                |  , ; '   |  / ||   | '/  '|  | '.'| /  /`--'  /\s
                 ---'  |   :    ||   :    :|;  :    ;'--'.     / \s
                        \\   \\  /  \\   \\  /  |  ,   /   `--'---'  \s
                         `----'    `----'    ---`-'              \s
                                                                 \s""");
    }
}
