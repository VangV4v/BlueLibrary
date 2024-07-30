import axiosClient from "../base/base-axios";

const publisherAPI = {

    findAllPublishers(token) {

        return axiosClient.get("/api/v1/publishers/", {
            headers: {
                Authorization: "Bearer " + token
            }
        })
    },
    addPublisher(token, data) {

        return axiosClient.post("/api/v1/publishers/", data, {
            headers: {
                Authorization: "Bearer " + token
            }
        })
    },
    editPublisher(token, data) {

        return axiosClient.put("/api/v1/publishers/", data, {
            headers: {
                Authorization: "Bearer " + token
            }
        })
    },
    deletePublisher(token, data) {

        return axiosClient.delete("/api/v1/publishers/", {
            data: data,
            headers: {
                Authorization: "Bearer " + token
            }
        })
    }
};

export default publisherAPI;