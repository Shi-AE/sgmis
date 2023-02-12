package com.AE.sgmis.util;

import com.AE.sgmis.pojo.User;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;

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