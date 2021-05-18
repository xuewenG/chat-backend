package me.xuewen.chat.mapper;

import me.xuewen.chat.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContactMapper {

    @Select("SELECT * " +
            "from contact " +
            "WHERE userId=#{userId}")
    List<Contact> getAllContactByUserId(Integer userId);
}
