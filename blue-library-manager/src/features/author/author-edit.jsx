import { Flex } from 'antd';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import InputField from '../../components/input/input-field/input-field';
import InputDatePicker from '../../components/input/input-datepicker/input-datepicker';
import SelectCountry from '../../components/select/select-country/select-country';
import countryList from 'react-select-country-list'
import { Button } from '@mui/material';
import PublishIcon from '@mui/icons-material/Publish';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import dayjs from 'dayjs';
import authorAPI from '../../api/authors/author-api';
import { useSelector } from 'react-redux';
import { useLocation } from 'react-router-dom';
import HiddenField from '../../components/input/input-hidden/input-hidden';

const schema = yup.object({
    fullName: yup.string().required("FullName is not empty"),
    dateOfBirth: yup.string().required("Please choose BirthDay"),
    country: yup.string().required("Please select Country"),
}).required();

function AuthorEditPage() {
    const [listCountries, setListCountries] = useState([]);
    const location = useLocation();
    const token = useSelector(state => state.auth.authResponse.jwt);
    const form = useForm({
        defaultValues: {
            fullName: location.state.fullName,
            dateOfBirth: dayjs(location.state.dateOfBirth),
            country: location.state.country,
            countOfBook: location.state.countOfBook,
            authorId: location.state.authorId
        },
        resolver: yupResolver(schema)
    });
    const handleSubmit = (data) => {

        const param = {
            country: data.country,
            fullName: data.fullName,
            authorId: data.authorId,
            countOfBook: data.countOfBook,
            dateOfBirth: dayjs(data.dateOfBirth).format("YYYY-MM-DD")
        };

        authorAPI.updateAuthor(token, param)
            .then((response) => {

                window.location.href = "/authors";
            });
    };
    useEffect(() => {

        const countries = new Array([]);
        countryList().getLabels().forEach((item) => {

            const country = {
                label: item,
                value: item
            };
            countries.push(country);
        });
        setListCountries(countries);
    }, []);
    return (
        <>
            <form onSubmit={form.handleSubmit(handleSubmit)}>
                <Flex vertical gap="large">
                    <HiddenField name="authorId" control={form.control} />
                    <HiddenField name="countOfBook" control={form.control} />
                    <InputField label="Full Name" name="fullName" control={form.control} />
                    <InputDatePicker label="BirthDay" name="dateOfBirth" control={form.control} />
                    <SelectCountry label="Country" name="country" control={form.control} options={listCountries} />
                    <Button type='submit' variant='contained' sx={{ width: '100px' }} endIcon={<PublishIcon />}>UPDATE</Button>
                </Flex>
            </form>
        </>
    );
}

export default AuthorEditPage;