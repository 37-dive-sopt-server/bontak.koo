package org.sopt.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.sopt.domain.article.entity.QArticle.article;
import static org.sopt.domain.member.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findAllWithMember() {
        return queryFactory
                .selectFrom(article)
                .join(article.member, member).fetchJoin()
                .fetch();
    }
}

