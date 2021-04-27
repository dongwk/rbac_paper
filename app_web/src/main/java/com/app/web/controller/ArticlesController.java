package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.model.model.Article;
import com.app.model.model.BusinessUser;
import com.app.model.model.Comment;
import com.app.model.model.Img;
import com.app.service.service.ArticleService;
import com.app.service.service.BusinessUserService;
import com.app.service.service.CommentService;
import com.app.service.service.ImgService;
import com.app.web.common.R;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.CommonAnot;
import com.app.web.config.annotation.WechatAuthorization;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.CommentPo;
import com.app.web.utils.ImgUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
*
* @author dongwk
* @date 2021-03-17
* @version 1.0
*/
@RestController
@RequestMapping("/articles")
public class ArticlesController extends BaseRestController<ArticleService, Article> {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BusinessUserService businessUserService;

    @Autowired
    private ImgService imgService;

    @CommonAnot(enablePage = true)
    @WechatAuthorization
    @GetMapping
    public R<?> list(Integer cid){
        if (cid != null && cid.intValue() < 1) {
            return R.EMPTY_LIST();
        }
        if (cid != null) {
            return R.SUCCESS(baseService.listByCategoryId(cid));
        } else {
            return R.SUCCESS(baseService.list());
        }
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(baseService.get(id));
    }


    @Authorization
    @GetMapping("/{id}/comments")
    public R<?> comments(@PathVariable("id") Integer id){
        PageHelper.startPage(getPageCurrent(), getPageSize());
        // 所有评论用户ID
        Set<Integer> allBusinessUserId = Sets.newHashSet();
        // 所有评论
        List<CommentPo> commentPos = Lists.newArrayList();

        // 主评论（不是回复的评论）
        List<Comment> comments = commentService.listNoReplyByArticleId(id);
        Map<Integer, CommentPo> commentMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(comments)) {
            List<Integer> commentIds = Lists.newArrayListWithCapacity(comments.size());
            for (Comment comment : comments) {
                CommentPo commentPo = new CommentPo(comment);
                commentMap.put(commentPo.getId(), commentPo);
                commentIds.add(commentPo.getId());
                allBusinessUserId.add(comment.getBusinessUserId());
                commentPos.add(commentPo);
            }
            // 主评论下的评论
            List<Comment> replyFirstComments = commentService.listUnderFirstByCommentIds(id, commentIds, Constants.INT_5);
            if (CollectionUtils.isNotEmpty(replyFirstComments)) {
                for (Comment replyFirstComment : replyFirstComments) {
                    allBusinessUserId.add(replyFirstComment.getBusinessUserId());
                    CommentPo commentPo = commentMap.get(replyFirstComment.getFirstCommentId());
                    if (commentPo.getReplyFirstComments() == null) {
                        commentPo.setReplyFirstComments(Lists.newArrayList());
                    }
                    CommentPo replyFirstCommentPo = new CommentPo(replyFirstComment);
                    commentPo.getReplyFirstComments().add(replyFirstCommentPo);
                    commentPos.add(replyFirstCommentPo);
                }
            }

            // 头像ID
            List<Integer> allHeadImgId = Lists.newArrayList();

            // 用户
            List<BusinessUser> employes = businessUserService.listByIds(Lists.newArrayList(allBusinessUserId));
            Map<Integer, BusinessUser> employeMap = Maps.newLinkedHashMap();
            if (CollectionUtils.isNotEmpty(employes)) {
                for (BusinessUser employe : employes) {
                    allHeadImgId.add(employe.getAvatarImgId());
                    employeMap.put(employe.getId(), employe);
                }
            }

            // 图片
            Map<Integer, Img> imgMap = Maps.newLinkedHashMap();
            if (CollectionUtils.isNotEmpty(allHeadImgId)) {
                List<Img> imgs = imgService.listByImgIds(allHeadImgId);
                if (CollectionUtils.isNotEmpty(imgs)) {
                    imgMap = imgs.stream().collect(Collectors.toMap(Img::getId, Function.identity(), (v1, v2) -> v1));
                }
            }

            // 加载用户图片
            for (CommentPo commentPo : commentPos) {
                BusinessUser employe = employeMap.get(commentPo.getBusinessUserId());
                if (employe != null) {
                    Img img = imgMap.get(employe.getAvatarImgId());
                    if (img != null) {
                        commentPo.setAvatarImgUrl(ImgUtils.fullUrl(img.getThumbnailUrl()));
                    }
                }
            }
        }

        return R.SUCCESS(commentMap);
    }
}