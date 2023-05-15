package com.AE.sgmis.controller;

import com.AE.sgmis.exceptions.*;
import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import com.AE.sgmis.pojo.PigeonWrapper;
import com.AE.sgmis.pojo.Xxpz;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.result.SuccessCode;
import com.AE.sgmis.service.PigeonInfoService;
import com.AE.sgmis.service.PigeonService;
import com.AE.sgmis.service.XxpzService;
import com.AE.sgmis.util.FileUtil;
import com.AE.sgmis.util.PigeonFileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/pigeon")
public class PigeonController {

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private PigeonFileUtil pigeonFileUtil;
    @Autowired
    private PigeonService pigeonService;
    @Autowired
    private PigeonInfoService pigeonInfoService;
    @Value("${xxpz.systemGid}")
    private Integer systemGid;
    @Autowired
    private XxpzService xxpzService;
    @Value("${file.pigeon.path}")
    private String basePath;
    @Value("${file.pigeon.type}")
    private String[] typeArray;
    /**
     * 图片类型
     */
    private Set<String> type;
    private Path path;
    private final Set<String> fields = Set.of("ys", "yan", "lx", "state", "jb");

    @PostConstruct
    public void init() {
        //初始化资源
        path = Paths.get(basePath);
        type = Set.of(typeArray);
        //初始化文件夹
        fileUtil.initDirectory(path);
    }

    /**
     * 上传鸽子照片
     */
    @PostMapping("picture")
    public Result uploadPigeonPicture(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new FileSaveException("不能上传空文件");
        }

        //检查文件类型
        fileUtil.checkFileType(file, type);

        //文保存到服务器
        String fileName = fileUtil.storeFile(file, path);

        return new Result(fileName, SuccessCode.Success.code, "保存成功");
    }

    /**
     * 根据id获取鸽子血统书信息
     */
    @GetMapping("{id}")
    public Result getPigeon(@PathVariable Long id, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 id = id and gid = gid
        QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
        pigeonQueryWrapper.eq("id", id).eq("gid", gid);

        Pigeon pigeon = pigeonService.getOne(pigeonQueryWrapper);

        if (pigeon == null) {
            throw new NotFoundException("未找到鸽子信息");
        }

        //获取pid,以保证安全
        Long pid = pigeon.getId();

        //条件 pid = pid
        QueryWrapper<PigeonInfo> pigeonInfoQueryWrapper = new QueryWrapper<>();
        pigeonInfoQueryWrapper.eq("pid", pid);

        PigeonInfo pigeonInfo = pigeonInfoService.getOne(pigeonInfoQueryWrapper);

        //包装
        PigeonWrapper pigeonWrapper = new PigeonWrapper();
        pigeonWrapper.setPigeon(pigeon);
        pigeonWrapper.setPigeonInfo(pigeonInfo);
        return new Result(pigeonWrapper, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据id获取基础信息
     */
    @GetMapping("base/{id}")
    public Result getBasePigeon(@PathVariable Long id, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 id and gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("gid", gid);

        Pigeon pigeon = pigeonService.getOne(wrapper);

        //检查
        if (pigeon == null) {
            throw new NotFoundException("获取失败");
        }

        return new Result(pigeon, SuccessCode.Success.code, "获取成功");
    }

    /**
     * 根据用户获取所有鸽子信息
     */
    @GetMapping
    public Result listPigeon(HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //条件 gid = gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);

        List<Pigeon> list = pigeonService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "获取成功");
    }

    /**
     * 根据页面信息获取鸽子信息
     */
    @PatchMapping
    public Result listPagePigeon(@RequestBody Map<String, Long> pageInfo, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        //获取页面信息
        Long current = pageInfo.get("current");
        Long pageSize = pageInfo.get("pageSize");

        //检查
        if (current == null || pageSize == null) {
            throw new NotFoundException("分页信息错误");
        }

        //设置分页条件
        Page<Pigeon> page = new Page<>();
        page.setCurrent(current)
                .setSize(pageSize)
                .setSearchCount(false);

        //条件 gid = gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);

        page = pigeonService.page(page, wrapper);
        List<Pigeon> records = page.getRecords();

        return new Result(records, SuccessCode.Success.code, "获取成功");
    }

    /**
     * 新增或更新鸽子
     * 记录日志
     */
    @PostMapping
    public Result addOrUpdatePigeon(@RequestBody PigeonWrapper pigeonWrapper, HttpServletRequest request) {
        //解析包装
        Pigeon pigeon = pigeonWrapper.getPigeon();
        PigeonInfo pigeonInfo = pigeonWrapper.getPigeonInfo();
        Long oid = pigeonWrapper.getOid();

        //检查必填参数
        if (pigeon.getRingNumber() == null) {
            throw new SaveFailException("足环不能为空");
        }
        if (pigeon.getSex() == null) {
            throw new SaveFailException("性别不能为空");
        }

        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        //执行保存
        Long pid = pigeonService.saveOrUpdatePigeonById(pigeon, pigeonInfo, oid, gid, account);

        return new Result(pid, SuccessCode.Success.code, "上传成功");
    }

    /**
     * 根据id,整体更新一只鸽子数据
     * 记录日志
     */
    @PutMapping
    public Result updatePigeon(@RequestBody Pigeon pigeon, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        //确保gid安全
        if (!gid.equals(pigeon.getGid())) {
            throw new UserInformationException("用户信息错误");
        }

        //更新时间
        pigeon.setUpdateData(LocalDate.now());

        //保证id、足环存在
        if (pigeon.getId() == null || pigeon.getRingNumber() == null) {
            throw new NotFoundException("鸽子信息错误");
        }

        pigeonService.updatePigeon(pigeon, account);

        return new Result(pigeon, SuccessCode.Success.code, "更新成功");
    }

    /**
     * 根据 字段类型 和 id列表 更新对应内容
     * 记录更新时间
     * 记录日志
     */
    @PutMapping("{field}/{data}")
    public Result updatePigeonByTypeAndIds(@RequestBody List<Long> ids, @PathVariable String field, @PathVariable String data, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        //检查数据完整
        if (field == null || data == null || ids == null || data.equals("null")) {
            throw new SaveFailException("提交信息错误");
        }

        if (ids.size() == 0) {
            throw new SaveFailException("请选择至少一只鸽子");
        }

        //field字段 防止sql注入
        if (!fields.contains(field)) {
            throw new MaliciousSqlInjectionException("不合法的输入");
        }

        pigeonService.updatePigeonByTypeAndIds(ids, field, data, gid, account);

        return new Result(SuccessCode.Success.code, "更新成功");
    }

    /**
     * 根据id删除鸽子
     * 记录日志
     */
    @DeleteMapping("{id}/{sex}")
    public Result removePigeonById(@PathVariable Long id, @PathVariable String sex, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        //检查数据完整
        if (id == null || sex == null || !(sex.equals("雄") || sex.equals("雌"))) {
            throw new SaveFailException("提交信息错误");
        }

        pigeonService.deletePigeonById(id, sex, gid, account);

        return new Result(SuccessCode.Success.code, "删除成功");
    }

    /**
     * 根据pigeons批量删除鸽子
     * 记录日志
     */
    @DeleteMapping()
    public Result removePigeonByIds(@RequestBody List<Pigeon> pigeons, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        pigeonService.deletePigeonByIds(pigeons, gid, account);

        return new Result(SuccessCode.Success.code, "批量删除成功");
    }

    /**
     * 批量分享鸽子血统信息
     * 分享者记录日志
     * 接收者记录日志
     */
    @PostMapping("share/{receiveGid}")
    public Result sharePigeon(@RequestBody List<Long> ids, @PathVariable Long receiveGid, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        if (ids == null || ids.size() == 0 || receiveGid == null) {
            throw new SaveFailException("提交信息错误");
        }

        pigeonService.sharePigeon(ids, receiveGid, gid, account);

        return new Result(SuccessCode.Success.code, "共享成功");
    }

    /**
     * 根据性别并排除子代id获取鸽子
     */
    @PostMapping("parent/{sex}")
    public Result getPigeonParent(@RequestBody List<Long> ids, @PathVariable String sex, HttpServletRequest request) {
        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");

        if (sex == null) {
            throw new NotFoundException("信息有误，请重试");
        }

        //条件 id notin ids and sex = sex and gid = gid
        QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
        wrapper.notIn(ids.size() > 0, "id", ids)
                .eq("sex", sex)
                .eq("gid", gid);


        //字段
        wrapper.select("id", "ring_number", "name", "bloodline", "sex");

        //获取
        List<Pigeon> list = pigeonService.list(wrapper);

        return new Result(list, SuccessCode.Success.code, "获取成功");
    }

    /**
     * 关联血亲关系
     * 记录日志
     */
    @PatchMapping("relate")
    public Result relatePigeon(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        //解析body
        Long id = Long.valueOf((String) body.get("id"));
        String sex = (String) body.get("sex");
        String ringNumber = (String) body.get("ringNumber");
        Long oid = Long.valueOf((String) body.get("oid"));

        //检查信息完整
        if (sex == null) {
            throw new SaveFailException("信息不完整，请重试");
        }

        //获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        pigeonService.relatePigeon(id, sex, ringNumber, oid, gid, account);

        return new Result(SuccessCode.Success.code, "关联成功");
    }

    /**
     * 上传文件并解析
     * 保存鸽子
     * 记录日志
     */
    @PostMapping("file")
    public Result savePigeonByFile(MultipartFile file, HttpServletRequest request) {
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new FileSaveException("不能上传空文件");
        }

        //检出文件格式
        String fileType = fileUtil.checkFileType(file, "application/vnd.ms-excel", "application/x-tika-ooxml", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 获取gid
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long gid = (Long) info.get("gid");
        //获取账号名
        String account = (String) info.get("account");

        //获取检验数据
        //添加条件 gid or gid = systemGid
        QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid).or().eq("gid", systemGid);
        //字段
        wrapper.select("name", "type");
        //查询
        List<Xxpz> list = xxpzService.list(wrapper);

        //封装检验数据
        Map<String, Set<String>> xxpzMap = new HashMap<>();
        xxpzMap.put("yspz", new HashSet<>());
        xxpzMap.put("yanpz", new HashSet<>());
        xxpzMap.put("lxpz", new HashSet<>());
        xxpzMap.put("province", new HashSet<>());
        xxpzMap.put("country", new HashSet<>());
        xxpzMap.put("state", new HashSet<>());
        list.forEach(item -> {
            String type = item.getType();
            Set<String> names = xxpzMap.get(type);
            if (names != null) {
                if (type.equals("province") || type.equals("country")) {
                    names.add(item.getName().split("/")[1]);
                } else {
                    names.add(item.getName());
                }
            }
        });

        List<Map<String, PigeonWrapper>> pigeonWrappers;

        if (fileType.equals("application/vnd.ms-excel")) {
            //旧版
            pigeonWrappers = pigeonFileUtil.getPigeonByXls(file, xxpzMap);
        } else {
            //新版
            pigeonWrappers = pigeonFileUtil.getPigeonByXlsx(file, xxpzMap);
        }

        if (pigeonWrappers == null) {
            throw new FileParseException("解析失败");
        }

        pigeonService.savePigeonByFile(pigeonWrappers, gid, account);

        return new Result(SuccessCode.Success.code, "入库成功");
    }
}
