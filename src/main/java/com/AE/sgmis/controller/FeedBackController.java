package com.AE.sgmis.controller;

import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.util.QRCodeUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feedback")
public class FeedBackController {

    @Autowired
    private QRCodeUtil qrCodeUtil;

    @Value("${feedback.githubUrl}")
    private String githubUrl;
    @Value("${feedback.guatUrl}")
    private String guatUrl;
    @Value("${feedback.mailUrl}")
    private String mailUrl;
    @GetMapping("github")
    public Result getGithubQrCode() {
        String uuid = IdWorker.get32UUID();
        String qrCode = qrCodeUtil.createQrCode(githubUrl + "?t=" + uuid);
        return new Result(qrCode, SuccessCode.Success.code, "获取成功");
    }

    @GetMapping("guat")
    public Result getGuatQrCode() {
        String uuid = IdWorker.get32UUID();
        String qrCode = qrCodeUtil.createQrCode(guatUrl + "?t=" + uuid);
        return new Result(qrCode, SuccessCode.Success.code, "获取成功");
    }

    @GetMapping("mail")
    public Result getMainQrCode() {
        String uuid = IdWorker.get32UUID();
        String qrCode = qrCodeUtil.createQrCode(mailUrl + "?t=" + uuid);
        return new Result(qrCode, SuccessCode.Success.code, "获取成功");
    }
}
