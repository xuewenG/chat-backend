package me.xuewen.chat.mapper;

import me.xuewen.chat.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * " +
            "from message " +
            "WHERE " +
            "(fromId = #{userId} and toId = #{contactId} and contactType = #{contactType}) " +
            "or " +
            "(fromId = #{contactId} and toId = #{userId} and contactType = #{contactType}) " +
            "ORDER BY id")
    List<Message> selectMessage(Integer contactType, Integer userId, Integer contactId);

    @Insert("INSERT INTO " +
            "message(fromId,toId,contactType,type,content,time) " +
            "VALUES(#{fromId},#{toId},#{contactType},#{type},#{content},#{time})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    void insertMessage(Message message);

    @Update("UPDATE message " +
            "SET state=2 " +
            "WHERE userId = #{friendId} " +
            "and friendId = #{userId} " +
            "and type = 1 and state = 1")
    void readAllPrivateMessage(Integer userId, Integer friendId);
}
