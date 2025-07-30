import axios from "axios";
const URL_API = 'http://localhost:8081/api/mercado/v1/facturas';

export const consultarFacturas = async () => {
    return axios.get(`${URL_API}`).then(r => r.data);
}
