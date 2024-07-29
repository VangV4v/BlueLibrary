import React, { useEffect, useState } from 'react';
import { Flex, Image, Layout, Menu, theme, Typography } from 'antd';
import iconLogo from '../../assets/images/icons/icon-logo.png';
import { Link, Route, Routes } from 'react-router-dom';
import HomePage from '../homepage/home-page';
import LoginPage from '../login/login';
import NotFound from '../notfound/404';
import { useSelector } from 'react-redux';
import AuthPage from '../auth/auth-page';
import MenuPage from '../menu-page/menu-page';
import TypePage from '../types/type-page';
import TypeAddPage from '../types/type-add';
import HeaderPage from '../header/header-page';
import TypeEditPage from '../types/type-edit';
import AuthorPage from '../author/author-page';
import AuthorAddPage from '../author/author-add';
const { Content, Footer, Sider } = Layout;

const MainLayout = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    const [isAuthenticated, setAuthenticated] = useState(false);
    const authReduxInfo = useSelector(state => state.auth.authResponse);
    const statusAuthenticated = useSelector(state => state.authStatus.status);
    useEffect(() => {

        const expirationDate = new Date(authReduxInfo.expiration).getTime();
        const currentDate = new Date().getTime();

        if (authReduxInfo.authenticated && !(currentDate > expirationDate)) {

            setAuthenticated(true);
        } else {

            setAuthenticated(false);
        }
    }, [authReduxInfo]);
    return (
        <Layout>
            <Sider
                theme='light'
                breakpoint="lg"
                collapsedWidth="0"
            >
                <div className="demo-logo-vertical" />
                <Link to='/home'>
                    <Flex>
                        <Image preview={false} width='75px' src={iconLogo} />
                        <Typography.Title>LUE</Typography.Title>
                    </Flex>
                </Link>
                <Menu>
                    <Flex vertical gap='small' align='flex-start'>
                        <MenuPage isAuthenticated={isAuthenticated}></MenuPage>
                        <AuthPage isAuthenticated={isAuthenticated}></AuthPage>
                    </Flex>
                </Menu>
            </Sider>
            <Layout>
                <HeaderPage colorBgContainer={colorBgContainer} />
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
                            <Route path='/' element={<HomePage isAuthenticated={statusAuthenticated} />} />
                            <Route path='/home' element={<HomePage isAuthenticated={statusAuthenticated} />} />
                            <Route path='/login' element={<LoginPage />} />
                            <Route path='/types' element={<TypePage />} />
                            <Route path='/types/type-add' element={<TypeAddPage />} />
                            <Route path='/types/type-edit' element={<TypeEditPage />} />
                            <Route path='/authors' element={<AuthorPage />} />
                            <Route path='/authors/author-add' element={<AuthorAddPage />} />
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