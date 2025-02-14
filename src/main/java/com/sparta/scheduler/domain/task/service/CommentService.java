package com.sparta.scheduler.domain.task.service;

import com.sparta.scheduler.domain.task.dto.CommentRequestDto;
import com.sparta.scheduler.domain.task.dto.CommentResponseDto;
import com.sparta.scheduler.domain.task.dto.TaskRequestDto;
import com.sparta.scheduler.domain.task.dto.TaskResponseDto;
import com.sparta.scheduler.domain.task.entity.Comment;
import com.sparta.scheduler.domain.task.entity.Task;
import com.sparta.scheduler.domain.task.repository.CommentRepository;
import com.sparta.scheduler.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final TaskRepository taskRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Task task = taskRepository.findById(requestDto.getTaskId()).orElseThrow(()
                -> new RuntimeException("존재하지 않는 일정입니다"));
        Comment comment = Comment.from(requestDto);
        comment.setTask(task);
        Comment commentSave = repository.save(comment);
        return commentSave.to();
    }

    public List<CommentResponseDto> getCommentList() {
        return repository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    public CommentResponseDto getComment(Long id) {
        Comment comment = findByComment(id);
        return comment.to();
    }

    public void updateTask(Long id, CommentRequestDto requestDto) {
        Comment comment = findByComment(id);
        comment.init(requestDto);
        repository.saveAndFlush(comment);
    }

    public void deleteComment(Long id) {
        findByComment(id);
        repository.deleteById(id);
    }

    private Comment findByComment(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("해당 id를 찾을 수 없습니다"));
    }

}
