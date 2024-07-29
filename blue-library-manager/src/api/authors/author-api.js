import axiosClient from "../base/base-axios";

const authorAPI = {

    findAllAuthors(token) {

        return axiosClient.get("/api/v1/authors/", {
            headers: {
                Authorization: "Bearer " + token
            }
        })
    }
};

export default authorAPI;