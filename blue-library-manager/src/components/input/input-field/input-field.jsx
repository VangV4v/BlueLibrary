import React from 'react';
import { Controller } from 'react-hook-form';
import { TextField } from '@mui/material';

function InputField({ label, name, control }) {

    return (
        <Controller

            name={name}
            control={control}
            render={({ field, fieldState, formState }) => (
                <TextField

                    {...field}
                    label={label}
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    defaultValue={formState.defaultValues}
                    fullWidth
                />
            )}
        />
    );
}

export default InputField;