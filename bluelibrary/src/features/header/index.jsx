import { AppBar, Box, Button, Container, Stack, Toolbar, Typography } from '@mui/material';
import logo from '../../assets/images/logolibrary.png';
import React from 'react';
import '../../assets/css/main.css';
import LoginIcon from '@mui/icons-material/Login';

function HeaderPage(props) {
    return (
        <Box>
            <AppBar className='MyAppBar'
                position='static'
                style={{
                    backgroundColor: 'white'
                }}
            >
                <Container maxWidth='xl'>
                    <Toolbar>
                        <Box sx={{ flexGrow: 1 }}>
                            <Stack direction='row' spacing={1}>
                                <Button sx={{ mr: 4 }}>
                                    <img src={logo} className='logo-appbar' alt='logo' />
                                    <Typography>BLUE LIBRARY</Typography>
                                </Button>
                                <Button><Typography>Home</Typography></Button>
                                <Button><Typography>Books</Typography></Button>
                                <Button><Typography>BLOG</Typography></Button>
                            </Stack>
                        </Box>
                        <Box sx={{ flexGrow: 0 }}>
                            <Button startIcon={<LoginIcon />}>Login</Button>
                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
        </Box>
    );
}

export default HeaderPage;