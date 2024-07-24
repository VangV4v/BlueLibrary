import React, { useState } from 'react';
import InputField from '../../components/input/input-field/input-field';
import { useForm } from 'react-hook-form';
import InputPassword from '../../components/input/input-password/input-password';
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { Flex, Spin } from 'antd';
import { Button } from '@mui/material';
import LoginIcon from '@mui/icons-material/Login';
import { useDispatch } from 'react-redux';
import { authenticateEmployee } from '../../apps/slice/auth-slice';
import { useNavigate } from 'react-router-dom';
import { changeAuthStatus } from '../../apps/slice/auth-status';

const schema = yup
    .object({
        username: yup.string().required("Username is not empty"),
        password: yup.string().required("Password is not empty"),
    })
    .required();

function LoginPage() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [isShowLoading, setShowLoading] = useState(false);
    const form = useForm({
        defaultValues: {
            username: "",
            password: ""
        },
        resolver: yupResolver(schema)
    });
    const submitLogin = (param) => {

        setShowLoading(true);
        setTimeout(() => {

            const action = authenticateEmployee(param);
            dispatch(action)
                .unwrap()
                .then(response => {

                    localStorage.setItem("authResponse", JSON.stringify(response));
                    dispatch(changeAuthStatus(true));
                    navigate("/home");
                })
                .catch(err => {
                    setShowLoading(false);
                    console.log(err);
                });
        }, 2000)
    };
    return (
        <>
            <form onSubmit={form.handleSubmit(submitLogin)}>
                <Flex vertical gap='large'>
                    {isShowLoading && <Spin size='large'></Spin>}
                    <InputField label="Username" name="username" control={form.control} />
                    <InputPassword label="Password" name="password" control={form.control} />
                    <Button type='submit' variant='outlined' endIcon={<LoginIcon />}>LOGIN</Button>
                </Flex>

            </form>
        </>
    );
}

export default LoginPage;