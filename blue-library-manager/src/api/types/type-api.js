import axiosClient from "../base/base-axios";

const typeAPI = {

    findAllTypes(token) {

        return axiosClient.get("/api/v1/types/", {
            headers: {
                Authorization: "Bearer " + token,
            }
        });
    },
    addType(token, param) {

        return axiosClient.post("/api/v1/types/", param, {
            headers: {
                Authorization: "Bearer " + token,
            }
        });
    },
    updateType(token, param) {

        return axiosClient.put("/api/v1/types/", param, {
            headers: {
                Authorization: "Bearer " + token,
            }
        });
    },
    deleteType(token, param) {

        return axiosClient.delete("/api/v1/types/", {
            data: param,
            headers: {
                Authorization: "Bearer " + token
            }
        });
    }
};

export default typeAPI;