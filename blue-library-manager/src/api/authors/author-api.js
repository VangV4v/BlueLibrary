import axiosClient from "../base/base-axios";

const authorAPI = {

    findAllAuthors(token) {

        return axiosClient.get("/api/v1/authors/", {
            headers: {
                Authorization: "Bearer " + token
            }
        })
    },
    addAuthor(token, param) {

        return axiosClient.post("/api/v1/authors/", param, {
            headers: {
                Authorization: "Bearer " + token
            }
        })
    },
    updateAuthor(token, param) {

        return axiosClient.put("/api/v1/authors/", param, {
            headers: {
                Authorization: "Bearer " + token
            }
        });
    },
    deleteAuthor(token, param) {

        return axiosClient.delete("/api/v1/authors/", {
            data: param,
            headers: {
                Authorization: "Bearer " + token
            }
        })
    }
};

export default authorAPI;