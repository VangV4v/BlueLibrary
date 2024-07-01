import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Controller } from 'react-hook-form';
import { IconButton, TextField } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';


PasswordField.propTypes = {
    label: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    control: PropTypes.object.isRequired,
    inputChange: PropTypes.func.isRequired,
};

function PasswordField({ label, name, control, inputChange }) {

    const [isShowPassword, setShowPassword] = useState(false);

    function handleShowPassword() {

        setShowPassword(!isShowPassword);
    }

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
                    type={!isShowPassword ? 'password' : 'text'}
                    InputProps={{
                        endAdornment: <IconButton onClick={handleShowPassword}> {!isShowPassword ? <Visibility /> : <VisibilityOff />} </IconButton>
                    }}
                    onClick={changeFlag}
                />
            )}
        />
    );
}

export default PasswordField;