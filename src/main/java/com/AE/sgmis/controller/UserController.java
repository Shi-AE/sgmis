package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.DeleteFailException;
import com.AE.sgmis.exceptions.SaveFailException;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.UserService;
import com.AE.sgmis.util.EncryptUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController implements InitializingBean {

    @Autowired
    private UserService userService;
    @Autowired
    private EncryptUtil encryptUtil;
    @Value("${user.defaultPassword}")
    private String defaultPassword;
    private byte[] defaultPasswordBin;

    /**
     * 一次性初始化为字节数组
     * 减少服务器资源消耗
     */
    @Override
    public void afterPropertiesSet() {
        defaultPasswordBin = defaultPassword.getBytes();
        log.info("{} 初始化 defaultPassword 化为字节数组完成", UserController.class);
    }

    /**
     * 获取成员信息
     */
    @GetMapping
    public Result getUserList(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //gid = gid
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        //设置查询字段 id gid account admin
        wrapper.select("id", "gid", "account", "admin");
        //执行
        List<User> list = userService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 获取管理员的账号名和gid用于血统分享
     */
    @GetMapping("admin")
    public Result getAdmin(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 gid <> gid and admin = true
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("admin", true).notIn("gid", gid);

        //字段
        wrapper.select("account", "gid");

        List<User> list = userService.list(wrapper);

        System.out.println(list);

        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 重置成员密码
     */
    @PutMapping
    public Result resetPassword(@RequestBody User user, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //检查gid安全
        if (!user.getGid().equals(gid)) {
            throw new SaveFailException("用户信息不匹配，请重试");
        }
        //id = id and gid = gid
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", user.getId()).eq("gid", gid);
        //设置字段 admin
        wrapper.select("admin");
        //检测操作对象是否为管理员
        user = userService.getOne(wrapper);
        if (user == null) {
            throw new SaveFailException("用户信息不匹配，请重试");
        }
        if (user.getAdmin()) {
            throw new SaveFailException("无法操作管理员信息");
        }
        //重置密码
        user.setPassword(defaultPasswordBin);
        //密码加密
        encryptUtil.passwordEncrypt(user);
        //执行
        boolean success = userService.update(user, wrapper);
        if (!success) {
            log.error("重置密码传入 {} 发生服务器错误", user);
            throw new SaveFailException("保存失败，请重试");
        }
        return new Result(SuccessCode.Success.code, "用户密码重置成功");
    }

    /**
     * 添加新成员
     */
    @PostMapping
    public Result addUser(@RequestBody User user, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //设置用户信息
        user.setGid(gid);
        user.setAdmin(false);
        user.setPassword(defaultPasswordBin);
        //加密密码
        encryptUtil.passwordEncrypt(user);
        //执行
        boolean success = userService.save(user);
        if (!success) {
            log.error("新建传入 {} 发生服务器错误", user);
            throw new SaveFailException("创建新用户发生错误，请重试");
        }
        return new Result(user, SuccessCode.Success.code, "创建新用户成功，请新用户尽快更改密码");
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("{id}")
    public Result deleteUser(HttpServletRequest request, @PathVariable Long id) {
        System.out.println(id);
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //id = id and gid = gid
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("gid", gid);
        //执行
        boolean success = userService.remove(wrapper);
        if (!success) {
            throw new DeleteFailException("用户不存在，请重试");
        }
        return new Result(SuccessCode.Success.code, "成员删除成功");
    }
}
