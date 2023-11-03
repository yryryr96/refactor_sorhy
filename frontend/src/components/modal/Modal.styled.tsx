import styled, { css } from 'styled-components';
const StyledModalWrapper = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
`;

const ModalHeader = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 10%;
    padding: 0 2% 0 0;
`;

const ModalCenter = styled.div`
    display: flex;
    flex-direction: column;
    padding: 5% 5%;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 75%;
    border: 1px solid black;
`;

const StyledModal = styled.div`
    border-radius: 15px;
    box-shadow: 0px 0px 3px 1px ${(props) => props.theme.colors.gray};
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    background-color: ${(props) => props.theme.colors.white};
    display: flex;
    flex-direction: column;
    align-items: center;

    gap: 0px;
`;

const StyledBigText = styled.div.attrs<any>((props) => ({}))`
    ${(props) => {
        const font = props.theme.fonts.HangeulFontSemiBold;
        const black = props.theme.colors.black;
        const gray = props.theme.colors.gray;
        const ml = props.theme.fontSizes.medium;
        const small = props.theme.fontSizes.small;

        return css`
            font-family: ${font};
            text-align: center;

            width: 100%;
            height: 5%;
            display: flex;
            flex-direction: column;
            align-items: center;

            .title {
                color: ${black};
                font-size: ${ml};
            }

            .subtitle {
                color: ${gray};
                font-size: ${small};
            }
        `;
    }};
`;

export { ModalHeader, StyledModalWrapper, StyledModal, StyledBigText, ModalCenter };
