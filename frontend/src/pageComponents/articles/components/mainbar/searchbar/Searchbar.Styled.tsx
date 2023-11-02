import styled, { css } from 'styled-components';

const StyledSearchBar = styled.div`
    display: flex;
    flex-direction: column;
    width: 50vw;
    height: 25vh;
    padding: 3% 3%;
    border: 1px solid lightgray;
    border-radius: 20px;
    box-shadow: rgba(0, 0, 0, 0.25) 0px 0.0625em 0.0625em, rgba(0, 0, 0, 0.25) 0px 0.125em 0.5em,
        rgba(255, 255, 255, 0.1) 0px 0px 0px 1px inset;
    gap: 10%;
`;

const TopContainer = styled.div`
    width: 100%;
    height: 30%;

    font-size: 30px;
    font-weight: bold;
    color: #318fff;
`;

const BottomContainer = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;

    width: 30%; // 100%
    height: 70%;
    padding: 0% 2%;
    gap: 10%;
`;
export { StyledSearchBar, TopContainer, BottomContainer };
