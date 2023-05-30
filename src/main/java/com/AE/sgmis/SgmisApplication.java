package com.AE.sgmis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
@EnableScheduling
@MapperScan("com.AE.sgmis.mapper")
public class SgmisApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SgmisApplication.class, args);

        System.out.println("""
                ////////////////////////////////////////////////////////////////////
                //                          _ooOoo_                               //
                //                         o8888888o                              //
                //                         88" . "88                              //
                //                         (| ^_^ |)                              //
                //                         O\\  =  /O                              //
                //                      ____/`---'\\____                           //
                //                    .'  \\\\|     |//  `.                         //
                //                   /  \\\\|||  :  |||//  \\                        //
                //                  /  _||||| -:- |||||-  \\                       //
                //                  |   | \\\\\\  -  /// |   |                       //
                //                  | \\_|  ''\\---/''  |   |                       //
                //                  \\  .-\\__  `-`  ___/-. /                       //
                //                ___`. .'  /--.--\\  `. . ___                     //
                //              ."" '<  `.___\\_<|>_/___.'  >'"".                  //
                //            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //
                //            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //
                //      ========`-.____`-.___\\_____/___.-`____.-'========         //
                //                           `=---='                              //
                //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
                //            佛祖保佑       永不宕机     永无BUG                     //
                ////////////////////////////////////////////////////////////////////
                """);
        log.info("sgmis系统启动成功");

        System.out.println("""
                ヾ(@^▽^@)ノ 岳飞越好赛鸽云库管理系统启动成功 .ヽ(^Д^*)/ .
                ███████╗ ██████╗ ███╗   ███╗██╗███████╗
                ██╔════╝██╔════╝ ████╗ ████║██║██╔════╝
                ███████╗██║  ███╗██╔████╔██║██║███████╗
                ╚════██║██║   ██║██║╚██╔╝██║██║╚════██║
                ███████║╚██████╔╝██║ ╚═╝ ██║██║███████║
                ╚══════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝╚══════╝
                                                      \s""");
    }
}
