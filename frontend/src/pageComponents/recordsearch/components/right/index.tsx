'use client';

import { StyledRecord, StyledRightHeader, StyledRightBody } from './Right.Styled';

const Right = (props: any) => {
    const { userId } = props;

    return (
        <>
            <StyledRightHeader>
                <div>전적 히스토리</div>
            </StyledRightHeader>
            <StyledRightBody>
                <StyledRecord />
                <StyledRecord />
                <StyledRecord />
                <StyledRecord />
                <StyledRecord />
            </StyledRightBody>
        </>
    );
};

export default Right;
