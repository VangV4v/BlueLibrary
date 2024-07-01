import { Col, Image, Row, Typography } from 'antd';
import React, { useState } from 'react';
import loginImage from '../../assets/images/background/loginimage.jpg'
import { Box, Button, CircularProgress, Container, Stack } from '@mui/material';
import { useForm } from 'react-hook-form';
import { yupResolver } from "@hookform/resolvers/yup"
import InputField from '../../components/input/input-field';
import * as yup from "yup"
import PasswordField from '../../components/input/password-field';
import { Login } from '@mui/icons-material';
import { useDispatch } from 'react-redux';
import { authenticate } from '../../apps/slice/authenticate';
import { useNavigate } from 'react-router-dom';
const { Text } = Typography;

const schema = yup
    .object({
        username: yup.string().required("Username must be entered"),
        password: yup.string().required("Password must be entered"),
    }).required();

function LoginPage(props) {

    const [isFlagNotification, setFlagNotification] = useState(false);
    const [isLoading, setLoading] = useState(false);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const form = useForm({
        defaultValues: {
            username: '',
            password: ''
        },
        resolver: yupResolver(schema)
    });
    function handleSubmit(data) {
        const action = authenticate(data);
        dispatch(action)
            .unwrap()
            .then(success => {
                setLoading(true);
                setTimeout(() => {
                    setLoading(false);
                    return navigate("/");
                }, 2500)
            })
            .catch(err => {
                setFlagNotification(true);
            });
    }
    const getFlag = (flag) => {
        setFlagNotification(false);
    }
    return (
        <Container sx={{ pt: 5 }}>
            <Row>
                <Col span={6}>
                    <Box className='pdt-10'>
                        <form onSubmit={form.handleSubmit(handleSubmit)}>
                            <Stack spacing={4}>
                                <Box>
                                    <center>
                                        {isLoading && <CircularProgress></CircularProgress>}
                                    </center>
                                </Box>
                                <InputField label='UserName' name='username' control={form.control} inputChange={getFlag} />
                                <PasswordField label='PassWord' name='password' control={form.control} inputChange={getFlag} />
                                {isFlagNotification && <Text type='danger'>Username or Password is inccorect</Text>}
                                <Button type='submit' variant='contained' endIcon={<Login />}>LOGIN</Button>
                            </Stack>
                        </form>
                    </Box>
                </Col>
                <Col span={1}></Col>
                <Col span={17}>
                    <Image src={loginImage} preview={false} alt='Keke' width='750px' height='650px' />
                </Col>
            </Row >
        </Container>
    );
}

export default LoginPage;