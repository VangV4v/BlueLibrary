import { Visibility, VisibilityOff } from '@mui/icons-material';
import { IconButton, TextField } from '@mui/material';
import * as React from 'react';
import { Controller } from 'react-hook-form';

export interface PasswordFieldProps {
    label: string,
    name: string,
    control: object
}

export default function PasswordField({ name, label, control }: PasswordFieldProps) {
    const [isHidePassword, setHidePassword] = React.useState(true);
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
                    variant='standard'
                    InputProps={{
                        endAdornment: <IconButton onClick={() => { setHidePassword(!isHidePassword) }}>{isHidePassword ? <Visibility /> : <VisibilityOff />}</IconButton>
                    }}
                />
            )}
        />
    );
}