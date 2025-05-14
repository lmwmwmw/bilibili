package com.lmw.web.service.impl;

import com.lmw.model.entity.Comment;
import com.lmw.web.mapper.CommentMapper;
import com.lmw.web.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
