import { Footer } from 'antd/es/layout/layout';
import React from 'react';

function FooterPage(props) {
    return (
        <Footer
            style={{
                textAlign: 'center',
            }}
        >
            Ant Design ©{new Date().getFullYear()} Created by Ant UED
        </Footer>
    );
}

export default FooterPage;