import { Visibility, VisibilityOff } from '@mui/icons-material';
import { IconButton, TextField } from '@mui/material';
import React, { useState } from 'react';
import { Controller } from 'react-hook-form';

function InputPassword({ label, name, control }) {

    const [isHidePassword, setHidePassword] = useState(true);
    return (
        <Controller

            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField

                    {...field}
                    type={isHidePassword ? 'password' : 'text'}
                    label={label}
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    fullWidth
                    InputProps={{
                        endAdornment: <IconButton onClick={() => setHidePassword(!isHidePassword)}>{isHidePassword ? <Visibility /> : <VisibilityOff />}</IconButton>
                    }}
                />
            )
            }
        />
    );
}

export default InputPassword;