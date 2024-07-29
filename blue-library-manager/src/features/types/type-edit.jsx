import React from 'react';
import { useForm } from 'react-hook-form'; import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { Flex } from 'antd';
import InputField from '../../components/input/input-field/input-field';
import InputArea from '../../components/input/input-area/input-area';
import { Button } from '@mui/material';
import PublishIcon from '@mui/icons-material/Publish';
import typeAPI from '../../api/types/type-api';
import { useSelector } from 'react-redux';
import { useLocation } from 'react-router-dom';
import HiddenField from '../../components/input/input-hidden/input-hidden';

const schema = yup
    .object({
        typeName: yup.string().required("Type Name is not empty"),
        typeDescription: yup.string().required("Type Description is not empty"),
    })
    .required();

function TypeEditPage() {

    const location = useLocation();
    const form = useForm({
        defaultValues: {
            typeName: location.state.typeName,
            typeDescription: location.state.typeDescription,
            typeId: location.state.typeId,
            countOfBook: location.state.countOfBook
        },
        resolver: yupResolver(schema)
    });
    const token = useSelector(state => state.auth.authResponse.jwt);
    const handleSubmit = (data) => {

        typeAPI.updateType(token, data).then(response => {

            window.location.href = "/types";
        });
    };

    return (
        <>
            <form onSubmit={form.handleSubmit(handleSubmit)}>
                <Flex vertical gap='middle'>
                    <HiddenField name="typeId" control={form.control} />
                    <HiddenField name="countOfBook" control={form.control} />
                    <InputField name="typeName" label="Type Name" control={form.control} />
                    <InputArea name="typeDescription" label="Type Description" control={form.control} row={4} />
                    <Button type='submit' variant='contained' sx={{ width: '100px' }} endIcon={<PublishIcon />}>UPDATE</Button>
                </Flex>
            </form>
        </>
    );
}

export default TypeEditPage;