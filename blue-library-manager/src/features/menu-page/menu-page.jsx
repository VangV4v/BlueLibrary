import { Flex, Menu, Typography } from 'antd';
import MenuItem from 'antd/es/menu/MenuItem';
import React from 'react';
import { Link } from 'react-router-dom';
import HomeIcon from '@mui/icons-material/Home';
import CategoryIcon from '@mui/icons-material/Category';
import PersonIcon from '@mui/icons-material/Person';
import StoreIcon from '@mui/icons-material/Store';

function MenuPage({ isAuthenticated }) {
    return (
        <>
            {isAuthenticated &&
                <MenuItem>
                    <Link to='/home'>
                        <Flex gap='middle' className='pdtc-p1'>
                            <HomeIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                            <Typography.Text className='text-menu'>Home</Typography.Text>
                        </Flex>
                    </Link>
                </MenuItem>
            }
            {isAuthenticated &&
                <MenuItem>
                    <Link to='/types'>
                        <Flex gap='middle' className='pdtc-p1'>
                            <CategoryIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                            <Typography.Text className='text-menu'>Types</Typography.Text>
                        </Flex>
                    </Link>
                </MenuItem>
            }
            {isAuthenticated &&
                <MenuItem>
                    <Link to='/login'>
                        <Flex gap='middle' className='pdtc-p1'>
                            <PersonIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                            <Typography.Text className='text-menu'>Authors</Typography.Text>
                        </Flex>
                    </Link>
                </MenuItem>
            }
            {isAuthenticated &&
                <MenuItem>
                    <Link to='/login'>
                        <Flex gap='middle' className='pdtc-p1'>
                            <StoreIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                            <Typography.Text className='text-menu'>Publishers</Typography.Text>
                        </Flex>
                    </Link>
                </MenuItem>
            }
        </>
    );
}

export default MenuPage;