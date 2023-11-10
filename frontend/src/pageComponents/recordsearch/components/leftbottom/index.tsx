'use client';

import {
    StyledLeftBottomContainer,
    StyledBottomHeader,
    StyledBottomBody,
    StyledCharContent,
} from './LeftBottom.Styled';
import Image from 'next/image';
const LeftBottom = (props: any) => {
    const { userId } = props;

    return (
        <StyledLeftBottomContainer>
            <StyledBottomHeader>
                <Image src="/chr2.png" width={35} height={35} alt="내 최애 캐릭터" />내 최애 캐릭터는?
            </StyledBottomHeader>
            <StyledBottomBody>
                <StyledCharContent>
                    <Image src="/chr2.png" width={35} height={35} alt="내 최애 캐릭터 1" />
                    정영록정영록정영록
                </StyledCharContent>
                <StyledCharContent>
                    <Image src="/chr3.png" width={35} height={35} alt="내 최애 캐릭터 2" />
                    정영록정영록정영록
                </StyledCharContent>
                <StyledCharContent>
                    <Image src="/chr4.png" width={35} height={35} alt="내 최애 캐릭터 3" />
                    정영록정영록정영록
                </StyledCharContent>
            </StyledBottomBody>
        </StyledLeftBottomContainer>
    );
};

export default LeftBottom;
