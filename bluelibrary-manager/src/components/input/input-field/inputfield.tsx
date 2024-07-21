import { TextField } from '@mui/material';
import { Controller } from 'react-hook-form';

export interface InputFieldProps {
    label: string,
    name: string,
    control: object
}

export default function InputField({ label, name, control }: InputFieldProps) {
    return (
        <Controller

            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField {...field}
                    label={label}
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    variant='standard'
                />
            )}
        />
    );
}
