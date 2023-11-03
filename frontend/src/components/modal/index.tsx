import { useRouter } from 'next/navigation';
import Button from '../button';
import { StyledModalWrapper, StyledModal, StyledBigText, ModalHeader, ModalCenter } from './Modal.styled';
import { ModalType } from './Modal.type';

const Modal = (props: ModalType) => {
    const router = useRouter();
    return props.isOpen ? (
        <StyledModalWrapper>
            <StyledModal style={{ backgroundColor: 'white', width: '700px', height: '700px' }}>
                <ModalHeader>
                    {props.imgsrc ? (
                        <img src={props.imgsrc} alt="Image from props" style={{ width: '10%', height: '5%' }} />
                    ) : (
                        <img src="/pencil_icon2.svg" alt="Default Image" style={{ width: '8%', height: '47%' }} />
                    )}
                    <p style={{ fontSize: '25px', fontWeight: 'bold' }}>글 작성</p>
                </ModalHeader>

                <StyledBigText>
                    <div className="title">{props.bigtext}</div>
                    {props.smalltext !== '' ? <div className="subtitle"> {props.smalltext} </div> : null}
                </StyledBigText>
                <ModalCenter></ModalCenter>
                <div style={{ display: 'flex', justifyContent: 'space-evenly', width: '100%' }}>
                    <div style={{ width: '43%', height: '38px' }}>
                        <Button
                            use="bgGray"
                            label={props.cancel}
                            style={{ borderRadius: '5px' }}
                            onClick={props.onClose}
                        />
                    </div>
                    <div style={{ width: '43%', height: '38px' }}>
                        <Button
                            use={'#318fff'}
                            label={props.confirm}
                            style={{ borderRadius: '5px' }}
                            onClick={() => {
                                if (props.onConfirmClick) {
                                    props.onConfirmClick();
                                } else if (props.onDelete) {
                                    props.onDelete();
                                }
                            }}
                        />
                    </div>
                </div>
            </StyledModal>
        </StyledModalWrapper>
    ) : null;
};

export default Modal;
