import styled, { css } from 'styled-components';

const StyledLeftTopContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    height: 30%;
    border: 1px solid black;
`;

const StyledTopContainer = styled.div`
    display: flex;
    width: 100%;
    height: 100%;
`;

const StyledBottomContainer = styled.div`
    display: flex;
    justify-content: center;
    width: 60%;
    height: 100%;
    border: 1px solid red;
    margin-left: 40%;
`;

const StyledPictureSide = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40%;
    height: 100%;
`;

const StyledTitleInfo = styled.div`
    display: flex;
    flex-direction: column;

    width: 60%;
    height: 100%;
`;

const StyledTopTitle = styled.div`
    display: flex;

    align-items: center;
    width: 100%;
    height: 60%;

    font-size: 33px;
    font-weight: bold;
`;

const StyledBottomTitle = styled.div`
    display: flex;
    width: 100%;
    height: 40%;
    font-size: 19px;
    color: gray;
`;

const StyledUpdateRecord = styled.div`
    display: flex;
    width: 40%;
    height: 40%;
    border: 1px solid black;
`;

export {
    StyledUpdateRecord,
    StyledTopTitle,
    StyledBottomTitle,
    StyledBottomContainer,
    StyledTopContainer,
    StyledLeftTopContainer,
    StyledPictureSide,
    StyledTitleInfo,
};
