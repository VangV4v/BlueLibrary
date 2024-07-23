import React from 'react';
import { UploadOutlined, UserOutlined, VideoCameraOutlined } from '@ant-design/icons';
import { Flex, Image, Layout, Menu, theme, Typography } from 'antd';
import { Button } from '@mui/material';
import iconLogo from '../../assets/images/icons/icon-logo.png';
import { Link, Route, Routes } from 'react-router-dom';
import HomePage from '../homepage/home-page';
import LoginPage from '../login/login';
import NotFound from '../notfound/404';
import MenuItem from 'antd/es/menu/MenuItem';
import HomeIcon from '@mui/icons-material/Home';
import CategoryIcon from '@mui/icons-material/Category';
import PersonIcon from '@mui/icons-material/Person';
import StoreIcon from '@mui/icons-material/Store';
const { Header, Content, Footer, Sider } = Layout;
const items = [UserOutlined, VideoCameraOutlined, UploadOutlined, UserOutlined].map(
    (icon, index) => ({
        key: String(index + 1),
        icon: React.createElement(icon),
        label: `nav ${index + 1}`,
    }),
);
const MainLayout = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout>
            <Sider
                theme='light'
                breakpoint="lg"
                collapsedWidth="0"
                onBreakpoint={(broken) => {
                }}
                onCollapse={(collapsed, type) => {
                }}
            >
                <div className="demo-logo-vertical" />
                <Link to='/home'>
                    <Flex>
                        <Image preview={false} width='75px' src={iconLogo} />
                        <Typography.Title>LUE</Typography.Title>
                    </Flex>
                </Link>
                <Menu>
                    <MenuItem>
                        <Link to='/home'>
                            <Flex gap='middle' className='pdtc-p1'>
                                <HomeIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                                <Typography.Text className='text-menu'>Home</Typography.Text>
                            </Flex>
                        </Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to='/categories'>
                            <Flex gap='middle' className='pdtc-p1'>
                                <CategoryIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                                <Typography.Text className='text-menu'>Categories</Typography.Text>
                            </Flex>
                        </Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to='/login'>
                            <Flex gap='middle' className='pdtc-p1'>
                                <PersonIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                                <Typography.Text className='text-menu'>Authors</Typography.Text>
                            </Flex>
                        </Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to='/login'>
                            <Flex gap='middle' className='pdtc-p1'>
                                <StoreIcon sx={{ color: '#7F82FF' }} fontSize='medium' />
                                <Typography.Text className='text-menu'>Publishers</Typography.Text>
                            </Flex>
                        </Link>
                    </MenuItem>
                </Menu>
            </Sider>
            <Layout>
                <Header
                    style={{
                        padding: 0,
                        background: colorBgContainer,
                    }}
                />
                <Content
                    style={{
                        margin: '24px 16px 0',
                    }}
                >
                    <div
                        style={{
                            padding: 24,
                            minHeight: 360,
                            background: colorBgContainer,
                            borderRadius: borderRadiusLG,
                        }}
                    >
                        <Routes>
                            <Route path='/' element={<HomePage />} />
                            <Route path='/home' element={<HomePage />} />
                            <Route path='/login' element={<LoginPage />} />
                            <Route path='*' element={<NotFound />} />
                        </Routes>
                    </div>
                </Content>
                <Footer
                    style={{
                        textAlign: 'center',
                    }}
                >
                    Ant Design ©{new Date().getFullYear()} Created by Ant UED
                </Footer>
            </Layout>
        </Layout>
    );
};
export default MainLayout;