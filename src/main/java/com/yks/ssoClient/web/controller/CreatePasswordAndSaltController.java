package com.yks.ssoClient.web.controller;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;

/**
 * @Author 125C01063135
 * @Create 2017-10-24 16:04
 * @Desc 用于创建密码及盐
 **/
public class CreatePasswordAndSaltController {
    public static void main(String[] args) {
        String password = "123456";
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-256");
        Long numOfIterations = 77L;
        hashService.setHashIterations(numOfIterations.intValue());
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = randomNumberGenerator.nextBytes().toHex();
        HashRequest request = new HashRequest.Builder().setSalt(salt).setSource(password).build();
        String digestedPassword = hashService.computeHash(request).toHex();
        System.out.println("加密的密码：" + digestedPassword);
        System.out.println("盐：" + salt);
    }
}
