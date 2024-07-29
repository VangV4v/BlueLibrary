import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import typeAPI from '../../api/types/type-api';
import { useSelector } from 'react-redux';
import { Flex } from 'antd';
import { Button } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { DataGrid, useGridApiRef } from '@mui/x-data-grid';
import { useLocation, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

const columns = [

    {
        field: 'id', headerName: 'Type ID', width: 100
    },
    {
        field: 'typeName',
        headerName: 'Type Name',
        width: 200,
    },
    {
        field: 'typeDescription',
        headerName: 'Type Description',
        width: 400,
    },
    {
        field: 'countOfBook',
        headerName: 'CountOfBook',
        type: 'number',
        width: 110,
    },
];

export default function TypePage() {
    const [dataTypes, setDataTypes] = useState([]);
    const token = useSelector(state => state.auth.authResponse.jwt);
    const apiRef = useGridApiRef();
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {

        const fetchData = async () => {

            const data = await typeAPI.findAllTypes(token);
            const listData = Array.from(data.data);

            const newDataMapped = listData.map((item) => ({
                ...item,
                id: item.typeId
            }));
            setDataTypes(newDataMapped);
        }
        fetchData();
    }, []);

    const toEditPage = () => {
        const data = apiRef.current.getSelectedRows().values().next().value;
        navigate("/types/type-edit", { state: data });
        location.state = data;
    }

    const handleDeleteType = () => {

        const data = apiRef.current.getSelectedRows().values().next().value;

        Swal.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {

            if (result.isConfirmed) {
                typeAPI.deleteType(token, data).then(response => {

                    Swal.fire({
                        title: "Deleted!",
                        text: "Your file has been deleted.",
                        icon: "success"
                    }).then((result) => {

                        if (result.isConfirmed) {

                            window.location.href = "/types";
                        }
                    });
                });
            }
        });
    };

    return (
        <>
            <Box sx={{ width: '100%' }}>
                <Flex className='pd-b1' gap='small'>
                    <Button variant='outlined' href='/types/type-add' endIcon={<AddIcon fontSize='large' />}>ADD</Button>
                    <Button variant='outlined' onClick={toEditPage} endIcon={<EditIcon fontSize='large' />}>EDIT</Button>
                    <Button variant='outlined' onClick={handleDeleteType} endIcon={<DeleteIcon fontSize='large' />}>DELETE</Button>
                </Flex>
                <DataGrid
                    rows={dataTypes}
                    columns={columns}
                    apiRef={apiRef}
                    initialState={{
                        pagination: {
                            paginationModel: {
                                pageSize: 5,
                            },
                        },
                    }}
                    pageSizeOptions={[5]}
                    checkboxSelection
                />
            </Box>
        </>
    );
}