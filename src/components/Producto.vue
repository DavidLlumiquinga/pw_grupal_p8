<template>
  <div>
    <h2>Gestión de Productos/Servicios</h2>
    <div>
      <input v-model="producto.id" type="number" placeholder="ID (para actualizar/borrar)" />
      <input v-model="producto.codigoBarras" type="text" placeholder="Código de Barras" />
      <input v-model="producto.nombre" type="text" placeholder="Nombre" />
      <select v-model="producto.categoria">
        <option value="producto">Producto</option>
        <option value="servicio">Servicio</option>
      </select>
      <input v-model="producto.stock" type="number" placeholder="Stock (solo producto)" :disabled="producto.categoria === 'servicio'" />
      <input v-model="producto.precio" type="number" placeholder="Precio (sin impuestos)" />
      <input v-model="producto.impuestos" type="text" placeholder="Impuestos (ej: IVA,ICE separados por coma)" />
    </div>
    <button @click="guardar">Guardar</button>
    <button @click="actualizar">Actualizar</button>
    <button @click="borrar">Borrar</button>
    <div v-if="mensaje" :style="{color: mensajeColor, marginTop: '10px'}">{{ mensaje }}</div>
  </div>
</template>

<script>

import { guardarFachada, actualizarFachada, borrarPorIdFachada } from '../Clients/ProductoClient';

export default {
  data() {
    return {
      producto: {
        id: '',
        codigoBarras: '',
        nombre: '',
        categoria: 'producto',
        stock: '',
        precio: '',
        impuestos: '',
      },
      mensaje: '',
      mensajeColor: 'green',
    };
  },
  methods: {
    async guardar() {
      try {
        if (!this.producto.codigoBarras || !this.producto.nombre || !this.producto.categoria || !this.producto.precio) {
          this.mensaje = 'Completa todos los campos obligatorios para guardar.';
          this.mensajeColor = 'red';
          return;
        }
        const productoToBody = {
          codigoBarras: this.producto.codigoBarras,
          nombre: this.producto.nombre,
          categoria: this.producto.categoria,
          stock: this.producto.categoria === 'producto' ? this.producto.stock : null,
          precio: this.producto.precio,
          impuestos: this.producto.impuestos.split(',').map(i => i.trim()),
        };
        await guardarFachada(productoToBody);
        this.mensaje = 'Producto/Servicio guardado correctamente.';
        this.mensajeColor = 'green';
      } catch (e) {
        this.mensaje = 'Error al guardar: ' + (e.response?.data || e.message);
        this.mensajeColor = 'red';
      }
    },
    async actualizar() {
      try {
        if (!this.producto.id) {
          this.mensaje = 'Ingresa el ID para actualizar.';
          this.mensajeColor = 'red';
          return;
        }
        const productoToBody = {
          codigoBarras: this.producto.codigoBarras,
          nombre: this.producto.nombre,
          categoria: this.producto.categoria,
          stock: this.producto.categoria === 'producto' ? this.producto.stock : null,
          precio: this.producto.precio,
          impuestos: this.producto.impuestos.split(',').map(i => i.trim()),
        };
        await actualizarFachada(this.producto.id, productoToBody);
        this.mensaje = 'Producto/Servicio actualizado correctamente.';
        this.mensajeColor = 'green';
      } catch (e) {
        this.mensaje = 'Error al actualizar: ' + (e.response?.data || e.message);
        this.mensajeColor = 'red';
      }
    },
    async borrar() {
      try {
        if (!this.producto.id) {
          this.mensaje = 'Ingresa el ID para borrar.';
          this.mensajeColor = 'red';
          return;
        }
        await borrarPorIdFachada(this.producto.id);
        this.mensaje = 'Producto/Servicio borrado correctamente.';
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

.titulo {
  text-align: center;
  font-size: 2.2rem;
  font-weight: 700;
  color: #2c3e50;
  margin-bottom: 2rem;
  letter-spacing: 1px;
}

.consulta-barra {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 1.5rem;
  gap: 0.5rem;
}

.input-barra {
  padding: 0.7rem 1.2rem;
  border: 1px solid #d1d5db;
  border-radius: 8px;
  font-size: 1rem;
  outline: none;
  transition: border 0.2s;
}
.input-barra:focus {
  border-color: #007bff;
}

.btn-consultar {
  background: linear-gradient(90deg, #007bff 0%, #0056b3 100%);
  color: #fff;
  border: none;
  border-radius: 8px;
  padding: 0.7rem 1.5rem;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  box-shadow: 0 2px 8px rgba(0,0,0,0.07);
  transition: background 0.2s, transform 0.1s;
}
.btn-consultar:hover {
  background: linear-gradient(90deg, #0056b3 0%, #007bff 100%);
  transform: translateY(-2px) scale(1.03);
}

.tabla-consulta {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}
.tabla-consulta table {
  border-collapse: collapse;
  min-width: 700px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 24px rgba(44,62,80,0.08);
  overflow: hidden;
}
.tabla-consulta th, .tabla-consulta td {
  padding: 1rem 1.2rem;
  text-align: center;
}
.tabla-consulta th {
  background: #007bff;
  color: #fff;
  font-weight: 600;
  letter-spacing: 0.5px;
}
.tabla-consulta tr:nth-child(even) {
  background: #f2f6fc;
}
.tabla-consulta tr:hover {
  background: #eaf1fb;
}

.form-producto {
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
.form-row {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.input-form {
  flex: 1;
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
  min-width: 250px;
  display: inline-block;
}

/* Animación fade para transiciones */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.4s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

</style>