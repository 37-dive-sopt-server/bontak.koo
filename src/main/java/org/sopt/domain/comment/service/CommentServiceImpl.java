package org.sopt.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.entity.Article;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.comment.dto.req.CommentCreateRequest;
import org.sopt.domain.comment.dto.req.CommentUpdateRequest;
import org.sopt.domain.comment.dto.res.CommentResponse;
import org.sopt.domain.comment.entity.Comment;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.GeneralException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public void create(Long memberId, CommentCreateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorCode.MEMBER_NOT_FOUND));

        Article article = articleRepository.findById(request.articleId())
                .orElseThrow(() -> new GeneralException(ErrorCode.ARTICLE_NOT_FOUND));

        Comment comment = Comment.create(member, article, request.content());
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findByArticle(Long articleId) {
        return commentRepository.findByArticleIdAndIsDeletedFalseOrderByCreatedAtAsc(articleId)
                .stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getMember().getId(),
                        c.getMember().getName(),
                        c.getContent(),
                        c.getCreatedAt()
                ))
                .toList();
    }

    public void update(Long memberId, Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GeneralException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getMember().getId().equals(memberId)) {
            throw new GeneralException(ErrorCode.UNAUTHORIZED_COMMENT);
        }

        comment.updateContent(request.content());
    }

    public void delete(Long memberId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GeneralException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getMember().getId().equals(memberId)) {
            throw new GeneralException(ErrorCode.UNAUTHORIZED_COMMENT);
        }

        comment.delete();
    }
}
