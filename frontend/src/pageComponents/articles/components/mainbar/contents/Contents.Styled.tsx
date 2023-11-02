import styled, { css } from 'styled-components';

const StyledContentsBox = styled.div`
    display: flex;
    flex-direction: column;
    width: 50vw;
    height: 68vh;
    padding: 2% 2%;
    border: 1px solid lightgray;
    border-radius: 20px;
    box-shadow: 1px 1px 2px 1px lightgray;
    gap: 5%;
`;

const StyledContentContainer = styled.div`
    width: 100%;
    height: 26%;
    border: 1px solid lightgray;
    border-radius: 10px;
    box-shadow: 1px 1px 2px 1px lightgray;
`;

export { StyledContentsBox, StyledContentContainer };
