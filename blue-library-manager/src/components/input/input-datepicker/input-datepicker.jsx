import React from 'react';
import { Controller } from 'react-hook-form';
import { DatePicker } from 'antd';
import dayjs from 'dayjs';

function InputDatePicker({ label, name, control, width }) {
    return (
        <Controller

            name={name}
            control={control}
            render={({ field }) => (
                <DatePicker

                    {...field}
                    format="YYYY-MM-DD"
                    size='large'
                    // placeholder={label}
                    style={{
                        width: width || "400px"
                    }}
                />
            )}
        />
    );
}

export default InputDatePicker;