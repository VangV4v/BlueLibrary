import axiosClient from "../base/base-axios";

const authEmployeeAPI = {

    authenticate(param) {

        return axiosClient.post("/api/v1/auth/employee/", param);
    }
};

export default authEmployeeAPI;