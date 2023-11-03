'use client';
import React, { useState } from 'react';
import {
    StyledSearchBar,
    TopContainer,
    BottomContainer,
    ClickBox,
    SelectBox,
    SearchInput,
    SearchButton,
} from './Searchbar.Styled';
import DropBox from '@/components/dropbox';
import Image from 'next/image';

const SearchBar = () => {
    const [selectedOption, setSelectedOption] = useState<String>('');

    const options = [
        { label: 'Option 1', value: 'option1' },
        { label: 'Option 2', value: 'option2' },
        { label: 'Option 3', value: 'option3' },
    ];

    return (
        <StyledSearchBar>
            <TopContainer>
                검색 옵션
                <Image src="/pencil_icon2.svg" width={30} height={30} alt="Pencil_Icon" />
            </TopContainer>
            <BottomContainer>
                <ClickBox>
                    <Image src="/recenticon.svg" width={35} height={35} alt="Recent" />
                    Recent
                </ClickBox>
                <ClickBox color="red">
                    <Image src="/hoticon.svg" width={35} height={35} alt="Hot" />
                    Hot
                </ClickBox>
                <SelectBox>
                    <option value="option1">자유 게시판</option>
                    <option value="option2">회사 게시판</option>
                    <option value="option3">Tips</option>
                </SelectBox>
                <SearchInput type="text" placeholder="키워드를 입력하세요" />
                <SearchButton type="submit">검색</SearchButton>
            </BottomContainer>
        </StyledSearchBar>
    );
};

export default SearchBar;
