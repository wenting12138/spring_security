package com.wen.t;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 15:23
 * @ClassName 类名称
 * @Description 类描述
 */
public class PasswordDecode {


    @Test
    public void decode(){

        String password = "666";
        // 加密
        String s = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(s);


        boolean b = BCrypt.checkpw(password, "$2a$10$UsdfD03UwiQ3tQSHOovhHOVVFqyuVh/sxxjJQMLu3a5TO.PDNqn.6");
        System.out.println(b);
    }

}
