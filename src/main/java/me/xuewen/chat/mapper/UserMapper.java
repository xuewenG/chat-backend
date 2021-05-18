package me.xuewen.chat.mapper;

import me.xuewen.chat.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM " +
            "`user` " +
            "WHERE `user`.`id` = #{id}")
    User getById(Integer id);

    @Select("SELECT * FROM " +
            "`user` " +
            "WHERE `user`.`account` = #{account}")
    User getByAccount(String account);

    @Select("SELECT * FROM " +
            "`user` " +
            "WHERE `user`.`email` = #{email}")
    User getByEmail(String email);

    @Insert("INSERT INTO " +
            "`user`(`account`, `password`, `nickname`, `gender`, `birthday`, `avatar`, `email`) " +
            "VALUES(#{account}, #{password}, #{nickname}, #{gender}, #{birthday}, #{avatar}, #{email})")
    void register(String account, String password, String email, String nickname, String avatar, Timestamp birthday, Integer gender);
}
