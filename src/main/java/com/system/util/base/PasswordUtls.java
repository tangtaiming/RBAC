package com.system.util.base;

import com.rbac.application.orm.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码工具
 */
public class PasswordUtls {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";

    private int hashIterations = 2;

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void encryptPassword(User user) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setSalt(salt);

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations)
                .toHex();
        user.setPassword(newPassword);
    }

}
