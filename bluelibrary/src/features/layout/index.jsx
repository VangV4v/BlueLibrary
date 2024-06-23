import React from 'react';
import HeaderPage from '../header';
import { Box, Container } from '@mui/material';
import carousel1 from '../../assets/images/carousel/carousel1.jpg';
import carousel2 from '../../assets/images/carousel/carousel2.jpg';
import carousel3 from '../../assets/images/carousel/carousel3.jpg';
import { Carousel, Image } from 'antd';

function MainLayout(props) {

    return (
        <>
            <HeaderPage></HeaderPage>
            <Box sx={{ pt: 1 }}>
                <Container>
                    <Carousel arrows speed={1000} autoplay>
                        <Image src={carousel1} />
                        <Image src={carousel2} />
                        <Image src={carousel3} />
                    </Carousel>
                </Container>
            </Box>
            <Box sx={{ pt: 2 }}>
                Hello
            </Box>
        </>
    );
}

export default MainLayout;