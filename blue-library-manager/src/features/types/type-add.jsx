import React from 'react';
import { useForm } from 'react-hook-form'; import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { Flex } from 'antd';
import InputField from '../../components/input/input-field/input-field';
import InputArea from '../../components/input/input-area/input-area';
import { Button } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import typeAPI from '../../api/types/type-api';
import { useSelector } from 'react-redux';

const schema = yup
    .object({
        typeName: yup.string().required("Type Name is not empty"),
        typeDescription: yup.string().required("Type Description is not empty"),
    })
    .required();

function TypeAddPage() {
    const form = useForm({
        defaultValues: {
            typeName: "",
            typeDescription: ""
        },
        resolver: yupResolver(schema)
    });
    const token = useSelector(state => state.auth.authResponse.jwt);

    const handleSubmit = (data) => {

        typeAPI.addType(token, data).then(response => {

            window.location.href = "/types";
        });
    };

    return (
        <>
            <form onSubmit={form.handleSubmit(handleSubmit)}>
                <Flex vertical gap='middle'>
                    <InputField name="typeName" label="Type Name" control={form.control} />
                    <InputArea name="typeDescription" label="Type Description" control={form.control} row={4} />
                    <Button type='submit' variant='contained' sx={{ width: '100px' }} endIcon={<AddIcon />}>ADD</Button>
                </Flex>
            </form>
        </>
    );
}

export default TypeAddPage;