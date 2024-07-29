import { Select } from 'antd';
import React from 'react';
import { Controller } from 'react-hook-form';

function SelectCountry({ label, name, control, options }) {

    return (
        <Controller

            name={name}
            control={control}
            render={({ field }) => (

                <Select
                    {...field}
                    showSearch
                    options={options}
                    placeholder={label}
                    optionFilterProp='label'
                />
            )}
        />
    );
}

export default SelectCountry;