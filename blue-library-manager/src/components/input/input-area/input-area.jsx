import React from 'react';
import { Controller } from 'react-hook-form';
import { TextField } from '@mui/material';

function InputArea({ label, name, control, row }) {
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
                    fullWidth
                    multiline
                    defaultValue={formState.defaultValues}
                    rows={row}
                />
            )}
        />
    );
}

export default InputArea;