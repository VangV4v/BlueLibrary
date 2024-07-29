import { Flex } from 'antd';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import InputField from '../../components/input/input-field/input-field';
import InputDatePicker from '../../components/input/input-datepicker/input-datepicker';
import SelectCountry from '../../components/select/select-country/select-country';
import countryList from 'react-select-country-list'
import { Button } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import dayjs from 'dayjs';

const schema = yup.object({
    fullName: yup.string().required("FullName is not empty"),
    dateOfBirth: yup.string().required("Please choose BirthDay"),
    country: yup.string().required("Please select Country"),
}).required();

function AuthorAddPage() {
    const [listCountries, setListCountries] = useState([]);
    const form = useForm({
        defaultValues: {
            fullName: "",
            dateOfBirth: dayjs("2023-12-01"),
            country: ""
        },
        resolver: yupResolver(schema)
    });
    const handleSubmit = (data) => {
        console.log(data);
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
                    <InputField label="Full Name" name="fullName" control={form.control} />
                    <InputDatePicker label="BirthDay" name="dateOfBirth" control={form.control} />
                    <SelectCountry label="Country" name="country" control={form.control} options={listCountries} />
                    <Button type='submit' variant='contained' sx={{ width: '100px' }} endIcon={<AddIcon />}>ADD</Button>
                </Flex>
            </form>
        </>
    );
}

export default AuthorAddPage;