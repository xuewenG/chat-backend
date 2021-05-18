package me.xuewen.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 1865357292321788309L;
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private Integer contactType;
    private Integer type;
    private String content;
    private Timestamp time;
}
