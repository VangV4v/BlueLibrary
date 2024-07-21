import { useForm } from 'react-hook-form';
import InputField from '../../components/input/input-field/inputfield';
import AuthenticateModel from '../../models/AuthenticateModel';
import PasswordField from '../../components/input/password-field/passwordfield';
import { Col, Flex, Row } from 'antd';
import { Button } from '@mui/material';
import LoginIcon from '@mui/icons-material/Login';
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import authenticateApi from '../../api/authapi/authapi';

const schema = yup
    .object({
        username: yup.string().required("Username is not empty"),
        password: yup.string().required("Password is not empty"),
    })
    .required()

export default function LoginPage() {
    const form = useForm({
        defaultValues: {
            username: '',
            password: ''
        },
        resolver: yupResolver(schema)
    });
    const handleLogin = (data: AuthenticateModel) => {

        const auth = authenticateApi.authenticate(data);
        console.log(auth);
    };
    return (
        <>
            <form onSubmit={form.handleSubmit(handleLogin)}>
                <Row>
                    <Col span={6}></Col>
                    <Col span={12}>
                        <Flex vertical gap='large'>
                            <InputField name='username' label='Username' control={form.control}></InputField>
                            <PasswordField name='password' label='Password' control={form.control} />
                            <Button type='submit' endIcon={<LoginIcon />} variant='contained'>LOGIN</Button>
                        </Flex>
                    </Col>
                    <Col span={6}></Col>
                </Row>
            </form >
        </>
    );
}