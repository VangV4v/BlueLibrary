import React from 'react';
import { Controller } from 'react-hook-form';
import { TextField } from '@mui/material';

function HiddenField({ name, control }) {

    return (
        <Controller

            name={name}
            control={control}
            render={({ field, formState }) => (
                <TextField

                    sx={{
                        display: 'none'
                    }}
                    type='hidden'
                    defaultValue={formState.defaultValues}
                    fullWidth
                />
            )}
        />
    );
}

export default HiddenField;