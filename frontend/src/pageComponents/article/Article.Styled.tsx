import styled, { css } from 'styled-components';

const ArticleContainer = styled.div`
    display: flex;
    position: relative;

    width: 100vw;
    height: 100vh;
`;

const StyledArticle = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    position: relative;
    width: 100vw;
    height: 100vh; //추후 수정 필요 임의값
    background-image: url('/background7.jpg');
    background-size: cover;

    padding: 6vh 6vw;
    z-index: 1;
    gap: 6%;
`;

const StyledRightContent = styled.div`
    display: flex;
    width: 20%;
    height: 90%;
`;

const StyledArticleContent = styled.div`
    display: flex;
    flex-direction: column;
    width: 60vw;
    height: 90vh;
    padding: 3% 3%;
    border: 1px solid lightgray;
    border-radius: 20px;
    background-color: white;
    box-shadow: rgba(0, 0, 0, 0.25) 0px 0.0625em 0.0625em, rgba(0, 0, 0, 0.25) 0px 0.125em 0.5em,
        rgba(255, 255, 255, 0.1) 0px 0px 0px 1px inset;
    gap: 20px;
`;

const StyledArticleHeader = styled.div`
    display: flex;
    flex-direction: column;
    border-bottom: 2px solid #218fff;
    width: 100%;
    height: 8%;
    font-size: 28px;
    font-weight: bold;
    color: #218fff;
`;

const StyledArticleTop = styled.div`
    display: flex;
    border-bottom: 1px solid lightgray;
    width: 100%;
    height: 9%;
    font-size: 30px;
    /* padding: 0% 5%; */
`;

const StyledArticleBody = styled.div`
    display: flex;
    width: 100%;
    height: 70%;
    padding: 3% 3%;
    border: 1px solid lightgray;
    border-radius: 20px;
    background-color: white;
    box-shadow: rgba(0, 0, 0, 0.25) 0px 0.0625em 0.0625em, rgba(0, 0, 0, 0.25) 0px 0.125em 0.5em,
        rgba(255, 255, 255, 0.1) 0px 0px 0px 1px inset;
    gap: 10px;
`;

export {
    StyledArticleTop,
    StyledArticle,
    ArticleContainer,
    StyledArticleContent,
    StyledArticleHeader,
    StyledArticleBody,
    StyledRightContent,
};
