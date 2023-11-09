'use client';

import { StyledRecordMain } from './RecordSearch.Styled';
import LeftBottom from './components/leftbottom';
import LeftTop from './components/lefttop';
import LeftMid from './components/leftmid';
import Right from './components/right';

const RecordSearch = (props: any) => {
    const { userId } = props;

    return (
        <StyledRecordMain>
            <LeftTop userId={userId} />
            <LeftMid userId={userId} />
            <LeftBottom userId={userId} />
            <Right userId={userId} />
        </StyledRecordMain>
    );
};

export default RecordSearch;
