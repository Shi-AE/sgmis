# 运行方法

- 后端（spring boot）位于 `/sgmis/src`  配置 `src/main/resources/application.yml` 中`mysql`数据库和`redis`数据库
- 前端（vite + vue）位于 `v-dev/sgmis` 进入文件夹执行
  - 配置文件位于 `v-dev/sgmis/vite.config.js`
  - `npm install`
  - `npm run dev`
- Android（java原生）端 位于`smgis/Android`

### tip

登录表单的默认值
account：root
password：admin123


密码使用`Argon2`算法加密，依赖第三方库`org.bouncycastle`
加密配置位于`application.yml`中
``` yml
encrypt:
  memoryPowOfTwo: 13
  iterations: 9
  parallelism: 5
  hashLength: 512
```
由于将参数设置过高，执行速度较慢，可自行调整参数，调整参数后需**重新创建用户**
可以在测试类中通过这段代码创建用户
```java
@SpringBootTest
public class AdminTest {

    @Autowired
    private UserService userService;
    @Autowired
    private EncryptUtil encryptUtil;

    @Test
    void add() {
        String account = "user123";
        String password = "123456";
        Boolean admin = true;
        User user = User
                .builder()
                .account(account)
                .password(password.getBytes())
                .admin(admin)
                .gid(IdWorker.getId())
                .build();
        encryptUtil.passwordEncrypt(user);
        System.out.println(user);
        userService.save(user);
    }
}
```
![image](https://github.com/Shi-AE/sgmis/assets/111409670/0312e8e5-8981-4b7a-8ff1-b699d9aa4cb5)

# sgmis 团队协作赛鸽数据管理系统

## 1 项目分析

### 1.1 项目概述

互联网时代对鸽舍数据管理提出了新的任务和要求，借助计算机网络和IT技术等手段可以推进和提升鸽舍数据管理的质量和成效。基于网络技术的智慧鸽舍数据管理系统是一套完整的创新鸽舍数据管理模式，提供数字化解决方案，能够使鸽舍数据的收集、存储和分析更加便捷、高效、规范和富有成效。该系统通过利用计算机网络和IT技术，实现鸽舍数据的自动化录入、实时更新和统计分析，帮助鸽舍管理员更好地管理和监控鸽舍数据。同时，系统还提供可视化的数据展示和报告生成功能，使用户能够直观地了解鸽舍的运营情况和鸽子的健康状况。通过使用智慧鸽舍数据管理系统，鸽舍管理工作将更加便捷高效，提升鸽舍数据管理的质量和成效。

### 1.2 项目任务

设计一个多功能系统，用于团队协作管理鸽子数据。该系统将提供以下功能：

1. 鸽子数据录入、编辑和展示：用户可以录入和编辑鸽子的相关数据，包括鸽子的基本信息、血统信息等。系统将提供界面展示鸽子数据，以便团队成员查看和管理。
2. 鸽子数据展示：系统将提供多种展示方式，包括以血统书形式展示鸽子的家谱关系，以及展示鸽子的平辈信息和子代信息等。
3. 血统书形式录入和快速入库：用户可以使用血统书形式录入鸽子的家谱信息，并将其快速存入数据库中。此外，系统还支持通过文件导入的方式批量录入鸽子数据。
4. 批量编写和高级批量操作：系统提供批量编写功能，方便用户同时编辑多个鸽子的信息。此外，还提供高级批量操作功能，例如批量修改特定属性、批量删除等。
5. 多团队共享鸽子血统：系统支持多团队共享鸽子的血统信息。
6. 鸽棚巢箱管理鸽子：系统提供鸽棚和巢箱管理功能，用户可以记录和管理鸽子的栖息地信息，方便团队成员对鸽子的定位和管理。
7. 记录操作日志：系统将记录用户的操作日志，包括数据录入、编辑、删除等操作，以便团队成员追溯和审查操作历史。
8. 信息选项字典：系统提供信息选项字典，包括鸽子属性、血统书格式等选项，以确保数据的一致性和准确性。
9. 鸽舍信息编辑：用户可以编辑鸽舍的相关信息，包括鸽舍的名称、地址等，以便更好地管理鸽子的栖息环境。
10. 血统书展示信息编辑：系统提供血统书展示信息的编辑功能，用户可以自定义展示血统书时所显示的字段和格式。
11. 用户信息管理：系统提供用户信息管理功能，包括用户账户安全和用户注销。
12. 团队信息管理：管理员可以管理团队成员添加、删除、权限管理、了解团队成员在线情况。
13. 登录信息记录：系统将记录用户的登录信息，包括登录时间、IP地址等，以确保账户安全和追踪登录历史。
14. 信息统计展示：系统将提供鸽子数据的统计展示与分析功能，帮助用户了解团队的整体情况。

### 1.3 系统依赖

#### 1.2.1 运行时环境

本系统基于 B/S 架构，用户端通过瘦客户端浏览器通过 URL 访问与服务端建立服务连接

![image-20230607152247388](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230607152247388.png?raw=true)

服务端环境，客户端环境要求如下： 

1. 服务端系统环境： CentOS 7.9 x86 或 Linux、Unix 内核皆可，在 jvm 的 Xms64m,Xmx128m 约束下，系统硬件要求最低内存 2G，磁盘 40G，4vCPU 方能 正常运行。 
2.  客户端环境：推荐使用谷歌 Chrome 浏览器或支持 Chrome 内核的浏览器，不推荐 IE8 及以下浏览器访问。

#### 1.2.2 运行时基本软件环境

1. JRE17，Java Runtime Environment，此软件为 Java 项目运行时环境。JRE17是支持spring boot3.x的最低运行环境要求
2. Mysql 服务，系统使用 Mysql8.30 作为项目数据库，因此务必保证 Mysql 服务正 常运行，默认开放 3306 端口对外提供服务，运行失败时，可使用 lsof -i:3306 指 令排查端口占用情况，并结合 ps aux | grep [pid]进行排查端口占用应用。
3. Redis 服务，系统默认使用单体 Redis 模式为平台提供缓存服务，用于存储权限， 临时产生的数据等，默认端口 6379。
4. Nginx 服务，为前端页面提供静态资源访问服务，以及为后端服务提供反向代理能力。

##### 运行环境2.0

1. docker容器 24.0.7 使用 `docker-compose up -d` 部署
2. Docker Compose version v2.21.0
3. 结尾处提供部署细节文件

#### 1.2.3 开发环境

1. IDEA 2023.3
2. JDK20，Java 开发工具包
3. Maven 3.8.1，本项目的包管理工具，方便自动导入，版本管理，依赖处理，便 捷打包发布，使用前提需要联网。
4. datagrip 2023.3，Mysql 服务 UI 管理工具。
5. WebStorm 2023.3，前端编码工具。
6. Git+Gihub，项目版本管理。

#### 1.2.4 开发框架

1. 后端：
   1. SpringBoot 3.1.0，对 Spring 以及 SpringMVC 的进一步封装实现了自 动装配能力。
2. 前端：
   1. Vue3.js 相关开发套件。

#### 1.2.5 中间件及第三方模块依赖

1. 后端
   1. Redis: 一个高性能的缓存服务，可用于缓存数据、提高系统性能等方面。
   2. MyBatis、MyBatis plus: 优秀的持久层框架，用于数据库操作和ORM（对象关系映射）。
   3. bcprov-jdk15on: 密码加密工具，提供了密码学算法的实现，用于数据加密和解密操作。
   4. lombok: 一个Java库，可以通过注解简化Java类的开发，减少样板代码。
   5. jwt: JSON Web Token（JWT）的Java实现，用于生成和验证基于令牌的身份验证。
   6. logback-classic: 一个灵活的日志框架，用于记录应用程序的日志信息。
   7. tika-core: 一个开源的内容分析工具，用于从各种文档中提取和解析元数据和文本内容。
   8. poi、poi-ooxml: 用于处理Microsoft Office文档（如Excel、Word）的Java库，提供了读写和操作这些文档的功能。
   9. UserAgentUtils: 用于解析和识别用户代理字符串（User-Agent）的工具，可用于获取用户的浏览器和操作系统信息。
   10. com.google.zxing:core: Google开发的二维码生成和解析库，可用于生成和解析二维码图像。
   11. docker: 服务器端部署容器搭建工具
2. 前端
   1. axios: 一个基于Promise的HTTP客户端，用于在浏览器和Node.js中发送HTTP请求。
   2. dayjs: 一个轻量级的日期处理库，用于解析、格式化和操作日期。
   3. echarts: 一个强大的数据可视化库，用于创建各种图表和可视化效果。
   4. json-bigint: 一个用于处理大整数的JSON解析和序列化库。
   5. arco-design: 提供了一系列现代化的UI组件和样式，用于构建用户界面。

### 1.4 UML分析

#### 1.4.1  系统的功能需求

根据上述对多人协作赛鸽信息管理系统的分析，可以得出对系统管理员、团队管理员、组员功能性分析：

系统管理员功能：

(1)  分配团队管理员账号

(2)  重置团队管理员密码

(3)  设置系统公告

(4)  设置帮助信息

(5)  接受用户反馈



 

团队管理员功能：

(1)  拥有账号

(2)  登录

(3)  修改密码

(4)  修改个人信息

(5)  添加鸽子信息

(6)  鸽子快速入库

(7)  文件方式快速入库

(8)  修改鸽子信息

(9)  批量修改鸽子信息

(10) 查看鸽子信息

(11) 删除鸽子信息

(12) 填写鸽子日志

(13) 共享鸽子信息

(14) 将鸽子加入巢箱

(15) 查看操作日志

(16) 查看统计信息

(17) 新建鸽棚巢箱

(18) 修改巢箱信息

(19) 删除鸽棚巢箱

(20) 查看鸽棚巢箱

(21) 查看巢箱鸽子

(22) 添加私有选项名称

(23) 修改私有选项名称

(24) 查看私有选项名称

(25) 删除私有选项名称

(26) 修改鸽舍信息

(27) 查看鸽舍信息

(28) 修改血统书信息

(29) 查看血统书信息

(30) 新增组员

(31) 查看组员

(32) 重置组员密码

(33) 删除组员

(34) 查看组员在线情况

(35) 查看组员登录信息

(36) 查看帮助信息

(37) 联系系统管理员

 

组员功能：

(1)  拥有账号

(2)  登录

(3)  修改密码

(4)  修改个人信息

(5)  添加鸽子信息

(6)  鸽子快速入库

(7)  文件方式快速入库

(8)  修改鸽子信息

(9)  批量修改鸽子信息

(10) 查看鸽子信息

(11) 删除鸽子信息

(12) 填写鸽子日志

(13) 将鸽子加入巢箱

(14) 查看操作日志

(15) 查看统计信息

(16) 新建鸽棚巢箱

(17) 修改巢箱信息

(18) 查看鸽棚巢箱

(19) 查看巢箱鸽子

(20) 查看帮助信息

(21) 联系系统管理员

 

#### 1.4.2  需求模型设计

##### 1.4.2.1 系统参与者

根据对多人协作赛鸽信息管理系统的分析，可以确定系统有三类用户：系统管理员、团队管理员、组员对系统参与者的描述如下。

1)   系统管理员

**描述**: 系统管理员可以分配团队管理员账号、重置团队管理员密码、设置系统公告、设置帮助信息、接受用户反馈

 

2)   团队管理员

**描述**: 团队管理员可以登录、修改密码、修改个人信息、添加鸽子信息、鸽子快速入库、文件方式快速入库、修改鸽子信息、查看鸽子信息、批量修改鸽子信息、删除鸽子信息、填写鸽子日志、共享鸽子信息、将鸽子加入巢箱、查看操作日志、查看统计信息、新建鸽棚巢箱、修改巢箱信息、查看鸽棚巢箱、查看巢箱鸽子、添加私有选项名称、修改私有选项名称、查看私有选项名称、删除私有选项名称、修改鸽舍信息、查看鸽舍信息、修改血统书信息、查看血统书信息、新增组员、查看组员、重置组员密码、删除组员、查看组员在线情况、查看组员登录信息、查看帮助信息、联系系统管理员。

 

3)   团队成员

**描述**: 团队成员可以登录、修改密码、修改个人信息、添加鸽子信息、鸽子快速入库、文件方式快速入库、修改鸽子信息、批量修改鸽子信息、查看鸽子信息、删除鸽子信息、填写鸽子日志、将鸽子加入巢箱、查看操作日志、查看统计信息、新建鸽棚巢箱、修改巢箱信息、查看鸽棚巢箱、查看巢箱鸽子、查看帮助信息、联系系统管理员。

 

##### 1.4.2.2 系统用例

在上述中，已经识别了两个参与者，接下来通过对需求的进一步分析，可以确定系统中有如下用例存在。

(1)  登录

本用例给团队管理员和团队成员实现登录的功能 

(2)  修改密码

本用例给团队管理员和团队成员实现修改密码的功能

(3)  修改个人信息

本用例给团队管理员和团队成员实现修改个人信息的功能

(4)  添加鸽子信息

本用例给团队管理员和团队成员实现添加鸽子信息的功能

(5)  鸽子快速入库

本用例给团队管理员和团队成员实现鸽子快速入库的功能

(6)  文件方式快速入库

本用例给团队管理员和团队成员实现文件方式快速入库的功能

(7)  修改鸽子信息

本用例给团队管理员和团队成员实现修改鸽子信息的功能

(8)  批量修改鸽子信息

本用例给团队管理员和团队成员实现批量修改鸽子信息的功能

(9)  查看鸽子信息

本用例给团队管理员和团队成员实现查看鸽子信息的功能

(10)      删除鸽子信息

本用例给团队管理员和团队成员实现删除鸽子信息的功能

(11)      填写鸽子日志

本用例给团队管理员和团队成员实现填写鸽子日志的功能

(12)      共享鸽子信息

本用例给团队管理员实现共享鸽子信息的功能

(13)      将鸽子加入巢箱

本用例给团队管理员和团队成员实现将鸽子加入巢箱的功能

(14)      查看操作日志

本用例给团队管理员和团队成员实现查看操作日志的功能

(15)      查看统计信息

本用例给团队管理员和团队成员实现查看统计信息的功能

(16)      新建鸽棚巢箱

本用例给团队管理员和团队成员实现新建鸽棚巢箱的功能

(17)      修改巢箱信息

本用例给团队管理员和团队成员实现修改巢箱信息的功能

(18)      查看鸽棚巢箱

本用例给团队管理员和团队成员实现查看鸽棚巢箱的功能

(19)      查看巢箱鸽子

本用例给团队管理员和团队成员实现查看巢箱鸽子的功能

(20)      添加私有选项名称

本用例给团队管理员实现添加私有选项名称的功能

(21)      修改私有选项名称

本用例给团队管理员实现修改私有选项名称的功能

(22)      查看私有选项名称

本用例给团队管理员实现查看私有选项名称的功能

(23)      删除私有选项名称

本用例给团队管理员实现共删除私有选项名称的功能

(24)      修改鸽舍信息

本用例给团队管理员实现修改鸽舍信息的功能

(25)      查看鸽舍信息

本用例给团队管理员实现查看鸽舍信息的功能

(26)      修改血统书信息

本用例给团队管理员实现修改血统书信息的功能

(27)      查看血统书信息

本用例给团队管理员实现查看血统书信息的功能

(28)      新增组员

本用例给团队管理员实现新增组员的功能

(29)      查看组员

本用例给团队管理员实现查看组员的功能

(30)      重置组员密码

本用例给团队管理员实现重置组员密码的功能

(31)      删除组员

本用例给团队管理员实现删除组员的功能

(32)      查看组员在线情况

本用例给团队管理员实现查看组员在线情况的功能

(33)      查看组员登录信息

本用例给团队管理员实现查看组员登录信息的功能

(34)      查看帮助信息

本用例给团队管理员和团队成员实现查看帮助信息的功能 

(35)      联系系统管理员

本用例给团队管理员和团队成员实现联系系统管理员的功能 

(36)      分配团队管理员账号

本用例给系统管理员实现分配团队管理员账号的功能 

(37)      重置团队管理员密码

本用例给系统管理员实现重置团队管理员密码的功能

(38)      设置系统公告

本用例给系统管理员实现设置系统公告的功能

(39)      设置帮助信息

本用例给系统管理员实现设置帮助信息的功能

(40)      接受用户反馈

本用例给系统管理员实现接受用户反馈的功能

 

 

得出参与者和用例后，还要得出他们之间的关系。

登录、修改密码、修改个人信息、添加鸽子信息、鸽子快速入库、文件方式快速入库、修改鸽子信息、批量修改鸽子信息、查看鸽子信息、删除鸽子信息、填写鸽子日志、将鸽子加入巢箱、查看操作日志、查看统计信息、新建鸽棚巢箱、修改巢箱信息、查看鸽棚巢箱、查看巢箱鸽子、查看帮助信息、联系系统管理员是团队管理员和团队成员共有的；共享鸽子信息、添加私有选项名称、修改私有选项名称、查看私有选项名称、删除私有选项名称、修改鸽舍信息、查看鸽舍信息、修改血统书信息、查看血统书信息、新增组员、查看组员、重置组员密码、删除组员、查看组员在线情况、查看组员登录信息是团队管理员拥有的；系统管理员可以分配团队管理员账号、重置团队管理员密码、设置系统公告、设置帮助信息、接受用户反馈是系统管理员拥有的。

#### 1.4.3 用例图

##### 1.4.3.1  系统管理员用例图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image002.png)

图2-1 系统管理员用例图

##### 1.4.3.2  团队管理员用例图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image004.png)

图2-2 2.3.2  团队管理员用例图

 

##### 1.4.3.3 团队成员用例图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image006.png)

图2-2 2.3.3  团队成员用例图

 

##### 1.4.3.4 用例的事件流描述

a. 登录用例图事件流描述

\1. 登录

1.1前置条件

无

1.2后置条件（Post-Conditions）

如果这个用例成功，系统显示登录成功并跳转到首页；如果用例失败，系统显示账号不存在或者密码错误，系统状态不发生变化。

1.3扩充点

自动登录

1.4事件流

1.4.1基流

当团队管理员或团队成员打开登录界面，用例启动。

团队管理员或团队成员提供token来检查token是否符合免登录规则

1.4.2分支流

如果提供的token不符合免登录规则，则要求团队管理员或团队成员提供账号密码，来确认登录权限

1.4.3替代流

E-1:如果符合面登录条件则跳转到首页，用例终止

E-2:如果登录失败则要求用户重新输入登录账号密码，用例继续

E-3:如果登录次数超过限制次数，则提示不允许登录，用例终止



 

b. 查看组员在线情况用例图事件流描述

\1. 查看组员在线情况

1.1前置条件

用例开始前，团队管理员处于登录状态

1.2后置条件（Post-Conditions）

如果用例成功，系统显示出成员的登录情况（活跃，离开，离线）；如果用例失败系统显示运行错误，系统跳转500页面

1.3扩充点

无

1.4事件流

1.4.1基流

当团队管理员进入成员管理页面，用例启动

系统根据团队id查看团队中所有成员的登录token状态判断成员的登录状态（活跃，离开，离线）

1.4.2分支流

如果提供的token不符合权限，系统跳转403页面 

1.4.3替代流

E-1: 如果查询失败跳转500提示页面，用例终止

E-2:如果权限校验失败跳转403提示页面，用例终止

 

c. 新增鸽子信息用例图事件流描述

\1. 新增鸽子信息

1.1前置条件

用例开始前，团队管理员或者团队成员必须登录到系统中。

1.2后置条件（Post-Conditions）

如果用例成功，系统将鸽子信息添加入数据库，并显示添加成功；如果用例失败系统提示错误信息。

1.3扩充点

无

1.4事件流

1.4.1基流

当团队管理员或团队成员提交鸽子信息，用例启动

根据团队管理员或团队成员提交的信息，检查信息格式正确性，将鸽子信息存入数据库

根据鸽子的父代id更新鸽子的父代信息

1.4.2分支流

当提交的鸽子id存在于记录中时执行更新操作

根据团队管理员或团队成员提交的鸽子信息，检查信息格式正确性，更新数据库中的数据

根据鸽子的父代id更新鸽子的父代信息

1.4.3替代流

E-1:如果提交信息格式检查错误，系统提示格式错误，用例终止

E-2:鸽子id提交不正确，系统提示更新出错，用例终止 

 



#### 1.4.3 静态模型设计

##### 1.4.3.1 实体类

1.用户实体类

类User描述了用户的信息，用户的信息包括：用户id、用户账号、用户密码（加密）、用户密码盐值、用户所属组id、团队管理员判别符。

私有属性包括如下：

| id: Long         | 用户id           |
| ---------------- | ---------------- |
| account: String  | 用户账号         |
| password: byte[] | 用户密码（加密） |
| salt: byte[]     | 用户密码盐值     |
| gid: Long        | 用户所属组id     |
| admin: Boolean   | 团队管理员判别符 |

公有方法：

| getId():Long                | 获取用户id           |
| --------------------------- | -------------------- |
| getAccount ():String        | 获取用户账号         |
| getPassword ():byte[]       | 获取用户密码（加密） |
| getSalt ():byte[]           | 获取用户密码盐值     |
| getGid ():Long              | 获取用户所属组id     |
| getAdmin **():B****oolean** | 获取团队管理员判别符 |
| setId():void                | 设置用户id           |
| setAccount ():void          | 设置用户账号         |
| setPassword ():void         | 设置用户密码（加密） |
| setSalt ():void             | 设置用户密码盐值     |
| setGid ():void              | 设置用户所属组id     |
| setAdmin **():**void        | 设置团队管理员判别符 |

 

2.鸽子信息实体类

 

私有属性包括如下：

| id: Long              | 鸽子id         |
| --------------------- | -------------- |
| updateData: LocalDate | 鸽子更新日期   |
| gpcx: String          | 鸽子鸽棚巢箱名 |
| pictureUrl: String    | 鸽子图片url    |
| ringNumber: String    | 鸽子足环       |
| name: String          | 鸽子名称       |
| bloodline: String     | 鸽子血统名称   |
| sex: String           | 鸽子性别       |
| fid: Long             | 父鸽id         |
| mid: Long             | 母鸽id         |
| yan: String           | 鸽子眼色名称   |
| ys: String            | 鸽子羽色名称   |
| lx: String            | 鸽子类型名称   |
| state: String         | 鸽子状态名称   |
| jb: String            | 鸽子级别名称   |
| isGrade: String       | 鸽子是否赛绩鸽 |
| remark: String        | 鸽子备注       |
| gid: Long             | 鸽子所属组id   |

公有方法：

| getId: Long              | 获取鸽子id         |
| ------------------------ | ------------------ |
| getUpdateData: LocalDate | 获取鸽子更新日期   |
| getGpcx: String          | 获取鸽子鸽棚巢箱名 |
| getPictureUrl: String    | 获取鸽子图片url    |
| getRingNumber: String    | 获取鸽子足环       |
| getName: String          | 获取鸽子名称       |
| getBloodline: String     | 获取鸽子血统名称   |
| getSex: String           | 获取鸽子性别       |
| getFid: Long             | 获取父鸽id         |
| getMid: Long             | 获取母鸽id         |
| getYan: String           | 获取鸽子眼色名称   |
| getYs: String            | 获取鸽子羽色名称   |
| getLx: String            | 获取鸽子类型名称   |
| getState: String         | 获取鸽子状态名称   |
| getJb: String            | 获取鸽子级别名称   |
| getIsGrade: String       | 获取鸽子是否赛绩鸽 |
| getRemark: String        | 获取鸽子备注       |
| getGid: Long             | 获取鸽子所属组id   |
| setId: void              | 设置鸽子id         |
| setUpdateData: void      | 设置鸽子更新日期   |
| setGpcx: void            | 设置鸽子鸽棚巢箱名 |
| setPictureUrl: void      | 设置鸽子图片url    |
| setRingNumber: void      | 设置鸽子足环       |
| setName: void            | 设置鸽子名称       |
| setBloodline: void       | 设置鸽子血统名称   |
| setSex: void             | 设置鸽子性别       |
| setFid: void             | 设置父鸽id         |
| setMid: void             | 设置母鸽id         |
| setYan: void             | 设置鸽子眼色名称   |
| setYs: void              | 设置鸽子羽色名称   |
| setLx: void              | 设置鸽子类型名称   |
| setState: void           | 设置鸽子状态名称   |
| setJb: void              | 设置鸽子级别名称   |
| setIsGrade: void         | 设置鸽子是否赛绩鸽 |
| setRemark: void          | 设置鸽子备注       |
| setGid: void             | 设置鸽子所属组id   |

 

3.鸽舍实体类

 

私有属性包括如下：

| id: Long            | 鸽舍id       |
| ------------------- | ------------ |
| name: String        | 鸽舍名称     |
| location: String    | 鸽舍地址定位 |
| address: String     | 鸽舍详细地址 |
| years: Integer      | 养鸽年数     |
| bloodline: String   | 主养血系     |
| performance: String | 优秀赛绩     |
| gid: Long           | 所属组id     |

公有方法：

| getId: Long            | 获取鸽舍id       |
| ---------------------- | ---------------- |
| getName: String        | 获取鸽舍名称     |
| getLocation: String    | 获取鸽舍地址定位 |
| getAddress: String     | 获取鸽舍详细地址 |
| getYears: Integer      | 获取养鸽年数     |
| getBloodline: String   | 获取主养血系     |
| getPerformance: String | 获取优秀赛绩     |
| getGid: Long           | 获取所属组id     |
| setId: void            | 设置鸽舍id       |
| setName: void          | 设置鸽舍名称     |
| setLocation: void      | 设置鸽舍地址定位 |
| setAddress: void       | 设置鸽舍详细地址 |
| setYears: void         | 设置养鸽年数     |
| setBloodline: void     | 设置主养血系     |
| setPerformance: void   | 设置优秀赛绩     |
| setGid: void           | 设置所属组id     |

 

4.血统书实体类

 

私有属性包括如下：

| id: Long          | 鸽舍id         |
| ----------------- | -------------- |
| logoUrl: String   | logo图片的地址 |
| name: String      | 鸽舍全称       |
| ShortName: String | 鸽舍简称       |
| phone: String     | 联系电话       |
| mail: String      | 联系邮箱       |
| url: String       | 鸽舍网址       |
| address: String   | 具体地址       |
| gid: Long         | 所属组id       |

公有方法：

| getId: Long          | 获取鸽舍id         |
| -------------------- | ------------------ |
| getLogoUrl: String   | 获取logo图片的地址 |
| getName: String      | 获取鸽舍全称       |
| getShortName: String | 获取鸽舍简称       |
| getPhone: String     | 获取联系电话       |
| getMail: String      | 获取联系邮箱       |
| getUrl: String       | 获取鸽舍网址       |
| getAddress: String   | 获取具体地址       |
| getGid: Long         | 获取所属组id       |
| setId: void          | 设置鸽舍id         |
| setLogoUrl: void     | 设置logo图片的地址 |
| setName: void        | 设置鸽舍全称       |
| setShortName: void   | 设置鸽舍简称       |
| setPhone: void       | 设置联系电话       |
| setMail: void        | 设置联系邮箱       |
| setUrl: void         | 设置鸽舍网址       |
| setAddress: void     | 设置具体地址       |
| setGid: void         | 设置所属组id       |

 

5.鸽棚巢箱实体类

 

私有属性包括如下：

| id: Long                   | 鸽棚巢箱id |
| -------------------------- | ---------- |
| name: String               | 鸽棚名称   |
| cxNumber: Integer          | 巢箱数量   |
| pigeonPopulation:  Integer | 鸽子数量   |
| gid: Long                  | 所属组id   |

公有方法：

| getId: Long                  | 获取鸽棚巢箱id |
| ---------------------------- | -------------- |
| getName: String              | 获取鸽棚名称   |
| getCxNumber: Integer         | 获取巢箱数量   |
| getPigeonPopulation: Integer | 获取鸽子数量   |
| getGid: Long                 | 获取所属组id   |
| setId: void                  | 设置鸽棚巢箱id |
| setName: void                | 设置鸽棚名称   |
| setCxNumber: void            | 设置巢箱数量   |
| setPigeonPopulation: void    | 设置鸽子数量   |
| setGid: void                 | 设置所属组id   |

 

6.系统配置实体类

 

私有属性包括如下：

| id: Long        | 系统配置id       |
| --------------- | ---------------- |
| name: LocalDate | 系统配置名称     |
| author: String  | 系统配置创建者   |
| type: String    | 系统配置类型     |
| gid: String     | 系统配置所属组id |

公有方法：

| getId: Long       | 获取系统配置id       |
| ----------------- | -------------------- |
| getName: String   | 获取系统配置名称     |
| getAuthor: String | 获取系统配置创建者   |
| getType: String   | 获取系统配置类型     |
| getGid: Long      | 获取系统配置所属组id |
| setId: void       | 设置系统配置id       |
| setName: void     | 设置系统配置名称     |
| setAuthor: void   | 设置系统配置创建者   |
| setType: void     | 设置系统配置类型     |
| setGid: void      | 设置系统配置所属组id |

 

##### 1.4.3.2 界面类

1.鸽子库界面类

 

公共操作包括如下： 

listPigeon()

根据用户组获取所有鸽子信息，当进入页面或更新页面时，该操作被调用

updatePigeonByTypeAndIds(List<Long> ids, String field, String data)

根据 字段类型 和 id列表 批量更新对应鸽子内容，当点击修改操作时，该操作被调用

removePigeonById(id)

根据id删除鸽子，当点击删除操作时，该操作被调用

removePigeonByIds(List<Pigeon> pigeons)

批量删除鸽子信息，当点击批量删除时，该操作被调用

sharePigeon (List<Long> ids,  Long receiveGid)

批量分享鸽子血统信息，当点击批量分享时，该操作被调用

savePigeonByFile (MultipartFile file)

文件形式批量上传鸽子信息，当点击上传文件时，该操作被调用

 

2.选项设置界面类

 

getAllByType(String type)

根据类型获取所有数据，当进入页面时，该操作被调用

deleteOne(Long id)

根据id删除数据，当点击单个删除时，该操作被调用

batchDelete(List<Long> ids)

批量删除选项配置，当点击批量删除选项时，该操作被调用

add(Xxpz xxpz, String type)

新增选项配置，当点击新增时，该操作被调用

update(Xxpz xxpz)

根据id更新数据，当点击更新操作时，该操作被调用

 

3.管理员设置界面类 

 

getUserList()

获取成员信息，当进入页面或页面跟新时，该操作被调用

resetPassword(User user)

重置成员密码，当点击重置成员密码时，该操作被调用

addUser(User user)

添加新成员，当点击新增成员时，该操作被调用

deleteUser(Long id)

根据id删除成员，当点击单个删除时，该操作被调用

 

##### 1.4.3.3 包图

将系统中的类分为11个包，及包config、包controller、包exceptions、包interceptor、包mapper、包pojo、包result、包runner、包schedule、包service、包util。包config由配置类构成，包controller由控制器类构成，包exceptions异常类构成，包interceptor由过滤器类构成，包mapper由数据库映射类构成，包pojo由实体类构成；包result结果包装类构成、包runner启动运行类构成、包schedule定时任务类构成、包service由业务服务类构成、包util工具类构成。依赖关系如下图3-1所示。

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image008.png)

图3-1 系统包图

 

##### 1.4.3.4 类图   

1.鸽子实体类图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image010.png)

2.用户实体类图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image012.png)

3.区域定位实体类图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image014.png)

4. 团队管理员页面图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image016.png)

#### 1.4.4 动态模型设计

##### 1.4.4.1 顺序图

1.团队管理员或团队成员添加或更新鸽子信息顺序图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image018.png)

2.共享血统顺序图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image020.png)

3.删除用户顺序图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image022.png)

##### 1.4.4.2 通信图

1.团队管理员或团队成员添加或更新鸽子信息通信图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image024.png)

2.共享血统通信图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image026.png)

3.删除用户通信图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image028.png)

 

##### 1.4.4.3 活动图

1.上传或更新鸽舍信息活动图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image030.png)

2.重置用户密码活动图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image032.png)

3.用户登录活动图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image034.png)

##### 1.4.4.4 状态机图

1.团队管理员状态图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image036.png)



 

#### 1.4.5 物理模型设计

-  部署图

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image038.png)

 



## 2 系统设计分析

### 2.1 数据表设计

![img](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/clip_image040.png)

### 2.2 后端设计

#### 2.2.1 系统配置

##### 2.2.1.1 pom.xml maven依赖配置

- 项目设定

  ```xml
  <groupId>com.AE</groupId>
  <artifactId>sgmis</artifactId>
  <version>1.0</version>
  <name>sgmis</name>
  <description>sgmis</description>
  <properties>
      <java.version>20</java.version>
  </properties>
  ```

- 核心依赖及版本控制

  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  
      <dependency>
          <groupId>org.mybatis.spring.boot</groupId>
          <artifactId>mybatis-spring-boot-starter</artifactId>
          <version>3.0.0</version>
      </dependency>
  
      <dependency>
          <groupId>com.mysql</groupId>
          <artifactId>mysql-connector-j</artifactId>
          <scope>runtime</scope>
      </dependency>
  
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
  
      <!--加密工具-->
      <dependency>
          <groupId>org.bouncycastle</groupId>
          <artifactId>bcprov-jdk15on</artifactId>
          <version>1.70</version>
      </dependency>
      <!--mapper类自动-->
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
      </dependency>
      <!--mp-->
      <dependency>
          <groupId>com.baomidou</groupId>
          <artifactId>mybatis-plus-boot-starter</artifactId>
          <version>3.5.3.1</version>
      </dependency>
      <!--jwt-->
      <dependency>
          <groupId>com.auth0</groupId>
          <artifactId>java-jwt</artifactId>
          <version>4.3.0</version>
      </dependency>
      <!--日志-->
      <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
      </dependency>
      <!--fastjson-->
      <dependency>
          <groupId>com.alibaba.fastjson2</groupId>
          <artifactId>fastjson2</artifactId>
          <version>2.0.19</version>
          <scope>test</scope>
      </dependency>
      <!--文件检查工具-->
      <dependency>
          <groupId>org.apache.tika</groupId>
          <artifactId>tika-core</artifactId>
          <version>2.7.0</version>
      </dependency>
      <!--解析office文件-->
      <dependency>
          <groupId>org.apache.poi</groupId>
          <artifactId>poi</artifactId>
          <version>5.2.3</version>
      </dependency>
      <dependency>
          <groupId>org.apache.poi</groupId>
          <artifactId>poi-ooxml</artifactId>
          <version>5.2.3</version>
      </dependency>
      <!--解析用户设备信息-->
      <dependency>
          <groupId>eu.bitwalker</groupId>
          <artifactId>UserAgentUtils</artifactId>
          <version>1.21</version>
      </dependency>
  
      <!--二维码相关-->
      <dependency>
          <groupId>com.google.zxing</groupId>
          <artifactId>core</artifactId>
          <version>3.5.1</version>
      </dependency>
      <dependency>
          <groupId>com.google.zxing</groupId>
          <artifactId>javase</artifactId>
          <version>3.5.1</version>
      </dependency>
  
      <!--redis-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-redis</artifactId>
      </dependency>
  </dependencies>
  ```

- 插件

  ```xml
  <plugins>
      <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
      <!--打包阶段忽略test-->
      <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <configuration>
              <skip>true</skip>
          </configuration>
      </plugin>
  </plugins>
  ```

##### 2.2.1.2 logback-spring.xml 系统日志配置文件

```xml
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- 设置时间等信息格式-->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="chapters.configuration" level="INFO"/>

    <!--设置日志级别，部署阶段设置为INFO-->
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>

</configuration>
```

##### 2.2.1.3 application.yml 项目配置

自定义配置方便全局修改设定

```yml
# 服务端口
server:
  port: 8080

spring:
# 数据源
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://42.193.192.61:3306/sgmis
    username: root
    password: guatdev
  # 时间序列化格式
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: Asia/Shanghai
  # 文件限制
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
  # redis
  data:
    redis:
      host: localhost
      port: 6379
      password:
      database: 0

# 自定义配置     

# 加密算法配置
encrypt:
  memoryPowOfTwo: 13
  iterations: 9
  parallelism: 5
  hashLength: 512

# koten算法配置
jwt:
  #秒
  expires: 21600
  update-gap: 1800
  secret-key: S&h(i(to*A@E#
  ipLimit: 10

# 配置信息的表名，防止sql注入
xxpz:
  tableNames: yspz, lxpz, yanpz, jbpz, ylhl, gdcgzt, breeder, country, province, state
  systemGid: 0

# 默认密码
user:
  defaultPassword: 123456

#系统文件保存路径
file:
  logo:
    path: logo
    type: image/jpeg, image/png, image/gif, image/tiff
  pigeon:
    path: pigeon
    type: image/jpeg, image/png, image/gif, image/tiff

# 子代寻找递归深度
pigeon:
  generation:
    limit: 7

#一次性能查出的最大数据量限制
page:
  maxLimit: 10000

# 最大获取图片数量
carousel:
  limit: 10

# 获取最近数据天数限制
# 天
recent:
  create: 30
  delete: 30
  oplog: 30

#日志保存最大天数
#day
log:
  maxHistory: 180

# 系统二维码生成配置
QR:
  width: 168
  height: 168
  image-format: png
  charset: utf-8
  base64-image: data:image/png;base64,%s
  logo-url: system/logo/logo.jpg
  background: 0xFFFFFF
  foreground: 0x000000
  logo-width: 36
  logo-height: 36

# 反馈信息二维码地址
feedback:
  githubUrl: https://github.com/Shi-AE/sgmis
  guatUrl: https://www.guat.edu.cn
  mailUrl: mailto:2021070030101@guat.edu.cn
  
# 模板文件地址名称
template:
  pigeon: template/pigeonTemplate.xlsx
```

#### 2.2.2 注解配置

- LocalDateTimeSerializerConfig LDT序列化格式配置

  ```java
  /**
   * LDT配置
   */
  @Configuration
  public class LocalDateTimeSerializerConfig {
  
      @Value("${spring.jackson.date-format}")
      private String pattern;
  
      /**
       * 序列化器
       */
      @Bean
      public LocalDateTimeSerializer localDateTimeSerializer() {
          return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
      }
  
      /**
       * 反序列化器
       */
      @Bean
      public LocalDateTimeDeserializer localDateTimeDeserializer() {
          return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
      }
  
      @Bean
      public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
          return builder -> {
              builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
              builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
          };
      }
  }
  ```

- MybatisPlusConfig MP分页插件配置

  ```java
  /**
   * mp配置
   */
  @Configuration
  public class MybatisPlusConfig {
      /**
       * 分页插件
       */
      @Bean
      public MybatisPlusInterceptor mybatisPlusInterceptor() {
          MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
          mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
          return mybatisPlusInterceptor;
      }
  }
  ```

- RedisConfig redis配置

  ```java
  /**
   * redis配置
   */
  @Configuration
  public class RedisConfig {
      @Bean
      public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
          RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
          redisTemplate.setConnectionFactory(redisConnectionFactory);
          redisTemplate.setKeySerializer(new StringRedisSerializer());
          redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
          return redisTemplate;
      }
  }
  ```

- ResourceConfig 系统资源路径配置

  ```java
  /**
   * 资源配置
   */
  @Slf4j
  @Configuration
  public class ResourceConfig implements WebMvcConfigurer {
  
      @Autowired
      private Environment environment;
  
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          //遍历配置文件找到所有匹配项
          StandardServletEnvironment servletEnvironment = (StandardServletEnvironment) environment;
          MutablePropertySources propertySources = servletEnvironment.getPropertySources();
          propertySources.forEach(source -> {
              if (source instanceof MapPropertySource && source.getName().contains("yml")) {
                  for (String name : ((MapPropertySource) source).getPropertyNames()) {
                      if (name.matches("^file\\.(.*)+\\.path$")) {
                          String basePath = environment.getProperty(name);
                          registry.addResourceHandler("/" + basePath + "/**")
                                  .addResourceLocations("file:" + basePath + "/");
                          log.info("成功加载 {} 资源路径", name);
                      }
                  }
              }
          });
      }
  }
  ```

- InterceptorConfig 过滤器配置

  ```java
  /**
   * 过滤器配置
   */
  @Configuration
  public class InterceptorConfig implements WebMvcConfigurer {
      @Autowired
      private LoginInterceptor loginInterceptor;
      @Autowired
      private AdminInterceptor adminInterceptor;
      @Autowired
      private BlackInterceptor blackInterceptor;
  
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          //ip黑名单检测拦截器
          registry.addInterceptor(blackInterceptor)
                  .addPathPatterns("/api/**")
                  .order(1);
  
          //请求登录验证拦截器
          registry.addInterceptor(loginInterceptor)
                  .addPathPatterns("/api/**")
                  .excludePathPatterns("/api/login/authority")
                  .excludePathPatterns("/api/login/free")
                  .order(2);
  
          //管理员请求拦截器
          registry.addInterceptor(adminInterceptor)
                  .addPathPatterns("/api/user/**")
                  .addPathPatterns("/api/login/admin")
                  .order(3);
      }
  }
  ```

#### 2.2.3 过滤器逻辑

- LoginInterceptor 登录过滤器，验证是否携带登录令牌

  ```java
  @Slf4j
  @Component
  public class LoginInterceptor implements HandlerInterceptor {
  
      @Autowired
      private JwtUtil jwtUtil;
      @Autowired
      private WhitelistUtil whitelistUtil;
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessException {
          //获取请求头证书
          String token = request.getHeader("Authorization");
  
          //验证证书
          DecodedJWT decoded = jwtUtil.verifyToken(token);
  
          //获取token中的信息
          Map<String, Object> claim = decoded.getClaim("info").asMap();
  
          //在redis中验证证书
          Long id = (Long) claim.get("id");
          String ip = (String) claim.get("ip");
          boolean verified = whitelistUtil.verifyToken(id, ip, token);
          if (!verified) {
              throw new AccessException("登录过期");
          }
  
          //如果距离redis过期时间达到设定时间则重置redis过期时间
          whitelistUtil.updateExpires(id, ip);
  
          //向请求域中添加用户信息
          request.setAttribute("info", claim);
  
          return true;
      }
  }
  ```

- AdminInterceptor 管理员过滤器，验证管理员身份

  ```java
  @Component
  public class AdminInterceptor implements HandlerInterceptor {
  
      @Autowired
      private UserService userService;
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
          //获取 id，admin
          Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
          Long id = (Long) info.get("id");
          Boolean admin = (Boolean) info.get("admin");
          //请求头管理员验证
          if (!admin) {
              throw new AccessException("您没有管理员权限，请联系管理员获得权限");
          }
          //数据库管理员验证
          User user = userService.getById(id);
          if (!user.getAdmin()) {
              throw new AccessException("请求头信息异常");
          }
          return true;
      }
  }
  ```

- BlackInterceptor 黑名单过滤器，判断IP是否被封禁

  ```java
  @Component
  public class BlackInterceptor implements HandlerInterceptor {
  
      @Autowired
      private IpUtil ipUtil;
      @Autowired
      private BlacklistUtil blacklistUtil;
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessException {
          //获取实际ip，避免使用token中的ip
          String ip = ipUtil.getIp(request);
  
          //通过ip获取黑名单中的信息
          //如果为空代表黑名单中没有此ip
          Object info = blacklistUtil.getForbiddenInfo(ip);
          if (info != null) {
              //此ip被禁用，返回禁用信息
              throw new AccessException(info.toString());
          }
  
          return true;
      }
  }
  ```

#### 2.2.4 系统启动自检

- RedisConnectionRunner redis连接自检

  ```java
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
  ```

#### 2.2.6 系统枚举类

- 异常状态码枚举（部分）

  ```java
  AccessException(403),
  NotFoundException(404),
  MaliciousSqlInjectionException(411),
  FreePassException(418);
  
  public final int code;
  ExceptionCode(int code) {
  this.code = code;
  }
  ```

- 成功状态码枚举

  ```java
  public enum SuccessCode {
  
      Success(200),
      LoginSuccess(201),
      ExitSuccess(202),
      AccessSuccess(203),
      ModifyPasswordSuccess(204);
  
      public final int code;
  
      SuccessCode(int code) {
          this.code = code;
      }
  }
  ```

- 日志类型枚举

  ```java
  public enum LogType {
      /**
       * 新增
       */
      INSERT(0, "新增鸽子"),
      /**
       * 修改
       */
      UPDATE(1, "修改信息"),
      /**
       * 删除
       */
      DELETE(2, "删除鸽子"),
      /**
       * 共享
       */
      SHARE(3, "共享血统"),
      /**
       * 接收血统
       */
      RECEIVE(4, "接收血统"),
      /**
       * 关联血亲
       */
      RELATE(5, "关联血亲"),
      /**
       * 解除血亲关系
       */
      UNPARENT(6, "解除血亲"),
      /**
       * 转移鸽棚巢箱
       */
      TRANSFER(7, "转移鸽棚巢箱"),
      /**
       * 其他
       */
      OTHER(8, "其他");
  
  
      private final int index;
      private final String tip;
  
      LogType(int index, String tip) {
          this.index = index;
          this.tip = tip;
      }
  
      public int getIndex() {
          return index;
      }
  
      public String getTip() {
          return tip;
      }
  }
  ```

- 违规等级枚举

  ```java
  public enum SeverityLevel {
      /**
       * 严重
       */
      Blocker(3600),
      /**
       * 中级
       */
      Critical(1800),
      /**
       * 初级
       */
      Major(600);
  
      private final long expire;
  
      SeverityLevel(long expire) {
          this.expire = expire;
      }
  
      public long getExpire() {
          return expire;
      }
  }
  ```

- redis命名空间枚举

  ```java
  public enum RedisNamespace {
  
      Ancestor(null, ""),
      Whitelist(Ancestor, "whitelist:"),
      Blacklist(Ancestor, "blacklist:"),
      Statistic(Ancestor, "statistic:"),
      Online(Statistic, "online:");
  
      RedisNamespace(RedisNamespace parent, String value) {
          this.value = parent == null ? "" : parent.value + value;
      }
      private final String value;
  
      public String getValue() {
          return value;
      }
  }
  ```

#### 2.2.5 统一返回格式

返回前端的统一json格式

{ "code": 200, "data": { },"message": "处理成功" }

```java
public class Result {
    private Object data;
    private Integer code;
    private String message;
}
```

#### 2.2.6 系统工具类 utils

- EncryptUtil 加密工具

  ```java
  /**
   * 加密工具类
   */
  @Component
  public class EncryptUtil {
      @Value("${encrypt.memoryPowOfTwo}")
      private int memoryPowOfTwo;
      @Value("${encrypt.iterations}")
      private int iterations;
      @Value("${encrypt.parallelism}")
      private int parallelism;
      @Value("${encrypt.hashLength}")
      private int hashLength;
      private final SecureRandom secureRandom = new SecureRandom();
  
      /**
       * 初始化并加载加密工具
       *
       * @param salt 盐值
       * @return 加密工具
       */
      private Argon2BytesGenerator getGenerator(byte[] salt) {
          //构造Argon2初始化工具
          Argon2Parameters argonBuilder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                  .withSalt(salt)
                  .withMemoryPowOfTwo(memoryPowOfTwo)
                  .withIterations(iterations)
                  .withParallelism(parallelism)
                  .build();
          //创建生产工具并初始化
          Argon2BytesGenerator generator = new Argon2BytesGenerator();
          generator.init(argonBuilder);
  
          return generator;
      }
  
      /**
       * 生成随机盐值
       *
       * @return 盐值二进制数组
       */
      private byte[] getRandomSalt() {
          byte[] salt = new byte[hashLength];
          secureRandom.nextBytes(salt);
          return salt;
      }
  
      /**
       * 密码加密
       *
       * @param user 用户信息
       */
      public void passwordEncrypt(User user) {
          byte[] password = user.getPassword();
          byte[] result = new byte[hashLength];
  
          byte[] randomSalt = getRandomSalt();
  
          Argon2BytesGenerator generator = getGenerator(randomSalt);
  
          generator.generateBytes(password, result);
  
          user.setPassword(result);
          user.setSalt(randomSalt);
      }
  
      /**
       * 密码校验
       *
       * @param password 密码
       * @param user     用户信息
       * @return 密码是否正确
       */
      public boolean passwordVerify(byte[] password, User user) {
          byte[] result = new byte[hashLength];
  
          byte[] salt = user.getSalt();
  
          Argon2BytesGenerator generator = getGenerator(salt);
  
          generator.generateBytes(password, result);
  
          return Arrays.equals(result, user.getPassword());
      }
  }
  ```

-  JwtUtil 生成解析token工具

  ```java
  //获取配置
  @Value("${jwt.secret-key}")
  private String secretKey;
  @Value("${jwt.expires}")
  private int expires;
  ```

  ```java
  /**
   * 获取无时间限制token
   */
  public String getLimitlessToken(Map<String, Object> claim) {
      return JWT.create()
              .withClaim("info", claim)
              .sign(Algorithm.HMAC256(secretKey));
  }
  ```

  ```java
  /**
   * 验证token
   *
   * @return 验证信息
   */
  public DecodedJWT verifyToken(String token) {
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
      return jwtVerifier.verify(token);
  }
  ```

- IpUtil 获取真实ip工具

  ```java
  /**
   * ip工具
   */
  @Component
  public class IpUtil {
  
      public String getIp(HttpServletRequest request) {
          String ip = request.getHeader("X-Forwarded-For");
          if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
              ip = request.getHeader("Proxy-Client-IP");
          }
          if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
              ip = request.getHeader("WL-Proxy-Client-IP");
          }
          if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
              ip = request.getRemoteAddr();
          }
          return ip;
      }
  }
  ```

- FileUtil 文件工具类

  ```java
  /**
   * 文件工具类
   */
  @Slf4j
  @Component
  public class FileUtil {
  
      private final Tika tika = new Tika();
  
      /**
       * 初始化文件夹
       */
      public void initDirectory(Path path) {
          try {
              Files.createDirectory(path);
          } catch (FileAlreadyExistsException ignored) {
          } catch (IOException e) {
              log.error("初始化bean发生错误，{} 存储文件夹创建失败", path.getFileName(), e);
          }
      }
  
      /**
       * 将文件保存在本地
       */
      public String storeFile(MultipartFile file, Path path) {
          try {
              String uniqueFileName = IdWorker.get32UUID() + "-" + file.getOriginalFilename();
              Files.copy(file.getInputStream(), path.resolve(uniqueFileName));
              return uniqueFileName;
          } catch (IOException e) {
              log.error("保存文件 {} 时发生错误", file, e);
              throw new FileSaveException("文件保存失败");
          }
      }
  
      /**
       * 根据文件目录获取一级子文件
       */
      public Stream<Path> getFileListByDirectory(Path dirPath) {
          try {
              return Files.list(dirPath);
          } catch (IOException e) {
              log.error("查询文件目录 {} 时发生错误", dirPath, e);
              throw new FileSaveException("文件保存失败");
          }
      }
  
      /**
       * 删除文件
       */
      public boolean deleteFile(String fileName, Path path) {
          try {
              Files.deleteIfExists(path.resolve(fileName));
              return true;
          } catch (IOException e) {
              log.error("文件删除失败，当文件名为 {}，path为 {}", fileName, path);
              return false;
          }
      }
  
      /**
       * 获得文件类型
       */
      public String getFileType(MultipartFile file) {
          try {
              return tika.detect(file.getInputStream());
          } catch (IOException e) {
              log.error("检查文件 {} 时文件流发生错误", file, e);
              throw new FileSaveException("文件保存失败");
          }
      }
  
      /**
       * 通过文件类型列表
       * 检查文件类型是否匹配
       */
      @SuppressWarnings("UnusedReturnValue")
      public String checkFileType(MultipartFile file, Set<String> typeList) {
          String fileType = getFileType(file);
          boolean checked = typeList.contains(fileType);
          if (!checked) {
              throw new FileSaveException("文件类型错误，请上传正确类型");
          }
          return fileType;
      }
  
      /**
       * 通过多字符串
       * 检查文件类型是否匹配
       */
      public String checkFileType(MultipartFile file, String... types) {
          Set<String> typeList = Set.of(types);
          String fileType = getFileType(file);
          boolean checked = typeList.contains(fileType);
          if (!checked) {
              throw new FileSaveException("文件类型错误，请上传正确类型");
          }
          return fileType;
      }
  
      /**
       * 根据相对路径获取文件
       * 将文件装入响应
       */
      public void responseFileByRelativePath(String path, HttpServletResponse response) {
          //获取文件
          File file = new File(path);
          try {
              ServletOutputStream out = response.getOutputStream();
              byte[] fileBytes = FileUtils.readFileToByteArray(file);
              out.write(fileBytes);
          } catch (FileNotFoundException e) {
              log.error("鸽子入库文件模板发生文件未找到错误，路径：{}", "");
          } catch (IOException e) {
              log.error("鸽子入库文件模板发生IO错误，路径：{}", "");
          }
      }
  }
  ```

- QRCodeUtil 二维码工具

  读取配置

  ```java
  /**
   * 默认宽度
   */
  @Value("${QR.width}")
  private int width;
  /**
   * 默认高度
   */
  @Value("${QR.height}")
  private int height;
  /**
   * 图片格式
   */
  @Value("${QR.image-format}")
  private String imageFormat;
  /**
   * 字符编码
   */
  @Value("${QR.charset}")
  private String charset;
  /**
   * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析
   */
  @Value("${QR.base64-image}")
  private String base64Image;
  /**
   * 系统logo地址
   */
  @Value("${QR.logo-url}")
  private String logoUrl;
  /**
   * logo宽度
   */
  @Value("${QR.logo-width}")
  private int logoWidth;
  /**
   * logo高度
   */
  @Value("${QR.logo-height}")
  private int logoHeight;
  /**
   * 二维码前景色
   */
  @Value("${QR.foreground}")
  private int foreground;
  /**
   * 二维码背景色
   */
  @Value("${QR.background}")
  private int background;
  
  private Map<EncodeHintType, Object> qrConfig;
  
  @PostConstruct
  private void init() {
      qrConfig = new HashMap<>();
      //指定字符集
      qrConfig.put(EncodeHintType.CHARACTER_SET, charset);
      //指定二维码的纠错等级为中级
      qrConfig.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      //设置图片的边距
      qrConfig.put(EncodeHintType.MARGIN, 2);
      log.info("QRCodeUtil qrConfig 初始化完成 {}", qrConfig);
  }
  ```

  核心代码

  ```java
  /**
   * 根据宽高生成二维码
   */
  public String createQrCode(String content, int width, int height) {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      try {
          // 创建 QRCodeWriter 对象
          QRCodeWriter qrCodeWriter = new QRCodeWriter();
          // 生成 BitMatrix 对象
          BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, qrConfig);
          // 创建 BufferedImage 对象
          BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
          //设置像素点颜色
          for (int x = 0; x < width; x++) {
              for (int y = 0; y < height; y++) {
                  bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? foreground : background);
              }
          }
          //插入logo
          insertLogo(bufferedImage, width, height, logoUrl, logoWidth, logoHeight);
          //输出
          ImageIO.write(bufferedImage, imageFormat, output);
      } catch (Exception e) {
          log.error("服务错误， 生成二维码失败", e);
      }
      return String.format(base64Image, new String(Base64.encode(output.toByteArray())));
  }
  
  /**
   * 插入logo
   */
  private void insertLogo(BufferedImage source,
                          int width, int height,
                          String logoUrl,
                          int logoWidth, int logoHeight) throws Exception {
      Image src = ImageIO.read(new File(logoUrl));
      // 插入LOGO
      Graphics2D graph = source.createGraphics();
      int x = (width - logoWidth) / 2;
      int y = (height - logoHeight) / 2;
      graph.drawImage(src, x, y, logoWidth, logoHeight, null);
      Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 6, 6);
      graph.setStroke(new BasicStroke(3f));
      graph.draw(shape);
      graph.dispose();
  }
  ```

- redis相关

  - RedisUtil redis工具

    ```java
    /**
     * redis工具
     */
    @Slf4j
    @Component
    public class RedisUtil {
    
        @Autowired
        RedisTemplate<String, Object> redisTemplate;
    
        /**
         * 发送ping命令
         * 检查数据库连接
         */
        public boolean PING() {
            try {
                String execute = redisTemplate.execute(RedisConnectionCommands::ping);
                if (execute == null || !execute.equals("PONG")) {
                    log.error("redis PING 失败 {}}", execute);
                    return false;
                }
            } catch (RedisConnectionFailureException e) {
                log.error("redis连接错误", e);
                return false;
            }
            return true;
        }
    
        /**
         * 普通缓存放入并设置时间
         *
         * @param namespace 命名空间
         * @param key       键
         * @param value     值
         * @param time      时间(秒) time要大于0 如果time小于等于0 将设置无限期
         */
        public void set(RedisNamespace namespace, String key, Object value, long time) {
            if (time > 0) {
                redisTemplate.opsForValue().set(namespace.getValue() + key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(namespace.getValue() + key, value);
            }
        }
    
        /**
         * 无命名空间
         * 普通缓存放入并设置时间
         *
         * @param key   键
         * @param value 值
         * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
         */
        public void set(String key, Object value, long time) {
            set(RedisNamespace.Ancestor, key, value, time);
        }
    
        /**
         * 带父级命名空间
         * 获取命名空间下的所有key
         */
        public Set<String> keys(RedisNamespace redisNamespace, Object namespace) {
            return redisTemplate.keys(redisNamespace.getValue() + namespace + "*");
        }
    
        /**
         * 带父级命名空间
         * 获取命名空间下的所有key
         */
        public Set<String> keys(RedisNamespace redisNamespace) {
            return redisTemplate.keys(redisNamespace.getValue() + "*");
        }
    
        /**
         * 带命名空间
         * 查询过期时间
         *
         * @return 时间(秒) 返回0代表为永久有效
         */
        public Long getExpire(RedisNamespace namespace, String key) {
            return redisTemplate.getExpire(namespace.getValue() + key);
        }
    
        /**
         * 无命名空间
         * 查询过期时间
         *
         * @return 时间(秒) 返回0代表为永久有效
         */
        public Long getExpire(String key) {
            return getExpire(RedisNamespace.Ancestor, key);
        }
    
        /**
         * 带命名空间
         * 删除缓存
         */
        public void del(RedisNamespace namespace, String key) {
            redisTemplate.delete(namespace.getValue() + key);
        }
    
        /**
         * 无命名空间
         * 删除缓存
         */
        public void del(String key) {
            del(RedisNamespace.Ancestor, key);
        }
    
        /**
         * 带命名空间
         * 获取普通缓存
         */
        public Object get(RedisNamespace namespace, String key) {
            return key == null ? null : redisTemplate.opsForValue().get(namespace.getValue() + key);
        }
    
        /**
         * 带命名空间
         * 指定缓存失效时间
         */
        public void expire(RedisNamespace namespace, String key, long time) {
            if (time > 0) {
                redisTemplate.expire(namespace.getValue() + key, time, TimeUnit.SECONDS);
            }
        }
    }
    ```

  - WhitelistUtil 白名单工具

    ```java
    /**
     * 白名单工具类
     */
    @Component
    public class WhitelistUtil {
    
        @Autowired
        private RedisUtil redisUtil;
        @Value("${jwt.expires}")
        private int expires;
        @Value("${jwt.ipLimit}")
        private int ipLimit;
        @Value("${jwt.update-gap}")
        private Integer updateGap;
    
        /**
         * 将token存入白名单
         */
        public void setToken(Long id, String ip, String token) {
            redisUtil.set(
                    RedisNamespace.Whitelist,
                    id + ":" + ip,
                    token,
                    expires
            );
        }
    
        /**
         * 限制单用户id的在线设备数
         * 如果存在超出限制的设备数
         * 则将其token踢下线
         */
        public void limitToken(Long id) {
            //获取此id下的所有key
            Set<String> keys = redisUtil.keys(RedisNamespace.Whitelist, id + ":");
    
            //如果key个数小于限制则放行
            if (keys.size() <= ipLimit) {
                return;
            }
    
            //删除距离过期时间短的token
    
            //查询出key对应的过期时间，放入优先队列
            //Queue元素为数组，e[0]key，e[1]为过期时间
            Queue<Object[]> expiresQueue = new PriorityQueue<>(Comparator.comparingLong(e -> (Long) e[1]));
            keys.forEach(key -> expiresQueue.add(new Object[]{key, redisUtil.getExpire(key)}));
    
            while (expiresQueue.size() > ipLimit) {
                Object[] poll = expiresQueue.poll();
                assert poll != null;
                redisUtil.del((String) poll[0]);
            }
        }
    
        /**
         * 验证白名单中的token
         */
        public boolean verifyToken(Long id, String ip, String token) {
            //获取token
            Object redisToken = redisUtil.get(RedisNamespace.Whitelist, id + ":" + ip);
            if (redisToken == null) {
                return false;
            }
            return redisToken.equals(token);
        }
    
        /**
         * 重置redis过期时间
         */
        public void updateExpires(Long id, String ip) {
            Long redisExpires = redisUtil.getExpire(RedisNamespace.Whitelist, id + ":" + ip);
            if (expires - redisExpires > updateGap) {
                redisUtil.expire(RedisNamespace.Whitelist, id + ":" + ip, expires);
            }
        }
    
        /**
         * 统计活跃人数
         */
        public long getActive(RedisNamespace namespace, String key) {
            //根据获取包含key的key
            Set<String> keys = redisUtil.keys(namespace, key);
    
            AtomicLong count = new AtomicLong();
    
            //筛选并统计最近活跃的人数
            keys.forEach(k -> {
                Long tokenExpires = redisUtil.getExpire(k);
    
                if (expires - tokenExpires < updateGap) {
                    count.getAndIncrement();
                }
            });
    
            return count.get();
        }
    
        /**
         * 将用户的所有设备
         * 从白名单中剔除
         */
        public void deleteToken(Long id) {
            Set<String> keys = redisUtil.keys(RedisNamespace.Whitelist, id + ":");
            //删除所有设备
            keys.forEach(key -> redisUtil.del(key));
        }
    
        /**
         * 查看用户的活跃状态
         */
        public int getState(RedisNamespace namespace, String key) {
            //根据获取包含key的key
            Set<String> keys = redisUtil.keys(namespace, key);
    
            int state = 0;
    
            //查看每个设备的活跃状态
            for (String k : keys) {
                Long tokenExpires = redisUtil.getExpire(k);
    
                //如果满足条件，将状态设为最高在线状态
                if (expires - tokenExpires < updateGap) {
                    state = 2;
                    break;
                }
    
                //如果满足条件设置此状态
                if (state < 1 && tokenExpires > 0) {
                    state = 1;
                }
            }
    
            return state;
        }
    }
    ```

  - BlacklistUtil 黑名单工具

    ```java
    /**
     * 黑名单工具
     */
    @Component
    public class BlacklistUtil {
    
        @Autowired
        private RedisUtil redisUtil;
    
        /**
         * 根据级别向黑名单中添加ip
         * 并设置过期时间
         */
        public void addForbiddenIp(String ip, Object message, SeverityLevel level) {
            redisUtil.set(RedisNamespace.Blacklist, ip, message, level.getExpire());
        }
    
        /**
         * 通过ip获取黑名单中的信息
         */
        public Object getForbiddenInfo(String ip) {
            return redisUtil.get(RedisNamespace.Blacklist, ip);
        }
    }
    ```

#### 2.2.7 异常处理中心

- 项目异常父类，为异常添加设置状态码方法

  ```java
  public class ProjectException extends RuntimeException{
      private int code;
  
      public int getCode() {
          return code;
      }
  
      public void setCode(int code) {
          this.code = code;
      }
  
      public ProjectException(String message) {
          super(message);
      }
  }
  ```

- 异常类举例（通行证异常）

  ```java
  public class AccessException extends ProjectException{
      public AccessException(String message) {
          super(message);
          setCode(ExceptionCode.AccessException.code);
      }
  }
  ```

- 异常捕获处理

  - 普通异常处理

    ```java
    @ExceptionHandler({NotFindUserException.class, PasswordErrorException.class,
            ConfirmPasswordInconsistencyException.class, UnchangedPasswordException.class,
            DeleteFailException.class, SaveFailException.class, FieldsDuplicateException.class,
            PasswordUpdateFailException.class, AccessException.class, FileSaveException.class,
            UserInformationException.class, NotFoundException.class, FileParseException.class,
            LogException.class, FreePassException.class})
    private Result doCommonException(ProjectException exception) {
        return new Result(exception.getCode(), exception.getMessage());
    }
    ```

  - 通行证异常处理

    ```java
    /**
     * 403无访问权限
     */
    @ExceptionHandler({JWTDecodeException.class, SignatureVerificationException.class})
    private Result doAccessException() {
        return new Result(ExceptionCode.AccessException.code, "通行证失效");
    }
    
    /**
     * 登录过期
     */
    @ExceptionHandler({TokenExpiredException.class})
    private Result doExpiredException() {
        return new Result(ExceptionCode.ExpiredException.code, "通信证过期");
    }
    ```

  - 数据库异常

    ```java
    /**
     * 对数据库操作的数据过大
     */
    @ExceptionHandler(DataTruncation.class)
    private Result doDataTruncationException(DataTruncation exception) {
        log.warn("用户上传的数据超出数据库预期 {}", exception.getMessage());
        return new Result(ExceptionCode.SaveFailException.code, "上传的数据过大，请联系管理员获取支持");
    }
    
    /**
     * 数据库唯一字段发生重复
     */
    @ExceptionHandler(DuplicateKeyException.class)
    private Result doDuplicateKeyException() {
        return new Result(ExceptionCode.DuplicateKeyException.code, "信息已存在");
    }
    ```

  - sql注入异常特殊处理

    ```java
    /**
     * 非法sql注入
     */
    @ExceptionHandler(MaliciousSqlInjectionException.class)
    private Result doMaliciousSqlInjectionException(MaliciousSqlInjectionException exception, HttpServletRequest request) {
        //获取实际ip，避免使用token中的ip
        String ip = ipUtil.getIp(request);
    
        //添加信息
        Map<String, Object> cause = new HashMap<>();
        cause.put("ip", ip);
        cause.put("message", exception.getMessage());
        cause.put("route", request.getServletPath());
    
        //按严重程度加入黑名单
        blacklistUtil.addForbiddenIp(ip, cause, exception.getSeverityLevel());
    
        return new Result(exception.getCode(), exception.getMessage());
    }
    ```

#### 2.2.8 系统定时任务

- LogSchedule 登录信息日志定时任务

  ```java
  @Slf4j
  @Component
  public class LogSchedule {
  
      @Autowired
      private LoginMsgService loginMsgService;
  
      /**
       * 定时清理登录日志信息
       */
      @Scheduled(cron = "0 0 3 * * *")
      public void loginLogTask() {
  
          int remove = loginMsgService.removeForCountTask();
  
          log.info("成功清理 {} 条过期登录信息记录", remove);
      }
  }
  ```

  业务层

  ```java
  @Autowired
  private LoginMsgMapper loginMsgMapper;
  @Value("${log.maxHistory}")
  private Integer maxHistory;
  @Override
  public int removeForCountTask() {
      //获取历史时间
      LocalDateTime historyTime = LocalDateTime.now().minusDays(maxHistory);
  
      QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
      wrapper.lt("time", historyTime);
  
      return loginMsgMapper.delete(wrapper);
  }
  ```

- OnlineSchedule 线上人数计数定时任务

  ```java
  @Slf4j
  @Component
  public class OnlineSchedule {
  
      @Autowired
      private RedisUtil redisUtil;
      @Autowired
      private WhitelistUtil whitelistUtil;
  
      /**
       * 固定时间间隔
       * 从上次执行完算起
       * 每个十分钟统计在线人数
       */
      @Scheduled(fixedDelay = 600000, initialDelay = 1000)
      public void countOnline() {
          //获取白名单中的所有活跃人数
          long active = whitelistUtil.getActive(RedisNamespace.Whitelist, "");
  
          //将统计的人数存入缓存，不设置过期
          redisUtil.set(RedisNamespace.Online, "", active, -1);
  
          log.info("统计当前活跃人数 {}", active);
      }
  }
  ```

- OplogSchedule 操作日志定时任务

  ```java
  @Slf4j
  @Component
  public class OplogSchedule {
  
      @Autowired
      private OplogService oplogService;
      @Autowired
      private PigeonService pigeonService;
  
      /**
       * 定时为操作日志补充足环
       */
      @Scheduled(cron = "0 0 3,12,19 * * *")
      public void replenishRingNumber() {
          //select pid
          //from t_oplog
          //where pid is not null
          //and ring_number is null
          //group by pid
          QueryWrapper<Oplog> oplogQueryWrapper = new QueryWrapper<>();
          oplogQueryWrapper.select("pid")
                  .isNotNull("pid")
                  .isNull("ring_number")
                  .groupBy("pid");
  
          List<Oplog> oplogList = oplogService.list(oplogQueryWrapper);
  
          //计数
          AtomicInteger count = new AtomicInteger();
  
          oplogList.forEach(oplog -> {
              Long pid = oplog.getPid();
  
              //根据pid查询ringNumber
              Pigeon pigeon = pigeonService.getById(pid);
              if (pigeon == null) {
                  return;
              }
              String ringNumber = pigeon.getRingNumber();
  
              //更新oplog的ringNumber
              int update = oplogService.updateRingNUmber(pid, ringNumber);
  
              count.addAndGet(update);
          });
  
          log.info("成功补充日志ringNumber {} 条", count);
      }
  }
  ```

- PictureSchedule 处理图片资源管理定时任务

  ```java
  @Slf4j
  @Component
  public class PictureSchedule {
  
      @Autowired
      private XtspzService xtspzService;
      @Autowired
      private PigeonService pigeonService;
      @Autowired
      private FileUtil fileUtil;
      @Value("${file.logo.path}")
      private String logoBasePath;
      @Value("${file.pigeon.path}")
      private String pigeonBasePath;
  
      /**
       * 删除logo多余图片
       * 每天 3点，12点，19点
       */
      @Scheduled(cron = "0 0 3,12,19 * * *")
      public void deleteLogoPictureTask() {
          //查出所有的图片存放到
          QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
          wrapper.select("logo_url");
          List<Xtspz> list = xtspzService.list(wrapper);
  
          //装入set方便查询
          Set<String> logoUrlSet = new HashSet<>();
          list.forEach(xtspz -> {
              if (xtspz != null) {
                  logoUrlSet.add(xtspz.getLogoUrl());
              }
          });
  
          //统计
          AtomicInteger count = new AtomicInteger();
  
          Path logoPath = Paths.get(logoBasePath);
          Stream<Path> fileList = fileUtil.getFileListByDirectory(logoPath);
          fileList.forEach(file -> {
              String fileName = file.getFileName().toString();
              //判断是否在数据库中
              if (!logoUrlSet.contains(fileName)) {
                  //删除
                  boolean success = fileUtil.deleteFile(fileName, logoPath);
                  if (success) {
                      log.info("成功删除logo图片 {}", fileName);
                      count.getAndIncrement();
                  }
              }
          });
  
          log.info("此次清除logo多余资源图片 {} 个", count);
      }
  
      /**
       * 删除pigeon多余图片
       * 每天 3点，12点，19点
       */
      @Scheduled(cron = "0 0 3,12,19 * * *")
      public void deletePigeonPictureTask() {
          //查出所有的图片存放到
          QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
          wrapper.select("picture_url");
          List<Pigeon> list = pigeonService.list(wrapper);
  
          //装入set方便查询
          Set<String> pigeonUrlSet = new HashSet<>();
          list.forEach(pigeon -> {
              if (pigeon != null) {
                  pigeonUrlSet.add(pigeon.getPictureUrl());
              }
          });
  
          //统计
          AtomicInteger count = new AtomicInteger();
  
          Path pigeonPath = Paths.get(pigeonBasePath);
          Stream<Path> fileList = fileUtil.getFileListByDirectory(pigeonPath);
          fileList.forEach(file -> {
              String fileName = file.getFileName().toString();
              //判断是否在数据库中
              if (!pigeonUrlSet.contains(fileName)) {
                  //删除
                  boolean success = fileUtil.deleteFile(fileName, pigeonPath);
                  if (success) {
                      log.info("成功删除pigeon多余图片 {}", fileName);
                      count.getAndIncrement();
                  }
              }
          });
  
          log.info("此次清除pigeon多余图片资源 {} 个", count);
      }
  }
  ```

#### 2.2.9 系统详细设计与实现

##### 2.2.9.1 登录服务

- 登录的密码验证设计

  数据库中存储的信息为password的加密结果和加密的盐值

  ```sql
  create table t_user
  (
      id       bigint       not null comment '自然主键'
          primary key,
      account  varchar(255) not null comment '账号',
      password blob         not null comment '密码哈希值',
      salt     blob         not null comment '盐值',
      gid      bigint       not null comment '组id',
      admin    bit          not null comment '管理员权限',
      constraint account
          unique (account)
  );
  ```

  加密方法为将password和salt拼串进行Argon2算法哈希加密，保护数据库避免发生数据库密码泄露

  登录实现逻辑如下：

  ```java
  //验证密码并更新加密
  user = loginService.loginVerify(user);
  loginService.updateEncrypt(user);
  ```

  验证登录密码

  ```java
  //查询用户
  QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", inputUser.getAccount());
  User user = userMapper.selectOne(accountQuery);
  
  if (user == null) {
      throw new NotFindUserException("该用户不存在");
  }
  
  //验证密码
  boolean passVerify = encryptUtil.passwordVerify(inputUser.getPassword(), user);
  
  if (!passVerify) {
      throw new PasswordErrorException("密码错误");
  }
  
  //传入密码以更新加密
  user.setPassword(inputUser.getPassword());
  
  return user;
  ```

  更新加密结果

  每次登录，重新获取盐值，跟新数据库中信息，避免信息泄露造成安全问题

  ```java
  encryptUtil.passwordEncrypt(user);
  int updateSuccess = userMapper.updateById(user);
  if (updateSuccess != 1) {
      log.error("传入用户参数 {} 时发生系统更新错误", user);
      throw new PasswordUpdateFailException("密码更新发生错误");
  }
  ```

  处理信息

  ```java
  //拿取用户信息
  String ip = ipUtil.getIp(request);
  //解析请求头中User-Agent
  String userAgent = request.getHeader("User-Agent");
  UserAgent parseUserAgent = UserAgent.parseUserAgentString(userAgent);
  //获取用户设备信息
  Browser browser = parseUserAgent.getBrowser();
  Version browserVersion = parseUserAgent.getBrowserVersion();
  OperatingSystem os = parseUserAgent.getOperatingSystem();
  Long id = user.getId();
  Long gid = user.getGid();
  Boolean admin = user.getAdmin();
  
  //把用户信息集合成map
  Map<String, Object> claim = new HashMap<>();
  claim.put("id", id);
  claim.put("account", account);
  claim.put("gid", gid);
  claim.put("admin", admin);
  claim.put("ip", ip);
  
  //传入用户信息map，为用户生成token
  String token = jwtUtil.getLimitlessToken(claim);
  
  //将token存入redis
  whitelistUtil.setToken(id, ip, token);
  
  //根据uid最大限制剔除多余的ip下的token
  whitelistUtil.limitToken(id);
  
  //记录登录日志
  LoginMsg loginMsg = new LoginMsg();
  loginMsg.setIp(ip);
  loginMsg.setGid(gid);
  loginMsg.setTime(LocalDateTime.now());
  loginMsg.setAccount(account);
  loginMsg.setBrowser(browser.getName() + browserVersion.getVersion());
  loginMsg.setOs(os.getName());
  loginMsg.setDevice(os.getDeviceType().getName());
  boolean success = loginMsgService.save(loginMsg);
  if (!success) {
      log.error("记录登录信息 {} 出错", loginMsg);
      throw new SaveFailException("登录出错");
  }
  ```

- 退出登录实现逻辑

  ```java
  //获取id
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long id = (Long) info.get("id");
  
  //剔除线上token
  whitelistUtil.deleteToken(id);
  
  //销毁session
  session.invalidate();
  ```

- 免登录验证

  与登录拦截器逻辑类似

- 修改密码逻辑实现

  ```java
  //验证原密码
  loginService.loginVerify(user);
  
  //装配为现密码
  user.setPassword(userVo.getNewPassword().getBytes());
  
  //添加id
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long id = (Long) info.get("id");
  user.setId(id);
  
  //更新密码，并加密
  loginService.updateEncrypt(user);
  
  //剔除线上token
  whitelistUtil.deleteToken(id);
  ```

##### 2.2.9.2 选项配置字典服务

此服务的设计旨在提高代码复用率，但可能存在潜在的SQL注入风险。为了应对这个问题，引入了字段名校验机制，并对SQL注入者进行惩罚。

- 校验配置

  ```java
  @Value("${xxpz.tableNames}")
  private String[] names;
  ```

- 初始化

  ```java
  private Set<String> tableNames;
  
  @PostConstruct
  public void init() {
      tableNames = Set.of(names);
      log.info("选项配置类型防注入set初始化完成");
  }
  ```

- 核心逻辑实现

  ```java
  /**
   * 获取所有类型的所有数据
   */
  @GetMapping
  public Result getAll(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //添加条件 gid or gid = systemGid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid).or().eq("gid", systemGid);
  
      //字段
      wrapper.select("name", "type");
  
      //查询
      List<Xxpz> list = xxpzService.list(wrapper);
      return new Result(list, SuccessCode.Success.code, "查询成功");
  }
  
  /**
   * 根据类型获取所有数据
   */
  @GetMapping("{type}")
  public Result getAllByType(@PathVariable String type, HttpServletRequest request) {
      //判断是否异常type注入
      if (!tableNames.contains(type)) {
          //中级不会发生sql注入
          throw new MaliciousSqlInjectionException("配置类型异常注入", SeverityLevel.Critical);
      }
  
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //添加条件 type and gid or gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("type", type).and(qw -> qw.eq("gid", systemGid).or().eq("gid", gid));
  
      //查询
      List<Xxpz> list = xxpzService.list(wrapper);
      return new Result(list, SuccessCode.Success.code, "查询成功");
  }
  
  /**
   * 根据id删除数据
   */
  @DeleteMapping("{id}")
  public Result deleteOne(@PathVariable("id") Long id, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //添加条件 id = id and gid = gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid).eq("id", id);
  
      //执行
      boolean success = xxpzService.remove(wrapper);
      if (!success) {
          throw new DeleteFailException("配置选项不存在或无法删除");
      }
      return new Result(SuccessCode.Success.code, "删除成功");
  }
  
  /**
   * 批量删除选项配置
   */
  @Transactional
  @PostMapping("delete")
  public Result batchDelete(@RequestBody List<Long> ids, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //条件 (id in ids) and gid = gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.in("id", ids).eq("gid", gid);
  
      boolean success = xxpzService.remove(wrapper);
      if (!success) {
          throw new DeleteFailException("配置选项不存在或无法删除");
      }
  
      return new Result(SuccessCode.Success.code, "删除成功");
  }
  
  /**
   * 新增选项配置
   */
  @PostMapping("{type}")
  public Result add(@RequestBody Xxpz xxpz, @PathVariable String type, HttpServletRequest request) {
      //判断是否异常type注入
      if (!tableNames.contains(type)) {
          //中级修改请求，但不会发生sql注入
          throw new MaliciousSqlInjectionException("配置类型异常注入", SeverityLevel.Critical);
      }
  
      //从请求域中获取用户信息
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      xxpz.setAuthor((String) info.get("account"));
      xxpz.setGid((Long) info.get("gid"));
      xxpz.setType(type);
  
      boolean success = xxpzService.save(xxpz);
      if (!success) {
          log.error("新增配置传入 {} 时发生服务错误", xxpz);
          throw new SaveFailException("服务错误，添加失败");
      }
  
      return new Result(xxpz, SuccessCode.Success.code, "添加成功");
  }
  
  /**
   * 根据id更新数据
   */
  @PutMapping
  public Result update(@RequestBody Xxpz xxpz, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //安全检查
      if (xxpz.getGid() == null || !xxpz.getGid().equals(gid)) {
          throw new SaveFailException("用户信息不匹配，请重试");
      }
  
      //条件 id = id and gid = gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("id", xxpz.getId()).eq("gid", gid);
  
      //执行
      boolean success = xxpzService.update(xxpz, wrapper);
      if (!success) {
          throw new SaveFailException("数据更新失败，请重试");
      }
      return new Result(SuccessCode.Success.code, "更新成功");
  }
  ```

##### 2.2.9.3 血统书配置服务

此服务提供图片文件的上传，为此设计了文件格式检查，和资源路径配置

- 配置与初始化

  ```java
  @Value("${file.logo.path}")
  private String basePath;
  @Value("${file.logo.type}")
  private String[] typeArray;
  @Autowired
  private XtspzService xtspzService;
  /**
   * 图片类型
   */
  private Set<String> type;
  private Path path;
  
  /**
   * bean初始化
   */
  @PostConstruct
  public void init() {
      //初始化资源
      path = Paths.get(basePath);
      type = Set.of(typeArray);
      //初始化文件夹
      fileUtil.initDirectory(path);
  }
  ```

- 上传图片

  ```java
  /**
   * 上传logo图片
   */
  @PostMapping("logo")
  public Result uploadLogo(MultipartFile file) {
  
      if (Objects.isNull(file) || file.isEmpty()) {
          throw new FileSaveException("不能上传空文件");
      }
  
      //检查文件类型
      fileUtil.checkFileType(file, type);
  
      //文保存到服务器
      String fileName = fileUtil.storeFile(file, path);
  
      return new Result(fileName, SuccessCode.Success.code, "保存成功");
  }
  ```

- 其他信息的保存与读取

  ```java
  /**
   * 获取所有信息
   */
  @GetMapping
  public Result getInfo(HttpServletRequest request) {
      //从请求域中获取用户信息
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
      //条件 gid = gid
      QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid);
      //查询字段
      wrapper.select("logo_url", "name", "short_name", "phone", "mail", "url", "address");
      Xtspz xtspz = xtspzService.getOne(wrapper);
      if (xtspz == null) {
          xtspz = new Xtspz();
      }
      return new Result(xtspz, SuccessCode.Success.code, "查询成功");
  }
  
  /**
   * 保存信息数据
   */
  @PostMapping
  public Result postInfo(@RequestBody Xtspz xtspz, HttpServletRequest request) {
      //从请求域中获取用户信息
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
      //条件 gid = gid
      QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid);
      //装填
      xtspz.setGid(gid);
      //执行
      boolean success = xtspzService.saveOrUpdate(xtspz, wrapper);
      if (!success) {
          throw new SaveFailException("信息保存失败");
      }
      return new Result(SuccessCode.Success.code, "保存成功");
  }
  ```

##### 2.2.9.4 团队用户账号管理服务

提供了成员的添加、删除、查看信息、查看状态等功能

- 密码字节初始化

  ```java
  @Value("${user.defaultPassword}")
  private String defaultPassword;
  private byte[] defaultPasswordBin;
  
  /**
   * 一次性初始化为字节数组
   * 减少服务器资源消耗
   */
  @PostConstruct
  public void init() {
      defaultPasswordBin = defaultPassword.getBytes();
      log.info("初始化 defaultPassword 化为字节数组完成 {}", defaultPasswordBin);
  }
  ```

- 获取成员在线状态

  通过查看redis中的信息获取在线状态，状态分为三个级别具体实现见 **白名单工具**。

  服务层逻辑如下：

  ```java
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
  
  //查看在线状态并将所有信息装入vo
  List<AdminUserVo> adminUserList = new ArrayList<>();
  list.forEach(user -> {
      //获取id
      Long id = user.getId();
      //获取状态
      int state = whitelistUtil.getState(RedisNamespace.Whitelist, id + ":");
      //加入返回列表
      AdminUserVo adminUser = AdminUserVo.builder()
              .state(state)
              .account(user.getAccount())
              .admin(user.getAdmin())
              .gid(user.getGid())
              .id(id)
              .build();
      adminUserList.add(adminUser);
  });
  ```

- 重置密码

  重置密码逻辑与修改用户自身密码逻辑类似。

  其中安全检查严密度由以下代码体现：

  ```java
  //获取gid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long gid = (Long) info.get("gid");
  
  //检查gid安全
  if (!user.getGid().equals(gid)) {
      throw new SaveFailException("用户信息不匹配，请重试");
  }
  
  //获取传入的id
  Long id = user.getId();
  
  //id = id and gid = gid
  QueryWrapper<User> wrapper = new QueryWrapper<>();
  wrapper.eq("id", id).eq("gid", gid);
  
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
  ```

##### 2.2.9.5 鸽舍信息设置服务

获取信息和保存信息的逻辑与血统书配置服务相似。实现逻辑见 **2.2.9.3 血统书配置服务**。

##### 2.2.9.6 地址信息服务

鸽舍信息设置服务需要实现省市区的级联查询。此服务提供了省市区级联查询功能，以下是级联查询后端接口的简单实现：

思路：根据父级id查询子级信息。

```java
@RestController
@RequestMapping("api")
public class AddressController {

    @Autowired
    private ProvincialService provincialService;
    @Autowired
    private UrbanService urbanService;
    @Autowired
    private AreasService areasService;

    /**
     * 查询省
     */
    @GetMapping("provincial")
    public Result getProvincial() {
        List<Provincial> list = provincialService.list();
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据省查询市
     */
    @GetMapping("urban/{id}")
    public Result getUrban(@PathVariable Long id) {
        QueryWrapper<Urban> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        List<Urban> list = urbanService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }

    /**
     * 根据市查询区
     */
    @GetMapping("area/{id}")
    public Result getAreas(@PathVariable Long id) {
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);
        List<Area> list = areasService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "查询成功");
    }
}
```

##### 2.2.9.7 鸽棚巢箱服务

该服务提供：获取鸽棚巢箱信息、添加鸽棚巢箱、修改信息、删除鸽棚巢箱、加入鸽子、统计鸽子数量等接口：

由于数据表t_pigeon_gpcx未提供权限控制字段，所以需要连表进行权限查询

- 统一权限查询封装

  ```java
  /**
   * 检查权限
   * 检查是否gpcxId被用户修改
   * 用以查询非本团队的gpcx信息
   */
  private String check(Long gpcxId, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //条件 id = gpcxId
      QueryWrapper<Gpcx> wrapper = new QueryWrapper<>();
      wrapper.eq("id", gpcxId);
  
      //字段 gid
      wrapper.select("gid", "name");
  
      //执行
      Gpcx gpcx = gpcxService.getOne(wrapper);
  
      //检查
      if (!gid.equals(gpcx.getGid())) {
          throw new MaliciousSqlInjectionException("非法查询", SeverityLevel.Critical);
      }
  
      return gpcx.getName();
  }
  ```

- 统计鸽棚巢箱中鸽子数量逻辑

  ```java
  /**
   * 获取鸽棚巢箱中鸽子的数量
   */
  @GetMapping("pigeon/{gpcxId}")
  public Result getPigeonNumber(@PathVariable Long gpcxId, HttpServletRequest request) {
      //检查权限
      check(gpcxId, request);
      //执行查询
      //条件 gpcxId = gpcxId
      QueryWrapper<PigeonGpcx> wrapper = new QueryWrapper<>();
      wrapper.eq("gpcx_id", gpcxId);
      //执行计数
      long count = pigeonGpcxService.count(wrapper);
      return new Result(count, SuccessCode.Success.code, "查询成功");
  }
  ```

- 添加鸽子业务（记录日志具体逻辑见 **2.2.9.10 操作日志服务**）

  逻辑思路：批量操作，不考录原鸽棚巢箱，都改为目标鸽棚巢箱，最后想用户反馈具体操作数量

  ```java
  @Override
  @Transactional
  public int addPigeonToGpcx(List<Long> ids, Long gpcxId, String name, String account, Long gid) {
  
      AtomicReference<Integer> updateNumber = new AtomicReference<>(0);
      //获取更新时间
      LocalDate now = LocalDate.now();
  
      ids.forEach(id -> {
          UpdateWrapper<PigeonGpcx> updateWrapper = new UpdateWrapper<>();
          updateWrapper.eq("pid", id).set("gpcx_id", gpcxId);
          //更新
          int update = pigeonGpcxMapper.update(null, updateWrapper);
  
          if (!SqlHelper.retBool(update)) {
              //新增
              PigeonGpcx pigeonGpcx = new PigeonGpcx();
              pigeonGpcx.setPid(id);
              pigeonGpcx.setGpcxId(gpcxId);
              int insert = pigeonGpcxMapper.insert(pigeonGpcx);
  
              if (!SqlHelper.retBool(insert)) {
                  throw new SaveFailException("新增时发生错误");
              }
          } else {
              //记录更新数
              updateNumber.getAndSet(updateNumber.get() + 1);
          }
  
          oplogService.oplog(account, id, gid, name, LogType.TRANSFER);
      });
  
      //更新鸽子信息
      //条件 id = ids 设置 更新 时间 和 鸽棚名
      UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
      wrapper.in("id", ids)
              .set("gpcx", name)
              .set("update_data", now);
      int update = pigeonMapper.update(null, wrapper);
      if (!SqlHelper.retBool(update) || update != ids.size()) {
          throw new SaveFailException("更新鸽子信息失败，请重试");
      }
  
      return updateNumber.get();
  }
  ```

- 增删改查业务（大体相似）

##### 2.2.9.8 鸽子信息服务

列举重要内容

- 分页读取鸽子信息

  使用分页插件实现

  ```java
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
  ```

- 新增鸽子业务

  此业务对录入信息较为宽容，适用性强，导致代码圈复杂度较高

  逻辑思路：

  1. 获取当前日期。
  2. 设置鸽子数据的更新时间为当前日期。
  3. 检查鸽子数据的`id`字段是否为空，如果为空则表示该记录不存在，执行新增操作。
  4. 如果鸽子数据的`id`字段不为空，表示记录已存在，执行更新操作。
  5. 在新增操作中，填充鸽子额外信息的创建时间、`gid`和`pid`字段，然后分别执行基础信息和额外信息的插入操作。
  6. 在更新操作中，根据给定的`gid`和`id`构建查询条件，然后执行基础信息的更新操作。
  7. 同样根据`pid`构建查询条件，执行额外信息的更新操作。
  8. 记录相应操作的日志，包括账号信息、鸽子的环号、`pid`、`gid`和操作类型。
  9. 如果存在指定的子代鸽子的`oid`，则更新其父代字段为当前鸽子的`pid`，根据鸽子的性别决定是更新`fid`还是`mid`字段。
  10. 再次记录更新子代父代关系的日志，包括账号信息、子代鸽子的环号、`gid`、当前鸽子的环号和操作类型。
  11. 返回鸽子数据的`pid`。
 ```java
@Override
@Transactional
public Long saveOrUpdatePigeonById(Pigeon pigeon, PigeonInfo pigeonInfo, Long oid, Long gid, String account) {
    //获取当前时间
    LocalDate now = LocalDate.now();

    //装填更新时间
    pigeon.setUpdateData(now);

    //pid
    Long pid = pigeon.getId();

    //判断此记录是否存在
    if (pid == null) {
        //不存在，新增
        //装填创建时间
        pigeonInfo.setCreateTime(now);

        //装填gid
        pigeon.setGid(gid);

        //执行新增
        int insertPigeon = pigeonMapper.insert(pigeon);

        //检查新增
        if (!SqlHelper.retBool(insertPigeon)) {
            throw new SaveFailException("新增鸽子基础信息时失败");
        }

        //获取pid
        pid = pigeon.getId();

        //装填pid
        pigeonInfo.setPid(pid);

        //执行新增
        int insertPigeonInfo = pigeonInfoMapper.insert(pigeonInfo);

        //检查新增
        if (!SqlHelper.retBool(insertPigeonInfo)) {
            throw new SaveFailException("新增鸽子额外信息时失败");
        }

        //记录日志
        oplogService.oplog(account, pigeon.getRingNumber(), pid, gid, LogType.INSERT);
    } else {
        //存在，更新
        //条件 gid = gid and id = id
        QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
        pigeonWrapper.eq("gid", gid).eq("id", pid);

        //执行更新
        int pigeonUpdate = pigeonMapper.update(pigeon, pigeonWrapper);

        //检查更新
        if (!SqlHelper.retBool(pigeonUpdate)) {
            throw new SaveFailException("更新鸽子基础信息时失败");
        }

        //条件 pid = pid
        QueryWrapper<PigeonInfo> pigeonInfoWrapper = new QueryWrapper<>();
        pigeonInfoWrapper.eq("pid", pid);

        //执行更新
        int updatePigeonInfo = pigeonInfoMapper.update(pigeonInfo, pigeonInfoWrapper);

        //检查更新
        if (!SqlHelper.retBool(updatePigeonInfo)) {
            throw new SaveFailException("更新鸽子基额外息时失败");
        }

        //记录日志
        oplogService.oplog(account, pigeon.getRingNumber(), pid, gid, LogType.UPDATE);
    }

    //为此鸽子的子代更新父代id
    if (oid != null) {
        //条件
        UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", oid)
                .eq("gid", gid)
                .set("update_data", now)
                .set(pigeon.getSex().equals("雄"), "fid", pid)
                .set(pigeon.getSex().equals("雌"), "mid", pid);

        int offspringPigeonUpdate = pigeonMapper.update(null, wrapper);

        //检查跟新
        if (!SqlHelper.retBool(offspringPigeonUpdate)) {
            throw new SaveFailException("鸽子子代信息更新失败");
        }

        //记录日志
        oplogService.oplog(account, oid, gid, pigeon.getRingNumber(), LogType.RELATE);
    }

    return pid;
}
 ```

- 删除、批量删除鸽子业务（记录日志具体逻辑见 **2.2.9.10 操作日志服务**）

  逻辑思路：

  1. 使用`id`和`gid`条件构建查询条件`pigeonQueryWrapper`，然后通过`selectOne`方法获取符合条件的鸽子数据`pigeon`，以备后续记录日志使用。
  2. 使用`pigeonQueryWrapper`删除基础信息，即从数据库中删除满足条件的鸽子数据。如果删除失败，则抛出`DeleteFailException`异常。
  3. 使用`id`条件构建查询条件`pigeonInfoQueryWrapper`，然后通过`delete`方法删除额外信息，即删除与该鸽子数据关联的额外信息。如果删除失败，则抛出`DeleteFailException`异常。
  4. 记录删除操作的日志，调用`oplogService.oplog`方法记录操作日志，包括账号信息、鸽子的环号、`gid`和操作类型。
  5. 更新子代的父代信息。首先根据`gid`和性别条件（"雌"或"雄"）构建查询条件`queryWrapper`，然后使用`selectList`方法获取所有满足条件的子代鸽子数据`offspringPigeons`。
  6. 获取当前日期。
  7. 遍历每个子代鸽子数据，通过`UpdateWrapper`构建更新条件`updateWrapper`，根据子代鸽子的性别设置对应的父代字段为`null`，并设置`update_data`为当前日期。
  8. 使用`pigeonMapper`的`update`方法执行更新操作，如果更新失败，则抛出`SaveFailException`异常。
  9. 记录解除血亲关系的日志，调用`oplogService.oplog`方法记录操作日志，包括账号信息、子代鸽子的环号、`gid`和操作类型。

  ```java
  @Override
  @Transactional
  public void deletePigeonById(Long id, String sex, Long gid, String account) {
      //条件 id AND gid
      QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
      pigeonQueryWrapper.eq("id", id).eq("gid", gid);
  
      //获取信息以记录日志
      Pigeon pigeon = pigeonMapper.selectOne(pigeonQueryWrapper);
  
      //删除基础信息
      int deletePigeon = pigeonMapper.delete(pigeonQueryWrapper);
  
      if (!SqlHelper.retBool(deletePigeon)) {
          throw new DeleteFailException("基础信息删除失败，请重试");
      }
  
      //条件 pid = id
      QueryWrapper<PigeonInfo> pigeonInfoQueryWrapper = new QueryWrapper<>();
      pigeonInfoQueryWrapper.eq("pid", id);
  
      //删除额外信息
      int deletePigeonInfo = pigeonInfoMapper.delete(pigeonInfoQueryWrapper);
  
      if (!SqlHelper.retBool(deletePigeonInfo)) {
          log.error("id = {} 时，额外信息删除失败", id);
          throw new DeleteFailException("额外信息删除失败，请重试");
      }
  
      //记录日志
      oplogService.oplog(account, pigeon.getRingNumber(), gid, LogType.DELETE);
  
      //更新子代的父代信息
      //因为要记录日志所以先检索所有要更新的数据
      QueryWrapper<Pigeon> queryWrapper = new QueryWrapper<>();
      //条件
      queryWrapper.eq("gid", gid)
              .eq(sex.equals("雌"), "mid", id)
              .eq(sex.equals("雄"), "fid", id);
      List<Pigeon> offspringPigeons = pigeonMapper.selectList(queryWrapper);
  
      //获取更新时间
      LocalDate now = LocalDate.now();
  
      //更新
      offspringPigeons.forEach(offspringPigeon -> {
          UpdateWrapper<Pigeon> updateWrapper = new UpdateWrapper<>();
          updateWrapper.eq("id", offspringPigeon.getId())
                  .set("update_data", now)
                  .set(sex.equals("雄"), "fid", null)
                  .set(sex.equals("雌"), "mid", null);
  
          int update = pigeonMapper.update(null, updateWrapper);
  
          if (!SqlHelper.retBool(update)) {
              throw new SaveFailException("解除血亲关系出错");
          }
  
          //记录日志
          oplogService.oplog(account, offspringPigeon.getRingNumber(), offspringPigeon.getId(), gid, LogType.UNPARENT);
      });
  }
  ```

- 分享血统业务（记录日志具体逻辑见 **2.2.9.10 操作日志服务**）

  逻辑思路：

  1. 根据给定的`ids`和`gid`条件，使用`QueryWrapper`构建查询条件，从`Pigeon`表中获取符合条件的数据。
  2. 检查返回的数据数量是否与`ids`数量一致，如果不一致则抛出`NotFoundException`异常。
  3. 创建一个空的`pigeonInfos`列表，用于存储额外信息。
  4. 遍历每个鸽子数据，根据鸽子的`id`查询对应的额外信息，如果信息不存在则抛出`NotFoundException`异常，否则将信息添加到`pigeonInfos`列表中。
  5. 获取当前日期。
  6. 遍历每个鸽子数据，进行数据处理和插入操作。
  7. 对于每个鸽子数据，先记录发送者的日志。
  8. 对于鸽子数据，清除`id`、`fid`和`mid`字段，修改`updateData`为当前日期，将`gid`设置为接收方的`receiveGid`，然后插入到数据库中，如果插入失败则抛出`SaveFailException`异常。
  9. 对于鸽子额外信息，清除`id`字段，修改`createTime`为当前日期，将`pid`设置为刚插入的鸽子数据的`id`，然后插入到数据库中，如果插入失败则抛出`SaveFailException`异常。
  10. 最后记录接收者的日志。

  ```java
  @Override
  @Transactional
  public void sharePigeon(List<Long> ids, Long receiveGid, Long gid, String account) {
      //根据ids获取所有数据
      //条件 gid and id in ids
      QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
      pigeonWrapper.eq("gid", gid).in("id", ids);
      List<Pigeon> pigeons = pigeonMapper.selectList(pigeonWrapper);
  
      //检查数据个数是否准确
      int n = ids.size();
      if (n != pigeons.size()) {
          throw new NotFoundException("数据获取错误");
      }
  
      //同时获取额外信息，一一对应
      List<PigeonInfo> pigeonInfos = new ArrayList<>();
      pigeons.forEach(pigeon -> {
          QueryWrapper<PigeonInfo> pigeonInfoWrapper = new QueryWrapper<>();
          pigeonInfoWrapper.eq("pid", pigeon.getId());
          PigeonInfo pigeonInfo = pigeonInfoMapper.selectOne(pigeonInfoWrapper);
          //检查
          if (pigeonInfo == null) {
              throw new NotFoundException("数据获取错误");
          }
          pigeonInfos.add(pigeonInfo);
      });
  
      //获取日期
      LocalDate now = LocalDate.now();
  
      //处理数据，并插入
      for (int i = 0; i < n; i++) {
          Pigeon pigeon = pigeons.get(i);
          //发送者记录日志
          oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), gid, LogType.SHARE);
  
          //pigeon
          //清除 id, fid, mid
          //修改 update_data, gid
          //清除
          pigeon.setId(null);
          pigeon.setFid(null);
          pigeon.setMid(null);
          //修改
          pigeon.setUpdateData(now);
          pigeon.setGid(receiveGid);
          //插入
          int insertPigeon = pigeonMapper.insert(pigeon);
          //检查
          if (!SqlHelper.retBool(insertPigeon)) {
              throw new SaveFailException("分享基础信息失败");
          }
  
          //pigeonInfo
          //清除 id
          //修改 create_time, pid
          PigeonInfo pigeonInfo = pigeonInfos.get(i);
          //清除
          pigeonInfo.setId(null);
          //修改
          pigeonInfo.setCreateTime(now);
          pigeonInfo.setPid(pigeon.getId());
          //插入
          int insertPigeonInfo = pigeonInfoMapper.insert(pigeonInfo);
          //检查
          if (!SqlHelper.retBool(insertPigeonInfo)) {
              throw new SaveFailException("额外基础信息失败");
          }
  
          //接收者记录日志
          oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), receiveGid, LogType.RECEIVE);
      }
  }
  ```

  

- 关联血亲业务（记录日志具体逻辑见 **2.2.9.10 操作日志服务**）

  传入子代和父代，将子代信息改为父代id

  设计思路：

  1. 构建条件，通过`oid`和`gid`筛选需要更新的鸽子记录。
  2. 使用条件构建器(`UpdateWrapper`)设置更新的字段和值，包括更新日期(`update_data`)、父代(`fid`)或母代(`mid`)字段的值。
  3. 执行更新操作，将更新应用到符合条件的鸽子记录。
  4. 检查更新是否成功，如果更新失败，则记录错误日志并抛出保存失败的异常。
  5. 记录关联操作的日志，包括账号信息、子代鸽子的`oid`、`gid`以及相关的环号。

  ```java
  @Override
  @Transactional
  public void relatePigeon(Long id, String sex, String ringNumber, Long oid, Long gid, String account) {
      //获取更新日期
      LocalDate now = LocalDate.now();
  
      //条件
      UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
      wrapper.eq("id", oid)
              .eq("gid", gid)
              .set("update_data", now)
              .set(sex.equals("雄"), "fid", id)
              .set(sex.equals("雌"), "mid", id);
  
      int update = pigeonMapper.update(null, wrapper);
  
      if (!SqlHelper.retBool(update)) {
          log.error("关联 id = {}, oid = {} 时发生错误", id, oid);
          throw new SaveFailException("关联失败");
      }
  
      //记录日志
      oplogService.oplog(account, oid, gid, ringNumber, LogType.RELATE);
  }
  ```

- 解析文件保存鸽子业务

  - 文件校验

    ```java
    if (Objects.isNull(file) || file.isEmpty()) {
        throw new FileSaveException("不能上传空文件");
    }
    
    //检查文件格式
    String fileType = fileUtil.checkFileType(file, "application/vnd.ms-excel", "application/x-tika-ooxml", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    
    ```

  - 为文件解析提供校验数据

    ```java
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
    ```

  - 区分版本提高适应性

    ```java
    List<Map<String, PigeonWrapperVo>> pigeonWrappers;
    
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
    ```

  - PigeonFileUtil 文件解析工具

    - 文件解析

      ```java
      /**
       * 旧版.xls解析工具
       * Office 2007- XML
       */
      public List<Map<String, PigeonWrapperVo>> getPigeonByXls(MultipartFile file, Map<String, Set<String>> xxpzMap) {
          List<Map<String, PigeonWrapperVo>> pigeonWrappers;
      
          try {
              HSSFWorkbook sheets = new HSSFWorkbook(file.getInputStream());
              HSSFSheet sheet = sheets.getSheetAt(0);
              pigeonWrappers = parsePojo(sheet, xxpzMap);
              sheets.close();
          } catch (IOException e) {
              log.error("检查文件 {} 时文件流发生错误", file, e);
              throw new FileSaveException("文件保存失败");
          }
      
          return pigeonWrappers;
      }
      
      /**
       * 新版.xlsx解析工具
       * Office 2007+ XML
       */
      public List<Map<String, PigeonWrapperVo>> getPigeonByXlsx(MultipartFile file, Map<String, Set<String>> xxpzMap) {
          List<Map<String, PigeonWrapperVo>> pigeonWrappers;
      
          try {
              XSSFWorkbook sheets = new XSSFWorkbook(file.getInputStream());
              XSSFSheet sheet = sheets.getSheetAt(0);
              pigeonWrappers = parsePojo(sheet, xxpzMap);
              sheets.close();
          } catch (IOException e) {
              log.error("检查文件 {} 时文件流发生错误", file, e);
              throw new FileSaveException("文件保存失败");
          }
      
          return pigeonWrappers;
      }
      ```

    - 解析表格（举例）

      ```java
      /**
       * 获取并检验
       * 赛绩描述
       * 固定行：8
       */
      private String getDetail(Row row) {
          Cell cell = row.getCell(8);
          if (cell == null || cell.getCellType() != CellType.STRING) {
              return null;
          }
          return cell.getStringCellValue();
      }
      
      /**
       * 获取并检验
       * 羽色
       * 固定行：6
       */
      private String getYs(Row row, Map<String, Set<String>> xxpzMap) {
          Cell cell = row.getCell(6);
          if (cell == null || cell.getCellType() != CellType.STRING) {
              return null;
          }
          String ys = cell.getStringCellValue();
          //检验
          if (!xxpzMap.get("yspz").contains(ys)) {
              throw new FileParseException("羽色异常");
          }
          return ys;
      }
      
      /**
       * 获取并检验
       * 足环号
       * 固定行：0
       */
      private String getRingNumber(Row row, Map<String, Set<String>> xxpzMap) {
          Cell cell = row.getCell(0);
          if (cell == null || cell.getCellType() != CellType.STRING) {
              throw new FileParseException("足环号异常");
          }
          String ringNumber = cell.getStringCellValue();
          //检验
          verifyRingNumber(ringNumber, xxpzMap);
          return ringNumber;
      }
      ```

    - 解析表格代码中使用到的校验

      ```java
      /**
       * 检验足环规范
       */
      private void verifyRingNumber(String ringNumber, Map<String, Set<String>> xxpzMap) {
          //检验
          String[] ringNumberSplit = ringNumber.split("-");
          Set<String> country = xxpzMap.get("country");
          Set<String> province = xxpzMap.get("province");
          //国家
          if (!country.contains(ringNumberSplit[0])) {
              throw new FileParseException("足环错误：不存在的国家代号");
          }
          //年份
          if (ringNumberSplit[1].length() != 4) {
              throw new FileParseException("足环错误：错误的年份");
          }
          //国家
          if (!province.contains(ringNumberSplit[2])) {
              throw new FileParseException("足环错误：不存在的省份代号");
          }
      }
      ```

  - 最后将文件解析的信息存入数据库（记录日志具体逻辑见 **2.2.9.10 操作日志服务**）

    设计思路：

    1. 获取当前日期。
    2. 对每个父代子代信息进行保存操作。
    3. 对于每个父代，首先根据足环、性别和`gid`进行检索，判断是否已存在对应的鸽子记录。
    4. 如果鸽子记录不存在，则执行新增操作。设置父代鸽子的更新时间、`gid`等信息，并分别执行基础信息和额外信息的插入操作。
    5. 如果鸽子记录已存在，则直接获取其`id`作为子代鸽子的父代`fid`。
    6. 对于每个母代，采取类似的操作，判断鸽子记录是否已存在，如果不存在则执行新增操作，如果存在则获取其`id`作为子代鸽子的母代`mid`。
    7. 对于每个子代鸽子，设置其更新时间、`gid`等信息，并分别执行基础信息和额外信息的插入操作。
    8. 记录相关操作的日志，包括账号信息、鸽子的环号、`id`、`gid`和操作类型。
    9. 如果存在父代，记录子代与父代的关系的日志，包括账号信息、子代鸽子的环号、`id`、`gid`以及父代鸽子的环号。
    10. 如果存在母代，记录子代与母代的关系的日志，包括账号信息、子代鸽子的环号、`id`、`gid`以及母代鸽子的环号。

    ```java
    @Override
    @Transactional
    public void savePigeonByFile(List<Map<String, PigeonWrapperVo>> pigeonWrappers, Long gid, String account) {
        //获取时间
        LocalDate now = LocalDate.now();
    
        //保存父代
        pigeonWrappers.forEach(pigeonMap -> {
            PigeonWrapperVo pigeonWrapperVo = pigeonMap.get("pigeon");
            //父
            PigeonWrapperVo father = pigeonMap.get("father");
            if (father != null) {
                //父fatherPigeon
                Pigeon fatherPigeon = father.getPigeon();
    
                //先根据足环检索是否存在
                QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
                wrapper.eq("ring_number", fatherPigeon.getRingNumber())
                        .eq("sex", "雄")
                        .eq("gid", gid);
                Pigeon selectPigeon = pigeonMapper.selectOne(wrapper);
    
                if (selectPigeon == null) {
                    //新增
                    fatherPigeon.setUpdateData(now);
                    fatherPigeon.setGid(gid);
                    int fatherPigeonInsert = pigeonMapper.insert(fatherPigeon);
                    if (!SqlHelper.retBool(fatherPigeonInsert)) {
                        throw new SaveFailException("父鸽基础信息保存失败");
                    }
    
                    //父fatherPigeonInfo
                    PigeonInfo fatherPigeonInfo = new PigeonInfo();
                    fatherPigeonInfo.setCreateTime(now);
                    fatherPigeonInfo.setPid(fatherPigeon.getId());
                    int fatherPigeonInfoInsert = pigeonInfoMapper.insert(fatherPigeonInfo);
                    if (!SqlHelper.retBool(fatherPigeonInfoInsert)) {
                        throw new SaveFailException("父鸽额外信息保存失败");
                    }
    
                    //给子代装填fid
                    pigeonWrapperVo.getPigeon().setFid(fatherPigeon.getId());
    
                    //记录日志
                    oplogService.oplog(account, fatherPigeon.getRingNumber(), fatherPigeon.getId(), gid, LogType.INSERT);
                } else {
                    //给子代装填fid
                    pigeonWrapperVo.getPigeon().setFid(selectPigeon.getId());
                }
            }
    
            //母
            PigeonWrapperVo mother = pigeonMap.get("mother");
            if (mother != null) {
                //母motherPigeon
                Pigeon motherPigeon = mother.getPigeon();
    
                //先根据足环检索是否存在
                QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
                wrapper.eq("ring_number", motherPigeon.getRingNumber())
                        .eq("sex", "雌")
                        .eq("gid", gid);
                Pigeon selectPigeon = pigeonMapper.selectOne(wrapper);
    
                if (selectPigeon == null) {
                    //新增
                    motherPigeon.setUpdateData(now);
                    motherPigeon.setGid(gid);
                    int fatherPigeonInsert = pigeonMapper.insert(motherPigeon);
                    if (!SqlHelper.retBool(fatherPigeonInsert)) {
                        throw new SaveFailException("母鸽基础信息保存失败");
                    }
                    //母motherPigeonInfo
                    PigeonInfo motherPigeonInfo = new PigeonInfo();
                    motherPigeonInfo.setCreateTime(now);
                    motherPigeonInfo.setPid(motherPigeon.getId());
                    int motherPigeonInfoInsert = pigeonInfoMapper.insert(motherPigeonInfo);
                    if (!SqlHelper.retBool(motherPigeonInfoInsert)) {
                        throw new SaveFailException("母鸽额外信息保存失败");
                    }
                    //给子代装填mid
                    pigeonWrapperVo.getPigeon().setMid(motherPigeon.getId());
    
                    //记录日志
                    oplogService.oplog(account, motherPigeon.getRingNumber(), motherPigeon.getId(), gid, LogType.INSERT);
                } else {
                    //给子代装填mid
                    pigeonWrapperVo.getPigeon().setMid(selectPigeon.getId());
                }
            }
    
            //保存子代信息
            //pigeon
            Pigeon pigeon = pigeonWrapperVo.getPigeon();
            pigeon.setUpdateData(now);
            pigeon.setGid(gid);
            int pigeonInsert = pigeonMapper.insert(pigeon);
            if (!SqlHelper.retBool(pigeonInsert)) {
                throw new SaveFailException("子代鸽基础信息保存失败");
            }
            //pigeonInfo
            PigeonInfo pigeonInfo = pigeonWrapperVo.getPigeonInfo();
            pigeonInfo.setCreateTime(now);
            pigeonInfo.setPid(pigeon.getId());
            int pigeonInfoInsert = pigeonInfoMapper.insert(pigeonInfo);
            if (!SqlHelper.retBool(pigeonInfoInsert)) {
                throw new SaveFailException("子代鸽额外信息保存失败");
            }
    
            //记录日志
            oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), gid, LogType.INSERT);
            if (father != null) {
                oplogService.oplog(account, pigeon.getRingNumber(),
                        pigeon.getId(), gid,
                        father.getPigeon().getRingNumber(),
                        LogType.RELATE
                );
            }
            if (mother != null) {
                oplogService.oplog(account, pigeon.getRingNumber(),
                        pigeon.getId(), gid,
                        mother.getPigeon().getRingNumber(),
                        LogType.RELATE
                );
            }
        });
    }
    ```

- 返回后端文件模板

  ```java
  /**
   * 读取模板返回给前端
   */
  @GetMapping("template")
  public void getFileTemplate(HttpServletResponse response) {
      // 设置响应正文的MIME类型
      response.setContentType("application/vnd.ms-excel");
      //返回文件
      fileUtil.responseFileByRelativePath(pigeonTemplatePath, response);
  }
  ```

##### 2.2.9.9 快速入库服务

- 模糊查询，为快速入库提供搜索

  ```java
  //获取gid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long gid = (Long) info.get("gid");
  
  //条件 gid and sex and ring like value
  QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
  wrapper.eq("gid", gid)
          .eq(sex.equals("father"), "sex", "雄")
          .eq(sex.equals("mother"), "sex", "雌")
          .like("ring_number", value);
  
  //字段
  wrapper.select("id", "ring_number");
  
  List<Pigeon> list = pigeonService.list(wrapper);
  ```

- 快速入库

  - 解析前端信息

    ```java
    //解析请求体
    Long fid = map.get("fid") == null ? null : Long.valueOf((String) map.get("fid"));
    Long mid = map.get("mid") == null ? null : Long.valueOf((String) map.get("mid"));
    List<Map<String, Object>> pigeonMaps = (List<Map<String, Object>>) map.get("pigeons");
    
    String fr = null;
    String mr = null;
    //获取父代足环
    if (fid != null) {
        Pigeon fp = pigeonService.getById(fid);
        fr = fp.getRingNumber();
    }
    if (mid != null) {
        Pigeon mp = pigeonService.getById(mid);
        mr = mp.getRingNumber();
    }
    
    //检查
    if (fr == null && mr == null) {
        throw new SaveFailException("信息不完整");
    }
    
    //获取gid
    Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
    Long gid = (Long) info.get("gid");
    String account = (String) info.get("account");
    
    //获取日期
    LocalDate now = LocalDate.now();
    
    //装配
    List<Pigeon> pigeons = new ArrayList<>();
    List<PigeonInfo> pigeonInfos = new ArrayList<>();
    pigeonMaps.forEach(pigeonMap -> {
        //pigeon
        Pigeon pigeon = new Pigeon();
        pigeon.setUpdateData(now);
        pigeon.setRingNumber((String) pigeonMap.get("ringNumber"));
        pigeon.setSex((String) pigeonMap.get("sex"));
        pigeon.setFid(fid);
        pigeon.setMid(mid);
        pigeon.setGid(gid);
        pigeons.add(pigeon);
        //pigeonInfo
        PigeonInfo pigeonInfo = new PigeonInfo();
        pigeonInfo.setCreateTime(now);
        pigeonInfos.add(pigeonInfo);
    });
    ```

  - 入库

    设计思路：

    1. 使用循环遍历`pigeons`列表，逐个处理每个鸽子对象。
    2. 对于每个鸽子对象，执行插入操作，将鸽子基础信息保存到数据库中。
    3. 检查插入操作的结果，如果插入失败，则抛出保存失败的异常。
    4. 获取插入后的鸽子记录的ID和环号。
    5. 将鸽子的ID赋值给对应的鸽子额外信息(`pigeonInfos`)中的`pid`字段。
    6. 记录鸽子基础信息的插入日志，包括账号信息、环号、ID、GID和操作类型为INSERT。
    7. 如果存在父代环号(`fatherRingNumber`)，记录与父代的关联日志。
    8. 如果存在母代环号(`motherRingNumber`)，记录与母代的关联日志。
    9. 对于每个鸽子额外信息(`pigeonInfo`)，执行插入操作，将额外信息保存到数据库中。
    10. 检查插入操作的结果，如果插入失败，则抛出保存失败的异常。

    ```java
    @Override
    @Transactional
    public void rapidBatchAddPigeon(List<Pigeon> pigeons, List<PigeonInfo> pigeonInfos, Long gid, String account, String fatherRingNumber, String motherRingNumber) {
    
        for (int i = 0, pigeonsSize = pigeons.size(); i < pigeonsSize; i++) {
            Pigeon pigeon = pigeons.get(i);
    
            //新增
            int insert = pigeonMapper.insert(pigeon);
    
            //检查
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("保存鸽子基础信息失败");
            }
    
            Long id = pigeon.getId();
            String ringNumber = pigeon.getRingNumber();
    
            //装填pid
            pigeonInfos.get(i).setPid(id);
    
            //记录日志
            oplogService.oplog(account, ringNumber, id, gid, LogType.INSERT);
            if (fatherRingNumber != null) {
                oplogService.oplog(account, ringNumber, id, gid, fatherRingNumber, LogType.RELATE);
            }
            if (motherRingNumber != null) {
                oplogService.oplog(account, ringNumber, id, gid, motherRingNumber, LogType.RELATE);
            }
        }
    
        pigeonInfos.forEach(pigeonInfo -> {
            int insert = pigeonInfoMapper.insert(pigeonInfo);
    
            //检查
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("保存鸽子额外信息失败");
            }
        });
    }
    ```

##### 2.2.9.10 操作日志服务

- 后端内部日志操作（举例）

  ```java
  @Override
  @Transactional
  public void oplog(String account, String ringNumber, Long pid, Long gid, LogType type) {
      Oplog oplog = new Oplog(
              account, ringNumber, type.getIndex(),
              type.getTip(), LocalDateTime.now(), pid, gid
      );
      int insert = oplogMapper.insert(oplog);
      if (!SqlHelper.retBool(insert)) {
          log.error("{} 日志记录失败", oplog);
          throw new LogException("日志保存失败");
      }
  }
  
  @Override
  @Transactional
  public void oplog(String account, String ringNumber, Long gid, LogType type) {
      if (type != LogType.DELETE) {
          throw new LogException("日志类型错误");
      }
      Oplog oplog = new Oplog(
              account, ringNumber, type.getIndex(),
              type.getTip(), LocalDateTime.now(), gid
      );
      int insert = oplogMapper.insert(oplog);
      if (!SqlHelper.retBool(insert)) {
          log.error("{} 日志记录失败", oplog);
          throw new LogException("日志保存失败");
      }
  }
  ```

- 条件分页查询

  设计思路：

  1. 从请求属性中获取`info`参数，该参数是一个Map对象，包含了一个名为`gid`的Long类型值，表示鸽舍的ID。
  2. 解析请求体中的条件参数，包括`content`、`tip`、`ringNumber`、`timeRange`、`author`、`current`和`pageSize`等。
  3. 检查`current`和`pageSize`是否为空，如果为空，则抛出异常。
  4. 解析时间范围参数`timeRange`，获取开始时间`startTime`和结束时间`endTime`。
  5. 创建查询条件`QueryWrapper`对象`wrapper`，设置各个查询条件，包括鸽舍ID、内容、提示、环号、作者和时间范围等。
  6. 创建分页对象`Page`，设置当前页数`current`、每页数量`pageSize`和最大限制`maxLimit`。
  7. 调用`oplogService`的`page`方法执行分页查询，传入分页对象和查询条件对象。
  8. 获取查询结果的分页对象`page`。

  ```java
  /**
   * 根据分页条件获取页面信息
   */
  @PostMapping
  public Result getConditionPage(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") PagingConditionVo condition, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //解析请求体
      Integer content = condition.getContent();
      String tip = condition.getTip();
      String ringNumber = condition.getRingNumber();
      List<LocalDateTime> timeRange = condition.getTimeRange();
      String author = condition.getAuthor();
      Integer current = condition.getCurrent();
      Integer pageSize = condition.getPageSize();
  
      if (current == null || pageSize == null) {
          log.error("PagingCondition {} 缺少必要条件", condition);
          throw new NotFoundException("服务错误");
      }
  
      LocalDateTime startTime = null;
      LocalDateTime endTime = null;
      if (timeRange != null && timeRange.size() == 2) {
          startTime = timeRange.get(0);
          endTime = timeRange.get(1);
      }
  
      //条件
      QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
      wrapper.eq(content != null, "content", content)
              .like(tip != null, "tip", tip)
              .like(ringNumber != null, "ring_number", ringNumber)
              .like(author != null, "author", author)
              .between(startTime != null && endTime != null, "time", startTime, endTime)
              .orderByDesc("time")
              .eq("gid", gid);
  
      //分页
      Page<Oplog> page = new Page<>();
      page.setCurrent(current)
              .setSize(pageSize)
              .setMaxLimit(maxLimit);
  
  
      page = oplogService.page(page, wrapper);
  
      return new Result(page, SuccessCode.Success.code, "查询成功");
  }
  ```

##### 2.2.9.12 鸽子详细信息服务

此服务提供详细页面的信息数据库查询与整合

- 查询子代

  设计思路：

  1. 从请求属性中获取`info`参数，该参数是一个Map对象，包含了一个名为`gid`的Long类型值，表示鸽舍的ID。
  2. 创建一个空的`pigeonOffspringList`，用于存储每一代的子孙鸽子列表。
  3. 创建一个初始代的鸽子列表，其中只包含一个祖先鸽子，将其添加到`pigeonOffspringList`中。
  4. 设置初始代数`generation`为0。
  5. 进入迭代循环，当代数小于限制数`limit`且当前代的鸽子列表不为空时进行迭代。
  6. 获取当前代的鸽子列表`pigeonList`。
  7. 创建一个空的下一代鸽子列表`nextPigeonList`。
  8. 遍历当前代的鸽子列表，对于每只鸽子，获取其ID作为祖先ID。
  9. 构建查询条件，要求`gid`等于指定的鸽舍ID，并且祖先ID等于当前鸽子的ID（即查找其子代鸽子）。
  10. 使用查询条件查询鸽子列表，并将结果添加到下一代鸽子列表`nextPigeonList`中。
  11. 将下一代鸽子列表`nextPigeonList`添加到`pigeonOffspringList`中。
  12. 代数`generation`递增。
  13. 循环回到步骤5，继续迭代下一代鸽子，直到达到限制数`limit`或者当前代的鸽子列表为空。
  14. 迭代结束后，`pigeonOffspringList`中存储了每一代的子孙鸽子列表。

  ```java
  /**
   * 根据id查询子代列表
   */
  @GetMapping("offspring/{id}")
  public Result getOffspringList(@PathVariable Long id, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      List<ArrayList<Pigeon>> pigeonOffspringList = new ArrayList<>();
  
      //装填祖先
      {
          Pigeon pigeon = new Pigeon();
          pigeon.setId(id);
          ArrayList<Pigeon> pigeonList = new ArrayList<>();
          pigeonList.add(pigeon);
          pigeonOffspringList.add(pigeonList);
      }
      int generation = 0;
  
      //迭代
      while (generation < limit && pigeonOffspringList.get(generation).size() > 0) {
          ArrayList<Pigeon> pigeonList = pigeonOffspringList.get(generation);
          ArrayList<Pigeon> nextPigeonList = new ArrayList<>();
  
          pigeonList.forEach(pigeon -> {
              Long tid = pigeon.getId();
              //条件 gid = gid and (mid = id or fid = id)
              LambdaQueryWrapper<Pigeon> wrapper = new LambdaQueryWrapper<Pigeon>()
                      .eq(Pigeon::getGid, gid)
                      .and(w -> w.eq(Pigeon::getMid, tid).or().eq(Pigeon::getFid, tid));
              List<Pigeon> list = pigeonService.list(wrapper);
              nextPigeonList.addAll(list);
          });
  
          pigeonOffspringList.add(nextPigeonList);
          generation++;
      }
  
      return new Result(pigeonOffspringList, SuccessCode.Success.code, "查询成功");
  }
  ```

  

- 搜索同辈

  设计思路：

  1. 从请求属性中获取`info`参数，该参数是一个Map对象，包含了一个名为`gid`的Long类型值，表示鸽舍的ID。
  2. 构建查询条件，要求`gid`等于指定的鸽舍ID，并且`id`等于指定的鸽子ID。
  3. 设置要查询的字段为`fid`和`mid`。
  4. 使用查询条件查询鸽子信息，获取满足条件的单个鸽子对象。
  5. 如果没有找到满足条件的鸽子对象，则返回查询成功的结果。
  6. 从查询结果中获取父代ID`fid`和母代ID`mid`。
  7. 创建一个空的`pigeonMap`，用于存储不同类型的亲缘关系鸽子列表。
  8. 如果存在父代ID`fid`，则进行同父异母关系的查询。构建查询条件要求`fid`等于指定的父代ID，并且`id`不等于指定的鸽子ID，`gid`等于指定的鸽舍ID，同时如果存在母代ID`mid`，要求`mid`不等于指定的母代ID。执行查询，并将结果存储到`pigeonMap`的"fatherHalf"键下。
  9. 如果存在母代ID`mid`，则进行同母异父关系的查询。构建查询条件要求`mid`等于指定的母代ID，并且`id`不等于指定的鸽子ID，`gid`等于指定的鸽舍ID，同时如果存在父代ID`fid`，要求`fid`不等于指定的父代ID。执行查询，并将结果存储到`pigeonMap`的"motherHalf"键下。
  10. 如果同时存在父代ID`fid`和母代ID`mid`，则进行同父同母关系的查询。构建查询条件要求`fid`等于指定的父代ID，`mid`等于指定的母代ID，并且`id`不等于指定的鸽子ID，`gid`等于指定的鸽舍ID。执行查询，并将结果存储到`pigeonMap`的"full"键下。
  11. 最终，`pigeonMap`中存储了不同类型的亲缘关系鸽子列表。

  ```java
  /**
   * 根据id搜索同辈
   */
  @GetMapping("peer/{id}")
  public Result getPeerList(@PathVariable Long id, HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //检索自己获取fid和mid
      //条件 id and gid
      QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid).eq("id", id);
  
      //字段
      wrapper.select("fid", "mid");
  
      Pigeon pigeon = pigeonService.getOne(wrapper);
  
      //没有找到父代
      if (pigeon == null) {
          return new Result(SuccessCode.Success.code, "查询成功");
      }
  
  
      Long fid = pigeon.getFid();
      Long mid = pigeon.getMid();
  
      Map<String, List<Pigeon>> pigeonMap = new HashMap<>();
  
      //同父异母
      if (fid != null) {
          //条件 fid = fid and id != id and gid = gid and mid != mid
          QueryWrapper<Pigeon> fatherHalfWrapper = new QueryWrapper<>();
          fatherHalfWrapper.eq("fid", fid)
                  .eq("gid", gid)
                  .ne(mid != null, "mid", mid)
                  .ne("id", id);
          List<Pigeon> list = pigeonService.list(fatherHalfWrapper);
          pigeonMap.put("fatherHalf", list);
      }
  
      //同母异父
      if (mid != null) {
          //条件 mid = mid and id != id and gid = gid and fid != fid
          QueryWrapper<Pigeon> motherHalfWrapper = new QueryWrapper<>();
          motherHalfWrapper.eq("mid", mid)
                  .eq("gid", gid)
                  .ne(fid != null, "fid", fid)
                  .ne("id", id);
          List<Pigeon> list = pigeonService.list(motherHalfWrapper);
          pigeonMap.put("motherHalf", list);
      }
  
      //同父同母
      if (fid != null && mid != null) {
          //条件 fid = fid and id != id and gid = gid and mid = mid
          QueryWrapper<Pigeon> fullWrapper = new QueryWrapper<>();
          fullWrapper.eq("fid", fid)
                  .eq("gid", gid)
                  .eq("mid", mid)
                  .ne("id", id);
          List<Pigeon> list = pigeonService.list(fullWrapper);
          pigeonMap.put("full", list);
      }
  
      return new Result(pigeonMap, SuccessCode.Success.code, "查询成功");
  }
  ```

##### 2.2.9.13 首页信息分析服务

列举一些逻辑较为复杂的接口：

- 获取创建时间变化数据

  设计思路：

  1. 从请求属性中获取`info`参数，该参数是一个Map对象，包含了一个名为`gid`的Long类型值，表示鸽舍的ID。
  2. 构建查询条件，要求`gid`等于指定的鸽舍ID。
  3. 设置要查询的字段为`id`。
  4. 使用查询条件查询鸽子信息，获取满足条件的鸽子列表。
  5. 创建一个空的`countMap`，用于存储鸽子数量的哈希计数。
  6. 获取一个月前的日期`recent`，通过当前日期减去指定的天数`createRecent`得到。
  7. 对于每个鸽子对象，根据其ID查询对应的鸽子信息。
  8. 获取鸽子信息中的创建时间`createTime`，判断其是否在最近一个月之内。
  9. 如果鸽子创建时间在最近一个月之内，将其计数加1，并存储到`countMap`中对应日期的计数值上。
  10. 如果鸽子创建时间不在最近一个月之内，不进行计数。

  ```java
  /**
   * 获取创建时间变化数据
   * 根据设定的最近月数获取数据
   */
  @GetMapping("create")
  public Result getCreate(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //检索获取所有id
      QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
      pigeonWrapper.eq("gid", gid);
      pigeonWrapper.select("id");
      List<Pigeon> pigeonList = pigeonService.list(pigeonWrapper);
  
      //哈希计数map
      Map<String, Integer> countMap = new HashMap<>();
      //获取一个月前时间
      LocalDate recent = LocalDate.now().minusDays(createRecent);
      pigeonList.forEach(pigeon -> {
          //根据id获取时间
          Long id = pigeon.getId();
          QueryWrapper<PigeonInfo> wrapper = new QueryWrapper<>();
          wrapper.eq("pid", id)
                  .select("create_time");
          PigeonInfo pigeonInfo = pigeonInfoService.getOne(wrapper);
          //判断时间是否正确
          LocalDate createTime = pigeonInfo.getCreateTime();
          if (createTime.isAfter(recent)) {
              //如果存在
              countMap.computeIfPresent(createTime.toString(), (k, v) -> v + 1);
              //如果不存在
              countMap.computeIfAbsent(createTime.toString(), k -> 1);
          }
      });
  
      return new Result(countMap, SuccessCode.Success.code, "查询成功");
  }
  ```

- 获取删除时间变化数据

  sql语句：

  ```sql
  select DATE(time), count(0)
  from t_oplog
  where gid and content = 2 and time > recent
  group by DATE(time)
  order by time
  ```

  ```java
  /**
   * 获取删除时间变化数据
   * 根据设定的最近月数获取数据
   */
  @GetMapping("delete")
  public Result getDelete(HttpServletRequest request) {
  获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
  获取最近时间
      LocalDateTime recent = LocalDateTime.now().minusDays(deleteRecent);
  
      //条件语句
      //select DATE(time), count(0)
      //from t_oplog
      //where gid and content = 2 and time > recent
      //group by DATE(time)
      //order by time
      QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid)
              .eq("content", LogType.DELETE.getIndex())
              .gt("time", recent)
              .orderByAsc("time")
              .groupBy("DATE(time)")
              .select("DATE(time) time", "count(0) count");
  
      List<Map<String, Object>> countMap = oplogService.listMaps(wrapper);
      return new Result(countMap, SuccessCode.Success.code, "查询成功");
  }
  ```

- 根据最近日期获取操作的数据变化

  sql语句：

  ```sql
  select DATE(time), count(0), content
  from t_oplog
  where gid = 1640546214887645185
  and time > '2023-04-01 20:22:49'
  group by DATE(time), content
  order by time;
  ```

  ```java
  /**
   * 根据最近日期获取操作的数据变化
   */
  @GetMapping("oplog/line")
  public Result getOplogLine(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //获取最近时间
      LocalDateTime recent = LocalDateTime.now().minusDays(oplogRecent);
  
      //select DATE(time), count(0), content
      //from t_oplog
      //where gid = 1640546214887645185
      //and time > '2023-04-01 20:22:49'
      //group by DATE(time), content
      //order by time;
      QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
      wrapper.select("DATE(time) time", "content", "count(0) count")
              .eq("gid", gid)
              .gt("time", recent)
              .groupBy("DATE(time)", "content")
              .orderByAsc("time");
      List<Map<String, Object>> countMap = oplogService.listMaps(wrapper);
  
      return new Result(countMap, SuccessCode.Success.code, "查询成功");
  }
  ```

- 统计登录人员数据

  sql语句：

  ```sql
  select account, count(0), DATE(time)
  from t_login_msg
  where gid = 1640546214887645185
  and time > '2023-05-08 22:32:49'
  group by DATE(time), account
  order by time
  ```

  ```java
  /**
   * 统计登录人员数据
   */
  @GetMapping("login/count")
  public Result getLoginCount(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //获取最近时间
      LocalDateTime recent = LocalDateTime.now().minusDays(oplogRecent);
  
      //select account, count(0), DATE(time)
      //from t_login_msg
      //where gid = 1640546214887645185
      //and time > '2023-05-08 22:32:49'
      //group by DATE(time), account
      //order by time
      QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
      wrapper.select("account", "count(0) count", "DATE(time) time")
              .eq("gid", gid)
              .gt("time", recent)
              .groupBy("DATE(time)", "account")
              .orderByAsc("time");
  
      List<Map<String, Object>> countMap = loginMsgService.listMaps(wrapper);
  
      return new Result(countMap, SuccessCode.Success.code, "查询成功");
  }
  ```

- 获取组员在线人数

  设计思路（redis操作见 **2.2.6 系统工具类 utils - redis白名单工具**）：

  1. 从请求属性中获取`info`参数，该参数是一个Map对象，包含了一个名为`gid`的Long类型值，表示鸽舍的ID。
  2. 构建查询条件，要求`gid`等于指定的鸽舍ID。
  3. 设置查询结果要返回的字段，包括`id`字段，使用`select`方法指定查询字段。
  4. 使用`list`方法执行查询，返回结果为一个List<User>，每个User对象表示一个组内人员。
  5. 创建一个AtomicLong类型的变量`count`，用于计数。
  6. 遍历组内人员列表`userList`，对每个人员执行以下操作：
     - 获取人员的ID。
     - 调用`whitelistUtil.getActive`方法，传入Redis命名空间和ID作为参数，获取该人员的活动数。
     - 将活动数累加到`count`变量中。
  7. 循环结束后，`count`变量中存储了组内所有人员的活动总数。

  ```java
  /**
   * 获取组员在线人数
   */
  @GetMapping("online/group")
  public Result getOnlineGroup(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //根据gid获取组内人员的id
      QueryWrapper<User> wrapper = new QueryWrapper<>();
      wrapper.select("id")
              .eq("gid", gid);
  
      List<User> userList = userService.list(wrapper);
  
      AtomicLong count = new AtomicLong();
  
      userList.forEach(user -> {
          Long id = user.getId();
          long active = whitelistUtil.getActive(RedisNamespace.Whitelist, id + ":");
          count.addAndGet(active);
      });
  
      return new Result(count, SuccessCode.Success.code, "查询成功");
  }
  ```

##### 2.2.9.14 详细数据分析服务

列举不同种类的服务接口：

- 获取用户鸽子数据中的雌雄数量

  sql语句：

  1. 构建查询条件，要求`gid`等于指定的鸽舍ID，并且`sex`字段不为空。
  2. 设置查询结果要返回的字段，包括`count(sex)`和`sex`，使用`select`方法指定查询字段。
  3. 使用`groupBy`方法将结果按照`sex`字段进行分组。

  ```sql
  select count(sex), sex
  from t_pigeon
  where sex is not null
  and gid = 1640546214887645185
  group by sex;
  ```

  ```java
  /**
   * 根据gid获取用户鸽子数据中的雌雄数量
   */
  @GetMapping("sex")
  public Result getSexData(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //select count(sex), sex
      //from t_pigeon
      //where sex is not null
      //and gid = 1640546214887645185
      //group by sex;
      QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
      wrapper.select("sex", "COUNT(sex) count")
              .eq("gid", gid)
              .isNotNull("sex")
              .groupBy("sex");
  
      List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);
  
      return new Result(maps, SuccessCode.Success.code, "查询成功");
  }
  ```

- 获取眼色统计数据

  sql语句：

  1. 构建查询条件，要求`gid`等于指定的鸽舍ID，并且`yan`字段不为空。
  2. 设置查询结果要返回的字段，包括`count(yan)`和`yan`，使用`select`方法指定查询字段。
  3. 使用`groupBy`方法将结果按照`yan`字段进行分组。
  4. 使用`orderByAsc`方法将结果按照`count(yan)`进行升序排序。

  ```sql
  select count(yan), yan
  from t_pigeon
  where yan is not null
  and gid = 1640546214887645185
  group by yan
  order by count(yan);
  ```

  ```java
  /**
   * 根据gid获取眼色统计数据
   * 并排序返回
   */
  @GetMapping("yan")
  public Result getYanData(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //select count(yan), yan
      //from t_pigeon
      //where yan is not null
      //and gid = 1640546214887645185
      //group by yan
      //order by count(yan);
      QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
      wrapper.select("yan", "COUNT(yan) count")
              .eq("gid", gid)
              .isNotNull("yan")
              .groupBy("yan")
              .orderByDesc("COUNT(yan)");
  
      List<Map<String, Object>> maps = pigeonService.listMaps(wrapper);
  
      return new Result(maps, SuccessCode.Success.code, "查询成功");
  }
  ```

- 获取作育者的统计数据

  设计思路：

  1. 根据给定的鸽舍ID（gid），使用查询条件构建器（QueryWrapper）设置查询条件，要求`gid`字段等于指定的鸽舍ID。
  2. 设置查询结果要返回的字段，包括`id`，使用`select`方法指定查询字段。
  3. 执行查询，获取鸽子列表（pigeonList）。
  4. 创建一个空的哈希映射（HashMap）`countMap`，用于存储统计结果，其中键为来源（source），值为鸽子数量。
  5. 遍历鸽子列表，对于每个鸽子，获取其ID（id）。
  6. 使用查询条件构建器（QueryWrapper）设置查询条件，要求`pid`字段等于当前鸽子的ID。
  7. 设置查询结果要返回的字段，包括`source`，使用`select`方法指定查询字段。
  8. 执行查询，获取鸽子信息（pigeonInfo）。
  9. 判断鸽子信息是否为空，如果为空则跳过当前循环。
  10. 检查`countMap`中是否已存在该来源（source）的统计信息，如果不存在则将其添加到`countMap`中，并将鸽子数量设置为1。
  11. 如果`countMap`中已存在该来源的统计信息，则将该来源对应的鸽子数量加1。

  ```java
  /**
   * 根据gid获取作育者的统计数据
   */
  @GetMapping("source")
  public Result getSourceData(HttpServletRequest request) {
      //获取gid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //根据gid获取所有的pid
      QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
      pigeonQueryWrapper.select("id")
              .eq("gid", gid);
  
      List<Pigeon> pigeonList = pigeonService.list(pigeonQueryWrapper);
  
      Map<String, Integer> countMap = new HashMap<>();
      //根据pid获取source
      pigeonList.forEach(pigeon -> {
          Long id = pigeon.getId();
  
          QueryWrapper<PigeonInfo> infoQueryWrapper = new QueryWrapper<>();
          infoQueryWrapper.select("source")
                  .eq("pid", id);
  
          PigeonInfo pigeonInfo = pigeonInfoService.getOne(infoQueryWrapper);
          if (pigeonInfo == null) {
              return;
          }
          //map中没有，赋值为一
          countMap.computeIfAbsent(pigeonInfo.getSource(), k -> 1);
          //map中存在则计数
          countMap.computeIfPresent(pigeonInfo.getSource(), (k, v) -> v + 1);
      });
  
      return new Result(countMap, SuccessCode.Success.code, "获取成功");
  }
  ```

##### 2.2.9.15 信息反馈中心服务

将设定好的信息地址已二维码形式返回前端（二维码创建方法见**2.2.6 系统工具类 utils - 二维码工具**）

```java
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
```

#### 2.2.10 总结

##### 2.2.10.1 圈复杂度分析

- 圈复杂度表（部分）

| Method                                                       | CogC | ev(G) | iv(G) | v(G) |
| ------------------------------------------------------------ | ---- | ----- | ----- | ---- |
| com.AE.sgmis.service.impl.PigeonServiceImpl.savePigeonByFile(List<Map<String,  PigeonWrapperVo>>, Long, String) | 36   | 11    | 7     | 13   |
| com.AE.sgmis.util.IpUtil.getIp(HttpServletRequest)           | 6    | 1     | 10    | 10   |
| com.AE.sgmis.controller.PigeonController.savePigeonByFile(MultipartFile,  HttpServletRequest) | 12   | 3     | 6     | 8    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.saveOrUpdatePigeonById(Pigeon,  PigeonInfo, Long, Long, String) | 13   | 8     | 3     | 8    |
| com.AE.sgmis.controller.PigeonController.updatePigeonByTypeAndIds(List<Long>,  String, String, HttpServletRequest) | 4    | 4     | 4     | 7    |
| com.AE.sgmis.controller.RapidController.rapidAddPigeon(Map<String,  Object>, HttpServletRequest) | 6    | 2     | 5     | 7    |
| com.AE.sgmis.controller.DetailController.getPeerList(Long,  HttpServletRequest) | 5    | 2     | 4     | 6    |
| com.AE.sgmis.controller.OplogController.getConditionPage(PagingConditionVo,  HttpServletRequest) | 5    | 2     | 4     | 6    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.rapidBatchAddPigeon(List<Pigeon>,  List<PigeonInfo>, Long, String, String, String) | 9    | 4     | 4     | 6    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.sharePigeon(List<Long>,  Long, Long, String) | 8    | 6     | 2     | 6    |
| com.AE.sgmis.config.ResourceConfig.addResourceHandlers(ResourceHandlerRegistry) | 10   | 1     | 5     | 5    |
| com.AE.sgmis.controller.PigeonController.removePigeonById(Long,  String, HttpServletRequest) | 3    | 2     | 4     | 5    |
| com.AE.sgmis.controller.UserController.resetPassword(User,  HttpServletRequest) | 4    | 5     | 2     | 5    |
| com.AE.sgmis.service.impl.PigeonGpcxServiceImpl.addPigeonToGpcx(List<Long>,  Long, String, String, Long) | 8    | 4     | 3     | 5    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.deletePigeonByIds(List<Pigeon>,  Long, String) | 4    | 2     | 4     | 5    |
| com.AE.sgmis.util.PigeonFileUtil.getSex(Row)                 | 4    | 3     | 3     | 5    |
| com.AE.sgmis.util.QRCodeUtil.createQrCode(String,  int, int) | 7    | 1     | 4     | 5    |
| com.AE.sgmis.util.WhitelistUtil.getState(RedisNamespace,  String) | 6    | 3     | 2     | 5    |
| com.AE.sgmis.controller.PigeonController.sharePigeon(List<Long>,  Long, HttpServletRequest) | 2    | 2     | 3     | 4    |
| com.AE.sgmis.controller.PigeonController.updatePigeon(Pigeon,  HttpServletRequest) | 3    | 3     | 2     | 4    |
| com.AE.sgmis.controller.XxpzController.update(Xxpz,  HttpServletRequest) | 3    | 3     | 2     | 4    |
| com.AE.sgmis.runner.RedisConnectionRunner.run(ApplicationArguments) | 4    | 3     | 3     | 4    |
| com.AE.sgmis.schedule.PictureSchedule.deleteLogoPictureTask() | 7    | 1     | 4     | 4    |
| com.AE.sgmis.schedule.PictureSchedule.deletePigeonPictureTask() | 7    | 1     | 4     | 4    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.deletePigeonById(Long,  String, Long, String) | 4    | 4     | 2     | 4    |
| com.AE.sgmis.util.PigeonFileUtil.getLx(Row,  Map<String, Set<String>>) | 3    | 3     | 2     | 4    |
| com.AE.sgmis.util.PigeonFileUtil.getState(Row,  Map<String, Set<String>>) | 3    | 3     | 2     | 4    |
| com.AE.sgmis.util.PigeonFileUtil.getYan(Row,  Map<String, Set<String>>) | 3    | 3     | 2     | 4    |
| com.AE.sgmis.util.PigeonFileUtil.getYs(Row,  Map<String, Set<String>>) | 3    | 3     | 2     | 4    |
| com.AE.sgmis.util.PigeonFileUtil.parsePojo(Sheet,  Map<String, Set<String>>) | 6    | 2     | 3     | 4    |
| com.AE.sgmis.util.PigeonFileUtil.verifyRingNumber(String,  Map<String, Set<String>>) | 3    | 4     | 1     | 4    |
| com.AE.sgmis.util.RedisUtil.PING()                           | 3    | 2     | 4     | 4    |
| com.AE.sgmis.controller.DataController.getLoginMsg(Integer,  HttpServletRequest) | 2    | 2     | 1     | 3    |
| com.AE.sgmis.controller.DataController.getOplogData(Integer,  HttpServletRequest) | 2    | 2     | 1     | 3    |
| com.AE.sgmis.controller.DetailController.getOffspringList(Long,  HttpServletRequest) | 2    | 1     | 3     | 3    |
| com.AE.sgmis.controller.GpcxController.updateGpcx(Gpcx,  HttpServletRequest) | 2    | 3     | 2     | 3    |
| com.AE.sgmis.controller.LoginController.freePass(HttpServletRequest) | 2    | 2     | 1     | 3    |
| com.AE.sgmis.controller.LoginController.updatePassword(UpdateUserVo,  HttpServletRequest) | 2    | 3     | 1     | 3    |
| com.AE.sgmis.controller.OplogController.getLogById(Long,  Integer, HttpServletRequest) | 2    | 2     | 1     | 3    |
| com.AE.sgmis.controller.PigeonController.addOrUpdatePigeon(PigeonWrapperVo,  HttpServletRequest) | 2    | 3     | 1     | 3    |
| com.AE.sgmis.controller.PigeonController.listPagePigeon(Map<String,  Long>, HttpServletRequest) | 2    | 2     | 1     | 3    |
| com.AE.sgmis.controller.PigeonController.uploadPigeonPicture(MultipartFile) | 2    | 2     | 2     | 3    |
| com.AE.sgmis.controller.XtspzController.uploadLogo(MultipartFile) | 2    | 2     | 2     | 3    |
| com.AE.sgmis.controller.XxpzController.add(Xxpz,  String, HttpServletRequest) | 2    | 3     | 2     | 3    |
| com.AE.sgmis.interceptor.AdminInterceptor.preHandle(HttpServletRequest,  HttpServletResponse, Object) | 2    | 3     | 1     | 3    |
| com.AE.sgmis.service.impl.LoginServiceImpl.loginVerify(User) | 2    | 3     | 1     | 3    |
| com.AE.sgmis.service.impl.OplogServiceImpl.oplog(String,  String, Long, LogType) | 2    | 3     | 2     | 3    |
| com.AE.sgmis.util.FileUtil.initDirectory(Path)               | 2    | 1     | 2     | 3    |
| com.AE.sgmis.util.FileUtil.responseFileByRelativePath(String,  HttpServletResponse) | 2    | 1     | 3     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getBloodline(Row)           | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getDetail(Row)              | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getFatherName(Row)          | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getFatherRingNumber(Row,  Map<String, Set<String>>) | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getMotherName(Row)          | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getMotherRingNumber(Row,  Map<String, Set<String>>) | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getName(Row)                | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getRingNumber(Row,  Map<String, Set<String>>) | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.PigeonFileUtil.getSubRingNumber(Row)       | 2    | 2     | 2     | 3    |
| com.AE.sgmis.util.WhitelistUtil.limitToken(Long)             | 2    | 2     | 2     | 3    |
| com.AE.sgmis.controller.DataController.getCreate(HttpServletRequest) | 2    | 1     | 2     | 2    |
| com.AE.sgmis.controller.GpcxController.addGpcx(Gpcx,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.GpcxController.check(Long,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.GsxxController.getGsxx(HttpServletRequest) | 1    | 1     | 1     | 2    |
| com.AE.sgmis.controller.GsxxController.postGsxx(HttpServletRequest,  Gsxx) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.controller.LoginController.login(UserVo,  HttpServletRequest) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.controller.PigeonController.getBasePigeon(Long,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.PigeonController.getPigeon(Long,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.PigeonController.getPigeonParent(List<Long>,  String, HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.PigeonController.relatePigeon(Map<String,  Object>, HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.StatisticController.getSourceData(HttpServletRequest) | 2    | 2     | 1     | 2    |
| com.AE.sgmis.controller.UserController.addUser(User,  HttpServletRequest) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.controller.UserController.deleteUser(HttpServletRequest,  Long) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.XtspzController.getInfo(HttpServletRequest) | 1    | 1     | 1     | 2    |
| com.AE.sgmis.controller.XtspzController.postInfo(Xtspz,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.XxpzController.batchDelete(List<Long>,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.XxpzController.deleteOne(Long,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.controller.XxpzController.getAllByType(String,  HttpServletRequest) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.interceptor.BlackInterceptor.preHandle(HttpServletRequest,  HttpServletResponse, Object) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.interceptor.LoginInterceptor.preHandle(HttpServletRequest,  HttpServletResponse, Object) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.result.RedisNamespace.RedisNamespace(RedisNamespace,  String) | 1    | 1     | 1     | 2    |
| com.AE.sgmis.schedule.OplogSchedule.replenishRingNumber()    | 2    | 2     | 1     | 2    |
| com.AE.sgmis.service.impl.GpcxServiceImpl.gpcxRemoveById(Serializable) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.LoginServiceImpl.updateEncrypt(User) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.OplogServiceImpl.batchAddLog(List<Oplog>,  Long, String) | 2    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.OplogServiceImpl.oplog(String,  Long, Long, LogType) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.OplogServiceImpl.oplog(String,  Long, Long, String, LogType) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.OplogServiceImpl.oplog(String,  String, Long, Long, LogType) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.OplogServiceImpl.oplog(String,  String, Long, Long, String, LogType) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.relatePigeon(Long,  String, String, Long, Long, String) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.updatePigeon(Pigeon,  String) | 1    | 2     | 2     | 2    |
| com.AE.sgmis.service.impl.PigeonServiceImpl.updatePigeonByTypeAndIds(List<Long>,  String, String, Long, String) | 2    | 2     | 1     | 2    |
| com.AE.sgmis.util.FileUtil.checkFileType(MultipartFile,  Set<String>) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.util.FileUtil.checkFileType(MultipartFile,  String...) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.util.FileUtil.deleteFile(String,  Path)         | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.FileUtil.getFileListByDirectory(Path)      | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.FileUtil.getFileType(MultipartFile)        | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.FileUtil.storeFile(MultipartFile,  Path)   | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.PigeonFileUtil.getPigeonByXls(MultipartFile,  Map<String, Set<String>>) | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.PigeonFileUtil.getPigeonByXlsx(MultipartFile,  Map<String, Set<String>>) | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.RedisUtil.expire(RedisNamespace,  String, long) | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.RedisUtil.get(RedisNamespace,  String)     | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.RedisUtil.set(RedisNamespace,  String, Object, long) | 2    | 1     | 2     | 2    |
| com.AE.sgmis.util.WhitelistUtil.getActive(RedisNamespace,  String) | 2    | 1     | 2     | 2    |
| com.AE.sgmis.util.WhitelistUtil.updateExpires(Long,  String) | 1    | 1     | 2     | 2    |
| com.AE.sgmis.util.WhitelistUtil.verifyToken(Long,  String, String) | 1    | 2     | 1     | 2    |
| com.AE.sgmis.SgmisApplication.main(String[])                 | 0    | 1     | 1     | 1    |
| com.AE.sgmis.config.InterceptorConfig.addInterceptors(InterceptorRegistry) | 0    | 1     | 1     | 1    |
| com.AE.sgmis.config.LocalDateTimeSerializerConfig.jackson2ObjectMapperBuilderCustomizer() | 0    | 1     | 1     | 1    |
| com.AE.sgmis.config.LocalDateTimeSerializerConfig.localDateTimeDeserializer() | 0    | 1     | 1     | 1    |
| com.AE.sgmis.config.LocalDateTimeSerializerConfig.localDateTimeSerializer() | 0    | 1     | 1     | 1    |
| com.AE.sgmis.config.MybatisPlusConfig.mybatisPlusInterceptor() | 0    | 1     | 1     | 1    |
| com.AE.sgmis.config.RedisConfig.redisTemplate(RedisConnectionFactory) | 0    | 1     | 1     | 1    |
| com.AE.sgmis.controller.AddressController.getAreas(Long)     | 0    | 1     | 1     | 1    |
| com.AE.sgmis.controller.AddressController.getProvincial()    | 0    | 1     | 1     | 1    |
| com.AE.sgmis.controller.AddressController.getUrban(Long)     | 0    | 1     | 1     | 1    |

- 柱状分析图

![image-20230608201923566](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608201923566.png?raw=true)

- 总结

大部分方法的圈复杂度较低，这使得这些方法相对简单且易读。它们可能是一些基本的工具方法，例如文件处理或IP获取等，这些方法的功能相对独立和简单，不需要太多的条件分支或循环。这是一个积极的方面，因为低圈复杂度的方法更容易理解、测试和维护。

然而，也有少数方法的圈复杂度较高，这意味着它们的控制流程相对复杂，可能包含多个条件分支或循环语句。这些方法可能是业务逻辑较为复杂的方法，需要更多的测试用例来覆盖不同的分支情况。尽管如此，开发者仍然努力简化这些方法的代码，并添加了详尽的注释来解释其逻辑。这是一个积极的努力，因为注释可以帮助其他人理解代码的复杂性，从而提高可维护性。

然而，还需要注意的是，尽管已经进行了代码简化和注释的努力，但仍然需要精进代码的圈复杂度。高圈复杂度的方法可能导致代码难以理解、测试和维护。因此，开发者应该考虑进一步优化高圈复杂度的方法，以降低复杂度并增强代码的可读性和可维护性。这可以通过使用条件重构、循环重构、提取方法等技术来实现。

综上所述，大部分方法具有较低的圈复杂度，简单易读。对于圈复杂度较高的方法，已经采取了简化代码和添加注释的措施。然而，仍需要继续努力优化高圈复杂度的方法，并精进代码的圈复杂度。这样可以提高代码的可读性、可测试性和可维护性，使整个代码库更加健壮和可持续。

##### 2.2.10.2 设计重点

- 引入redis，利用redis缓存设计白名单、黑名单功能
  - 退出登录、更新密码等需要强制下线的业务可以剔除线上token
  - 禁用ip地址
- 更新token在redis的过期时间防止活跃用户过期
- 多资源路径配置阶段，设计解析环境配置文件功能
- 利用定时任务设计定时清理过期资源、统计在线人数以减轻服务器压力
- 服务器密码安全措施，密码采用Argon2加盐加密，每次登录对盐值进行更新
- 动态字段会发生sql注入，设计校验防止sql注入，并引入禁用ip机制

### 2.3 前端设计

#### 2.3.1 登录界面

![image-20230608202850022](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608202850022.png?raw=true)

#### 2.3.2 首页

- 轮播图

  ![image-20230608203100298](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608203100298.png?raw=true)

- 数据分析图表

  ![image-20230608203148795](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608203148795.png?raw=true)

#### 2.3.3 鸽子库

- 主要信息界面

![image-20230608203434860](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608203434860.png?raw=true)

![image-20230608203246770](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608203246770.png?raw=true)

- 弹窗

  ![image-20230608210747662](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608210747662.png?raw=true)

  ![image-20230608210810077](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608210810077.png?raw=true)

  

  ![image-20230608210832704](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608210832704.png?raw=true)

  ![image-20230608210853911](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608210853911.png?raw=true)

#### 2.3.4 编辑血统

![image-20230608211007461](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211007461.png?raw=true)

![image-20230608211121290](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211121290.png?raw=true)

![image-20230608211029615](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211029615.png?raw=true)

![image-20230608211046662](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211046662.png?raw=true)

#### 2.3.5 快速入库

![image-20230608211248677](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211248677.png?raw=true)

#### 2.3.6 高级批量操作

![image-20230608211351932](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211351932.png?raw=true)

#### 2.3.7 操作日志

![image-20230608211534296](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211534296.png?raw=true)

#### 2.3.8 统计中心

![image-20230608211604815](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211604815.png?raw=true)

#### 2.3.9 鸽棚巢箱

![image-20230608211711405](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211711405.png?raw=true)

![image-20230608211648668](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211648668.png?raw=true)

![image-20230608211733245](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211733245.png?raw=true)

#### 2.3.10 选项设置

![image-20230608211849462](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211849462.png?raw=true)

![image-20230608211911349](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211911349.png?raw=true)

#### 2.3.11 鸽舍信息

![image-20230608211935810](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608211935810.png?raw=true)

#### 2.3.12 血统书配置

![image-20230608212003602](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212003602.png?raw=true)

#### 2.3.13 用户设置

![image-20230608212057499](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212057499.png?raw=true)

#### 2.3.14 管理员设置

![image-20230608212133891](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212133891.png?raw=true)

![image-20230608212146924](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212146924.png?raw=true)

![image-20230608212203883](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212203883.png?raw=true)

#### 2.3.15 登录信息

![image-20230608212321792](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212321792.png?raw=true)

#### 2.3.16 反馈中心

![image-20230608212357639](http://git.hhzzss.cn/ShiAE/sgmis/-/raw/main/README/image-20230608212357639.png?raw=true)

#### 2.3.17 全局配置

##### 2.3.17.1 vite.config.js

```js
export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            "@": "/src"
        }
    },
    server: {
        host: "0.0.0.0",
        proxy: {
            "/api": {
                target: "http://localhost:8080",
                changeOrigin: true,
                headers: {
                    'X-Forwarded-For': '1.1.1.1',
                },
            },
            "/pigeon": {
                target: "http://localhost:8080",
                changeOrigin: true
            },
            "/logo": {
                target: "http://localhost:8080",
                changeOrigin: true
            }
        }
    }
})
```

##### 2.3.17.2 axios封装

```js
import axios from "axios"
import {addLoading, clearLoading} from "@/assets/js/loading.js"
import router from "@/router"
import store from "@/store"
import {Notification} from '@arco-design/web-vue'
import JSONbigint from "json-bigint"

//定义键盘事件
function preventKeyDown(event) {
    event.preventDefault();
    event.stopPropagation();
}

const axiosx = axios.create({
    baseURL: "/api",
    timeout: 30000,
    transformResponse: [function (data) {
        // 使用json-bigint解析Long型响应数据
        try {
            return JSONbigint.parse(data)
        } catch (error) {
            return data
        }
    }]
})

//请求拦截
axiosx.interceptors.request.use(config => {
    //禁止键盘事件
    document.addEventListener('keydown', preventKeyDown, false);
    //添加加载图标
    addLoading(config.message)
    //设置为正在发送请求
    store.commit("setPending", true)
    return {
        ...config,
        //添加请求头
        headers: {
            ...config.headers,

            Authorization: store.getters.doneToken
        }
    }
}, error => {
    //解除键盘事件
    document.removeEventListener('keydown', preventKeyDown, false);
    store.commit("setPending", false)
    return Promise.reject(error);
})

//响应拦截
axiosx.interceptors.response.use(response => {
    clearLoading()
    //解除键盘事件
    document.removeEventListener('keydown', preventKeyDown, false);
    //恶意sql注入
    if (response.data.code === 411) {
        Notification.error(response.data.message)
        store.commit("setToken", "")
        router.push({name: "403"})
    }
    //访问权限异常
    if (response.data.code === 403) {
        Notification.error(response.data.message)
        router.push({name: "403"})
    }
    //更新token防止活跃用户过期（已弃用）
    // if (response.headers.authorization) {
    //     store.commit("setToken", response.headers.authorization)
    // }
    store.commit("setPending", false)
    return response;
}, error => {
    clearLoading()
    //解除键盘事件
    document.removeEventListener('keydown', preventKeyDown, false);
    // router.push({ name: "500" })
    Notification.error(`${error.message} ${error.code}`)
    store.commit("setPending", false)
    return Promise.reject(error)
});

export default axiosx

```

##### 2.3.17.3 router路由

```js
import { createRouter, createWebHistory } from "vue-router"
import axiosx from "@/assets/js/axiosx.js"
import {Notification} from "@arco-design/web-vue";

const router = createRouter({
    history: createWebHistory('/'),
    routes: [
        {
            path: "/login",
            name: "login",
            component: () => import('@/views/login/login.vue'),
            meta: {
                title: "登录",
                free: true
            }
        },
        {
            path: "/main",
            component: () => import('@/components/layout.vue'),
            children: [
                {
                    path: "home",
                    name: "home",
                    component: () => import("@/views/home/home.vue"),
                    meta: {
                        title: "首页"
                    }
                },
                {
                    path: "loginMsg",
                    name: "loginMsg",
                    component: () => import("@/views/loginMsg/loginMsg.vue"),
                    meta: {
                        title: "登录信息"
                    }
                },
                {
                    path: "feedback",
                    name: "feedback",
                    component: () => import("@/views/feedback/feedback.vue"),
                    meta: {
                        title: "反馈中心"
                    }
                },
                {
                    path: "statistic",
                    name: "statistic",
                    component: () => import("@/views/statistic/statistic.vue"),
                    meta: {
                        title: "统计中心"
                    }
                },
                {
                    path: "gpcx",
                    name: "gpcx",
                    component: () => import("@/views/gpcx/gpcx.vue"),
                    meta: {
                        title: "鸽棚巢箱"
                    }
                },
                {
                    path: "system/gsxx",
                    name: "gsxx",
                    component: () => import("@/views/gsxx/gsxx.vue"),
                    meta: {
                        title: "鸽舍信息"
                    }
                },
                {
                    path: "gzk/:name?",
                    name: "gzk",
                    component: () => import("@/views/gzk/gzk.vue"),
                    meta: {
                        title: "鸽子库"
                    }
                },
                {
                    path: "pigeon/editPigeon/:id(\\d+)?",
                    name: "editPigeon",
                    component: () => import("@/views/editPigeon/editPigeon.vue"),
                    meta: {
                        title: "编辑血统"
                    }
                },
                {
                    path: "pigeon/rapid",
                    name: "rapid",
                    component: () => import("@/views/rapid/rapid.vue"),
                    meta: {
                        title: "快速入库"
                    }
                },
                {
                    path: "pigeon/batch",
                    name: "batch",
                    component: () => import("@/views/batch/batch.vue"),
                    meta: {
                        title: "高级批量操作"
                    }
                },
                {
                    path: "pigeon/log",
                    name: "log",
                    component: () => import("@/views/log/log.vue"),
                    meta: {
                        title: "操作日志"
                    }
                },
                {
                    path: "help",
                    name: "help",
                    component: () => import("@/views/help/help.vue"),
                    meta: {
                        title: "帮助中心"
                    }
                },
                {
                    path: "system/options",
                    name: "options",
                    component: () => import("@/views/options/options.vue"),
                    meta: {
                        title: "选项设置"
                    }
                },
                {
                    path: "system/xtspz",
                    name: "xtspz",
                    component: () => import("@/views/xtspz/xtspz.vue"),
                    meta: {
                        title: "血统书配置"
                    }
                },
                {
                    path: "system/admin",
                    name: "admin",
                    component: () => import("@/views/admin/admin.vue"),
                    meta: {
                        title: "管理员设置",
                        requiresAdminAuth: true
                    }
                },
                {
                    path: "system/user",
                    name: "user",
                    component: () => import("@/views/user/user.vue"),
                    meta: {
                        title: "用户设置"
                    }
                },
                {
                    path: "detail/:id(\\d+)?",
                    name: "detail",
                    component: () => import("@/views/detail/detail.vue"),
                    meta: {
                        title: "详情"
                    }
                }
            ],
            meta: {
                requiresAuth: true
            },
            redirect: { name: "home" }
        },
        {
            path: "/error",
            component: () => import('@/views/error/error.vue'),
            children: [
                {
                    path: "500",
                    name: "500",
                    component: () => import('@/views/error/components/500.vue'),
                    meta: {
                        title: "500"
                    }
                },
                {
                    path: "404/:undefined(.*)",
                    name: "404",
                    component: () => import("@/views/error/components/404.vue"),
                    meta: {
                        title: "404"
                    }
                },
                {
                    path: "403",
                    name: "403",
                    component: () => import("@/views/error/components/403.vue"),
                    meta: {
                        title: "403"
                    }
                }
            ]
        },
        {
            path: "/",
            redirect: { name: "login" }
        },
        {
            path: "/:undefined(.*)",
            redirect: { name: "404" }
        }
    ]
})

router.beforeEach(async (to) => {
    //免密登录
    if (to.meta.free) {
        await axiosx({
            method: "GET",
            url: "login/free",
            message: "验证信息"
        }).then(res => {
            if (res.data.code === 200) {
                router.push({ name: "home" })
                Notification.success(res.data.message)
            }
        })
    }
    //验证访问
    if (to.meta.requiresAuth) {
        await axiosx({
            method: "GET",
            url: "login",
            message: "登录验证"
        })
    }
    //验证管理员访问
    if(to.meta.requiresAdminAuth) {
        await axiosx({
            method: "GET",
            url: "login/admin",
            message: "验证管理员信息"
        })
    }
    //动态标题
    if (to.meta.title) {
        document.title = `赛鸽云库 -- ${to.meta.title}`
    }
})

export default router
```

##### 2.3.17.4 store vuex配置

```js
import {createStore} from 'vuex'
import createPersistedState from "vuex-persistedstate";

const store = createStore({
    plugins: [createPersistedState({
        reducer: state => {
            return {
                token: state.token,
                account: state.account,
                admin: state.admin
            }
        }
    })],
    state() {
        return {
            spin: {
                loading: false,
                tip: ""
            },
            token: "",
            account: "root",
            admin: false,
            isPending: false,
            logoName: "",
            pigeonResourcePath: `/pigeon`,
            logoResourcePath: `/logo`,
            recent: 30
        }
    },
    mutations: {
        setLoading(state, payload) {
            state.spin = payload
        },
        setToken(state, payload) {
            state.token = payload
        },
        setAccount(state, payload) {
            state.account = payload
        },
        setAdmin(state, payload) {
            state.admin = payload
        },
        setPending(state, payload) {
            state.isPending = payload
        },
        setLogoName(state, payload) {
            state.logoName = payload
        }
    },
    getters: {
        doneToken(state) {
            return state.token
        }
    }
})

export default store
```

##### 2.3.17.5 debounce.js防抖

```js
export function toDebounceFunction(func, delay, ...args) {
    //默认300毫秒
    delay = delay || 300

    let timer;
    return function () {
        clearTimeout(timer);
        timer = setTimeout(() => {
            func.apply(this, args);
        }, delay);
    }
}
```

## 3 部署记录

### 3.1 mysql

1. 下载 `mysql-8.0.33-linux-glibc2.28-x86_64.tar.gz` 文件

3. 解压 `mysql-8.0.33-linux-glibc2.28-x86_64.tar.gz` 文件
   ```shell
   tar xzf mysql-8.0.33-linux-glibc2.28-x86_64.tar.gz
   ```

5. 进入 MySQL 安装目录：
   ```shell
   cd /usr/local/mysql
   ```

6. 创建一个名为 `mysql` 的系统用户和用户组，以便 MySQL 服务可以以非特权用户身份运行：
   ```shell
   groupadd mysql
   useradd -r -g mysql -s /bin/false mysql
   ```

7. 为 MySQL 数据目录创建相应的目录，并设置正确的权限：
   ```shell
   mkdir data
   chown -R mysql:mysql data
   ```

8. 初始化 MySQL 数据目录：
   ```shell
   sudo bin/mysqld --initialize-insecure --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
   ```

9. 配置 MySQL 服务器。将 MySQL 配置文件 `my.cnf` 复制到正确的位置：
   ```shell
   cp support-files/my-default.cnf /etc/my.cnf
   ```

   然后编辑 `/etc/my.cnf` 文件，对 MySQL 进行必要的配置。

10. 启动 MySQL 服务器：
    ```shell
    bin/mysqld_safe --user=mysql &
    ```

11. 运行以下命令将 MySQL 客户端可执行文件添加到系统的 PATH 中：
    ```shell
    sudo ln -s /usr/local/mysql/bin/mysql /usr/local/bin/mysql
    ```

12. 验证 MySQL 是否成功安装。执行以下命令：
    ```shell
    mysql --version
    ```


### 3.2 jdk

1. 下载 `jdk-20_linux-x64_bin.tar.gz` 文件

2. 解压 `jdk-20_linux-x64_bin.tar.gz` 文件

   ```
   tar xzf jdk-20_linux-x64_bin.tar.gz
   ```

3. 配置环境变量，编辑 `/etc/profile` 文件

   ```
   export JAVA_HOME=/usr/app/jdk-20.0.1
   export PATH=$JAVA_HOME/bin:$PATH
   ```

4. 执行以下命令使环境变量生效

   ```
   source /etc/profile
   ```

5. 验证 JDK 安装是否成功

   ```
   java -version
   ```

### 3.3 redis

1. 下载`redis-7.0.11.tar.gz`文件

2. 解压`redis-7.0.11.tar.gz`

   ```
   tar xzf redis-7.0.11.tar.gz
   ```

3. 进入解压后的 Redis 目录

   ```
   cd redis-7.0.11
   ```

4. 编译和安装 Redis

   ```
   make
   ```

5. 编译完成后，执行以下命令来安装 Redis

   ```
   make install
   ```

6. 安装完成后，您可以在 Redis 的源代码目录中找到编译生成的可执行文件和配置文件。

7. 运行 Redis 服务器。在终端中执行以下命令启动 Redis 服务器

   ```
   redis-server
   ```

8. 检查 Redis 服务器的运行状态

   ```
   redis-cli ping
   ```

### 3.4 nginx

1. 下载 `nginx-1.24.0.tar.gz` 文件

2. 解压 `nginx-1.24.0.tar.gz` 文件

   ```sh
   tar xzf nginx-1.24.0.tar.gz
   ```

3. 进入解压后的 Nginx 目录

   ```
   cd nginx-1.24.0
   ```

4. 配置编译选项并编译 Nginx

   ```
   ./configure
   make
   ```

5. 编译完成后，执行以下命令安装

   ```
   make install
   ```

6. 配置 nginx.config

   ```
   worker_processes  1;
   
   events {
       worker_connections  1024;
   }
   
   
   http {
       include       mime.types;
       default_type  application/octet-stream;
   
       sendfile        on;
   
       keepalive_timeout  65;
   
       server {
           listen       80;
           server_name  localhost;
   
           root   /usr/main/static;
           index  index.html;
   
           location / {
               try_files $uri $uri/ /index.html;
           }
   
           location /api/ {
               proxy_pass  http://localhost:8080/api/;
   
               proxy_set_header        X-Real-IP       $remote_addr;
               proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
           }
   
           location /pigeon/ {
               proxy_pass  http://localhost:8080/pigeon/;
           }
   
           location /logo/ {
               proxy_pass  http://localhost:8080/logo/;
           }
       }
   
   }
   ```

7. 进入nginx安装目录启动

   ```
   ./sbin/nginx -c ./conf/nginx.conf
   ```


# 4 部署记录2.0

- sgmis-jdk

  - ```dockerfile
    FROM oraclelinux:9-slim
    
    LABEL auth=A.E.
    
    RUN set -eux; \
    	microdnf install \
    		gzip \
    		tar \
    		\
    		binutils \
    		freetype fontconfig \
    	; \
    	microdnf clean all
    
    ADD jdk-21_linux-x64_bin.tar.gz /usr/local/java/
    
    ENV JAVA_HOME=/usr/local/java/jdk-21.0.2
    ENV JRE_HOME=$JAVA_HOME/jre
    ENV PATH=$JAVA_HOME/bin:$PATH
    ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    ENV LANG C.UTF-8
    ```

- sgmis-web

  - ```dockerfile
    FROM nginx:1.24.0
    
    ENV LANG C.UTF-8
    
    COPY dist /dist
    
    CMD ["nginx", "-g", "daemon off;"]
    ```

- sgmis-app

  - ```dockerfile
    FROM jdk:21.0.2
    
    LABEL auth=A.E.
    
    ENV LANG C.UTF-8
    
    COPY app /app
    
    WORKDIR app
    
    ENTRYPOINT ["java", "-jar", "sgmis-1.0.jar"]
    ```

- compose.yml

  - ```yml
    name: sgmis
    
    services:
      sgmis-app:
        build: sgmis-app
        image: sgmis:2.0
        container_name: sgmis-app
        restart: always
        ports:
          - 8080:8080
        volumes:
          - /usr/serverApp/sgmis/sgmis-app/app:/app
        depends_on:
          - sgmis-mysql
          - sgmis-redis
    
      sgmis-mysql:
        image: mysql:8.3.0
        container_name: sgmis-mysql
        restart: always
        environment:
          MYSQL_ROOT_PASSWORD: guatdev
        ports:
          - 3306:3306
        volumes:
          - /usr/serverApp/sgmis/sgmis-mysql/log:/var/log/mysql
          - /usr/serverApp/sgmis/sgmis-mysql/conf:/etc/mysql/conf.d
          - /usr/serverApp/sgmis/sgmis-mysql/data:/var/lib/mysql
    
      sgmis-redis:
        image: redis:7.0
        container_name: sgmis-redis
        restart: always
        ports:
          - 6379:6379
        volumes:
          - /usr/serverApp/sgmis/sgmis-redis/conf:/etc/conf
          - /usr/serverApp/sgmis/sgmis-redis/data:/data
        command: redis-server /etc/conf/redis.conf
    
      sgmis-web:
        image: sgmis-web:2.0
        build: sgmis-web
        restart: always
        container_name: sgmis-web
        ports:
          - 80:80
        volumes:
          - /usr/serverApp/sgmis/sgmis-web/conf:/etc/nginx
          - /usr/serverApp/sgmis/sgmis-web/dist:/dist
    ```

    
