import styled, { css } from 'styled-components';

const StyledRightHeader = styled.div`
    display: flex;
    flex-direction: column;
    padding: 4% 0;
    width: 100%;
    height: 23.5%;
    font-size: 30px;
    font-weight: bold;
    border: 1px solid lightgray;
    color: #86bebe;
`;

const StyledRightBody = styled.div`
    display: flex;
    flex-direction: column;
    padding: 3% 3%;
    width: 100%;
    height: 71.5%;
    border: 1px solid gray;
    border-radius: 20px;
    gap: 10px;
`;

const StyledRecord = styled.div`
    display: flex;
    width: 100%;
    height: 20%;
    border: 1px solid #bfbfbf;
    border-radius: 20px;
`;

export { StyledRecord, StyledRightHeader, StyledRightBody };
