'use client';

import {
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
} from './Right.Styled';
import Image from 'next/image';

const Right = (props: any) => {
    const { userId } = props;

    return (
        <>
            <StyledRightHeader>
                <div>전적 히스토리</div>
            </StyledRightHeader>
            <StyledRightBody>
                <StyledRecord>
                    <StyledRecordColor background="red" />
                    <StyledRecordMain>
                        <StyledRecordMainTop>z</StyledRecordMainTop>
                        <StyledRecordMainBottom>
                            <StyledRecordBottomLeft>
                                <Image
                                    src="/chr1.png"
                                    width={65}
                                    height={65}
                                    alt="게임 내 초상화"
                                    style={{ borderRadius: '30px' }}
                                />
                            </StyledRecordBottomLeft>
                            <StyledRecordBottomRight></StyledRecordBottomRight>
                        </StyledRecordMainBottom>
                    </StyledRecordMain>
                    <StyledRecordVS></StyledRecordVS>
                </StyledRecord>
                <StyledRecord>
                    <StyledRecordColor background="#218fff" />
                </StyledRecord>
                <StyledRecord>
                    <StyledRecordColor background="red" />
                </StyledRecord>
                <StyledRecord>
                    <StyledRecordColor background="#218fff" />
                </StyledRecord>
                <StyledRecord>
                    <StyledRecordColor background="red" />
                </StyledRecord>
            </StyledRightBody>
        </>
    );
};

export default Right;
