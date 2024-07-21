import React, { useState } from 'react';
import {
    DesktopOutlined,
    FileOutlined,
    PieChartOutlined,
    TeamOutlined,
    UserOutlined,
} from '@ant-design/icons';
import type { MenuProps } from 'antd';
import { Breadcrumb, Flex, Image, Layout, Menu, theme, Typography } from 'antd';
import iconUrl from '../../assets/images/icons/icon-logo.png';
import { Button } from '@mui/material';
import { Route, Routes } from 'react-router-dom';
import HomePage from '../homepage/homepage';
import HomeIcon from '@mui/icons-material/Home';
import LoginIcon from '@mui/icons-material/Login';
import LoginPage from '../login/login';

const { Header, Content, Footer, Sider } = Layout;

type MenuItem = Required<MenuProps>['items'][number];

function getItem(
    label: React.ReactNode,
    key: React.Key,
    icon?: React.ReactNode,
    children?: MenuItem[],
): MenuItem {
    return {
        key,
        icon,
        children,
        label,
    } as MenuItem;
}

const items: MenuItem[] = [
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

const MainLayout: React.FC = () => {
    const [collapsed, setCollapsed] = useState(false);
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();

    return (
        <Layout className='main-layout' style={{ minHeight: '100vh' }} >
            <Sider theme='light' collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                <div className="demo-logo-vertical" />
                <Button variant='text' href='/home'>
                    <Flex>
                        <Image preview={false} src={iconUrl} width='75px'></Image>
                        {!collapsed && <Typography.Title level={1}>LUE</Typography.Title>}
                    </Flex>
                </Button>
                <Menu>
                    <Flex vertical>
                        <Button href='/home' startIcon={<HomeIcon />}> {!collapsed && <Typography.Text>HOME</Typography.Text>}</Button>
                        <Button href='/login' startIcon={<LoginIcon />}>{!collapsed && <Typography.Text>LOGIn</Typography.Text>}</Button>
                    </Flex>
                </Menu>
            </Sider>
            <Layout>
                <Header style={{ padding: 0, background: colorBgContainer }} />
                <Content style={{ margin: '0 16px' }}>
                    {/* <Breadcrumb style={{ margin: '16px 0' }}>
                        <Breadcrumb.Item>User</Breadcrumb.Item>
                        <Breadcrumb.Item>Bill</Breadcrumb.Item>
                    </Breadcrumb> */}
                    <Content>
                        <Routes>
                            <Route path='/home' element={<HomePage></HomePage>}></Route>
                            <Route path='/login' element={<LoginPage></LoginPage>}></Route>
                        </Routes>
                    </Content>
                </Content>
                <Footer style={{ textAlign: 'center' }}>
                    Ant Design ©{new Date().getFullYear()} Created by Ant UED
                </Footer>
            </Layout>
        </Layout>
    );
};

export default MainLayout;