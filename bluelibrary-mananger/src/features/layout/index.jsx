import React, { useState } from 'react';
import {
    DesktopOutlined,
    FileOutlined,
    PieChartOutlined,
    TeamOutlined,
    UserOutlined,
} from '@ant-design/icons';
import { Breadcrumb, Image, Layout, Menu, Slider, theme } from 'antd';
import FooterPage from './footer';
import { Route, Routes } from 'react-router-dom';
import HomePage from '../homepage';
import TypePage from '../types';
import MenuItem from 'antd/es/menu/MenuItem';
import logo from '../../assets/images/logo/logoadmin.png';
import style from '../../assets/css/main.css';
import { Button, Typography } from '@mui/material';
import SubMenu from 'antd/es/menu/SubMenu';

const { Header, Content, Footer, Sider } = Layout;
function getItem(label, key, icon, children) {
    return {
        key,
        icon,
        children,
        label,
    };
}
const items = [
    getItem('Option 1', '1', <PieChartOutlined />),
    getItem('Option 2', '2', <DesktopOutlined />),
    getItem('User', 'sub1', <UserOutlined />, [
        getItem('Tom', '3'),
        getItem('Bill', '4'),
        getItem('Alex', '5'),
    ]),
    getItem('Team', 'sub2', <TeamOutlined />, [getItem('Team 1', '6'), getItem('Team 2', '8')]),
    getItem('Files', '9', <FileOutlined />),
];
const MainLayout = () => {
    const [collapsed, setCollapsed] = useState(false);
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout
            style={{
                minHeight: '100vh',
            }}
        >
            <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                <div className="demo-logo-vertical" />
                <Menu className='menu-nav' defaultSelectedKeys={['1']} mode="inline" >
                    <MenuItem>
                        <Button href='/'><Typography className='button-nav'><img src={logo} alt='Logo' /></Typography></Button>
                    </MenuItem>
                    <MenuItem>
                        <Button href='/'><Typography className='button-nav'>Home</Typography></Button>
                    </MenuItem>
                    <MenuItem>
                        <Button href='/login'><Typography className='button-nav'>Login</Typography></Button>
                    </MenuItem>
                    <SubMenu title={<Typography className='plf-7'>MANAGEMENT</Typography>}>
                        <MenuItem>
                            <Button>Types</Button>
                        </MenuItem>
                        <MenuItem>
                            <Button>Publishers</Button>
                        </MenuItem>
                        <MenuItem>
                            <Button>Authors</Button>
                        </MenuItem>
                        <MenuItem>
                            <Button>Employee</Button>
                        </MenuItem>
                    </SubMenu>

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
                        margin: '0 16px',
                    }}
                >
                    <Breadcrumb
                        style={{
                            margin: '16px 0',
                        }}
                    >
                        <Breadcrumb.Item>User</Breadcrumb.Item>
                        <Breadcrumb.Item>Bill</Breadcrumb.Item>
                    </Breadcrumb>
                    <div
                        style={{
                            padding: 24,
                            minHeight: 360,
                            background: colorBgContainer,
                            borderRadius: borderRadiusLG,
                        }}
                    >
                        <Routes>
                            <Route path='/' element={<HomePage></HomePage>} ></Route>
                            <Route path='/home' element={<HomePage></HomePage>}></Route>
                            <Route path='/type' element={<TypePage></TypePage>}></Route>
                        </Routes>
                    </div>
                </Content>
                <FooterPage></FooterPage>
            </Layout>
        </Layout>
    );
};

export default MainLayout;