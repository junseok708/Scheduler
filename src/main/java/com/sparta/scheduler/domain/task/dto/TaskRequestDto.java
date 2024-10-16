package com.sparta.scheduler.domain.task.dto;

import com.sparta.scheduler.domain.task.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskRequestDto {
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Comment> comments;

}
