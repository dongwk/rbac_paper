package com.app.service.service;

import com.app.mapper.mapper.CommentMapper;
import com.app.model.model.Comment;
import com.app.service.base.BaseMapperService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
@Service
public class CommentService extends BaseMapperService<CommentMapper, Comment> {
    public List<Comment> listByArticleId(int articleId) {
        return baseMapper.listByArticleId(articleId);
    }

    public List<Comment> listNoReplyByArticleId(int articleId) {
        return baseMapper.listNoReplyByArticleId(articleId);
    }

    public List<Comment> listUnderFirstByCommentIds(int articleId, List<Integer> commentIds, int num) {
        return baseMapper.listUnderFirstByCommentIds(articleId, commentIds, num);
    }
}