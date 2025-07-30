<template>
  <div class="container">
    <h2 class="titulo">Gestión de Clientes</h2>
    <div class="form-cliente">
      <input v-model="cliente.id" type="number" placeholder="ID (para actualizar/borrar)" class="input-form" />
      <input v-model="cliente.cedula" type="text" placeholder="Cédula" class="input-form" />
      <input v-model="cliente.nombre" type="text" placeholder="Nombre" class="input-form" />
      <input v-model="cliente.apellido" type="text" placeholder="Apellido" class="input-form" />
      <input v-model="cliente.razonSocial" type="text" placeholder="Razón Social" class="input-form" />
      <input v-model="cliente.direccion" type="text" placeholder="Dirección" class="input-form" />
      <input v-model="cliente.telefono" type="text" placeholder="Teléfono" class="input-form" />
      <input v-model="cliente.email" type="email" placeholder="Correo electrónico" class="input-form" />
      
      <div class="form-actions">
        <button @click="guardar" class="btn-guardar">Guardar</button>
        <button @click="actualizar" class="btn-actualizar">Actualizar</button>
        <button @click="borrar" class="btn-borrar">Borrar</button>
      </div>
    </div>
    <div v-if="mensaje" class="mensaje" :style="{color: mensajeColor}">{{ mensaje }}</div>
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

<style scoped>
body {
  font-family: 'Segoe UI', 'Roboto', Arial, sans-serif;
  background: #f6f8fa;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
}

.titulo {
  text-align: center;
  font-size: 2.2rem;
  font-weight: 700;
  color: #2c3e50;
  margin-bottom: 2rem;
  letter-spacing: 1px;
}

.form-cliente {
  max-width: 600px;
  margin: 0 auto 2rem auto;
  background: #fff;
  padding: 2rem 2.5rem 1.5rem 2.5rem;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(44,62,80,0.09);
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

.input-form {
  padding: 0.7rem 1.1rem;
  border: 1px solid #d1d5db;
  border-radius: 8px;
  font-size: 1rem;
  outline: none;
  transition: border 0.2s;
}

.input-form:focus {
  border-color: #007bff;
}

.form-actions {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
  margin-top: 0.5rem;
}

.btn-guardar, .btn-actualizar, .btn-borrar {
  flex: 1;
  padding: 0.7rem 0;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
}

.btn-guardar {
  background: linear-gradient(90deg, #28a745 0%, #218838 100%);
  color: #fff;
}

.btn-guardar:hover {
  background: linear-gradient(90deg, #218838 0%, #28a745 100%);
  transform: translateY(-2px) scale(1.03);
}

.btn-actualizar {
  background: linear-gradient(90deg, #ffc107 0%, #e0a800 100%);
  color: #212529;
}

.btn-actualizar:hover {
  background: linear-gradient(90deg, #e0a800 0%, #ffc107 100%);
  transform: translateY(-2px) scale(1.03);
}

.btn-borrar {
  background: linear-gradient(90deg, #dc3545 0%, #c82333 100%);
  color: #fff;
}

.btn-borrar:hover {
  background: linear-gradient(90deg, #c82333 0%, #dc3545 100%);
  transform: translateY(-2px) scale(1.03);
}

.mensaje {
  text-align: center;
  font-size: 1.1rem;
  font-weight: 500;
  margin-top: 1.2rem;
  padding: 0.7rem 1rem;
  border-radius: 8px;
  background: #f8f9fa;
  box-shadow: 0 2px 8px rgba(0,0,0,0.04);
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}
</style>

