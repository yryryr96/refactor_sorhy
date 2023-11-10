import styled, { css } from 'styled-components';

const StyledRightHeader = styled.div`
    display: flex;
    flex-direction: column;
    padding: 5% 0;
    width: 100%;
    height: 23.5%;
    font-size: 30px;
    font-weight: bold;
    /* border: 1px solid lightgray; */
    color: #86bebe;
`;

const StyledRightBody = styled.div`
    display: flex;
    flex-direction: column;
    padding: 3% 3%;
    width: 90%;
    height: 71.5%;
    border: 1px solid gray;
    border-radius: 20px;
    gap: 10px;
`;

const StyledRecord = styled.div`
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 20%;
    border: 1px solid #bfbfbf;
    border-radius: 20px;
`;

const StyledRecordColor = styled.div<any>`
    display: flex;
    width: 4%;
    background-color: ${(props) => props.background || '#218fff'};

    border-bottom-left-radius: 20px;
    border-top-left-radius: 20px;
`;

const StyledRecordMain = styled.div<any>`
    display: flex;
    flex-direction: column;
    width: 50%;
    height: 100%;
    border: 1px solid black;
`;

const StyledRecordMainTop = styled.div<any>`
    display: flex;
    width: 100%;
    height: 30%;
    border: 1px solid black;
`;
const StyledRecordMainBottom = styled.div<any>`
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 70%;
    border: 1px solid black;
`;

const StyledRecordBottomLeft = styled.div<any>`
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40%;
    height: 100%;
    border: 1px solid black;
`;

const StyledRecordBottomRight = styled.div<any>`
    display: flex;
    align-items: center;
    width: 60%;
    height: 100%;
    border: 1px solid black;
`;

const StyledRecordVS = styled.div<any>`
    display: flex;
    width: 46%;
    height: 100%;
    border: 1px solid black;
`;

export {
    StyledRecordBottomLeft,
    StyledRecordBottomRight,
    StyledRecordMainBottom,
    StyledRecordMainTop,
    StyledRecordVS,
    StyledRecordMain,
    StyledRecordColor,
    StyledRecord,
    StyledRightHeader,
    StyledRightBody,
};
