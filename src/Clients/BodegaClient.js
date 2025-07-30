import axios from "axios";
const URL_API = 'http://localhost:8081/api/mercado/v1/bodegas';

const guardar = async (body)=> {
    const data = axios.post(`${URL_API}`,body).then(r => r.data);
    return data;
}

const actualizar = async (id, body) => {
    return axios.put(`${URL_API}/${id}`, body).then(r => r.data);
}

const borrarPorId = async (id) => {
    return axios.delete(`${URL_API}/${id}`).then(r => r.data);
}

export const guardarFachada = async (body)=> {
   return await guardar(body);
}

export const actualizarFachada =async (id , body) => {
     return await actualizar(id, body);
}

export const borrarPorIdFachada=async (id) => {
     return await borrarPorId(id);
}
