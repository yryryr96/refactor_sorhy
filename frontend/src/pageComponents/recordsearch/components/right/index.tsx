'use client';

import {
    StyledRightHeaderTop,
    StyledRightHeaderBottom,
    StyledVsContainer,
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
    StyledTeamContainer,
} from './Right.Styled';
import Image from 'next/image';

const Right = (props: any) => {
    const { gameResult } = props;

    console.log(gameResult.characterId);
    return (
        <>
            <StyledRightHeader>
                <StyledRightHeaderTop>
                    <Image src="/history.svg" width={38} height={38} alt="전적 히스토리" />
                    전적 히스토리
                </StyledRightHeaderTop>
                <StyledRightHeaderBottom></StyledRightHeaderBottom>
            </StyledRightHeader>
            <StyledRightBody>
                {gameResult.map((game: any, index: number) => (
                    <StyledRecord key={index}>
                        <StyledRecordColor iswinner={game.winner} />
                        <StyledRecordMain>
                            <StyledRecordMainTop>
                                <p style={{ fontWeight: 'bold', color: game.winner ? '#218fff' : 'red' }}>
                                    {game.winner ? '승' : '패'}
                                </p>
                                <Image src="/spear.svg" width={10} height={10} alt="slash" />
                                {game.gameType === 'MULTI' ? '멀티' : '싱글'}
                                <Image src="/spear.svg" width={10} height={10} alt="slash" />
                                {game.gameTitle}
                                <Image src="/spear.svg" width={10} height={10} alt="slash" />
                                {game.createdAt}
                            </StyledRecordMainTop>
                            <StyledRecordMainBottom>
                                <StyledRecordBottomLeft>
                                    <Image
                                        src={`/chr${game.characterId}.png`}
                                        width={65}
                                        height={65}
                                        alt="게임 내 초상화"
                                        style={{ borderRadius: '30px' }}
                                    />
                                </StyledRecordBottomLeft>
                                <StyledRecordBottomRight>
                                    <p style={{ color: '#218fff' }}>
                                        {game.score}점
                                        <Image
                                            src="/cuteline.svg"
                                            width={30}
                                            height={30}
                                            alt="내 점수"
                                            style={{ borderRadius: '20px' }}
                                        />
                                    </p>
                                    <p style={{ fontSize: '16px' }}>{game.characterId}번 캐릭터</p>
                                </StyledRecordBottomRight>
                            </StyledRecordMainBottom>
                        </StyledRecordMain>

                        <StyledRecordVS>
                            <StyledVsContainer>
                                {game.teamMember.map((team: any, index: any) => (
                                    <StyledTeamContainer key={index} style={{ gap: '7px' }}>
                                        <Image
                                            src="/chr5.png"
                                            width={20}
                                            height={20}
                                            alt="팀원 1"
                                            style={{ borderRadius: '20px' }}
                                        />
                                        {team.nickname}
                                    </StyledTeamContainer>
                                ))}
                            </StyledVsContainer>
                            <Image src="/versa.svg" width={35} height={35} alt="versa" />
                            <StyledVsContainer>
                                {game.enemy.map((user: any, index: any) => (
                                    <StyledTeamContainer key={index} style={{ gap: '7px' }}>
                                        <Image
                                            src="/chr5.png"
                                            width={20}
                                            height={20}
                                            alt="팀원 1"
                                            style={{ borderRadius: '20px' }}
                                        />
                                        {user.nickname}
                                    </StyledTeamContainer>
                                ))}
                            </StyledVsContainer>
                        </StyledRecordVS>
                    </StyledRecord>
                ))}
            </StyledRightBody>
        </>
    );
};

export default Right;
