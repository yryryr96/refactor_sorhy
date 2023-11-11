'use client';

import { useState,useEffect } from 'react';
import { StyledRecordMain, StyledRecordFrame, StyledLeftContainer, StyledRightContainer } from './RecordSearch.Styled';
import LeftBottom from './components/leftbottom';
import LeftTop from './components/lefttop';
import LeftMid from './components/leftmid';
import Right from './components/right';
import { loadBindings } from 'next/dist/build/swc';
import userSearchGet from '@/api/search/userSearchGet';

const RecordSearch = (props: any) => {
    const { userId } = props;
    const [userInfo, setUserInfo] = useState<any>([]);
    const [loading,setLoading] = useState(true);
    useEffect(() => {
        userSearchGet(userId)
            .then((res) => {
                setUserInfo(res.result);
                setLoading(false);
            })
            .catch((error) => {
                console.error('에러가 발생하였습니다:', error);
            });
    }, []);
    console.log(userInfo)
    return (
        <>
            <StyledRecordMain>
                <StyledRecordFrame>
                    <StyledLeftContainer>
                        <LeftTop userId={userId} />
                        <LeftMid userId={userId} />
                        <LeftBottom userId={userId} />
                    </StyledLeftContainer>
                    <StyledRightContainer>
                        <Right userId={userId} />
                    </StyledRightContainer>
                </StyledRecordFrame>
            </StyledRecordMain>
        </>
    );
};

export default RecordSearch;
