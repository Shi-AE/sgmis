# sgmis �������ݹ���ϵͳ

## 1 ��Ŀ����

### 1.1 ��Ŀ����

���һ���๦��ϵͳ�������Ŷ�Э������������ݡ���ϵͳ���ṩ���¹��ܣ�

1. ��������¼�롢�༭��չʾ���û�����¼��ͱ༭���ӵ�������ݣ��������ӵĻ�����Ϣ��Ѫͳ��Ϣ�ȡ�ϵͳ���ṩ����չʾ�������ݣ��Ա��Ŷӳ�Ա�鿴�͹���
2. ��������չʾ��ϵͳ���ṩ����չʾ��ʽ��������Ѫͳ����ʽչʾ���ӵļ��׹�ϵ���Լ�չʾ���ӵ�ƽ����Ϣ���Ӵ���Ϣ�ȡ�
3. Ѫͳ����ʽ¼��Ϳ�����⣺�û�����ʹ��Ѫͳ����ʽ¼����ӵļ�����Ϣ����������ٴ������ݿ��С����⣬ϵͳ��֧��ͨ���ļ�����ķ�ʽ����¼��������ݡ�
4. ������д�͸߼�����������ϵͳ�ṩ������д���ܣ������û�ͬʱ�༭������ӵ���Ϣ�����⣬���ṩ�߼������������ܣ����������޸��ض����ԡ�����ɾ���ȡ�
5. ���Ŷӹ������Ѫͳ��ϵͳ֧�ֶ��Ŷӹ�����ӵ�Ѫͳ��Ϣ��
6. ���ﳲ�������ӣ�ϵͳ�ṩ����ͳ�������ܣ��û����Լ�¼�͹�����ӵ���Ϣ����Ϣ�������Ŷӳ�Ա�Ը��ӵĶ�λ�͹���
7. ��¼������־��ϵͳ����¼�û��Ĳ�����־����������¼�롢�༭��ɾ���Ȳ������Ա��Ŷӳ�Ա׷�ݺ���������ʷ��
8. ��Ϣѡ���ֵ䣺ϵͳ�ṩ��Ϣѡ���ֵ䣬�����������ԡ�Ѫͳ���ʽ��ѡ���ȷ�����ݵ�һ���Ժ�׼ȷ�ԡ�
9. ������Ϣ�༭���û����Ա༭����������Ϣ��������������ơ���ַ�ȣ��Ա���õع�����ӵ���Ϣ������
10. Ѫͳ��չʾ��Ϣ�༭��ϵͳ�ṩѪͳ��չʾ��Ϣ�ı༭���ܣ��û������Զ���չʾѪͳ��ʱ����ʾ���ֶκ͸�ʽ��
11. �û���Ϣ����ϵͳ�ṩ�û���Ϣ�����ܣ������û��˻���ȫ���û�ע����
12. �Ŷ���Ϣ��������Ա���Թ����Ŷӳ�Ա��ӡ�ɾ����Ȩ�޹����˽��Ŷӳ�Ա���������
13. ��¼��Ϣ��¼��ϵͳ����¼�û��ĵ�¼��Ϣ��������¼ʱ�䡢IP��ַ�ȣ���ȷ���˻���ȫ��׷�ٵ�¼��ʷ��
14. ��Ϣͳ��չʾ��ϵͳ���ṩ�������ݵ�ͳ��չʾ��������ܣ������û��˽��Ŷӵ����������

### 1.2 ϵͳ����

#### 1.2.1 ����ʱ����

��ϵͳ���� B/S �ܹ����û���ͨ���ݿͻ��������ͨ�� URL ���������˽�����������

![image-20230607152247388](README\image-20230607152247388.png)

����˻������ͻ��˻���Ҫ�����£� 

1. �����ϵͳ������ CentOS 7.9 x86 �� Linux��Unix �ں˽Կɣ��� jvm �� Xms64m,Xmx128m Լ���£�ϵͳӲ��Ҫ������ڴ� 2G������ 40G��4vCPU ���� �������С� 
2.  �ͻ��˻������Ƽ�ʹ�ùȸ� Chrome �������֧�� Chrome �ں˵�����������Ƽ� IE8 ��������������ʡ�

#### 1.2.2 ����ʱ�����������

1. JRE17��Java Runtime Environment�������Ϊ Java ��Ŀ����ʱ������JRE17��֧��spring boot3.x��������л���Ҫ��
2. Mysql ����ϵͳʹ�� Mysql8.30 ��Ϊ��Ŀ���ݿ⣬�����ر�֤ Mysql ������ �����У�Ĭ�Ͽ��� 3306 �˿ڶ����ṩ��������ʧ��ʱ����ʹ�� lsof -i:3306 ָ ���Ų�˿�ռ������������ ps aux | grep [pid]�����Ų�˿�ռ��Ӧ�á�
3. Redis ����ϵͳĬ��ʹ�õ��� Redis ģʽΪƽ̨�ṩ����������ڴ洢Ȩ�ޣ� ��ʱ���������ݵȣ�Ĭ�϶˿� 6379��
4. Nginx ����Ϊǰ��ҳ���ṩ��̬��Դ���ʷ����Լ�Ϊ��˷����ṩ�������������

#### 1.2.3 ��������

1. IDEA 2023.3
2. JDK20��Java �������߰�
3. Maven 3.8.1������Ŀ�İ������ߣ������Զ����룬�汾�������������� �ݴ��������ʹ��ǰ����Ҫ������
4. datagrip 2023.3��Mysql ���� UI �����ߡ�
5. WebStorm 2023.3��ǰ�˱��빤�ߡ�
6. Git+Gihub����Ŀ�汾����

#### 1.2.4 �������

1. ��ˣ�
   1. SpringBoot 3.1.0���� Spring �Լ� SpringMVC �Ľ�һ����װʵ������ ��װ��������
2. ǰ�ˣ�
   1. Vue3.js ��ؿ����׼���

#### 1.2.5 �м����������ģ������

1. ���
   1. Redis: һ�������ܵĻ�����񣬿����ڻ������ݡ����ϵͳ���ܵȷ��档
   2. MyBatis��MyBatis plus: ����ĳ־ò��ܣ��������ݿ������ORM�������ϵӳ�䣩��
   3. bcprov-jdk15on: ������ܹ��ߣ��ṩ������ѧ�㷨��ʵ�֣��������ݼ��ܺͽ��ܲ�����
   4. lombok: һ��Java�⣬����ͨ��ע���Java��Ŀ���������������롣
   5. jwt: JSON Web Token��JWT����Javaʵ�֣��������ɺ���֤�������Ƶ������֤��
   6. logback-classic: һ��������־��ܣ����ڼ�¼Ӧ�ó������־��Ϣ��
   7. tika-core: һ����Դ�����ݷ������ߣ����ڴӸ����ĵ�����ȡ�ͽ���Ԫ���ݺ��ı����ݡ�
   8. poi��poi-ooxml: ���ڴ���Microsoft Office�ĵ�����Excel��Word����Java�⣬�ṩ�˶�д�Ͳ�����Щ�ĵ��Ĺ��ܡ�
   9. UserAgentUtils: ���ڽ�����ʶ���û������ַ�����User-Agent���Ĺ��ߣ������ڻ�ȡ�û���������Ͳ���ϵͳ��Ϣ��
   10. com.google.zxing:core: Google�����Ķ�ά�����ɺͽ����⣬���������ɺͽ�����ά��ͼ��
2. ǰ��
   1. axios: һ������Promise��HTTP�ͻ��ˣ��������������Node.js�з���HTTP����
   2. dayjs: һ�������������ڴ���⣬���ڽ�������ʽ���Ͳ������ڡ�
   3. echarts: һ��ǿ������ݿ��ӻ��⣬���ڴ�������ͼ��Ϳ��ӻ�Ч����
   4. json-bigint: һ�����ڴ����������JSON���������л��⡣
   5. arco-design: �ṩ��һϵ���ִ�����UI�������ʽ�����ڹ����û����档

## 2 ϵͳ��Ʒ���

### 2.1 ���ݱ����

![�����](�����.png)

### 2.2 ������

#### 2.2.1 ϵͳ����

##### 2.2.1.1 pom.xml maven��������

- ��Ŀ�趨

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

- �����������汾����

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
  
      <!--���ܹ���-->
      <dependency>
          <groupId>org.bouncycastle</groupId>
          <artifactId>bcprov-jdk15on</artifactId>
          <version>1.70</version>
      </dependency>
      <!--mapper���Զ�-->
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
      <!--��־-->
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
      <!--�ļ���鹤��-->
      <dependency>
          <groupId>org.apache.tika</groupId>
          <artifactId>tika-core</artifactId>
          <version>2.7.0</version>
      </dependency>
      <!--����office�ļ�-->
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
      <!--�����û��豸��Ϣ-->
      <dependency>
          <groupId>eu.bitwalker</groupId>
          <artifactId>UserAgentUtils</artifactId>
          <version>1.21</version>
      </dependency>
  
      <!--��ά�����-->
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

- ���

  ```xml
  <plugins>
      <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
      <!--����׶κ���test-->
      <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <configuration>
              <skip>true</skip>
          </configuration>
      </plugin>
  </plugins>
  ```

##### 2.2.1.2 logback-spring.xml ϵͳ��־�����ļ�

```xml
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- ����ʱ�����Ϣ��ʽ-->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="chapters.configuration" level="INFO"/>

    <!--������־���𣬲���׶�����ΪINFO-->
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>

</configuration>
```

##### 2.2.1.3 application.yml ��Ŀ����

�Զ������÷���ȫ���޸��趨

```yml
# ����˿�
server:
  port: 8080

spring:
# ����Դ
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://42.193.192.61:3306/sgmis
    username: root
    password: guatdev
  # ʱ�����л���ʽ
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: Asia/Shanghai
  # �ļ�����
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

# �Զ�������     

# �����㷨����
encrypt:
  memoryPowOfTwo: 13
  iterations: 9
  parallelism: 5
  hashLength: 512

# koten�㷨����
jwt:
  #��
  expires: 21600
  update-gap: 1800
  secret-key: S&h(i(to*A@E#
  ipLimit: 10

# ������Ϣ�ı�������ֹsqlע��
xxpz:
  tableNames: yspz, lxpz, yanpz, jbpz, ylhl, gdcgzt, breeder, country, province, state
  systemGid: 0

# Ĭ������
user:
  defaultPassword: 123456

#ϵͳ�ļ�����·��
file:
  logo:
    path: logo
    type: image/jpeg, image/png, image/gif, image/tiff
  pigeon:
    path: pigeon
    type: image/jpeg, image/png, image/gif, image/tiff

# �Ӵ�Ѱ�ҵݹ����
pigeon:
  generation:
    limit: 7

#һ�����ܲ�����������������
page:
  maxLimit: 10000

# ����ȡͼƬ����
carousel:
  limit: 10

# ��ȡ���������������
# ��
recent:
  create: 30
  delete: 30
  oplog: 30

#��־�����������
#day
log:
  maxHistory: 180

# ϵͳ��ά����������
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

# ������Ϣ��ά���ַ
feedback:
  githubUrl: https://github.com/Shi-AE/sgmis
  guatUrl: https://www.guat.edu.cn
  mailUrl: mailto:2021070030101@guat.edu.cn
  
# ģ���ļ���ַ����
template:
  pigeon: template/pigeonTemplate.xlsx
```

#### 2.2.2 ע������

- LocalDateTimeSerializerConfig LDT���л���ʽ����

  ```java
  /**
   * LDT����
   */
  @Configuration
  public class LocalDateTimeSerializerConfig {
  
      @Value("${spring.jackson.date-format}")
      private String pattern;
  
      /**
       * ���л���
       */
      @Bean
      public LocalDateTimeSerializer localDateTimeSerializer() {
          return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
      }
  
      /**
       * �����л���
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

- MybatisPlusConfig MP��ҳ�������

  ```java
  /**
   * mp����
   */
  @Configuration
  public class MybatisPlusConfig {
      /**
       * ��ҳ���
       */
      @Bean
      public MybatisPlusInterceptor mybatisPlusInterceptor() {
          MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
          mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
          return mybatisPlusInterceptor;
      }
  }
  ```

- RedisConfig redis����

  ```java
  /**
   * redis����
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

- ResourceConfig ϵͳ��Դ·������

  ```java
  /**
   * ��Դ����
   */
  @Slf4j
  @Configuration
  public class ResourceConfig implements WebMvcConfigurer {
  
      @Autowired
      private Environment environment;
  
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          //���������ļ��ҵ�����ƥ����
          StandardServletEnvironment servletEnvironment = (StandardServletEnvironment) environment;
          MutablePropertySources propertySources = servletEnvironment.getPropertySources();
          propertySources.forEach(source -> {
              if (source instanceof MapPropertySource && source.getName().contains("yml")) {
                  for (String name : ((MapPropertySource) source).getPropertyNames()) {
                      if (name.matches("^file\\.(.*)+\\.path$")) {
                          String basePath = environment.getProperty(name);
                          registry.addResourceHandler("/" + basePath + "/**")
                                  .addResourceLocations("file:" + basePath + "/");
                          log.info("�ɹ����� {} ��Դ·��", name);
                      }
                  }
              }
          });
      }
  }
  ```

- InterceptorConfig ����������

  ```java
  /**
   * ����������
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
          //ip���������������
          registry.addInterceptor(blackInterceptor)
                  .addPathPatterns("/api/**")
                  .order(1);
  
          //�����¼��֤������
          registry.addInterceptor(loginInterceptor)
                  .addPathPatterns("/api/**")
                  .excludePathPatterns("/api/login/authority")
                  .excludePathPatterns("/api/login/free")
                  .order(2);
  
          //����Ա����������
          registry.addInterceptor(adminInterceptor)
                  .addPathPatterns("/api/user/**")
                  .addPathPatterns("/api/login/admin")
                  .order(3);
      }
  }
  ```

#### 2.2.3 �������߼�

- LoginInterceptor ��¼����������֤�Ƿ�Я����¼����

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
          //��ȡ����ͷ֤��
          String token = request.getHeader("Authorization");
  
          //��֤֤��
          DecodedJWT decoded = jwtUtil.verifyToken(token);
  
          //��ȡtoken�е���Ϣ
          Map<String, Object> claim = decoded.getClaim("info").asMap();
  
          //��redis����֤֤��
          Long id = (Long) claim.get("id");
          String ip = (String) claim.get("ip");
          boolean verified = whitelistUtil.verifyToken(id, ip, token);
          if (!verified) {
              throw new AccessException("��¼����");
          }
  
          //�������redis����ʱ��ﵽ�趨ʱ��������redis����ʱ��
          whitelistUtil.updateExpires(id, ip);
  
          //��������������û���Ϣ
          request.setAttribute("info", claim);
  
          return true;
      }
  }
  ```

- AdminInterceptor ����Ա����������֤����Ա���

  ```java
  @Component
  public class AdminInterceptor implements HandlerInterceptor {
  
      @Autowired
      private UserService userService;
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
          //��ȡ id��admin
          Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
          Long id = (Long) info.get("id");
          Boolean admin = (Boolean) info.get("admin");
          //����ͷ����Ա��֤
          if (!admin) {
              throw new AccessException("��û�й���ԱȨ�ޣ�����ϵ����Ա���Ȩ��");
          }
          //���ݿ����Ա��֤
          User user = userService.getById(id);
          if (!user.getAdmin()) {
              throw new AccessException("����ͷ��Ϣ�쳣");
          }
          return true;
      }
  }
  ```

- BlackInterceptor ���������������ж�IP�Ƿ񱻷��

  ```java
  @Component
  public class BlackInterceptor implements HandlerInterceptor {
  
      @Autowired
      private IpUtil ipUtil;
      @Autowired
      private BlacklistUtil blacklistUtil;
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessException {
          //��ȡʵ��ip������ʹ��token�е�ip
          String ip = ipUtil.getIp(request);
  
          //ͨ��ip��ȡ�������е���Ϣ
          //���Ϊ�մ����������û�д�ip
          Object info = blacklistUtil.getForbiddenInfo(ip);
          if (info != null) {
              //��ip�����ã����ؽ�����Ϣ
              throw new AccessException(info.toString());
          }
  
          return true;
      }
  }
  ```

#### 2.2.4 ϵͳ�����Լ�

- RedisConnectionRunner redis�����Լ�

  ```java
  @Slf4j
  @Component
  public class RedisConnectionRunner implements ApplicationRunner {
  
      @Autowired
      private RedisUtil redisUtil;
      /**
       * �����������
       */
      private final int max = 5;
  
      /**
       * ����redis����
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
              log.error("redis����ʧ��");
  
              //����ʧ���˳�����
              System.exit(1);
          }
  
          log.info("redis���ӳɹ�");
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

#### 2.2.6 ϵͳö����

- �쳣״̬��ö�٣����֣�

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

- �ɹ�״̬��ö��

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

- ��־����ö��

  ```java
  public enum LogType {
      /**
       * ����
       */
      INSERT(0, "��������"),
      /**
       * �޸�
       */
      UPDATE(1, "�޸���Ϣ"),
      /**
       * ɾ��
       */
      DELETE(2, "ɾ������"),
      /**
       * ����
       */
      SHARE(3, "����Ѫͳ"),
      /**
       * ����Ѫͳ
       */
      RECEIVE(4, "����Ѫͳ"),
      /**
       * ����Ѫ��
       */
      RELATE(5, "����Ѫ��"),
      /**
       * ���Ѫ�׹�ϵ
       */
      UNPARENT(6, "���Ѫ��"),
      /**
       * ת�Ƹ��ﳲ��
       */
      TRANSFER(7, "ת�Ƹ��ﳲ��"),
      /**
       * ����
       */
      OTHER(8, "����");
  
  
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

- Υ��ȼ�ö��

  ```java
  public enum SeverityLevel {
      /**
       * ����
       */
      Blocker(3600),
      /**
       * �м�
       */
      Critical(1800),
      /**
       * ����
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

- redis�����ռ�ö��

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

#### 2.2.5 ͳһ���ظ�ʽ

����ǰ�˵�ͳһjson��ʽ

{ "code": 200, "data": { },"message": "����ɹ�" }

```java
public class Result {
    private Object data;
    private Integer code;
    private String message;
}
```

#### 2.2.6 ϵͳ������ utils

- EncryptUtil ���ܹ���

  ```java
  /**
   * ���ܹ�����
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
       * ��ʼ�������ؼ��ܹ���
       *
       * @param salt ��ֵ
       * @return ���ܹ���
       */
      private Argon2BytesGenerator getGenerator(byte[] salt) {
          //����Argon2��ʼ������
          Argon2Parameters argonBuilder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                  .withSalt(salt)
                  .withMemoryPowOfTwo(memoryPowOfTwo)
                  .withIterations(iterations)
                  .withParallelism(parallelism)
                  .build();
          //�����������߲���ʼ��
          Argon2BytesGenerator generator = new Argon2BytesGenerator();
          generator.init(argonBuilder);
  
          return generator;
      }
  
      /**
       * ���������ֵ
       *
       * @return ��ֵ����������
       */
      private byte[] getRandomSalt() {
          byte[] salt = new byte[hashLength];
          secureRandom.nextBytes(salt);
          return salt;
      }
  
      /**
       * �������
       *
       * @param user �û���Ϣ
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
       * ����У��
       *
       * @param password ����
       * @param user     �û���Ϣ
       * @return �����Ƿ���ȷ
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

-  JwtUtil ���ɽ���token����

  ```java
  //��ȡ����
  @Value("${jwt.secret-key}")
  private String secretKey;
  @Value("${jwt.expires}")
  private int expires;
  ```

  ```java
  /**
   * ��ȡ��ʱ������token
   */
  public String getLimitlessToken(Map<String, Object> claim) {
      return JWT.create()
              .withClaim("info", claim)
              .sign(Algorithm.HMAC256(secretKey));
  }
  ```

  ```java
  /**
   * ��֤token
   *
   * @return ��֤��Ϣ
   */
  public DecodedJWT verifyToken(String token) {
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
      return jwtVerifier.verify(token);
  }
  ```

- IpUtil ��ȡ��ʵip����

  ```java
  /**
   * ip����
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

- FileUtil �ļ�������

  ```java
  /**
   * �ļ�������
   */
  @Slf4j
  @Component
  public class FileUtil {
  
      private final Tika tika = new Tika();
  
      /**
       * ��ʼ���ļ���
       */
      public void initDirectory(Path path) {
          try {
              Files.createDirectory(path);
          } catch (FileAlreadyExistsException ignored) {
          } catch (IOException e) {
              log.error("��ʼ��bean��������{} �洢�ļ��д���ʧ��", path.getFileName(), e);
          }
      }
  
      /**
       * ���ļ������ڱ���
       */
      public String storeFile(MultipartFile file, Path path) {
          try {
              String uniqueFileName = IdWorker.get32UUID() + "-" + file.getOriginalFilename();
              Files.copy(file.getInputStream(), path.resolve(uniqueFileName));
              return uniqueFileName;
          } catch (IOException e) {
              log.error("�����ļ� {} ʱ��������", file, e);
              throw new FileSaveException("�ļ�����ʧ��");
          }
      }
  
      /**
       * �����ļ�Ŀ¼��ȡһ�����ļ�
       */
      public Stream<Path> getFileListByDirectory(Path dirPath) {
          try {
              return Files.list(dirPath);
          } catch (IOException e) {
              log.error("��ѯ�ļ�Ŀ¼ {} ʱ��������", dirPath, e);
              throw new FileSaveException("�ļ�����ʧ��");
          }
      }
  
      /**
       * ɾ���ļ�
       */
      public boolean deleteFile(String fileName, Path path) {
          try {
              Files.deleteIfExists(path.resolve(fileName));
              return true;
          } catch (IOException e) {
              log.error("�ļ�ɾ��ʧ�ܣ����ļ���Ϊ {}��pathΪ {}", fileName, path);
              return false;
          }
      }
  
      /**
       * ����ļ�����
       */
      public String getFileType(MultipartFile file) {
          try {
              return tika.detect(file.getInputStream());
          } catch (IOException e) {
              log.error("����ļ� {} ʱ�ļ�����������", file, e);
              throw new FileSaveException("�ļ�����ʧ��");
          }
      }
  
      /**
       * ͨ���ļ������б�
       * ����ļ������Ƿ�ƥ��
       */
      @SuppressWarnings("UnusedReturnValue")
      public String checkFileType(MultipartFile file, Set<String> typeList) {
          String fileType = getFileType(file);
          boolean checked = typeList.contains(fileType);
          if (!checked) {
              throw new FileSaveException("�ļ����ʹ������ϴ���ȷ����");
          }
          return fileType;
      }
  
      /**
       * ͨ�����ַ���
       * ����ļ������Ƿ�ƥ��
       */
      public String checkFileType(MultipartFile file, String... types) {
          Set<String> typeList = Set.of(types);
          String fileType = getFileType(file);
          boolean checked = typeList.contains(fileType);
          if (!checked) {
              throw new FileSaveException("�ļ����ʹ������ϴ���ȷ����");
          }
          return fileType;
      }
  
      /**
       * �������·����ȡ�ļ�
       * ���ļ�װ����Ӧ
       */
      public void responseFileByRelativePath(String path, HttpServletResponse response) {
          //��ȡ�ļ�
          File file = new File(path);
          try {
              ServletOutputStream out = response.getOutputStream();
              byte[] fileBytes = FileUtils.readFileToByteArray(file);
              out.write(fileBytes);
          } catch (FileNotFoundException e) {
              log.error("��������ļ�ģ�巢���ļ�δ�ҵ�����·����{}", "");
          } catch (IOException e) {
              log.error("��������ļ�ģ�巢��IO����·����{}", "");
          }
      }
  }
  ```

- QRCodeUtil ��ά�빤��

  ��ȡ����

  ```java
  /**
   * Ĭ�Ͽ��
   */
  @Value("${QR.width}")
  private int width;
  /**
   * Ĭ�ϸ߶�
   */
  @Value("${QR.height}")
  private int height;
  /**
   * ͼƬ��ʽ
   */
  @Value("${QR.image-format}")
  private String imageFormat;
  /**
   * �ַ�����
   */
  @Value("${QR.charset}")
  private String charset;
  /**
   * ԭ��ת��ǰ��û�� data:image/png;base64 ��Щ�ֶΣ����ظ�ǰ�����޷�������
   */
  @Value("${QR.base64-image}")
  private String base64Image;
  /**
   * ϵͳlogo��ַ
   */
  @Value("${QR.logo-url}")
  private String logoUrl;
  /**
   * logo���
   */
  @Value("${QR.logo-width}")
  private int logoWidth;
  /**
   * logo�߶�
   */
  @Value("${QR.logo-height}")
  private int logoHeight;
  /**
   * ��ά��ǰ��ɫ
   */
  @Value("${QR.foreground}")
  private int foreground;
  /**
   * ��ά�뱳��ɫ
   */
  @Value("${QR.background}")
  private int background;
  
  private Map<EncodeHintType, Object> qrConfig;
  
  @PostConstruct
  private void init() {
      qrConfig = new HashMap<>();
      //ָ���ַ���
      qrConfig.put(EncodeHintType.CHARACTER_SET, charset);
      //ָ����ά��ľ���ȼ�Ϊ�м�
      qrConfig.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      //����ͼƬ�ı߾�
      qrConfig.put(EncodeHintType.MARGIN, 2);
      log.info("QRCodeUtil qrConfig ��ʼ����� {}", qrConfig);
  }
  ```

  ���Ĵ���

  ```java
  /**
   * ���ݿ�����ɶ�ά��
   */
  public String createQrCode(String content, int width, int height) {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      try {
          // ���� QRCodeWriter ����
          QRCodeWriter qrCodeWriter = new QRCodeWriter();
          // ���� BitMatrix ����
          BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, qrConfig);
          // ���� BufferedImage ����
          BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
          //�������ص���ɫ
          for (int x = 0; x < width; x++) {
              for (int y = 0; y < height; y++) {
                  bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? foreground : background);
              }
          }
          //����logo
          insertLogo(bufferedImage, width, height, logoUrl, logoWidth, logoHeight);
          //���
          ImageIO.write(bufferedImage, imageFormat, output);
      } catch (Exception e) {
          log.error("������� ���ɶ�ά��ʧ��", e);
      }
      return String.format(base64Image, new String(Base64.encode(output.toByteArray())));
  }
  
  /**
   * ����logo
   */
  private void insertLogo(BufferedImage source,
                          int width, int height,
                          String logoUrl,
                          int logoWidth, int logoHeight) throws Exception {
      Image src = ImageIO.read(new File(logoUrl));
      // ����LOGO
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

- redis���

  - RedisUtil redis����

    ```java
    /**
     * redis����
     */
    @Slf4j
    @Component
    public class RedisUtil {
    
        @Autowired
        RedisTemplate<String, Object> redisTemplate;
    
        /**
         * ����ping����
         * ������ݿ�����
         */
        public boolean PING() {
            try {
                String execute = redisTemplate.execute(RedisConnectionCommands::ping);
                if (execute == null || !execute.equals("PONG")) {
                    log.error("redis PING ʧ�� {}}", execute);
                    return false;
                }
            } catch (RedisConnectionFailureException e) {
                log.error("redis���Ӵ���", e);
                return false;
            }
            return true;
        }
    
        /**
         * ��ͨ������벢����ʱ��
         *
         * @param namespace �����ռ�
         * @param key       ��
         * @param value     ֵ
         * @param time      ʱ��(��) timeҪ����0 ���timeС�ڵ���0 ������������
         */
        public void set(RedisNamespace namespace, String key, Object value, long time) {
            if (time > 0) {
                redisTemplate.opsForValue().set(namespace.getValue() + key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(namespace.getValue() + key, value);
            }
        }
    
        /**
         * �������ռ�
         * ��ͨ������벢����ʱ��
         *
         * @param key   ��
         * @param value ֵ
         * @param time  ʱ��(��) timeҪ����0 ���timeС�ڵ���0 ������������
         */
        public void set(String key, Object value, long time) {
            set(RedisNamespace.Ancestor, key, value, time);
        }
    
        /**
         * �����������ռ�
         * ��ȡ�����ռ��µ�����key
         */
        public Set<String> keys(RedisNamespace redisNamespace, Object namespace) {
            return redisTemplate.keys(redisNamespace.getValue() + namespace + "*");
        }
    
        /**
         * �����������ռ�
         * ��ȡ�����ռ��µ�����key
         */
        public Set<String> keys(RedisNamespace redisNamespace) {
            return redisTemplate.keys(redisNamespace.getValue() + "*");
        }
    
        /**
         * �������ռ�
         * ��ѯ����ʱ��
         *
         * @return ʱ��(��) ����0����Ϊ������Ч
         */
        public Long getExpire(RedisNamespace namespace, String key) {
            return redisTemplate.getExpire(namespace.getValue() + key);
        }
    
        /**
         * �������ռ�
         * ��ѯ����ʱ��
         *
         * @return ʱ��(��) ����0����Ϊ������Ч
         */
        public Long getExpire(String key) {
            return getExpire(RedisNamespace.Ancestor, key);
        }
    
        /**
         * �������ռ�
         * ɾ������
         */
        public void del(RedisNamespace namespace, String key) {
            redisTemplate.delete(namespace.getValue() + key);
        }
    
        /**
         * �������ռ�
         * ɾ������
         */
        public void del(String key) {
            del(RedisNamespace.Ancestor, key);
        }
    
        /**
         * �������ռ�
         * ��ȡ��ͨ����
         */
        public Object get(RedisNamespace namespace, String key) {
            return key == null ? null : redisTemplate.opsForValue().get(namespace.getValue() + key);
        }
    
        /**
         * �������ռ�
         * ָ������ʧЧʱ��
         */
        public void expire(RedisNamespace namespace, String key, long time) {
            if (time > 0) {
                redisTemplate.expire(namespace.getValue() + key, time, TimeUnit.SECONDS);
            }
        }
    }
    ```

  - WhitelistUtil ����������

    ```java
    /**
     * ������������
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
         * ��token���������
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
         * ���Ƶ��û�id�������豸��
         * ������ڳ������Ƶ��豸��
         * ����token������
         */
        public void limitToken(Long id) {
            //��ȡ��id�µ�����key
            Set<String> keys = redisUtil.keys(RedisNamespace.Whitelist, id + ":");
    
            //���key����С�����������
            if (keys.size() <= ipLimit) {
                return;
            }
    
            //ɾ���������ʱ��̵�token
    
            //��ѯ��key��Ӧ�Ĺ���ʱ�䣬�������ȶ���
            //QueueԪ��Ϊ���飬e[0]key��e[1]Ϊ����ʱ��
            Queue<Object[]> expiresQueue = new PriorityQueue<>(Comparator.comparingLong(e -> (Long) e[1]));
            keys.forEach(key -> expiresQueue.add(new Object[]{key, redisUtil.getExpire(key)}));
    
            while (expiresQueue.size() > ipLimit) {
                Object[] poll = expiresQueue.poll();
                assert poll != null;
                redisUtil.del((String) poll[0]);
            }
        }
    
        /**
         * ��֤�������е�token
         */
        public boolean verifyToken(Long id, String ip, String token) {
            //��ȡtoken
            Object redisToken = redisUtil.get(RedisNamespace.Whitelist, id + ":" + ip);
            if (redisToken == null) {
                return false;
            }
            return redisToken.equals(token);
        }
    
        /**
         * ����redis����ʱ��
         */
        public void updateExpires(Long id, String ip) {
            Long redisExpires = redisUtil.getExpire(RedisNamespace.Whitelist, id + ":" + ip);
            if (expires - redisExpires > updateGap) {
                redisUtil.expire(RedisNamespace.Whitelist, id + ":" + ip, expires);
            }
        }
    
        /**
         * ͳ�ƻ�Ծ����
         */
        public long getActive(RedisNamespace namespace, String key) {
            //���ݻ�ȡ����key��key
            Set<String> keys = redisUtil.keys(namespace, key);
    
            AtomicLong count = new AtomicLong();
    
            //ɸѡ��ͳ�������Ծ������
            keys.forEach(k -> {
                Long tokenExpires = redisUtil.getExpire(k);
    
                if (expires - tokenExpires < updateGap) {
                    count.getAndIncrement();
                }
            });
    
            return count.get();
        }
    
        /**
         * ���û��������豸
         * �Ӱ��������޳�
         */
        public void deleteToken(Long id) {
            Set<String> keys = redisUtil.keys(RedisNamespace.Whitelist, id + ":");
            //ɾ�������豸
            keys.forEach(key -> redisUtil.del(key));
        }
    
        /**
         * �鿴�û��Ļ�Ծ״̬
         */
        public int getState(RedisNamespace namespace, String key) {
            //���ݻ�ȡ����key��key
            Set<String> keys = redisUtil.keys(namespace, key);
    
            int state = 0;
    
            //�鿴ÿ���豸�Ļ�Ծ״̬
            for (String k : keys) {
                Long tokenExpires = redisUtil.getExpire(k);
    
                //���������������״̬��Ϊ�������״̬
                if (expires - tokenExpires < updateGap) {
                    state = 2;
                    break;
                }
    
                //��������������ô�״̬
                if (state < 1 && tokenExpires > 0) {
                    state = 1;
                }
            }
    
            return state;
        }
    }
    ```

  - BlacklistUtil ����������

    ```java
    /**
     * ����������
     */
    @Component
    public class BlacklistUtil {
    
        @Autowired
        private RedisUtil redisUtil;
    
        /**
         * ���ݼ���������������ip
         * �����ù���ʱ��
         */
        public void addForbiddenIp(String ip, Object message, SeverityLevel level) {
            redisUtil.set(RedisNamespace.Blacklist, ip, message, level.getExpire());
        }
    
        /**
         * ͨ��ip��ȡ�������е���Ϣ
         */
        public Object getForbiddenInfo(String ip) {
            return redisUtil.get(RedisNamespace.Blacklist, ip);
        }
    }
    ```

#### 2.2.7 �쳣��������

- ��Ŀ�쳣���࣬Ϊ�쳣�������״̬�뷽��

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

- �쳣�������ͨ��֤�쳣��

  ```java
  public class AccessException extends ProjectException{
      public AccessException(String message) {
          super(message);
          setCode(ExceptionCode.AccessException.code);
      }
  }
  ```

- �쳣������

  - ��ͨ�쳣����

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

  - ͨ��֤�쳣����

    ```java
    /**
     * 403�޷���Ȩ��
     */
    @ExceptionHandler({JWTDecodeException.class, SignatureVerificationException.class})
    private Result doAccessException() {
        return new Result(ExceptionCode.AccessException.code, "ͨ��֤ʧЧ");
    }
    
    /**
     * ��¼����
     */
    @ExceptionHandler({TokenExpiredException.class})
    private Result doExpiredException() {
        return new Result(ExceptionCode.ExpiredException.code, "ͨ��֤����");
    }
    ```

  - ���ݿ��쳣

    ```java
    /**
     * �����ݿ���������ݹ���
     */
    @ExceptionHandler(DataTruncation.class)
    private Result doDataTruncationException(DataTruncation exception) {
        log.warn("�û��ϴ������ݳ������ݿ�Ԥ�� {}", exception.getMessage());
        return new Result(ExceptionCode.SaveFailException.code, "�ϴ������ݹ�������ϵ����Ա��ȡ֧��");
    }
    
    /**
     * ���ݿ�Ψһ�ֶη����ظ�
     */
    @ExceptionHandler(DuplicateKeyException.class)
    private Result doDuplicateKeyException() {
        return new Result(ExceptionCode.DuplicateKeyException.code, "��Ϣ�Ѵ���");
    }
    ```

  - sqlע���쳣���⴦��

    ```java
    /**
     * �Ƿ�sqlע��
     */
    @ExceptionHandler(MaliciousSqlInjectionException.class)
    private Result doMaliciousSqlInjectionException(MaliciousSqlInjectionException exception, HttpServletRequest request) {
        //��ȡʵ��ip������ʹ��token�е�ip
        String ip = ipUtil.getIp(request);
    
        //�����Ϣ
        Map<String, Object> cause = new HashMap<>();
        cause.put("ip", ip);
        cause.put("message", exception.getMessage());
        cause.put("route", request.getServletPath());
    
        //�����س̶ȼ��������
        blacklistUtil.addForbiddenIp(ip, cause, exception.getSeverityLevel());
    
        return new Result(exception.getCode(), exception.getMessage());
    }
    ```

#### 2.2.8 ϵͳ��ʱ����

- LogSchedule ��¼��Ϣ��־��ʱ����

  ```java
  @Slf4j
  @Component
  public class LogSchedule {
  
      @Autowired
      private LoginMsgService loginMsgService;
  
      /**
       * ��ʱ�����¼��־��Ϣ
       */
      @Scheduled(cron = "0 0 3 * * *")
      public void loginLogTask() {
  
          int remove = loginMsgService.removeForCountTask();
  
          log.info("�ɹ����� {} �����ڵ�¼��Ϣ��¼", remove);
      }
  }
  ```

  ҵ���

  ```java
  @Autowired
  private LoginMsgMapper loginMsgMapper;
  @Value("${log.maxHistory}")
  private Integer maxHistory;
  @Override
  public int removeForCountTask() {
      //��ȡ��ʷʱ��
      LocalDateTime historyTime = LocalDateTime.now().minusDays(maxHistory);
  
      QueryWrapper<LoginMsg> wrapper = new QueryWrapper<>();
      wrapper.lt("time", historyTime);
  
      return loginMsgMapper.delete(wrapper);
  }
  ```

- OnlineSchedule ��������������ʱ����

  ```java
  @Slf4j
  @Component
  public class OnlineSchedule {
  
      @Autowired
      private RedisUtil redisUtil;
      @Autowired
      private WhitelistUtil whitelistUtil;
  
      /**
       * �̶�ʱ����
       * ���ϴ�ִ��������
       * ÿ��ʮ����ͳ����������
       */
      @Scheduled(fixedDelay = 600000, initialDelay = 1000)
      public void countOnline() {
          //��ȡ�������е����л�Ծ����
          long active = whitelistUtil.getActive(RedisNamespace.Whitelist, "");
  
          //��ͳ�Ƶ��������뻺�棬�����ù���
          redisUtil.set(RedisNamespace.Online, "", active, -1);
  
          log.info("ͳ�Ƶ�ǰ��Ծ���� {}", active);
      }
  }
  ```

- OplogSchedule ������־��ʱ����

  ```java
  @Slf4j
  @Component
  public class OplogSchedule {
  
      @Autowired
      private OplogService oplogService;
      @Autowired
      private PigeonService pigeonService;
  
      /**
       * ��ʱΪ������־�����㻷
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
  
          //����
          AtomicInteger count = new AtomicInteger();
  
          oplogList.forEach(oplog -> {
              Long pid = oplog.getPid();
  
              //����pid��ѯringNumber
              Pigeon pigeon = pigeonService.getById(pid);
              if (pigeon == null) {
                  return;
              }
              String ringNumber = pigeon.getRingNumber();
  
              //����oplog��ringNumber
              int update = oplogService.updateRingNUmber(pid, ringNumber);
  
              count.addAndGet(update);
          });
  
          log.info("�ɹ�������־ringNumber {} ��", count);
      }
  }
  ```

- PictureSchedule ����ͼƬ��Դ����ʱ����

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
       * ɾ��logo����ͼƬ
       * ÿ�� 3�㣬12�㣬19��
       */
      @Scheduled(cron = "0 0 3,12,19 * * *")
      public void deleteLogoPictureTask() {
          //������е�ͼƬ��ŵ�
          QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
          wrapper.select("logo_url");
          List<Xtspz> list = xtspzService.list(wrapper);
  
          //װ��set�����ѯ
          Set<String> logoUrlSet = new HashSet<>();
          list.forEach(xtspz -> {
              if (xtspz != null) {
                  logoUrlSet.add(xtspz.getLogoUrl());
              }
          });
  
          //ͳ��
          AtomicInteger count = new AtomicInteger();
  
          Path logoPath = Paths.get(logoBasePath);
          Stream<Path> fileList = fileUtil.getFileListByDirectory(logoPath);
          fileList.forEach(file -> {
              String fileName = file.getFileName().toString();
              //�ж��Ƿ������ݿ���
              if (!logoUrlSet.contains(fileName)) {
                  //ɾ��
                  boolean success = fileUtil.deleteFile(fileName, logoPath);
                  if (success) {
                      log.info("�ɹ�ɾ��logoͼƬ {}", fileName);
                      count.getAndIncrement();
                  }
              }
          });
  
          log.info("�˴����logo������ԴͼƬ {} ��", count);
      }
  
      /**
       * ɾ��pigeon����ͼƬ
       * ÿ�� 3�㣬12�㣬19��
       */
      @Scheduled(cron = "0 0 3,12,19 * * *")
      public void deletePigeonPictureTask() {
          //������е�ͼƬ��ŵ�
          QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
          wrapper.select("picture_url");
          List<Pigeon> list = pigeonService.list(wrapper);
  
          //װ��set�����ѯ
          Set<String> pigeonUrlSet = new HashSet<>();
          list.forEach(pigeon -> {
              if (pigeon != null) {
                  pigeonUrlSet.add(pigeon.getPictureUrl());
              }
          });
  
          //ͳ��
          AtomicInteger count = new AtomicInteger();
  
          Path pigeonPath = Paths.get(pigeonBasePath);
          Stream<Path> fileList = fileUtil.getFileListByDirectory(pigeonPath);
          fileList.forEach(file -> {
              String fileName = file.getFileName().toString();
              //�ж��Ƿ������ݿ���
              if (!pigeonUrlSet.contains(fileName)) {
                  //ɾ��
                  boolean success = fileUtil.deleteFile(fileName, pigeonPath);
                  if (success) {
                      log.info("�ɹ�ɾ��pigeon����ͼƬ {}", fileName);
                      count.getAndIncrement();
                  }
              }
          });
  
          log.info("�˴����pigeon����ͼƬ��Դ {} ��", count);
      }
  }
  ```

#### 2.2.9 ϵͳ��ϸ�����ʵ��

##### 2.2.9.1 ��¼����

- ��¼��������֤���

  ���ݿ��д洢����ϢΪpassword�ļ��ܽ���ͼ��ܵ���ֵ

  ```sql
  create table t_user
  (
      id       bigint       not null comment '��Ȼ����'
          primary key,
      account  varchar(255) not null comment '�˺�',
      password blob         not null comment '�����ϣֵ',
      salt     blob         not null comment '��ֵ',
      gid      bigint       not null comment '��id',
      admin    bit          not null comment '����ԱȨ��',
      constraint account
          unique (account)
  );
  ```

  ���ܷ���Ϊ��password��saltƴ������Argon2�㷨��ϣ���ܣ��������ݿ���ⷢ�����ݿ�����й¶

  ��¼ʵ���߼����£�

  ```java
  //��֤���벢���¼���
  user = loginService.loginVerify(user);
  loginService.updateEncrypt(user);
  ```

  ��֤��¼����

  ```java
  //��ѯ�û�
  QueryWrapper<User> accountQuery = new QueryWrapper<User>().eq("account", inputUser.getAccount());
  User user = userMapper.selectOne(accountQuery);
  
  if (user == null) {
      throw new NotFindUserException("���û�������");
  }
  
  //��֤����
  boolean passVerify = encryptUtil.passwordVerify(inputUser.getPassword(), user);
  
  if (!passVerify) {
      throw new PasswordErrorException("�������");
  }
  
  //���������Ը��¼���
  user.setPassword(inputUser.getPassword());
  
  return user;
  ```

  ���¼��ܽ��

  ÿ�ε�¼�����»�ȡ��ֵ���������ݿ�����Ϣ��������Ϣй¶��ɰ�ȫ����

  ```java
  encryptUtil.passwordEncrypt(user);
  int updateSuccess = userMapper.updateById(user);
  if (updateSuccess != 1) {
      log.error("�����û����� {} ʱ����ϵͳ���´���", user);
      throw new PasswordUpdateFailException("������·�������");
  }
  ```

  ������Ϣ

  ```java
  //��ȡ�û���Ϣ
  String ip = ipUtil.getIp(request);
  //��������ͷ��User-Agent
  String userAgent = request.getHeader("User-Agent");
  UserAgent parseUserAgent = UserAgent.parseUserAgentString(userAgent);
  //��ȡ�û��豸��Ϣ
  Browser browser = parseUserAgent.getBrowser();
  Version browserVersion = parseUserAgent.getBrowserVersion();
  OperatingSystem os = parseUserAgent.getOperatingSystem();
  Long id = user.getId();
  Long gid = user.getGid();
  Boolean admin = user.getAdmin();
  
  //���û���Ϣ���ϳ�map
  Map<String, Object> claim = new HashMap<>();
  claim.put("id", id);
  claim.put("account", account);
  claim.put("gid", gid);
  claim.put("admin", admin);
  claim.put("ip", ip);
  
  //�����û���Ϣmap��Ϊ�û�����token
  String token = jwtUtil.getLimitlessToken(claim);
  
  //��token����redis
  whitelistUtil.setToken(id, ip, token);
  
  //����uid��������޳������ip�µ�token
  whitelistUtil.limitToken(id);
  
  //��¼��¼��־
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
      log.error("��¼��¼��Ϣ {} ����", loginMsg);
      throw new SaveFailException("��¼����");
  }
  ```

- �˳���¼ʵ���߼�

  ```java
  //��ȡid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long id = (Long) info.get("id");
  
  //�޳�����token
  whitelistUtil.deleteToken(id);
  
  //����session
  session.invalidate();
  ```

- ���¼��֤

  ���¼�������߼�����

- �޸������߼�ʵ��

  ```java
  //��֤ԭ����
  loginService.loginVerify(user);
  
  //װ��Ϊ������
  user.setPassword(userVo.getNewPassword().getBytes());
  
  //���id
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long id = (Long) info.get("id");
  user.setId(id);
  
  //�������룬������
  loginService.updateEncrypt(user);
  
  //�޳�����token
  whitelistUtil.deleteToken(id);
  ```

##### 2.2.9.2 ѡ�������ֵ����

�˷�������ּ����ߴ��븴���ʣ������ܴ���Ǳ�ڵ�SQLע����ա�Ϊ��Ӧ��������⣬�������ֶ���У����ƣ�����SQLע���߽��гͷ���

- У������

  ```java
  @Value("${xxpz.tableNames}")
  private String[] names;
  ```

- ��ʼ��

  ```java
  private Set<String> tableNames;
  
  @PostConstruct
  public void init() {
      tableNames = Set.of(names);
      log.info("ѡ���������ͷ�ע��set��ʼ�����");
  }
  ```

- �����߼�ʵ��

  ```java
  /**
   * ��ȡ�������͵���������
   */
  @GetMapping
  public Result getAll(HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //������� gid or gid = systemGid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid).or().eq("gid", systemGid);
  
      //�ֶ�
      wrapper.select("name", "type");
  
      //��ѯ
      List<Xxpz> list = xxpzService.list(wrapper);
      return new Result(list, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  
  /**
   * �������ͻ�ȡ��������
   */
  @GetMapping("{type}")
  public Result getAllByType(@PathVariable String type, HttpServletRequest request) {
      //�ж��Ƿ��쳣typeע��
      if (!tableNames.contains(type)) {
          //�м����ᷢ��sqlע��
          throw new MaliciousSqlInjectionException("���������쳣ע��", SeverityLevel.Critical);
      }
  
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //������� type and gid or gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("type", type).and(qw -> qw.eq("gid", systemGid).or().eq("gid", gid));
  
      //��ѯ
      List<Xxpz> list = xxpzService.list(wrapper);
      return new Result(list, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  
  /**
   * ����idɾ������
   */
  @DeleteMapping("{id}")
  public Result deleteOne(@PathVariable("id") Long id, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //������� id = id and gid = gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid).eq("id", id);
  
      //ִ��
      boolean success = xxpzService.remove(wrapper);
      if (!success) {
          throw new DeleteFailException("����ѡ����ڻ��޷�ɾ��");
      }
      return new Result(SuccessCode.Success.code, "ɾ���ɹ�");
  }
  
  /**
   * ����ɾ��ѡ������
   */
  @Transactional
  @PostMapping("delete")
  public Result batchDelete(@RequestBody List<Long> ids, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //���� (id in ids) and gid = gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.in("id", ids).eq("gid", gid);
  
      boolean success = xxpzService.remove(wrapper);
      if (!success) {
          throw new DeleteFailException("����ѡ����ڻ��޷�ɾ��");
      }
  
      return new Result(SuccessCode.Success.code, "ɾ���ɹ�");
  }
  
  /**
   * ����ѡ������
   */
  @PostMapping("{type}")
  public Result add(@RequestBody Xxpz xxpz, @PathVariable String type, HttpServletRequest request) {
      //�ж��Ƿ��쳣typeע��
      if (!tableNames.contains(type)) {
          //�м��޸����󣬵����ᷢ��sqlע��
          throw new MaliciousSqlInjectionException("���������쳣ע��", SeverityLevel.Critical);
      }
  
      //���������л�ȡ�û���Ϣ
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      xxpz.setAuthor((String) info.get("account"));
      xxpz.setGid((Long) info.get("gid"));
      xxpz.setType(type);
  
      boolean success = xxpzService.save(xxpz);
      if (!success) {
          log.error("�������ô��� {} ʱ�����������", xxpz);
          throw new SaveFailException("����������ʧ��");
      }
  
      return new Result(xxpz, SuccessCode.Success.code, "��ӳɹ�");
  }
  
  /**
   * ����id��������
   */
  @PutMapping
  public Result update(@RequestBody Xxpz xxpz, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //��ȫ���
      if (xxpz.getGid() == null || !xxpz.getGid().equals(gid)) {
          throw new SaveFailException("�û���Ϣ��ƥ�䣬������");
      }
  
      //���� id = id and gid = gid
      QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
      wrapper.eq("id", xxpz.getId()).eq("gid", gid);
  
      //ִ��
      boolean success = xxpzService.update(xxpz, wrapper);
      if (!success) {
          throw new SaveFailException("���ݸ���ʧ�ܣ�������");
      }
      return new Result(SuccessCode.Success.code, "���³ɹ�");
  }
  ```

##### 2.2.9.3 Ѫͳ�����÷���

�˷����ṩͼƬ�ļ����ϴ���Ϊ��������ļ���ʽ��飬����Դ·������

- �������ʼ��

  ```java
  @Value("${file.logo.path}")
  private String basePath;
  @Value("${file.logo.type}")
  private String[] typeArray;
  @Autowired
  private XtspzService xtspzService;
  /**
   * ͼƬ����
   */
  private Set<String> type;
  private Path path;
  
  /**
   * bean��ʼ��
   */
  @PostConstruct
  public void init() {
      //��ʼ����Դ
      path = Paths.get(basePath);
      type = Set.of(typeArray);
      //��ʼ���ļ���
      fileUtil.initDirectory(path);
  }
  ```

- �ϴ�ͼƬ

  ```java
  /**
   * �ϴ�logoͼƬ
   */
  @PostMapping("logo")
  public Result uploadLogo(MultipartFile file) {
  
      if (Objects.isNull(file) || file.isEmpty()) {
          throw new FileSaveException("�����ϴ����ļ�");
      }
  
      //����ļ�����
      fileUtil.checkFileType(file, type);
  
      //�ı��浽������
      String fileName = fileUtil.storeFile(file, path);
  
      return new Result(fileName, SuccessCode.Success.code, "����ɹ�");
  }
  ```

- ������Ϣ�ı������ȡ

  ```java
  /**
   * ��ȡ������Ϣ
   */
  @GetMapping
  public Result getInfo(HttpServletRequest request) {
      //���������л�ȡ�û���Ϣ
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
      //���� gid = gid
      QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid);
      //��ѯ�ֶ�
      wrapper.select("logo_url", "name", "short_name", "phone", "mail", "url", "address");
      Xtspz xtspz = xtspzService.getOne(wrapper);
      if (xtspz == null) {
          xtspz = new Xtspz();
      }
      return new Result(xtspz, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  
  /**
   * ������Ϣ����
   */
  @PostMapping
  public Result postInfo(@RequestBody Xtspz xtspz, HttpServletRequest request) {
      //���������л�ȡ�û���Ϣ
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
      //���� gid = gid
      QueryWrapper<Xtspz> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid);
      //װ��
      xtspz.setGid(gid);
      //ִ��
      boolean success = xtspzService.saveOrUpdate(xtspz, wrapper);
      if (!success) {
          throw new SaveFailException("��Ϣ����ʧ��");
      }
      return new Result(SuccessCode.Success.code, "����ɹ�");
  }
  ```

##### 2.2.9.4 �Ŷ��û��˺Ź������

�ṩ�˳�Ա����ӡ�ɾ�����鿴��Ϣ���鿴״̬�ȹ���

- �����ֽڳ�ʼ��

  ```java
  @Value("${user.defaultPassword}")
  private String defaultPassword;
  private byte[] defaultPasswordBin;
  
  /**
   * һ���Գ�ʼ��Ϊ�ֽ�����
   * ���ٷ�������Դ����
   */
  @PostConstruct
  public void init() {
      defaultPasswordBin = defaultPassword.getBytes();
      log.info("��ʼ�� defaultPassword ��Ϊ�ֽ�������� {}", defaultPasswordBin);
  }
  ```

- ��ȡ��Ա����״̬

  ͨ���鿴redis�е���Ϣ��ȡ����״̬��״̬��Ϊ�����������ʵ�ּ� **����������**��

  ������߼����£�

  ```java
  //��ȡgid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long gid = (Long) info.get("gid");
  
  //gid = gid
  QueryWrapper<User> wrapper = new QueryWrapper<>();
  wrapper.eq("gid", gid);
  
  //���ò�ѯ�ֶ� id gid account admin
  wrapper.select("id", "gid", "account", "admin");
  
  //ִ��
  List<User> list = userService.list(wrapper);
  
  //�鿴����״̬����������Ϣװ��vo
  List<AdminUserVo> adminUserList = new ArrayList<>();
  list.forEach(user -> {
      //��ȡid
      Long id = user.getId();
      //��ȡ״̬
      int state = whitelistUtil.getState(RedisNamespace.Whitelist, id + ":");
      //���뷵���б�
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

- ��������

  ���������߼����޸��û����������߼����ơ�

  ���а�ȫ������ܶ������´������֣�

  ```java
  //��ȡgid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long gid = (Long) info.get("gid");
  
  //���gid��ȫ
  if (!user.getGid().equals(gid)) {
      throw new SaveFailException("�û���Ϣ��ƥ�䣬������");
  }
  
  //��ȡ�����id
  Long id = user.getId();
  
  //id = id and gid = gid
  QueryWrapper<User> wrapper = new QueryWrapper<>();
  wrapper.eq("id", id).eq("gid", gid);
  
  //�����ֶ� admin
  wrapper.select("admin");
  
  //�����������Ƿ�Ϊ����Ա
  user = userService.getOne(wrapper);
  if (user == null) {
      throw new SaveFailException("�û���Ϣ��ƥ�䣬������");
  }
  if (user.getAdmin()) {
      throw new SaveFailException("�޷���������Ա��Ϣ");
  }
  ```

##### 2.2.9.5 ������Ϣ���÷���

��ȡ��Ϣ�ͱ�����Ϣ���߼���Ѫͳ�����÷������ơ�ʵ���߼��� **2.2.9.3 Ѫͳ�����÷���**��

##### 2.2.9.6 ��ַ��Ϣ����

������Ϣ���÷�����Ҫʵ��ʡ�����ļ�����ѯ���˷����ṩ��ʡ����������ѯ���ܣ������Ǽ�����ѯ��˽ӿڵļ�ʵ�֣�

˼·�����ݸ���id��ѯ�Ӽ���Ϣ��

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
     * ��ѯʡ
     */
    @GetMapping("provincial")
    public Result getProvincial() {
        List<Provincial> list = provincialService.list();
        return new Result(list, SuccessCode.Success.code, "��ѯ�ɹ�");
    }

    /**
     * ����ʡ��ѯ��
     */
    @GetMapping("urban/{id}")
    public Result getUrban(@PathVariable Long id) {
        QueryWrapper<Urban> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        List<Urban> list = urbanService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "��ѯ�ɹ�");
    }

    /**
     * �����в�ѯ��
     */
    @GetMapping("area/{id}")
    public Result getAreas(@PathVariable Long id) {
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", id);
        List<Area> list = areasService.list(wrapper);
        return new Result(list, SuccessCode.Success.code, "��ѯ�ɹ�");
    }
}
```

##### 2.2.9.7 ���ﳲ�����

�÷����ṩ����ȡ���ﳲ����Ϣ����Ӹ��ﳲ�䡢�޸���Ϣ��ɾ�����ﳲ�䡢������ӡ�ͳ�Ƹ��������Ƚӿڣ�

�������ݱ�t_pigeon_gpcxδ�ṩȨ�޿����ֶΣ�������Ҫ�������Ȩ�޲�ѯ

- ͳһȨ�޲�ѯ��װ

  ```java
  /**
   * ���Ȩ��
   * ����Ƿ�gpcxId���û��޸�
   * ���Բ�ѯ�Ǳ��Ŷӵ�gpcx��Ϣ
   */
  private String check(Long gpcxId, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //���� id = gpcxId
      QueryWrapper<Gpcx> wrapper = new QueryWrapper<>();
      wrapper.eq("id", gpcxId);
  
      //�ֶ� gid
      wrapper.select("gid", "name");
  
      //ִ��
      Gpcx gpcx = gpcxService.getOne(wrapper);
  
      //���
      if (!gid.equals(gpcx.getGid())) {
          throw new MaliciousSqlInjectionException("�Ƿ���ѯ", SeverityLevel.Critical);
      }
  
      return gpcx.getName();
  }
  ```

- ͳ�Ƹ��ﳲ���и��������߼�

  ```java
  /**
   * ��ȡ���ﳲ���и��ӵ�����
   */
  @GetMapping("pigeon/{gpcxId}")
  public Result getPigeonNumber(@PathVariable Long gpcxId, HttpServletRequest request) {
      //���Ȩ��
      check(gpcxId, request);
      //ִ�в�ѯ
      //���� gpcxId = gpcxId
      QueryWrapper<PigeonGpcx> wrapper = new QueryWrapper<>();
      wrapper.eq("gpcx_id", gpcxId);
      //ִ�м���
      long count = pigeonGpcxService.count(wrapper);
      return new Result(count, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ��Ӹ���ҵ�񣨼�¼��־�����߼��� **2.2.9.10 ������־����**��

  �߼�˼·����������������¼ԭ���ﳲ�䣬����ΪĿ����ﳲ�䣬������û����������������

  ```java
  @Override
  @Transactional
  public int addPigeonToGpcx(List<Long> ids, Long gpcxId, String name, String account, Long gid) {
  
      AtomicReference<Integer> updateNumber = new AtomicReference<>(0);
      //��ȡ����ʱ��
      LocalDate now = LocalDate.now();
  
      ids.forEach(id -> {
          UpdateWrapper<PigeonGpcx> updateWrapper = new UpdateWrapper<>();
          updateWrapper.eq("pid", id).set("gpcx_id", gpcxId);
          //����
          int update = pigeonGpcxMapper.update(null, updateWrapper);
  
          if (!SqlHelper.retBool(update)) {
              //����
              PigeonGpcx pigeonGpcx = new PigeonGpcx();
              pigeonGpcx.setPid(id);
              pigeonGpcx.setGpcxId(gpcxId);
              int insert = pigeonGpcxMapper.insert(pigeonGpcx);
  
              if (!SqlHelper.retBool(insert)) {
                  throw new SaveFailException("����ʱ��������");
              }
          } else {
              //��¼������
              updateNumber.getAndSet(updateNumber.get() + 1);
          }
  
          oplogService.oplog(account, id, gid, name, LogType.TRANSFER);
      });
  
      //���¸�����Ϣ
      //���� id = ids ���� ���� ʱ�� �� ������
      UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
      wrapper.in("id", ids)
              .set("gpcx", name)
              .set("update_data", now);
      int update = pigeonMapper.update(null, wrapper);
      if (!SqlHelper.retBool(update) || update != ids.size()) {
          throw new SaveFailException("���¸�����Ϣʧ�ܣ�������");
      }
  
      return updateNumber.get();
  }
  ```

- ��ɾ�Ĳ�ҵ�񣨴������ƣ�

##### 2.2.9.8 ������Ϣ����

�о���Ҫ����

- ��ҳ��ȡ������Ϣ

  ʹ�÷�ҳ���ʵ��

  ```java
  //��ȡgid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long gid = (Long) info.get("gid");
  
  //��ȡҳ����Ϣ
  Long current = pageInfo.get("current");
  Long pageSize = pageInfo.get("pageSize");
  
  //���
  if (current == null || pageSize == null) {
      throw new NotFoundException("��ҳ��Ϣ����");
  }
  
  //���÷�ҳ����
  Page<Pigeon> page = new Page<>();
  page.setCurrent(current)
          .setSize(pageSize)
          .setSearchCount(false);
  
  //���� gid = gid
  QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
  wrapper.eq("gid", gid);
  
  page = pigeonService.page(page, wrapper);
  List<Pigeon> records = page.getRecords();
  ```

- ��������ҵ��

  ��ҵ���¼����Ϣ��Ϊ���ݣ�������ǿ�����´���Ȧ���ӶȽϸ�

  �߼�˼·��

  1. ��ȡ��ǰ���ڡ�
  2. ���ø������ݵĸ���ʱ��Ϊ��ǰ���ڡ�
  3. ���������ݵ�`id`�ֶ��Ƿ�Ϊ�գ����Ϊ�����ʾ�ü�¼�����ڣ�ִ������������
  4. ����������ݵ�`id`�ֶβ�Ϊ�գ���ʾ��¼�Ѵ��ڣ�ִ�и��²�����
  5. �����������У������Ӷ�����Ϣ�Ĵ���ʱ�䡢`gid`��`pid`�ֶΣ�Ȼ��ֱ�ִ�л�����Ϣ�Ͷ�����Ϣ�Ĳ��������
  6. �ڸ��²����У����ݸ�����`gid`��`id`������ѯ������Ȼ��ִ�л�����Ϣ�ĸ��²�����
  7. ͬ������`pid`������ѯ������ִ�ж�����Ϣ�ĸ��²�����
  8. ��¼��Ӧ��������־�������˺���Ϣ�����ӵĻ��š�`pid`��`gid`�Ͳ������͡�
  9. �������ָ�����Ӵ����ӵ�`oid`��������丸���ֶ�Ϊ��ǰ���ӵ�`pid`�����ݸ��ӵ��Ա�����Ǹ���`fid`����`mid`�ֶΡ�
  10. �ٴμ�¼�����Ӵ�������ϵ����־�������˺���Ϣ���Ӵ����ӵĻ��š�`gid`����ǰ���ӵĻ��źͲ������͡�
  11. ���ظ������ݵ�`pid`��

- ɾ��������ɾ������ҵ�񣨼�¼��־�����߼��� **2.2.9.10 ������־����**��

  �߼�˼·��

  1. ʹ��`id`��`gid`����������ѯ����`pigeonQueryWrapper`��Ȼ��ͨ��`selectOne`������ȡ���������ĸ�������`pigeon`���Ա�������¼��־ʹ�á�
  2. ʹ��`pigeonQueryWrapper`ɾ��������Ϣ���������ݿ���ɾ�����������ĸ������ݡ����ɾ��ʧ�ܣ����׳�`DeleteFailException`�쳣��
  3. ʹ��`id`����������ѯ����`pigeonInfoQueryWrapper`��Ȼ��ͨ��`delete`����ɾ��������Ϣ����ɾ����ø������ݹ����Ķ�����Ϣ�����ɾ��ʧ�ܣ����׳�`DeleteFailException`�쳣��
  4. ��¼ɾ����������־������`oplogService.oplog`������¼������־�������˺���Ϣ�����ӵĻ��š�`gid`�Ͳ������͡�
  5. �����Ӵ��ĸ�����Ϣ�����ȸ���`gid`���Ա�������"��"��"��"��������ѯ����`queryWrapper`��Ȼ��ʹ��`selectList`������ȡ���������������Ӵ���������`offspringPigeons`��
  6. ��ȡ��ǰ���ڡ�
  7. ����ÿ���Ӵ��������ݣ�ͨ��`UpdateWrapper`������������`updateWrapper`�������Ӵ����ӵ��Ա����ö�Ӧ�ĸ����ֶ�Ϊ`null`��������`update_data`Ϊ��ǰ���ڡ�
  8. ʹ��`pigeonMapper`��`update`����ִ�и��²������������ʧ�ܣ����׳�`SaveFailException`�쳣��
  9. ��¼���Ѫ�׹�ϵ����־������`oplogService.oplog`������¼������־�������˺���Ϣ���Ӵ����ӵĻ��š�`gid`�Ͳ������͡�

  ```java
  @Override
  @Transactional
  public void deletePigeonById(Long id, String sex, Long gid, String account) {
      //���� id AND gid
      QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
      pigeonQueryWrapper.eq("id", id).eq("gid", gid);
  
      //��ȡ��Ϣ�Լ�¼��־
      Pigeon pigeon = pigeonMapper.selectOne(pigeonQueryWrapper);
  
      //ɾ��������Ϣ
      int deletePigeon = pigeonMapper.delete(pigeonQueryWrapper);
  
      if (!SqlHelper.retBool(deletePigeon)) {
          throw new DeleteFailException("������Ϣɾ��ʧ�ܣ�������");
      }
  
      //���� pid = id
      QueryWrapper<PigeonInfo> pigeonInfoQueryWrapper = new QueryWrapper<>();
      pigeonInfoQueryWrapper.eq("pid", id);
  
      //ɾ��������Ϣ
      int deletePigeonInfo = pigeonInfoMapper.delete(pigeonInfoQueryWrapper);
  
      if (!SqlHelper.retBool(deletePigeonInfo)) {
          log.error("id = {} ʱ��������Ϣɾ��ʧ��", id);
          throw new DeleteFailException("������Ϣɾ��ʧ�ܣ�������");
      }
  
      //��¼��־
      oplogService.oplog(account, pigeon.getRingNumber(), gid, LogType.DELETE);
  
      //�����Ӵ��ĸ�����Ϣ
      //��ΪҪ��¼��־�����ȼ�������Ҫ���µ�����
      QueryWrapper<Pigeon> queryWrapper = new QueryWrapper<>();
      //����
      queryWrapper.eq("gid", gid)
              .eq(sex.equals("��"), "mid", id)
              .eq(sex.equals("��"), "fid", id);
      List<Pigeon> offspringPigeons = pigeonMapper.selectList(queryWrapper);
  
      //��ȡ����ʱ��
      LocalDate now = LocalDate.now();
  
      //����
      offspringPigeons.forEach(offspringPigeon -> {
          UpdateWrapper<Pigeon> updateWrapper = new UpdateWrapper<>();
          updateWrapper.eq("id", offspringPigeon.getId())
                  .set("update_data", now)
                  .set(sex.equals("��"), "fid", null)
                  .set(sex.equals("��"), "mid", null);
  
          int update = pigeonMapper.update(null, updateWrapper);
  
          if (!SqlHelper.retBool(update)) {
              throw new SaveFailException("���Ѫ�׹�ϵ����");
          }
  
          //��¼��־
          oplogService.oplog(account, offspringPigeon.getRingNumber(), offspringPigeon.getId(), gid, LogType.UNPARENT);
      });
  }
  ```

- ����Ѫͳҵ�񣨼�¼��־�����߼��� **2.2.9.10 ������־����**��

  �߼�˼·��

  1. ���ݸ�����`ids`��`gid`������ʹ��`QueryWrapper`������ѯ��������`Pigeon`���л�ȡ�������������ݡ�
  2. ��鷵�ص����������Ƿ���`ids`����һ�£������һ�����׳�`NotFoundException`�쳣��
  3. ����һ���յ�`pigeonInfos`�б����ڴ洢������Ϣ��
  4. ����ÿ���������ݣ����ݸ��ӵ�`id`��ѯ��Ӧ�Ķ�����Ϣ�������Ϣ���������׳�`NotFoundException`�쳣��������Ϣ��ӵ�`pigeonInfos`�б��С�
  5. ��ȡ��ǰ���ڡ�
  6. ����ÿ���������ݣ��������ݴ���Ͳ��������
  7. ����ÿ���������ݣ��ȼ�¼�����ߵ���־��
  8. ���ڸ������ݣ����`id`��`fid`��`mid`�ֶΣ��޸�`updateData`Ϊ��ǰ���ڣ���`gid`����Ϊ���շ���`receiveGid`��Ȼ����뵽���ݿ��У��������ʧ�����׳�`SaveFailException`�쳣��
  9. ���ڸ��Ӷ�����Ϣ�����`id`�ֶΣ��޸�`createTime`Ϊ��ǰ���ڣ���`pid`����Ϊ�ղ���ĸ������ݵ�`id`��Ȼ����뵽���ݿ��У��������ʧ�����׳�`SaveFailException`�쳣��
  10. ����¼�����ߵ���־��

  ```java
  @Override
  @Transactional
  public void sharePigeon(List<Long> ids, Long receiveGid, Long gid, String account) {
      //����ids��ȡ��������
      //���� gid and id in ids
      QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
      pigeonWrapper.eq("gid", gid).in("id", ids);
      List<Pigeon> pigeons = pigeonMapper.selectList(pigeonWrapper);
  
      //������ݸ����Ƿ�׼ȷ
      int n = ids.size();
      if (n != pigeons.size()) {
          throw new NotFoundException("���ݻ�ȡ����");
      }
  
      //ͬʱ��ȡ������Ϣ��һһ��Ӧ
      List<PigeonInfo> pigeonInfos = new ArrayList<>();
      pigeons.forEach(pigeon -> {
          QueryWrapper<PigeonInfo> pigeonInfoWrapper = new QueryWrapper<>();
          pigeonInfoWrapper.eq("pid", pigeon.getId());
          PigeonInfo pigeonInfo = pigeonInfoMapper.selectOne(pigeonInfoWrapper);
          //���
          if (pigeonInfo == null) {
              throw new NotFoundException("���ݻ�ȡ����");
          }
          pigeonInfos.add(pigeonInfo);
      });
  
      //��ȡ����
      LocalDate now = LocalDate.now();
  
      //�������ݣ�������
      for (int i = 0; i < n; i++) {
          Pigeon pigeon = pigeons.get(i);
          //�����߼�¼��־
          oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), gid, LogType.SHARE);
  
          //pigeon
          //��� id, fid, mid
          //�޸� update_data, gid
          //���
          pigeon.setId(null);
          pigeon.setFid(null);
          pigeon.setMid(null);
          //�޸�
          pigeon.setUpdateData(now);
          pigeon.setGid(receiveGid);
          //����
          int insertPigeon = pigeonMapper.insert(pigeon);
          //���
          if (!SqlHelper.retBool(insertPigeon)) {
              throw new SaveFailException("���������Ϣʧ��");
          }
  
          //pigeonInfo
          //��� id
          //�޸� create_time, pid
          PigeonInfo pigeonInfo = pigeonInfos.get(i);
          //���
          pigeonInfo.setId(null);
          //�޸�
          pigeonInfo.setCreateTime(now);
          pigeonInfo.setPid(pigeon.getId());
          //����
          int insertPigeonInfo = pigeonInfoMapper.insert(pigeonInfo);
          //���
          if (!SqlHelper.retBool(insertPigeonInfo)) {
              throw new SaveFailException("���������Ϣʧ��");
          }
  
          //�����߼�¼��־
          oplogService.oplog(account, pigeon.getRingNumber(), pigeon.getId(), receiveGid, LogType.RECEIVE);
      }
  }
  ```

  

- ����Ѫ��ҵ�񣨼�¼��־�����߼��� **2.2.9.10 ������־����**��

  �����Ӵ��͸��������Ӵ���Ϣ��Ϊ����id

  ���˼·��

  1. ����������ͨ��`oid`��`gid`ɸѡ��Ҫ���µĸ��Ӽ�¼��
  2. ʹ������������(`UpdateWrapper`)���ø��µ��ֶκ�ֵ��������������(`update_data`)������(`fid`)��ĸ��(`mid`)�ֶε�ֵ��
  3. ִ�и��²�����������Ӧ�õ����������ĸ��Ӽ�¼��
  4. �������Ƿ�ɹ����������ʧ�ܣ����¼������־���׳�����ʧ�ܵ��쳣��
  5. ��¼������������־�������˺���Ϣ���Ӵ����ӵ�`oid`��`gid`�Լ���صĻ��š�

  ```java
  @Override
  @Transactional
  public void relatePigeon(Long id, String sex, String ringNumber, Long oid, Long gid, String account) {
      //��ȡ��������
      LocalDate now = LocalDate.now();
  
      //����
      UpdateWrapper<Pigeon> wrapper = new UpdateWrapper<>();
      wrapper.eq("id", oid)
              .eq("gid", gid)
              .set("update_data", now)
              .set(sex.equals("��"), "fid", id)
              .set(sex.equals("��"), "mid", id);
  
      int update = pigeonMapper.update(null, wrapper);
  
      if (!SqlHelper.retBool(update)) {
          log.error("���� id = {}, oid = {} ʱ��������", id, oid);
          throw new SaveFailException("����ʧ��");
      }
  
      //��¼��־
      oplogService.oplog(account, oid, gid, ringNumber, LogType.RELATE);
  }
  ```

- �����ļ��������ҵ��

  - �ļ�У��

    ```java
    if (Objects.isNull(file) || file.isEmpty()) {
        throw new FileSaveException("�����ϴ����ļ�");
    }
    
    //����ļ���ʽ
    String fileType = fileUtil.checkFileType(file, "application/vnd.ms-excel", "application/x-tika-ooxml", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    
    ```

  - Ϊ�ļ������ṩУ������

    ```java
    //��ȡ��������
    //������� gid or gid = systemGid
    QueryWrapper<Xxpz> wrapper = new QueryWrapper<>();
    wrapper.eq("gid", gid).or().eq("gid", systemGid);
    //�ֶ�
    wrapper.select("name", "type");
    //��ѯ
    List<Xxpz> list = xxpzService.list(wrapper);
    
    //��װ��������
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

  - ���ְ汾�����Ӧ��

    ```java
    List<Map<String, PigeonWrapperVo>> pigeonWrappers;
    
    if (fileType.equals("application/vnd.ms-excel")) {
        //�ɰ�
        pigeonWrappers = pigeonFileUtil.getPigeonByXls(file, xxpzMap);
    } else {
        //�°�
        pigeonWrappers = pigeonFileUtil.getPigeonByXlsx(file, xxpzMap);
    }
    
    if (pigeonWrappers == null) {
        throw new FileParseException("����ʧ��");
    }
    ```

  - PigeonFileUtil �ļ���������

    - �ļ�����

      ```java
      /**
       * �ɰ�.xls��������
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
              log.error("����ļ� {} ʱ�ļ�����������", file, e);
              throw new FileSaveException("�ļ�����ʧ��");
          }
      
          return pigeonWrappers;
      }
      
      /**
       * �°�.xlsx��������
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
              log.error("����ļ� {} ʱ�ļ�����������", file, e);
              throw new FileSaveException("�ļ�����ʧ��");
          }
      
          return pigeonWrappers;
      }
      ```

    - ������񣨾�����

      ```java
      /**
       * ��ȡ������
       * ��������
       * �̶��У�8
       */
      private String getDetail(Row row) {
          Cell cell = row.getCell(8);
          if (cell == null || cell.getCellType() != CellType.STRING) {
              return null;
          }
          return cell.getStringCellValue();
      }
      
      /**
       * ��ȡ������
       * ��ɫ
       * �̶��У�6
       */
      private String getYs(Row row, Map<String, Set<String>> xxpzMap) {
          Cell cell = row.getCell(6);
          if (cell == null || cell.getCellType() != CellType.STRING) {
              return null;
          }
          String ys = cell.getStringCellValue();
          //����
          if (!xxpzMap.get("yspz").contains(ys)) {
              throw new FileParseException("��ɫ�쳣");
          }
          return ys;
      }
      
      /**
       * ��ȡ������
       * �㻷��
       * �̶��У�0
       */
      private String getRingNumber(Row row, Map<String, Set<String>> xxpzMap) {
          Cell cell = row.getCell(0);
          if (cell == null || cell.getCellType() != CellType.STRING) {
              throw new FileParseException("�㻷���쳣");
          }
          String ringNumber = cell.getStringCellValue();
          //����
          verifyRingNumber(ringNumber, xxpzMap);
          return ringNumber;
      }
      ```

    - ������������ʹ�õ���У��

      ```java
      /**
       * �����㻷�淶
       */
      private void verifyRingNumber(String ringNumber, Map<String, Set<String>> xxpzMap) {
          //����
          String[] ringNumberSplit = ringNumber.split("-");
          Set<String> country = xxpzMap.get("country");
          Set<String> province = xxpzMap.get("province");
          //����
          if (!country.contains(ringNumberSplit[0])) {
              throw new FileParseException("�㻷���󣺲����ڵĹ��Ҵ���");
          }
          //���
          if (ringNumberSplit[1].length() != 4) {
              throw new FileParseException("�㻷���󣺴�������");
          }
          //����
          if (!province.contains(ringNumberSplit[2])) {
              throw new FileParseException("�㻷���󣺲����ڵ�ʡ�ݴ���");
          }
      }
      ```

  - ����ļ���������Ϣ�������ݿ⣨��¼��־�����߼��� **2.2.9.10 ������־����**��

    ���˼·��

    1. ��ȡ��ǰ���ڡ�
    2. ��ÿ�������Ӵ���Ϣ���б��������
    3. ����ÿ�����������ȸ����㻷���Ա��`gid`���м������ж��Ƿ��Ѵ��ڶ�Ӧ�ĸ��Ӽ�¼��
    4. ������Ӽ�¼�����ڣ���ִ���������������ø������ӵĸ���ʱ�䡢`gid`����Ϣ�����ֱ�ִ�л�����Ϣ�Ͷ�����Ϣ�Ĳ��������
    5. ������Ӽ�¼�Ѵ��ڣ���ֱ�ӻ�ȡ��`id`��Ϊ�Ӵ����ӵĸ���`fid`��
    6. ����ÿ��ĸ������ȡ���ƵĲ������жϸ��Ӽ�¼�Ƿ��Ѵ��ڣ������������ִ����������������������ȡ��`id`��Ϊ�Ӵ����ӵ�ĸ��`mid`��
    7. ����ÿ���Ӵ����ӣ����������ʱ�䡢`gid`����Ϣ�����ֱ�ִ�л�����Ϣ�Ͷ�����Ϣ�Ĳ��������
    8. ��¼��ز�������־�������˺���Ϣ�����ӵĻ��š�`id`��`gid`�Ͳ������͡�
    9. ������ڸ�������¼�Ӵ��븸���Ĺ�ϵ����־�������˺���Ϣ���Ӵ����ӵĻ��š�`id`��`gid`�Լ��������ӵĻ��š�
    10. �������ĸ������¼�Ӵ���ĸ���Ĺ�ϵ����־�������˺���Ϣ���Ӵ����ӵĻ��š�`id`��`gid`�Լ�ĸ�����ӵĻ��š�

    ```java
    @Override
    @Transactional
    public void savePigeonByFile(List<Map<String, PigeonWrapperVo>> pigeonWrappers, Long gid, String account) {
        //��ȡʱ��
        LocalDate now = LocalDate.now();
    
        //���游��
        pigeonWrappers.forEach(pigeonMap -> {
            PigeonWrapperVo pigeonWrapperVo = pigeonMap.get("pigeon");
            //��
            PigeonWrapperVo father = pigeonMap.get("father");
            if (father != null) {
                //��fatherPigeon
                Pigeon fatherPigeon = father.getPigeon();
    
                //�ȸ����㻷�����Ƿ����
                QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
                wrapper.eq("ring_number", fatherPigeon.getRingNumber())
                        .eq("sex", "��")
                        .eq("gid", gid);
                Pigeon selectPigeon = pigeonMapper.selectOne(wrapper);
    
                if (selectPigeon == null) {
                    //����
                    fatherPigeon.setUpdateData(now);
                    fatherPigeon.setGid(gid);
                    int fatherPigeonInsert = pigeonMapper.insert(fatherPigeon);
                    if (!SqlHelper.retBool(fatherPigeonInsert)) {
                        throw new SaveFailException("���������Ϣ����ʧ��");
                    }
    
                    //��fatherPigeonInfo
                    PigeonInfo fatherPigeonInfo = new PigeonInfo();
                    fatherPigeonInfo.setCreateTime(now);
                    fatherPigeonInfo.setPid(fatherPigeon.getId());
                    int fatherPigeonInfoInsert = pigeonInfoMapper.insert(fatherPigeonInfo);
                    if (!SqlHelper.retBool(fatherPigeonInfoInsert)) {
                        throw new SaveFailException("���������Ϣ����ʧ��");
                    }
    
                    //���Ӵ�װ��fid
                    pigeonWrapperVo.getPigeon().setFid(fatherPigeon.getId());
    
                    //��¼��־
                    oplogService.oplog(account, fatherPigeon.getRingNumber(), fatherPigeon.getId(), gid, LogType.INSERT);
                } else {
                    //���Ӵ�װ��fid
                    pigeonWrapperVo.getPigeon().setFid(selectPigeon.getId());
                }
            }
    
            //ĸ
            PigeonWrapperVo mother = pigeonMap.get("mother");
            if (mother != null) {
                //ĸmotherPigeon
                Pigeon motherPigeon = mother.getPigeon();
    
                //�ȸ����㻷�����Ƿ����
                QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
                wrapper.eq("ring_number", motherPigeon.getRingNumber())
                        .eq("sex", "��")
                        .eq("gid", gid);
                Pigeon selectPigeon = pigeonMapper.selectOne(wrapper);
    
                if (selectPigeon == null) {
                    //����
                    motherPigeon.setUpdateData(now);
                    motherPigeon.setGid(gid);
                    int fatherPigeonInsert = pigeonMapper.insert(motherPigeon);
                    if (!SqlHelper.retBool(fatherPigeonInsert)) {
                        throw new SaveFailException("ĸ�������Ϣ����ʧ��");
                    }
                    //ĸmotherPigeonInfo
                    PigeonInfo motherPigeonInfo = new PigeonInfo();
                    motherPigeonInfo.setCreateTime(now);
                    motherPigeonInfo.setPid(motherPigeon.getId());
                    int motherPigeonInfoInsert = pigeonInfoMapper.insert(motherPigeonInfo);
                    if (!SqlHelper.retBool(motherPigeonInfoInsert)) {
                        throw new SaveFailException("ĸ�������Ϣ����ʧ��");
                    }
                    //���Ӵ�װ��mid
                    pigeonWrapperVo.getPigeon().setMid(motherPigeon.getId());
    
                    //��¼��־
                    oplogService.oplog(account, motherPigeon.getRingNumber(), motherPigeon.getId(), gid, LogType.INSERT);
                } else {
                    //���Ӵ�װ��mid
                    pigeonWrapperVo.getPigeon().setMid(selectPigeon.getId());
                }
            }
    
            //�����Ӵ���Ϣ
            //pigeon
            Pigeon pigeon = pigeonWrapperVo.getPigeon();
            pigeon.setUpdateData(now);
            pigeon.setGid(gid);
            int pigeonInsert = pigeonMapper.insert(pigeon);
            if (!SqlHelper.retBool(pigeonInsert)) {
                throw new SaveFailException("�Ӵ��������Ϣ����ʧ��");
            }
            //pigeonInfo
            PigeonInfo pigeonInfo = pigeonWrapperVo.getPigeonInfo();
            pigeonInfo.setCreateTime(now);
            pigeonInfo.setPid(pigeon.getId());
            int pigeonInfoInsert = pigeonInfoMapper.insert(pigeonInfo);
            if (!SqlHelper.retBool(pigeonInfoInsert)) {
                throw new SaveFailException("�Ӵ��������Ϣ����ʧ��");
            }
    
            //��¼��־
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

- ���غ���ļ�ģ��

  ```java
  /**
   * ��ȡģ�巵�ظ�ǰ��
   */
  @GetMapping("template")
  public void getFileTemplate(HttpServletResponse response) {
      // ������Ӧ���ĵ�MIME����
      response.setContentType("application/vnd.ms-excel");
      //�����ļ�
      fileUtil.responseFileByRelativePath(pigeonTemplatePath, response);
  }
  ```

##### 2.2.9.9 ����������

- ģ����ѯ��Ϊ��������ṩ����

  ```java
  //��ȡgid
  Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
  Long gid = (Long) info.get("gid");
  
  //���� gid and sex and ring like value
  QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
  wrapper.eq("gid", gid)
          .eq(sex.equals("father"), "sex", "��")
          .eq(sex.equals("mother"), "sex", "��")
          .like("ring_number", value);
  
  //�ֶ�
  wrapper.select("id", "ring_number");
  
  List<Pigeon> list = pigeonService.list(wrapper);
  ```

- �������

  - ����ǰ����Ϣ

    ```java
    //����������
    Long fid = map.get("fid") == null ? null : Long.valueOf((String) map.get("fid"));
    Long mid = map.get("mid") == null ? null : Long.valueOf((String) map.get("mid"));
    List<Map<String, Object>> pigeonMaps = (List<Map<String, Object>>) map.get("pigeons");
    
    String fr = null;
    String mr = null;
    //��ȡ�����㻷
    if (fid != null) {
        Pigeon fp = pigeonService.getById(fid);
        fr = fp.getRingNumber();
    }
    if (mid != null) {
        Pigeon mp = pigeonService.getById(mid);
        mr = mp.getRingNumber();
    }
    
    //���
    if (fr == null && mr == null) {
        throw new SaveFailException("��Ϣ������");
    }
    
    //��ȡgid
    Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
    Long gid = (Long) info.get("gid");
    String account = (String) info.get("account");
    
    //��ȡ����
    LocalDate now = LocalDate.now();
    
    //װ��
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

  - ���

    ���˼·��

    1. ʹ��ѭ������`pigeons`�б��������ÿ�����Ӷ���
    2. ����ÿ�����Ӷ���ִ�в�������������ӻ�����Ϣ���浽���ݿ��С�
    3. ����������Ľ�����������ʧ�ܣ����׳�����ʧ�ܵ��쳣��
    4. ��ȡ�����ĸ��Ӽ�¼��ID�ͻ��š�
    5. �����ӵ�ID��ֵ����Ӧ�ĸ��Ӷ�����Ϣ(`pigeonInfos`)�е�`pid`�ֶΡ�
    6. ��¼���ӻ�����Ϣ�Ĳ�����־�������˺���Ϣ�����š�ID��GID�Ͳ�������ΪINSERT��
    7. ������ڸ�������(`fatherRingNumber`)����¼�븸���Ĺ�����־��
    8. �������ĸ������(`motherRingNumber`)����¼��ĸ���Ĺ�����־��
    9. ����ÿ�����Ӷ�����Ϣ(`pigeonInfo`)��ִ�в����������������Ϣ���浽���ݿ��С�
    10. ����������Ľ�����������ʧ�ܣ����׳�����ʧ�ܵ��쳣��

    ```java
    @Override
    @Transactional
    public void rapidBatchAddPigeon(List<Pigeon> pigeons, List<PigeonInfo> pigeonInfos, Long gid, String account, String fatherRingNumber, String motherRingNumber) {
    
        for (int i = 0, pigeonsSize = pigeons.size(); i < pigeonsSize; i++) {
            Pigeon pigeon = pigeons.get(i);
    
            //����
            int insert = pigeonMapper.insert(pigeon);
    
            //���
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("������ӻ�����Ϣʧ��");
            }
    
            Long id = pigeon.getId();
            String ringNumber = pigeon.getRingNumber();
    
            //װ��pid
            pigeonInfos.get(i).setPid(id);
    
            //��¼��־
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
    
            //���
            if (!SqlHelper.retBool(insert)) {
                throw new SaveFailException("������Ӷ�����Ϣʧ��");
            }
        });
    }
    ```

##### 2.2.9.10 ������־����

- ����ڲ���־������������

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
          log.error("{} ��־��¼ʧ��", oplog);
          throw new LogException("��־����ʧ��");
      }
  }
  
  @Override
  @Transactional
  public void oplog(String account, String ringNumber, Long gid, LogType type) {
      if (type != LogType.DELETE) {
          throw new LogException("��־���ʹ���");
      }
      Oplog oplog = new Oplog(
              account, ringNumber, type.getIndex(),
              type.getTip(), LocalDateTime.now(), gid
      );
      int insert = oplogMapper.insert(oplog);
      if (!SqlHelper.retBool(insert)) {
          log.error("{} ��־��¼ʧ��", oplog);
          throw new LogException("��־����ʧ��");
      }
  }
  ```

- ������ҳ��ѯ

  ���˼·��

  1. �����������л�ȡ`info`�������ò�����һ��Map���󣬰�����һ����Ϊ`gid`��Long����ֵ����ʾ�����ID��
  2. �����������е���������������`content`��`tip`��`ringNumber`��`timeRange`��`author`��`current`��`pageSize`�ȡ�
  3. ���`current`��`pageSize`�Ƿ�Ϊ�գ����Ϊ�գ����׳��쳣��
  4. ����ʱ�䷶Χ����`timeRange`����ȡ��ʼʱ��`startTime`�ͽ���ʱ��`endTime`��
  5. ������ѯ����`QueryWrapper`����`wrapper`�����ø�����ѯ��������������ID�����ݡ���ʾ�����š����ߺ�ʱ�䷶Χ�ȡ�
  6. ������ҳ����`Page`�����õ�ǰҳ��`current`��ÿҳ����`pageSize`���������`maxLimit`��
  7. ����`oplogService`��`page`����ִ�з�ҳ��ѯ�������ҳ����Ͳ�ѯ��������
  8. ��ȡ��ѯ����ķ�ҳ����`page`��

  ```java
  /**
   * ���ݷ�ҳ������ȡҳ����Ϣ
   */
  @PostMapping
  public Result getConditionPage(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") PagingConditionVo condition, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //����������
      Integer content = condition.getContent();
      String tip = condition.getTip();
      String ringNumber = condition.getRingNumber();
      List<LocalDateTime> timeRange = condition.getTimeRange();
      String author = condition.getAuthor();
      Integer current = condition.getCurrent();
      Integer pageSize = condition.getPageSize();
  
      if (current == null || pageSize == null) {
          log.error("PagingCondition {} ȱ�ٱ�Ҫ����", condition);
          throw new NotFoundException("�������");
      }
  
      LocalDateTime startTime = null;
      LocalDateTime endTime = null;
      if (timeRange != null && timeRange.size() == 2) {
          startTime = timeRange.get(0);
          endTime = timeRange.get(1);
      }
  
      //����
      QueryWrapper<Oplog> wrapper = new QueryWrapper<>();
      wrapper.eq(content != null, "content", content)
              .like(tip != null, "tip", tip)
              .like(ringNumber != null, "ring_number", ringNumber)
              .like(author != null, "author", author)
              .between(startTime != null && endTime != null, "time", startTime, endTime)
              .orderByDesc("time")
              .eq("gid", gid);
  
      //��ҳ
      Page<Oplog> page = new Page<>();
      page.setCurrent(current)
              .setSize(pageSize)
              .setMaxLimit(maxLimit);
  
  
      page = oplogService.page(page, wrapper);
  
      return new Result(page, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

##### 2.2.9.12 ������ϸ��Ϣ����

�˷����ṩ��ϸҳ�����Ϣ���ݿ��ѯ������

- ��ѯ�Ӵ�

  ���˼·��

  1. �����������л�ȡ`info`�������ò�����һ��Map���󣬰�����һ����Ϊ`gid`��Long����ֵ����ʾ�����ID��
  2. ����һ���յ�`pigeonOffspringList`�����ڴ洢ÿһ������������б�
  3. ����һ����ʼ���ĸ����б�����ֻ����һ�����ȸ��ӣ�������ӵ�`pigeonOffspringList`�С�
  4. ���ó�ʼ����`generation`Ϊ0��
  5. �������ѭ����������С��������`limit`�ҵ�ǰ���ĸ����б�Ϊ��ʱ���е�����
  6. ��ȡ��ǰ���ĸ����б�`pigeonList`��
  7. ����һ���յ���һ�������б�`nextPigeonList`��
  8. ������ǰ���ĸ����б�����ÿֻ���ӣ���ȡ��ID��Ϊ����ID��
  9. ������ѯ������Ҫ��`gid`����ָ���ĸ���ID����������ID���ڵ�ǰ���ӵ�ID�����������Ӵ����ӣ���
  10. ʹ�ò�ѯ������ѯ�����б����������ӵ���һ�������б�`nextPigeonList`�С�
  11. ����һ�������б�`nextPigeonList`��ӵ�`pigeonOffspringList`�С�
  12. ����`generation`������
  13. ѭ���ص�����5������������һ�����ӣ�ֱ���ﵽ������`limit`���ߵ�ǰ���ĸ����б�Ϊ�ա�
  14. ����������`pigeonOffspringList`�д洢��ÿһ������������б�

  ```java
  /**
   * ����id��ѯ�Ӵ��б�
   */
  @GetMapping("offspring/{id}")
  public Result getOffspringList(@PathVariable Long id, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      List<ArrayList<Pigeon>> pigeonOffspringList = new ArrayList<>();
  
      //װ������
      {
          Pigeon pigeon = new Pigeon();
          pigeon.setId(id);
          ArrayList<Pigeon> pigeonList = new ArrayList<>();
          pigeonList.add(pigeon);
          pigeonOffspringList.add(pigeonList);
      }
      int generation = 0;
  
      //����
      while (generation < limit && pigeonOffspringList.get(generation).size() > 0) {
          ArrayList<Pigeon> pigeonList = pigeonOffspringList.get(generation);
          ArrayList<Pigeon> nextPigeonList = new ArrayList<>();
  
          pigeonList.forEach(pigeon -> {
              Long tid = pigeon.getId();
              //���� gid = gid and (mid = id or fid = id)
              LambdaQueryWrapper<Pigeon> wrapper = new LambdaQueryWrapper<Pigeon>()
                      .eq(Pigeon::getGid, gid)
                      .and(w -> w.eq(Pigeon::getMid, tid).or().eq(Pigeon::getFid, tid));
              List<Pigeon> list = pigeonService.list(wrapper);
              nextPigeonList.addAll(list);
          });
  
          pigeonOffspringList.add(nextPigeonList);
          generation++;
      }
  
      return new Result(pigeonOffspringList, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

  

- ����ͬ��

  ���˼·��

  1. �����������л�ȡ`info`�������ò�����һ��Map���󣬰�����һ����Ϊ`gid`��Long����ֵ����ʾ�����ID��
  2. ������ѯ������Ҫ��`gid`����ָ���ĸ���ID������`id`����ָ���ĸ���ID��
  3. ����Ҫ��ѯ���ֶ�Ϊ`fid`��`mid`��
  4. ʹ�ò�ѯ������ѯ������Ϣ����ȡ���������ĵ������Ӷ���
  5. ���û���ҵ����������ĸ��Ӷ����򷵻ز�ѯ�ɹ��Ľ����
  6. �Ӳ�ѯ����л�ȡ����ID`fid`��ĸ��ID`mid`��
  7. ����һ���յ�`pigeonMap`�����ڴ洢��ͬ���͵���Ե��ϵ�����б�
  8. ������ڸ���ID`fid`�������ͬ����ĸ��ϵ�Ĳ�ѯ��������ѯ����Ҫ��`fid`����ָ���ĸ���ID������`id`������ָ���ĸ���ID��`gid`����ָ���ĸ���ID��ͬʱ�������ĸ��ID`mid`��Ҫ��`mid`������ָ����ĸ��ID��ִ�в�ѯ����������洢��`pigeonMap`��"fatherHalf"���¡�
  9. �������ĸ��ID`mid`�������ͬĸ�츸��ϵ�Ĳ�ѯ��������ѯ����Ҫ��`mid`����ָ����ĸ��ID������`id`������ָ���ĸ���ID��`gid`����ָ���ĸ���ID��ͬʱ������ڸ���ID`fid`��Ҫ��`fid`������ָ���ĸ���ID��ִ�в�ѯ����������洢��`pigeonMap`��"motherHalf"���¡�
  10. ���ͬʱ���ڸ���ID`fid`��ĸ��ID`mid`�������ͬ��ͬĸ��ϵ�Ĳ�ѯ��������ѯ����Ҫ��`fid`����ָ���ĸ���ID��`mid`����ָ����ĸ��ID������`id`������ָ���ĸ���ID��`gid`����ָ���ĸ���ID��ִ�в�ѯ����������洢��`pigeonMap`��"full"���¡�
  11. ���գ�`pigeonMap`�д洢�˲�ͬ���͵���Ե��ϵ�����б�

  ```java
  /**
   * ����id����ͬ��
   */
  @GetMapping("peer/{id}")
  public Result getPeerList(@PathVariable Long id, HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //�����Լ���ȡfid��mid
      //���� id and gid
      QueryWrapper<Pigeon> wrapper = new QueryWrapper<>();
      wrapper.eq("gid", gid).eq("id", id);
  
      //�ֶ�
      wrapper.select("fid", "mid");
  
      Pigeon pigeon = pigeonService.getOne(wrapper);
  
      //û���ҵ�����
      if (pigeon == null) {
          return new Result(SuccessCode.Success.code, "��ѯ�ɹ�");
      }
  
  
      Long fid = pigeon.getFid();
      Long mid = pigeon.getMid();
  
      Map<String, List<Pigeon>> pigeonMap = new HashMap<>();
  
      //ͬ����ĸ
      if (fid != null) {
          //���� fid = fid and id != id and gid = gid and mid != mid
          QueryWrapper<Pigeon> fatherHalfWrapper = new QueryWrapper<>();
          fatherHalfWrapper.eq("fid", fid)
                  .eq("gid", gid)
                  .ne(mid != null, "mid", mid)
                  .ne("id", id);
          List<Pigeon> list = pigeonService.list(fatherHalfWrapper);
          pigeonMap.put("fatherHalf", list);
      }
  
      //ͬĸ�츸
      if (mid != null) {
          //���� mid = mid and id != id and gid = gid and fid != fid
          QueryWrapper<Pigeon> motherHalfWrapper = new QueryWrapper<>();
          motherHalfWrapper.eq("mid", mid)
                  .eq("gid", gid)
                  .ne(fid != null, "fid", fid)
                  .ne("id", id);
          List<Pigeon> list = pigeonService.list(motherHalfWrapper);
          pigeonMap.put("motherHalf", list);
      }
  
      //ͬ��ͬĸ
      if (fid != null && mid != null) {
          //���� fid = fid and id != id and gid = gid and mid = mid
          QueryWrapper<Pigeon> fullWrapper = new QueryWrapper<>();
          fullWrapper.eq("fid", fid)
                  .eq("gid", gid)
                  .eq("mid", mid)
                  .ne("id", id);
          List<Pigeon> list = pigeonService.list(fullWrapper);
          pigeonMap.put("full", list);
      }
  
      return new Result(pigeonMap, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

##### 2.2.9.13 ��ҳ��Ϣ��������

�о�һЩ�߼���Ϊ���ӵĽӿڣ�

- ��ȡ����ʱ��仯����

  ���˼·��

  1. �����������л�ȡ`info`�������ò�����һ��Map���󣬰�����һ����Ϊ`gid`��Long����ֵ����ʾ�����ID��
  2. ������ѯ������Ҫ��`gid`����ָ���ĸ���ID��
  3. ����Ҫ��ѯ���ֶ�Ϊ`id`��
  4. ʹ�ò�ѯ������ѯ������Ϣ����ȡ���������ĸ����б�
  5. ����һ���յ�`countMap`�����ڴ洢���������Ĺ�ϣ������
  6. ��ȡһ����ǰ������`recent`��ͨ����ǰ���ڼ�ȥָ��������`createRecent`�õ���
  7. ����ÿ�����Ӷ��󣬸�����ID��ѯ��Ӧ�ĸ�����Ϣ��
  8. ��ȡ������Ϣ�еĴ���ʱ��`createTime`���ж����Ƿ������һ����֮�ڡ�
  9. ������Ӵ���ʱ�������һ����֮�ڣ����������1�����洢��`countMap`�ж�Ӧ���ڵļ���ֵ�ϡ�
  10. ������Ӵ���ʱ�䲻�����һ����֮�ڣ������м�����

  ```java
  /**
   * ��ȡ����ʱ��仯����
   * �����趨�����������ȡ����
   */
  @GetMapping("create")
  public Result getCreate(HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //������ȡ����id
      QueryWrapper<Pigeon> pigeonWrapper = new QueryWrapper<>();
      pigeonWrapper.eq("gid", gid);
      pigeonWrapper.select("id");
      List<Pigeon> pigeonList = pigeonService.list(pigeonWrapper);
  
      //��ϣ����map
      Map<String, Integer> countMap = new HashMap<>();
      //��ȡһ����ǰʱ��
      LocalDate recent = LocalDate.now().minusDays(createRecent);
      pigeonList.forEach(pigeon -> {
          //����id��ȡʱ��
          Long id = pigeon.getId();
          QueryWrapper<PigeonInfo> wrapper = new QueryWrapper<>();
          wrapper.eq("pid", id)
                  .select("create_time");
          PigeonInfo pigeonInfo = pigeonInfoService.getOne(wrapper);
          //�ж�ʱ���Ƿ���ȷ
          LocalDate createTime = pigeonInfo.getCreateTime();
          if (createTime.isAfter(recent)) {
              //�������
              countMap.computeIfPresent(createTime.toString(), (k, v) -> v + 1);
              //���������
              countMap.computeIfAbsent(createTime.toString(), k -> 1);
          }
      });
  
      return new Result(countMap, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ��ȡɾ��ʱ��仯����

  sql��䣺

  ```sql
  select DATE(time), count(0)
  from t_oplog
  where gid and content = 2 and time > recent
  group by DATE(time)
  order by time
  ```

  ```java
  /**
   * ��ȡɾ��ʱ��仯����
   * �����趨�����������ȡ����
   */
  @GetMapping("delete")
  public Result getDelete(HttpServletRequest request) {
  ��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
  ��ȡ���ʱ��
      LocalDateTime recent = LocalDateTime.now().minusDays(deleteRecent);
  
      //�������
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
      return new Result(countMap, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ����������ڻ�ȡ���������ݱ仯

  sql��䣺

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
   * ����������ڻ�ȡ���������ݱ仯
   */
  @GetMapping("oplog/line")
  public Result getOplogLine(HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //��ȡ���ʱ��
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
  
      return new Result(countMap, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ͳ�Ƶ�¼��Ա����

  sql��䣺

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
   * ͳ�Ƶ�¼��Ա����
   */
  @GetMapping("login/count")
  public Result getLoginCount(HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //��ȡ���ʱ��
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
  
      return new Result(countMap, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ��ȡ��Ա��������

  ���˼·��redis������ **2.2.6 ϵͳ������ utils - redis����������**����

  1. �����������л�ȡ`info`�������ò�����һ��Map���󣬰�����һ����Ϊ`gid`��Long����ֵ����ʾ�����ID��
  2. ������ѯ������Ҫ��`gid`����ָ���ĸ���ID��
  3. ���ò�ѯ���Ҫ���ص��ֶΣ�����`id`�ֶΣ�ʹ��`select`����ָ����ѯ�ֶΡ�
  4. ʹ��`list`����ִ�в�ѯ�����ؽ��Ϊһ��List<User>��ÿ��User�����ʾһ��������Ա��
  5. ����һ��AtomicLong���͵ı���`count`�����ڼ�����
  6. ����������Ա�б�`userList`����ÿ����Աִ�����²�����
     - ��ȡ��Ա��ID��
     - ����`whitelistUtil.getActive`����������Redis�����ռ��ID��Ϊ��������ȡ����Ա�Ļ����
     - ������ۼӵ�`count`�����С�
  7. ѭ��������`count`�����д洢������������Ա�Ļ������

  ```java
  /**
   * ��ȡ��Ա��������
   */
  @GetMapping("online/group")
  public Result getOnlineGroup(HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //����gid��ȡ������Ա��id
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
  
      return new Result(count, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

##### 2.2.9.14 ��ϸ���ݷ�������

�оٲ�ͬ����ķ���ӿڣ�

- ��ȡ�û����������еĴ�������

  sql��䣺

  1. ������ѯ������Ҫ��`gid`����ָ���ĸ���ID������`sex`�ֶβ�Ϊ�ա�
  2. ���ò�ѯ���Ҫ���ص��ֶΣ�����`count(sex)`��`sex`��ʹ��`select`����ָ����ѯ�ֶΡ�
  3. ʹ��`groupBy`�������������`sex`�ֶν��з��顣

  ```sql
  select count(sex), sex
  from t_pigeon
  where sex is not null
  and gid = 1640546214887645185
  group by sex;
  ```

  ```java
  /**
   * ����gid��ȡ�û����������еĴ�������
   */
  @GetMapping("sex")
  public Result getSexData(HttpServletRequest request) {
      //��ȡgid
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
  
      return new Result(maps, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ��ȡ��ɫͳ������

  sql��䣺

  1. ������ѯ������Ҫ��`gid`����ָ���ĸ���ID������`yan`�ֶβ�Ϊ�ա�
  2. ���ò�ѯ���Ҫ���ص��ֶΣ�����`count(yan)`��`yan`��ʹ��`select`����ָ����ѯ�ֶΡ�
  3. ʹ��`groupBy`�������������`yan`�ֶν��з��顣
  4. ʹ��`orderByAsc`�������������`count(yan)`������������

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
   * ����gid��ȡ��ɫͳ������
   * �����򷵻�
   */
  @GetMapping("yan")
  public Result getYanData(HttpServletRequest request) {
      //��ȡgid
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
  
      return new Result(maps, SuccessCode.Success.code, "��ѯ�ɹ�");
  }
  ```

- ��ȡ�����ߵ�ͳ������

  ���˼·��

  1. ���ݸ����ĸ���ID��gid����ʹ�ò�ѯ������������QueryWrapper�����ò�ѯ������Ҫ��`gid`�ֶε���ָ���ĸ���ID��
  2. ���ò�ѯ���Ҫ���ص��ֶΣ�����`id`��ʹ��`select`����ָ����ѯ�ֶΡ�
  3. ִ�в�ѯ����ȡ�����б�pigeonList����
  4. ����һ���յĹ�ϣӳ�䣨HashMap��`countMap`�����ڴ洢ͳ�ƽ�������м�Ϊ��Դ��source����ֵΪ����������
  5. ���������б�����ÿ�����ӣ���ȡ��ID��id����
  6. ʹ�ò�ѯ������������QueryWrapper�����ò�ѯ������Ҫ��`pid`�ֶε��ڵ�ǰ���ӵ�ID��
  7. ���ò�ѯ���Ҫ���ص��ֶΣ�����`source`��ʹ��`select`����ָ����ѯ�ֶΡ�
  8. ִ�в�ѯ����ȡ������Ϣ��pigeonInfo����
  9. �жϸ�����Ϣ�Ƿ�Ϊ�գ����Ϊ����������ǰѭ����
  10. ���`countMap`���Ƿ��Ѵ��ڸ���Դ��source����ͳ����Ϣ�����������������ӵ�`countMap`�У�����������������Ϊ1��
  11. ���`countMap`���Ѵ��ڸ���Դ��ͳ����Ϣ���򽫸���Դ��Ӧ�ĸ���������1��

  ```java
  /**
   * ����gid��ȡ�����ߵ�ͳ������
   */
  @GetMapping("source")
  public Result getSourceData(HttpServletRequest request) {
      //��ȡgid
      Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
      Long gid = (Long) info.get("gid");
  
      //����gid��ȡ���е�pid
      QueryWrapper<Pigeon> pigeonQueryWrapper = new QueryWrapper<>();
      pigeonQueryWrapper.select("id")
              .eq("gid", gid);
  
      List<Pigeon> pigeonList = pigeonService.list(pigeonQueryWrapper);
  
      Map<String, Integer> countMap = new HashMap<>();
      //����pid��ȡsource
      pigeonList.forEach(pigeon -> {
          Long id = pigeon.getId();
  
          QueryWrapper<PigeonInfo> infoQueryWrapper = new QueryWrapper<>();
          infoQueryWrapper.select("source")
                  .eq("pid", id);
  
          PigeonInfo pigeonInfo = pigeonInfoService.getOne(infoQueryWrapper);
          if (pigeonInfo == null) {
              return;
          }
          //map��û�У���ֵΪһ
          countMap.computeIfAbsent(pigeonInfo.getSource(), k -> 1);
          //map�д��������
          countMap.computeIfPresent(pigeonInfo.getSource(), (k, v) -> v + 1);
      });
  
      return new Result(countMap, SuccessCode.Success.code, "��ȡ�ɹ�");
  }
  ```

##### 2.2.9.15 ��Ϣ�������ķ���

���趨�õ���Ϣ��ַ�Ѷ�ά����ʽ����ǰ�ˣ���ά�봴��������**2.2.6 ϵͳ������ utils - ��ά�빤��**��

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
        return new Result(qrCode, SuccessCode.Success.code, "��ȡ�ɹ�");
    }

    @GetMapping("guat")
    public Result getGuatQrCode() {
        String uuid = IdWorker.get32UUID();
        String qrCode = qrCodeUtil.createQrCode(guatUrl + "?t=" + uuid);
        return new Result(qrCode, SuccessCode.Success.code, "��ȡ�ɹ�");
    }

    @GetMapping("mail")
    public Result getMainQrCode() {
        String uuid = IdWorker.get32UUID();
        String qrCode = qrCodeUtil.createQrCode(mailUrl + "?t=" + uuid);
        return new Result(qrCode, SuccessCode.Success.code, "��ȡ�ɹ�");
    }
}
```

#### 2.2.10 �ܽ�

##### 2.2.10.1 Ȧ���Ӷȷ���

- Ȧ���Ӷȱ����֣�

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

- ��״����ͼ

![image-20230608201923566](README\image-20230608201923566.png)

- �ܽ�

�󲿷ַ�����Ȧ���ӶȽϵͣ���ʹ����Щ������Լ����׶������ǿ�����һЩ�����Ĺ��߷����������ļ������IP��ȡ�ȣ���Щ�����Ĺ�����Զ����ͼ򵥣�����Ҫ̫���������֧��ѭ��������һ�������ķ��棬��Ϊ��Ȧ���Ӷȵķ�����������⡢���Ժ�ά����

Ȼ����Ҳ������������Ȧ���ӶȽϸߣ�����ζ�����ǵĿ���������Ը��ӣ����ܰ������������֧��ѭ����䡣��Щ����������ҵ���߼���Ϊ���ӵķ�������Ҫ����Ĳ������������ǲ�ͬ�ķ�֧�����������ˣ���������ȻŬ������Щ�����Ĵ��룬��������꾡��ע�����������߼�������һ��������Ŭ������Ϊע�Ϳ��԰���������������ĸ����ԣ��Ӷ���߿�ά���ԡ�

Ȼ��������Ҫע����ǣ������Ѿ������˴���򻯺�ע�͵�Ŭ��������Ȼ��Ҫ���������Ȧ���Ӷȡ���Ȧ���Ӷȵķ������ܵ��´���������⡢���Ժ�ά������ˣ�������Ӧ�ÿ��ǽ�һ���Ż���Ȧ���Ӷȵķ������Խ��͸��ӶȲ���ǿ����Ŀɶ��ԺͿ�ά���ԡ������ͨ��ʹ�������ع���ѭ���ع�����ȡ�����ȼ�����ʵ�֡�

�����������󲿷ַ������нϵ͵�Ȧ���Ӷȣ����׶�������Ȧ���ӶȽϸߵķ������Ѿ���ȡ�˼򻯴�������ע�͵Ĵ�ʩ��Ȼ��������Ҫ����Ŭ���Ż���Ȧ���Ӷȵķ����������������Ȧ���Ӷȡ�����������ߴ���Ŀɶ��ԡ��ɲ����ԺͿ�ά���ԣ�ʹ�����������ӽ�׳�Ϳɳ�����

##### 2.2.10.2 ����ص�

- ����redis������redis������ư�����������������
  - �˳���¼�������������Ҫǿ�����ߵ�ҵ������޳�����token
  - ����ip��ַ
- ����token��redis�Ĺ���ʱ���ֹ��Ծ�û�����
- ����Դ·�����ý׶Σ���ƽ������������ļ�����
- ���ö�ʱ������ƶ�ʱ���������Դ��ͳ�����������Լ��������ѹ��
- ���������밲ȫ��ʩ���������Argon2���μ��ܣ�ÿ�ε�¼����ֵ���и���
- ��̬�ֶλᷢ��sqlע�룬���У���ֹsqlע�룬���������ip����

### 2.3 ǰ�����

#### 2.3.1 ��¼����

![image-20230608202850022](README\image-20230608202850022.png)

#### 2.3.2 ��ҳ

- �ֲ�ͼ

  ![image-20230608203100298](README\image-20230608203100298.png)

- ���ݷ���ͼ��

  ![image-20230608203148795](README\image-20230608203148795.png)

#### 2.3.3 ���ӿ�

- ��Ҫ��Ϣ����

![image-20230608203434860](README\image-20230608203434860.png)

![image-20230608203246770](README\image-20230608203246770.png)

- ����

  ![image-20230608210747662](README\image-20230608210747662.png)

  ![image-20230608210810077](README\image-20230608210810077.png)

  

  ![image-20230608210832704](README\image-20230608210832704.png)

  ![image-20230608210853911](README\image-20230608210853911.png)

#### 2.3.4 �༭Ѫͳ

![image-20230608211007461](README\image-20230608211007461.png)

![image-20230608211121290](README\image-20230608211121290.png)

![image-20230608211029615](README\image-20230608211029615.png)

![image-20230608211046662](README\image-20230608211046662.png)

#### 2.3.5 �������

![image-20230608211248677](README\image-20230608211248677.png)

#### 2.3.6 �߼���������

![image-20230608211351932](README\image-20230608211351932.png)

#### 2.3.7 ������־

![image-20230608211534296](README\image-20230608211534296.png)

#### 2.3.8 ͳ������

![image-20230608211604815](README\image-20230608211604815.png)

#### 2.3.9 ���ﳲ��

![image-20230608211711405](README\image-20230608211711405.png)

![image-20230608211648668](README\image-20230608211648668.png)

![image-20230608211733245](README\image-20230608211733245.png)

#### 2.3.10 ѡ������

![image-20230608211849462](README\image-20230608211849462.png)

![image-20230608211911349](README\image-20230608211911349.png)

#### 2.3.11 ������Ϣ

![image-20230608211935810](README\image-20230608211935810.png)

#### 2.3.12 Ѫͳ������

![image-20230608212003602](README\image-20230608212003602.png)

#### 2.3.13 �û�����

![image-20230608212057499](README\image-20230608212057499.png)

#### 2.3.14 ����Ա����

![image-20230608212133891](README\image-20230608212133891.png)

![image-20230608212146924](README\image-20230608212146924.png)

![image-20230608212203883](README\image-20230608212203883.png)

#### 2.3.15 ��¼��Ϣ

![image-20230608212321792](README\image-20230608212321792.png)

#### 2.3.16 ��������

![image-20230608212357639](README\image-20230608212357639.png)

#### 2.3.17 ȫ������

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

##### 2.3.17.2 axios��װ

```js
import axios from "axios"
import {addLoading, clearLoading} from "@/assets/js/loading.js"
import router from "@/router"
import store from "@/store"
import {Notification} from '@arco-design/web-vue'
import JSONbigint from "json-bigint"

//��������¼�
function preventKeyDown(event) {
    event.preventDefault();
    event.stopPropagation();
}

const axiosx = axios.create({
    baseURL: "/api",
    timeout: 30000,
    transformResponse: [function (data) {
        // ʹ��json-bigint����Long����Ӧ����
        try {
            return JSONbigint.parse(data)
        } catch (error) {
            return data
        }
    }]
})

//��������
axiosx.interceptors.request.use(config => {
    //��ֹ�����¼�
    document.addEventListener('keydown', preventKeyDown, false);
    //��Ӽ���ͼ��
    addLoading(config.message)
    //����Ϊ���ڷ�������
    store.commit("setPending", true)
    return {
        ...config,
        //�������ͷ
        headers: {
            ...config.headers,

            Authorization: store.getters.doneToken
        }
    }
}, error => {
    //��������¼�
    document.removeEventListener('keydown', preventKeyDown, false);
    store.commit("setPending", false)
    return Promise.reject(error);
})

//��Ӧ����
axiosx.interceptors.response.use(response => {
    clearLoading()
    //��������¼�
    document.removeEventListener('keydown', preventKeyDown, false);
    //����sqlע��
    if (response.data.code === 411) {
        Notification.error(response.data.message)
        store.commit("setToken", "")
        router.push({name: "403"})
    }
    //����Ȩ���쳣
    if (response.data.code === 403) {
        Notification.error(response.data.message)
        router.push({name: "403"})
    }
    //����token��ֹ��Ծ�û����ڣ������ã�
    // if (response.headers.authorization) {
    //     store.commit("setToken", response.headers.authorization)
    // }
    store.commit("setPending", false)
    return response;
}, error => {
    clearLoading()
    //��������¼�
    document.removeEventListener('keydown', preventKeyDown, false);
    // router.push({ name: "500" })
    Notification.error(`${error.message} ${error.code}`)
    store.commit("setPending", false)
    return Promise.reject(error)
});

export default axiosx

```

##### 2.3.17.3 router·��

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
                title: "��¼",
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
                        title: "��ҳ"
                    }
                },
                {
                    path: "loginMsg",
                    name: "loginMsg",
                    component: () => import("@/views/loginMsg/loginMsg.vue"),
                    meta: {
                        title: "��¼��Ϣ"
                    }
                },
                {
                    path: "feedback",
                    name: "feedback",
                    component: () => import("@/views/feedback/feedback.vue"),
                    meta: {
                        title: "��������"
                    }
                },
                {
                    path: "statistic",
                    name: "statistic",
                    component: () => import("@/views/statistic/statistic.vue"),
                    meta: {
                        title: "ͳ������"
                    }
                },
                {
                    path: "gpcx",
                    name: "gpcx",
                    component: () => import("@/views/gpcx/gpcx.vue"),
                    meta: {
                        title: "���ﳲ��"
                    }
                },
                {
                    path: "system/gsxx",
                    name: "gsxx",
                    component: () => import("@/views/gsxx/gsxx.vue"),
                    meta: {
                        title: "������Ϣ"
                    }
                },
                {
                    path: "gzk/:name?",
                    name: "gzk",
                    component: () => import("@/views/gzk/gzk.vue"),
                    meta: {
                        title: "���ӿ�"
                    }
                },
                {
                    path: "pigeon/editPigeon/:id(\\d+)?",
                    name: "editPigeon",
                    component: () => import("@/views/editPigeon/editPigeon.vue"),
                    meta: {
                        title: "�༭Ѫͳ"
                    }
                },
                {
                    path: "pigeon/rapid",
                    name: "rapid",
                    component: () => import("@/views/rapid/rapid.vue"),
                    meta: {
                        title: "�������"
                    }
                },
                {
                    path: "pigeon/batch",
                    name: "batch",
                    component: () => import("@/views/batch/batch.vue"),
                    meta: {
                        title: "�߼���������"
                    }
                },
                {
                    path: "pigeon/log",
                    name: "log",
                    component: () => import("@/views/log/log.vue"),
                    meta: {
                        title: "������־"
                    }
                },
                {
                    path: "help",
                    name: "help",
                    component: () => import("@/views/help/help.vue"),
                    meta: {
                        title: "��������"
                    }
                },
                {
                    path: "system/options",
                    name: "options",
                    component: () => import("@/views/options/options.vue"),
                    meta: {
                        title: "ѡ������"
                    }
                },
                {
                    path: "system/xtspz",
                    name: "xtspz",
                    component: () => import("@/views/xtspz/xtspz.vue"),
                    meta: {
                        title: "Ѫͳ������"
                    }
                },
                {
                    path: "system/admin",
                    name: "admin",
                    component: () => import("@/views/admin/admin.vue"),
                    meta: {
                        title: "����Ա����",
                        requiresAdminAuth: true
                    }
                },
                {
                    path: "system/user",
                    name: "user",
                    component: () => import("@/views/user/user.vue"),
                    meta: {
                        title: "�û�����"
                    }
                },
                {
                    path: "detail/:id(\\d+)?",
                    name: "detail",
                    component: () => import("@/views/detail/detail.vue"),
                    meta: {
                        title: "����"
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
    //���ܵ�¼
    if (to.meta.free) {
        await axiosx({
            method: "GET",
            url: "login/free",
            message: "��֤��Ϣ"
        }).then(res => {
            if (res.data.code === 200) {
                router.push({ name: "home" })
                Notification.success(res.data.message)
            }
        })
    }
    //��֤����
    if (to.meta.requiresAuth) {
        await axiosx({
            method: "GET",
            url: "login",
            message: "��¼��֤"
        })
    }
    //��֤����Ա����
    if(to.meta.requiresAdminAuth) {
        await axiosx({
            method: "GET",
            url: "login/admin",
            message: "��֤����Ա��Ϣ"
        })
    }
    //��̬����
    if (to.meta.title) {
        document.title = `�����ƿ� -- ${to.meta.title}`
    }
})

export default router
```

##### 2.3.17.4 store vuex����

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

##### 2.3.17.5 debounce.js����

```js
export function toDebounceFunction(func, delay, ...args) {
    //Ĭ��300����
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

## 3 �����¼

### 3.1 mysql

1. ���� `mysql-8.0.33-linux-glibc2.28-x86_64.tar.gz` �ļ�

3. ��ѹ `mysql-8.0.33-linux-glibc2.28-x86_64.tar.gz` �ļ�
   ```shell
   tar xzf mysql-8.0.33-linux-glibc2.28-x86_64.tar.gz
   ```

5. ���� MySQL ��װĿ¼��
   ```shell
   cd /usr/local/mysql
   ```

6. ����һ����Ϊ `mysql` ��ϵͳ�û����û��飬�Ա� MySQL ��������Է���Ȩ�û�������У�
   ```shell
   groupadd mysql
   useradd -r -g mysql -s /bin/false mysql
   ```

7. Ϊ MySQL ����Ŀ¼������Ӧ��Ŀ¼����������ȷ��Ȩ�ޣ�
   ```shell
   mkdir data
   chown -R mysql:mysql data
   ```

8. ��ʼ�� MySQL ����Ŀ¼��
   ```shell
   sudo bin/mysqld --initialize-insecure --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
   ```

9. ���� MySQL ���������� MySQL �����ļ� `my.cnf` ���Ƶ���ȷ��λ�ã�
   ```shell
   cp support-files/my-default.cnf /etc/my.cnf
   ```

   Ȼ��༭ `/etc/my.cnf` �ļ����� MySQL ���б�Ҫ�����á�

10. ���� MySQL ��������
    ```shell
    bin/mysqld_safe --user=mysql &
    ```

11. ����������� MySQL �ͻ��˿�ִ���ļ���ӵ�ϵͳ�� PATH �У�
    ```shell
    sudo ln -s /usr/local/mysql/bin/mysql /usr/local/bin/mysql
    ```

12. ��֤ MySQL �Ƿ�ɹ���װ��ִ���������
    ```shell
    mysql --version
    ```


### 3.2 jdk

1. ���� `jdk-20_linux-x64_bin.tar.gz` �ļ�

2. ��ѹ `jdk-20_linux-x64_bin.tar.gz` �ļ�

   ```
   tar xzf jdk-20_linux-x64_bin.tar.gz
   ```

3. ���û����������༭ `/etc/profile` �ļ�

   ```
   export JAVA_HOME=/usr/app/jdk-20.0.1
   export PATH=$JAVA_HOME/bin:$PATH
   ```

4. ִ����������ʹ����������Ч

   ```
   source /etc/profile
   ```

5. ��֤ JDK ��װ�Ƿ�ɹ�

   ```
   java -version
   ```

### 3.3 redis

1. ����`redis-7.0.11.tar.gz`�ļ�

2. ��ѹ`redis-7.0.11.tar.gz`

   ```
   tar xzf redis-7.0.11.tar.gz
   ```

3. �����ѹ��� Redis Ŀ¼

   ```
   cd redis-7.0.11
   ```

4. ����Ͱ�װ Redis

   ```
   make
   ```

5. ������ɺ�ִ��������������װ Redis

   ```
   make install
   ```

6. ��װ��ɺ��������� Redis ��Դ����Ŀ¼���ҵ��������ɵĿ�ִ���ļ��������ļ���

7. ���� Redis �����������ն���ִ�������������� Redis ������

   ```
   redis-server
   ```

8. ��� Redis ������������״̬

   ```
   redis-cli ping
   ```

### 3.4 nginx

1. ���� `nginx-1.24.0.tar.gz` �ļ�

2. ��ѹ `nginx-1.24.0.tar.gz` �ļ�

   ```sh
   tar xzf nginx-1.24.0.tar.gz
   ```

3. �����ѹ��� Nginx Ŀ¼

   ```
   cd nginx-1.24.0
   ```

4. ���ñ���ѡ����� Nginx

   ```
   ./configure
   make
   ```

5. ������ɺ�ִ���������װ

   ```
   make install
   ```

6. ���� nginx.config

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

7. ����nginx��װĿ¼����

   ```
   ./sbin/nginx -c ./conf/nginx.conf
   ```

   