import axios from "axios";
const URL_API = "http://localhost:8081/api/mercado/v1/facturas";

// CREATE
const guardar = async (body) => {
  const data = axios.post(`${URL_API}`, body).then((r) => r.data);
  return data;
};
export const guardarFachada = async (body) => {
  return await guardar(body);
};

// READ (get all)
const obtenerTodos = async () => {
  const data = axios.get(`${URL_API}`).then((r) => r.data);
  return data;
};
export const obtenerTodosFachada = async () => {
  return await obtenerTodos();
};

// READ (get by id)
const obtenerPorId = async (id) => {
  const data = axios.get(`${URL_API}/${id}`).then((r) => r.data);
  return data;
};
export const obtenerPorIdFachada = async (id) => {
  return await obtenerPorId(id);
};

// UPDATE
const actualizar = async (id, body) => {
  const data = axios.put(`${URL_API}/${id}`, body).then((r) => r.data);
  return data;
};
export const actualizarFachada = async (id, body) => {
  return await actualizar(id, body);
};

// DELETE
const eliminar = async (id) => {
  const data = axios.delete(`${URL_API}/${id}`).then((r) => r.data);
  return data;
};
export const eliminarFachada = async (id) => {
  return await eliminar(id);
};
