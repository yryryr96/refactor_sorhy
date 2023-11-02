'use client';

import { StyledSideBar, StyledSideItem } from './Sidebar.Styled';
import Image from 'next/image';

const SideBar = (props: any) => {
    const path = props.selectbtn;
    return (
        <StyledSideBar>
            <StyledSideItem font_size="20px" font_weight="bold" color="#318fff">
                게시판
                <Image src="/rightarrow.svg" priority={true} width={20} height={15} alt="오른쪽 화살표" />
            </StyledSideItem>
            <StyledSideItem>자유 게시판</StyledSideItem>
            <StyledSideItem>회사 게시판</StyledSideItem>
            <StyledSideItem font_size="20px" font_weight="bold" color="#318fff">
                나머지
                <Image src="/rightarrow.svg" priority={true} width={20} height={15} alt="오른쪽 화살표" />
            </StyledSideItem>
            <StyledSideItem>Tips</StyledSideItem>
            <StyledSideItem>버그 신고</StyledSideItem>
        </StyledSideBar>
    );
};

export default SideBar;
