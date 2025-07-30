<template>
  <div>
    <h2>Gestión de Clientes</h2>
    <div>
      <input v-model="cliente.id" type="number" placeholder="ID (para actualizar/borrar)" />
      <input v-model="cliente.cedula" type="text" placeholder="Cédula" />
      <input v-model="cliente.nombre" type="text" placeholder="Nombre" />
      <input v-model="cliente.apellido" type="text" placeholder="Apellido" />
      <input v-model="cliente.razonSocial" type="text" placeholder="Razón Social" />
      <input v-model="cliente.direccion" type="text" placeholder="Dirección" />
      <input v-model="cliente.telefono" type="text" placeholder="Teléfono" />
      <input v-model="cliente.email" type="email" placeholder="Correo electrónico" />
    </div>
    <button @click="guardar">Guardar</button>
    <button @click="actualizar">Actualizar</button>
    <button @click="borrar">Borrar</button>
    <div v-if="mensaje" :style="{color: mensajeColor, marginTop: '10px'}">{{ mensaje }}</div>
  </div>
</template>

<script>

import { guardarFachada, actualizarFachada, borrarPorIdFachada } from '../Clients/ClienteClient';

export default {
  data() {
    return {
      cliente: {
        id: '',
        cedula: '',
        nombre: '',
        apellido: '',
        razonSocial: '',
        direccion: '',
        telefono: '',
        email: '',
      },
      mensaje: '',
      mensajeColor: 'green',
    };
  },
  methods: {
    async guardar() {
      try {
        if (!this.cliente.cedula || !this.cliente.nombre || !this.cliente.apellido) {
          this.mensaje = 'Completa los campos obligatorios para guardar.';
          this.mensajeColor = 'red';
          return;
        }
        const clienteToBody = {
          cedula: this.cliente.cedula,
          nombre: this.cliente.nombre,
          apellido: this.cliente.apellido,
          razonSocial: this.cliente.razonSocial,
          direccion: this.cliente.direccion,
          telefono: this.cliente.telefono,
          email: this.cliente.email,
        };
        await guardarFachada(clienteToBody);
        this.mensaje = 'Cliente guardado correctamente.';
        this.mensajeColor = 'green';
      } catch (e) {
        this.mensaje = 'Error al guardar: ' + (e.response?.data || e.message);
        this.mensajeColor = 'red';
      }
    },
    async actualizar() {
      try {
        if (!this.cliente.id) {
          this.mensaje = 'Ingresa el ID para actualizar.';
          this.mensajeColor = 'red';
          return;
        }
        const clienteToBody = {
          cedula: this.cliente.cedula,
          nombre: this.cliente.nombre,
          apellido: this.cliente.apellido,
          razonSocial: this.cliente.razonSocial,
          direccion: this.cliente.direccion,
          telefono: this.cliente.telefono,
          email: this.cliente.email,
        };
        await actualizarFachada(this.cliente.id, clienteToBody);
        this.mensaje = 'Cliente actualizado correctamente.';
        this.mensajeColor = 'green';
      } catch (e) {
        this.mensaje = 'Error al actualizar: ' + (e.response?.data || e.message);
        this.mensajeColor = 'red';
      }
    },
    async borrar() {
      try {
        if (!this.cliente.id) {
          this.mensaje = 'Ingresa el ID para borrar.';
          this.mensajeColor = 'red';
          return;
        }
        await borrarPorIdFachada(this.cliente.id);
        this.mensaje = 'Cliente borrado correctamente.';
        this.mensajeColor = 'green';
      } catch (e) {
        this.mensaje = 'Error al borrar: ' + (e.response?.data || e.message);
        this.mensajeColor = 'red';
      }
    },
  },
};
</script>

<style scpoped>
h2 {
  color: #0d47a1;
  margin-bottom: 18px;
  text-align: center;
}
div {
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(13,71,161,0.08);
  padding: 32px 36px;
  margin: 32px auto;
  max-width: 480px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
input {
  display: block;
  width: 340px;
  margin-bottom: 18px;
  padding: 12px 14px;
  border: 1.5px solid #b0bec5;
  border-radius: 8px;
  font-size: 1.05rem;
  background: #f9fafb;
  transition: border 0.2s;
  outline: none;
}
input:focus {
  border: 2px solid #42b983;
  background: #e3f2fd;
}
button {
  background: linear-gradient(90deg, #42b983 0%, #0d47a1 100%);
  color: #fff;
  border: none;
  border-radius: 8px;
  padding: 12px 28px;
  margin: 10px 8px 0 0;
  font-size: 1.08rem;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.2s, box-shadow 0.2s;
  box-shadow: 0 2px 8px rgba(66,185,131,0.10);
}
button:hover {
  background: linear-gradient(90deg, #0d47a1 0%, #42b983 100%);
  box-shadow: 0 4px 16px rgba(13,71,161,0.13);
}
</style>

<style>
/* Puedes agregar estilos aquí */
</style>