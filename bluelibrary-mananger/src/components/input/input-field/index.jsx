import React from 'react';
import PropTypes from 'prop-types';
import { Controller } from 'react-hook-form';
import { TextField } from '@mui/material';

InputField.propTypes = {
    label: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    control: PropTypes.object.isRequired,
    inputChange: PropTypes.func.isRequired,
};

function InputField({ label, name, control, inputChange }) {

    function changeFlag() {
        inputChange(true);
    }

    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField
                    {...field}
                    label={label}
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    fullWidth
                    onClick={changeFlag}
                />
            )}
        />
    );
}

export default InputField;