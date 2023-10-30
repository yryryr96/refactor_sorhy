'use client'

import React, { useState,useEffect } from 'react';
import Input from '@/components/input';
import { useLoginHook } from '@/hooks/user/useLoginHook';

const Login = () => {
    const {handleChange, handleSubmit, handleOnChange, inputState, user, isRemember} = useLoginHook();
    const [nickname, setNickname] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        const checkbox = document.getElementById("LoginState") as HTMLInputElement;
        if (checkbox) {
          checkbox.checked = isRemember;
        }
      }, [isRemember]);


    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>닉네임:</label>
                    <Input type="text" value={nickname} onChange={handleChange} inputstate={inputState.nickname} />
                </div>
                <div>
                    <label>비밀번호:</label>
                    <Input type="password" value={password} onChange={handleChange} inputstate={inputState.password} />
                </div>
                <button type="submit">제출</button>
            </form>
        </div>
    );
}

export default Login;