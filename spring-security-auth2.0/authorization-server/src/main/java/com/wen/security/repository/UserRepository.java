package com.wen.security.repository;

import com.wen.security.model.Permission;
import com.wen.security.model.UerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 16:22
 * @ClassName 类名称
 * @Description 类描述
 */
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UerDto getUserByUsername(String username){
        String sql = "select * from t_user where username = ?";

        List<UerDto> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UerDto.class));

        if (list != null && list.size() == 1) {
            return list.get(0);
        }
       return null;

    }

    // 根据用户id查询 用户的权限;
    public List<String> findUserPermission(String userId){
        String sql = "SELECT p.* FROM t_permission p \n" +
                "\tINNER JOIN t_role_permission rp ON p.id = rp.permission_id\n" +
                "\tINNER JOIN t_role r ON r.id = rp.role_id\n" +
                "\tINNER JOIN t_user_role ur ON ur.role_id = r.id\n" +
                "\tINNER JOIN t_user u ON u.id = ur.user_id\n" +
                "WHERE u.id = ?";

        List<Permission> permissions = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Permission.class));

        List<String> list = new ArrayList<>();

        if (permissions != null || permissions.size() != 0) {
            permissions.forEach(permission -> {
                list.add(permission.getCode());
            });
        }
        return list;
    }

}
