import styled, { css } from 'styled-components';

const StyledMain = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    width: 100vw;
    height: 70vh;
    /* 
    font-family: ${(props) => props.theme.fonts.HangeulFontSemiBold};
    font-size: ${(props) => props.theme.fontSizes.small};
    color: ${(props) => props.theme.colors.black};
    cursor: pointer; */
`;

const StyledMainSearchBox = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    width: 860px;
    height: 150px;
    background-image: url('/MainText1.svg');
    background-size: cover;
`;

export { StyledMain, StyledMainSearchBox };
