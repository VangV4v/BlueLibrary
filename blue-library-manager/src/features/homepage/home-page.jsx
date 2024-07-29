import React from 'react';

function HomePage({ isAuthenticated }) {
    return (
        <>
            {
                isAuthenticated &&
                <>
                    Show
                </>
            }
        </>
    );
}

export default HomePage;