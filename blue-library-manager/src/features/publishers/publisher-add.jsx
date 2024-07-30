import React from 'react';
import { useForm } from 'react-hook-form'; import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { Flex } from 'antd';
import InputField from '../../components/input/input-field/input-field';
import InputArea from '../../components/input/input-area/input-area';
import { Button } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useSelector } from 'react-redux';
import publisherAPI from '../../api/publishers/publisher-api';

const schema = yup
    .object({
        name: yup.string().required("Name is not empty"),
        description: yup.string().required("Description is not empty"),
        address: yup.string().required("Address is not empty"),
    })
    .required();

function PublisherAddPage() {
    const form = useForm({
        defaultValues: {
            name: "",
            description: "",
            address: ""
        },
        resolver: yupResolver(schema)
    });
    const token = useSelector(state => state.auth.authResponse.jwt);

    const handleSubmit = (data) => {

        publisherAPI.addPublisher(token, data)
            .then((response) => {
                window.location.href = "/publishers";
            });
    };

    return (
        <>
            <form onSubmit={form.handleSubmit(handleSubmit)}>
                <Flex vertical gap='middle'>
                    <InputField name="name" label="Publisher Name" control={form.control} />
                    <InputField name="address" label="Address" control={form.control} />
                    <InputArea name="description" label="Description" control={form.control} row={4} />
                    <Button type='submit' variant='contained' sx={{ width: '100px' }} endIcon={<AddIcon />}>ADD</Button>
                </Flex>
            </form>
        </>
    );
}

export default PublisherAddPage;