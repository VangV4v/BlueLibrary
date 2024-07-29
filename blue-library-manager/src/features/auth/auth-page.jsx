import { Button, Flex, Modal, Typography } from 'antd';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import { useDispatch } from 'react-redux';
import { changeAuthStatus } from '../../apps/slice/auth-status';
import sessionStorage from 'redux-persist/es/storage/session';
import { logoutEmployee } from '../../apps/slice/auth-slice';

function AuthPage({ isAuthenticated }) {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        sessionStorage.removeItem("authResponse");
        sessionStorage.removeItem("authStatus");
        dispatch(changeAuthStatus(false));
        dispatch(logoutEmployee());
        setIsModalOpen(false);
        navigate("/login");

    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };

    return (
        <>
            {!isAuthenticated ?
                <Button href='/login' type='text'>
                    <Flex gap='middle' className='pdtc-p1'>
                        <LoginIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                        <Typography.Text className='text-menu'>Login</Typography.Text>
                    </Flex>
                </Button>
                :
                <Button onClick={showModal} type='text'>
                    <Flex gap='middle' className='pdtc-p1'>
                        <LogoutIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                        <Typography.Text className='text-menu'>Logout</Typography.Text>
                    </Flex>
                </Button>
            }
            <Modal title="Do you want logout account ?" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
            </Modal>
        </>
    );
}

export default AuthPage;