'use client';

import { useEffect, useState } from 'react';
import articleDetailGet from '@/api/article/articleDetailGet';
import {
    StyledArticle,
    ArticleContainer,
    StyledArticleContent,
    StyledArticleHeader,
    StyledArticleBody,
    StyledArticleTop,
    StyledRightContent,
} from './Article.Styled';
import RightBar from '../articles/components/rightbar';

import Image from 'next/image';
import Button from '@/components/button';
import Input from '@/components/input';
import HR from '@/components/hr';

export interface ArticleDetail {
    articleId?: number;
    nickname?: string;
    title?: string;
    imageUrl?: any;
    createdAt?: any;
}

const Article = (props: any) => {
    const { articleId } = props;
    const [articleDetail, setArticleDetail] = useState<ArticleDetail[]>([]);
    useEffect(() => {
        articleDetailGet(articleId)
            .then((res) => {
                setArticleDetail(res.result);
            })
            .catch((error) => {
                console.error('에러 발생:', error);
            });
    }, [articleId]);
    console.log(articleDetail);
    return (
        <ArticleContainer>
            <StyledArticle>
                <StyledArticleContent>
                    {articleDetail ? (
                        <>
                            <StyledArticleHeader>
                                {articleDetail.category === 'FREE'
                                    ? '자유 게시판'
                                    : articleDetail.category === 'TIP'
                                    ? 'Tips'
                                    : '회사 게시판'}
                            </StyledArticleHeader>
                            <StyledArticleTop> {articleDetail.title}</StyledArticleTop>
                            작성자 : {articleDetail.nickname} | 작성 일자 : {articleDetail.createdAt} | 조회 수 :
                            {articleDetail.viewCount}
                            <StyledArticleBody>
                                <Image src={articleDetail.imgUrl} width={370} height={370} alt="업로드 이미지" />
                                {articleDetail.content}
                            </StyledArticleBody>
                        </>
                    ) : (
                        <div>Loading...</div>
                    )}
                </StyledArticleContent>
                <StyledRightContent>
                    <RightBar />
                </StyledRightContent>
            </StyledArticle>
        </ArticleContainer>
    );
};

export default Article;
