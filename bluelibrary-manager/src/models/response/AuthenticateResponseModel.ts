interface AuthenticateResponseModel {

    authenticated: boolean,
    expiration: string,
    jwt: string,
    role: string
}

export default AuthenticateResponseModel;