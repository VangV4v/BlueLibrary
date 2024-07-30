import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import { useSelector } from 'react-redux';
import { Flex } from 'antd';
import { Button } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import authorAPI from '../../api/authors/author-api';
import { DataGrid, useGridApiRef } from '@mui/x-data-grid';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

const columns = [

    {
        field: 'id', headerName: 'Author ID', width: 150
    },
    {
        field: 'fullName',
        headerName: 'Full Name',
        width: 400,
    },
    {
        field: 'dateOfBirth',
        headerName: 'Date Of Birth',
        width: 200,
    },
    {
        field: 'country',
        headerName: 'Country',
        width: 200,
    },
    {
        field: 'countOfBook',
        headerName: 'CountOfBook',
        type: 'number',
        width: 110,
    },
];

export default function AuthorPage() {
    const token = useSelector(state => state.auth.authResponse.jwt);
    const navigate = useNavigate();
    const [dataAuthor, setDataAuthor] = useState([]);
    const apiRef = useGridApiRef();

    const toEditPage = () => {

        const authorData = apiRef.current.getSelectedRows().values().next().value;
        navigate("/authors/author-edit", {
            state: authorData
        });
    };

    const handleDeleteAuthor = () => {

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

                authorAPI.deleteAuthor(token, data)
                    .then((response) => {

                        Swal.fire({
                            title: "Deleted!",
                            text: "Your file has been deleted.",
                            icon: "success"
                        }).then((result) => {

                            if (result.isConfirmed) {

                                window.location.href = "/authors";
                            }
                        });
                    });

            }
        });
    };

    useEffect(() => {

        //get api
        const fetchApi = async () => {

            const response = await authorAPI.findAllAuthors(token);
            const listData = Array.from(response.data);
            const newDataMapped = listData.map((item) => ({
                ...item,
                id: item.authorId
            }));
            setDataAuthor(newDataMapped);
        };
        fetchApi();

    }, []);

    return (
        <>
            <Box sx={{ width: '100%' }}>
                <Flex className='pd-b1' gap='small'>
                    <Button variant='outlined' href='/authors/author-add' endIcon={<AddIcon fontSize='large' />}>ADD</Button>
                    <Button variant='outlined' onClick={toEditPage} endIcon={<EditIcon fontSize='large' />}>EDIT</Button>
                    <Button variant='outlined' onClick={handleDeleteAuthor} endIcon={<DeleteIcon fontSize='large' />}>DELETE</Button>
                </Flex>
                <DataGrid
                    rows={dataAuthor}
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