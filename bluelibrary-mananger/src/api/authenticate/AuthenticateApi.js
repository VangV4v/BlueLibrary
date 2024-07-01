import axiosClient from "../base";

const authenticateApi = {

    login(param) {

        return axiosClient.post('/api/v1/auth/employee/', param);
    }
};

export default authenticateApi;