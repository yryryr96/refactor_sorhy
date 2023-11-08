import styled, { css } from 'styled-components';

const StyledRankingMain = styled.div`
    display: flex;
    position: relative;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 140vh;
    background-image: url('/background21.jpg');
    background-size: cover;
`;

const StyledRankingFrame = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 75%;
    height: 90%;
    padding: 2% 2%;
    border: 1px solid lightgray;
    border-radius: 20px;
    background-color: white;
    box-shadow: rgba(0, 0, 0, 0.25) 0px 0.0625em 0.0625em, rgba(0, 0, 0, 0.25) 0px 0.125em 0.5em,
        rgba(255, 255, 255, 0.1) 0px 0px 0px 1px inset;
    gap: 20px;
`;

const StyledRankingHeader = styled.div`
    display: flex;
    align-items: center;
    width: 100%;
    height: 9%;
    font-size: 36px;
    font-weight: bold;
    color: #218fff;
    border-bottom: 2px solid #218fff;
    gap: 1%;
`;

const StyledRankingTop = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0 2%;
    width: 100%;
    height: 12%;

    gap: 3%;
`;

const StyledRankingSelector = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 8%;
    height: 68%;
    border: 1px solid #218fff;
    border-radius: 17px;
`;

const StyledRankingBody = styled.div`
    display: flex;
    flex-direction: column;

    width: 95%;
    height: 50%;
    padding: 0 2%;
    border: 1px solid gray;
    border-radius: 20px;
`;

export {
    StyledRankingMain,
    StyledRankingFrame,
    StyledRankingHeader,
    StyledRankingTop,
    StyledRankingSelector,
    StyledRankingBody,
};
