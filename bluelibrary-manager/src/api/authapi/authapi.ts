import AuthenticateRequestModel from "../../models/request/AuthenticateRequestModel";
import axiosClient from "../baseapi/AxiosClient";

const authenticateApi = {

    authenticate(param: AuthenticateRequestModel) {

        return axiosClient.post('/api/v1/auth/employee/', param);
    }
};

export default authenticateApi;