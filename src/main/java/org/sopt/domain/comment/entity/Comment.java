package org.sopt.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.article.entity.Article;
import org.sopt.domain.member.entity.Member;
import org.sopt.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Boolean isDeleted;

    public static Comment create(
            Member member,
            Article article,
            String content
    ) {
        return Comment.builder()
                .member(member)
                .article(article)
                .content(content)
                .isDeleted(false)
                .build();
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
