import React from 'react';
import { useForm } from 'react-hook-form'; import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { Flex } from 'antd';
import InputField from '../../components/input/input-field/input-field';
import InputArea from '../../components/input/input-area/input-area';
import { Button } from '@mui/material';
import PublishIcon from '@mui/icons-material/Publish';
import { useSelector } from 'react-redux';
import { useLocation } from 'react-router-dom';
import HiddenField from '../../components/input/input-hidden/input-hidden';
import publisherAPI from '../../api/publishers/publisher-api';

const schema = yup
    .object({
        name: yup.string().required("Name is not empty"),
        description: yup.string().required("Description is not empty"),
        address: yup.string().required("Address is not empty"),
    })
    .required();

function PublisherEditPage() {

    const location = useLocation();
    const form = useForm({
        defaultValues: {
            name: location.state.name,
            description: location.state.description,
            address: location.state.address,
            countOfBook: location.state.countOfBook,
            publisherId: location.state.publisherId
        },
        resolver: yupResolver(schema)
    });
    const token = useSelector(state => state.auth.authResponse.jwt);
    const handleSubmit = (data) => {

        publisherAPI.editPublisher(token, data)
            .then((response) => {

                window.location.href = "/publishers";
            });
    };

    return (
        <>
            <form onSubmit={form.handleSubmit(handleSubmit)}>
                <Flex vertical gap='middle'>
                    <HiddenField name="publisherId" control={form.control} />
                    <HiddenField name="countOfBook" control={form.control} />
                    <InputField name="name" label="Publisher Name" control={form.control} />
                    <InputField name="address" label="Address" control={form.control} />
                    <InputArea name="description" label="Description" control={form.control} row={4} />
                    <Button type='submit' variant='contained' sx={{ width: '100px' }} endIcon={<PublishIcon />}>UPDATE</Button>
                </Flex>
            </form>
        </>
    );
}

export default PublisherEditPage;