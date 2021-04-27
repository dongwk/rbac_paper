package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> listByArticleId(int articleId);

    List<Comment> listNoReplyByArticleId(int articleId);

    List<Comment> listUnderFirstByCommentIds(@Param("articleId") int articleId, @Param("commentIds") List<Integer> commentIds, @Param("num") int num);
}