package org.sopt.domain.article.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.domain.article.dto.req.CreateArticleReq;
import org.sopt.domain.article.dto.res.AllArticleRes;
import org.sopt.domain.article.dto.res.ArticleRes;
import org.sopt.domain.article.entity.Article;
import org.sopt.domain.article.repository.ArticleRepository;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.ArticleException;
import org.sopt.global.api.handler.MemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createArticle(Long userId, CreateArticleReq req) {

        if (articleRepository.existsByTitle(req.title())) {
            throw new ArticleException(ErrorCode.DUPLICATE_ARTICLE_TITLE);
        }

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> {
                    return new MemberException(ErrorCode.MEMBER_NOT_FOUND);
                });

        Article article = Article.create(
                member,
                req.title(),
                req.content(),
                req.articleTag()
        );

        articleRepository.save(article);
    }

    @Override
    public ArticleRes getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ErrorCode.ARTICLE_NOT_FOUND));

        return ArticleRes.from(article);
    }

    @Override
    public AllArticleRes getAllArticle() {
        List<Article> articleList = articleRepository.findAllWithMember();
        return AllArticleRes.from(articleList);
    }
}
