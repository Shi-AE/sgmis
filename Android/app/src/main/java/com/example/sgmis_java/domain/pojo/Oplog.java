package com.example.sgmis_java.domain.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class Oplog {
    /**
     * 自然主键
     */
    private Long id;
    /**
     * 作者
     */
    private String author;

    /**
     * 足环号
     */
    private String ringNumber;

    /**
     * 操作内容
     */
    private Integer content;
    /**
     * 操作提示
     */
    private String tip;
    /**
     * 操作时间
     */
    private LocalDateTime time;
    /**
     * 鸽子id
     */
    private Long pid;
    /**
     * 组id
     */
    private Long gid;

    @SuppressWarnings("unused")
    public Oplog() {
    }

    public Oplog(String author, String ringNumber, Integer content, String tip, LocalDateTime time, Long pid, Long gid) {
        this.author = author;
        this.ringNumber = ringNumber;
        this.content = content;
        this.tip = tip;
        this.time = time;
        this.pid = pid;
        this.gid = gid;
    }

    public Oplog(String author, String ringNumber, Integer content, String tip, LocalDateTime time, Long gid) {
        this.author = author;
        this.ringNumber = ringNumber;
        this.content = content;
        this.tip = tip;
        this.time = time;
        this.gid = gid;
    }

    public Oplog(String author, Integer content, String tip, LocalDateTime time, Long pid, Long gid) {
        this.author = author;
        this.content = content;
        this.tip = tip;
        this.time = time;
        this.pid = pid;
        this.gid = gid;
    }
}
