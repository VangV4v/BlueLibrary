import React, { useEffect, useState } from 'react';
import { Header } from 'antd/es/layout/layout';
import { Breadcrumb } from 'antd';
import { useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';

function HeaderPage({ colorBgContainer }) {

    const location = useLocation();
    const isAuthenticated = useSelector(state => state.authStatus.status);
    const [previous, setPrevious] = useState("");
    const [items, setItems] = useState([
        {
            title: "Home",
            href: "/home",
        }
    ]);
    useEffect(() => {

        const path = location.pathname.split("/");
        const newItem = [...items];

        for (let i = 0; i < path.length - 1; i++) {

            if (path[i] !== "" && path[i] !== "home") {

                const newPath = {
                    title: String(path[i]).charAt(0).toUpperCase() + String(path[i]).substring(1).toLowerCase(),
                    href: !path[i].startsWith("/", 0) ? "/" + path[i] : path[i],
                };
                newItem.push(newPath);
            }
        }
        const title = location.pathname.substring(location.pathname.lastIndexOf("-") + 1 || location.pathname.lastIndexOf("/") + 1, location.pathname.length);
        const lastUrl = {
            title: title.charAt(0).toUpperCase() + title.substring(1).toLowerCase(),
            href: location.pathname
        };
        newItem.push(lastUrl);
        setItems(newItem);
    }, [location.pathname]);
    return (
        <>
            {isAuthenticated &&
                <>
                    <Header
                        style={{
                            padding: 20,
                            background: colorBgContainer,
                        }}
                    >
                        <Breadcrumb className='bread-crumb-color' items={items}>
                        </Breadcrumb>
                    </Header>
                </>
            }
        </>
    );
}

export default HeaderPage;