'use client';

import Image from 'next/image';
import Input from '@/components/input';
import { StyledMain, StyledMainSearchBox } from './MainPage.Styled';

const MainPage = () => {
    return (
        <StyledMain>
            <Image src="/Logo.png" priority={true} width={430} height={110} alt="Logo" />
            <StyledMainSearchBox>
                <Input width={760} height={70} border_radi={60} />
            </StyledMainSearchBox>

            {/* <Image src="/MainText.png" priority={true} width={860} height={120} alt="Logo" /> */}
        </StyledMain>
    );
};

export default MainPage;
