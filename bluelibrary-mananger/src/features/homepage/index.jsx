import { Button } from 'antd';
import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

function HomePage(props) {

    const jwt = useSelector(state => state.auth.data);
    return (
        <div>
            Home Page {jwt}
        </div>
    );
}

export default HomePage;