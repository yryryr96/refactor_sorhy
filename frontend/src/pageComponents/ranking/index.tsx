'use client';

import {
    StyledRankingMain,
    StyledRankingFrame,
    StyledRankingHeader,
    StyledRankingTop,
    StyledRankingSelector,
    StyledRankingBody,
} from './Ranking.Styled';
import Image from 'next/image';

const Ranking = () => {
    return (
        <StyledRankingMain>
            <StyledRankingFrame>
                <StyledRankingHeader>
                    <Image src="/ranking.svg" width={50} height={50} alt="Ranking" />
                    Ranking
                </StyledRankingHeader>
                <StyledRankingTop>
                    <StyledRankingSelector>
                        <Image src="/house.svg" width={35} height={35} alt="house" />
                        House
                    </StyledRankingSelector>
                    <StyledRankingSelector>
                        <Image src="/balloon.svg" width={35} height={35} alt="balloon" />
                        Balloon
                    </StyledRankingSelector>
                    <StyledRankingSelector>
                        <Image src="/swim.svg" width={35} height={35} alt="swim" />
                        Swim
                    </StyledRankingSelector>
                    <StyledRankingSelector>
                        <Image src="/enterprise.svg" width={35} height={35} alt="enterprise" />
                        Company
                    </StyledRankingSelector>
                </StyledRankingTop>
                <StyledRankingBody>GOOD</StyledRankingBody>
            </StyledRankingFrame>
        </StyledRankingMain>
    );
};

export default Ranking;
