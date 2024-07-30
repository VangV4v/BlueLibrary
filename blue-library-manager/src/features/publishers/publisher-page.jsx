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
import publisherAPI from '../../api/publishers/publisher-api';

const columns = [

    {
        field: 'id', headerName: 'Publisher ID', width: 150
    },
    {
        field: 'name',
        headerName: 'Name of Publisher',
        width: 200,
    },
    {
        field: 'description',
        headerName: 'Description',
        width: 400,
    },
    {
        field: 'address',
        headerName: 'Address',
        width: 200,
    },
    {
        field: 'countOfBook',
        headerName: 'CountOfBook',
        type: 'number',
        width: 110,
    },
];

export default function PublisherPage() {
    const [dataPublishers, setDataPublishers] = useState([]);
    const token = useSelector(state => state.auth.authResponse.jwt);
    const apiRef = useGridApiRef();
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {

        const fetchData = async () => {

            const data = await publisherAPI.findAllPublishers(token);
            const listData = Array.from(data.data);
            const dataAfterMapped = listData.map((item) => ({

                ...item,
                id: item.publisherId
            }));
            setDataPublishers(dataAfterMapped);
        }
        fetchData();
    }, []);

    const toEditPage = () => {
        const data = apiRef.current.getSelectedRows().values().next().value;
        navigate("/publishers/publisher-edit", { state: data });
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
                publisherAPI.deletePublisher(token, data).then(response => {

                    Swal.fire({
                        title: "Deleted!",
                        text: "Your file has been deleted.",
                        icon: "success"
                    }).then((result) => {

                        if (result.isConfirmed) {

                            window.location.href = "/publishers";
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
                    <Button variant='outlined' href='/publishers/publisher-add' endIcon={<AddIcon fontSize='large' />}>ADD</Button>
                    <Button variant='outlined' onClick={toEditPage} endIcon={<EditIcon fontSize='large' />}>EDIT</Button>
                    <Button variant='outlined' onClick={handleDeleteType} endIcon={<DeleteIcon fontSize='large' />}>DELETE</Button>
                </Flex>
                <DataGrid
                    rows={dataPublishers}
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