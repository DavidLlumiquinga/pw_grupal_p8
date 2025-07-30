<template>
  <div>
    <h2 class="titulo">Gestión de Bodegas</h2>
    <form class="form-bodega" @submit.prevent>
      <div class="form-row">
        <input
          v-model="bodega.id"
          type="number"
          placeholder="ID (para actualizar/borrar)"
          class="input-form"
        />
        <input
          v-model="bodega.codigo"
          type="text"
          placeholder="Código"
          class="input-form"
        />
      </div>
      <div class="form-row">
        <input
          v-model="bodega.nombre"
          type="text"
          placeholder="Nombre"
          class="input-form"
        />
        <input
          v-model="bodega.ubicacion"
          type="text"
          placeholder="Ubicación"
          class="input-form"
        />
      </div>
      <div class="form-actions">
        <button class="btn-guardar" @click="guardar">Guardar</button>
        <button class="btn-actualizar" @click="actualizar">Actualizar</button>
        <button class="btn-borrar" @click="borrar">Borrar</button>
      </div>
      <div v-if="mensaje" :style="{ color: mensajeColor }" class="mensaje">
        {{ mensaje }}
      </div>
    </form>
  </div>
</template>

<script>
import {
  guardarFachada,
  actualizarFachada,
  borrarPorIdFachada,
} from "../Clients/BodegaClient";

export default {
  data() {
    return {
      bodega: {
        id: "",
        codigo: "",
        nombre: "",
        ubicacion: "",
      },
      mensaje: "",
      mensajeColor: "green",
    };
  },
  methods: {
    async guardar() {
      try {
        if (
          !this.bodega.codigo ||
          !this.bodega.nombre ||
          !this.bodega.ubicacion
        ) {
          this.mensaje = "Completa todos los campos para guardar.";
          this.mensajeColor = "red";
          return;
        }
        const bodegaToBody = {
          codigo: this.bodega.codigo,
          nombre: this.bodega.nombre,
          ubicacion: this.bodega.ubicacion,
        };
        await guardarFachada(bodegaToBody);
        this.mensaje = "Bodega guardada correctamente.";
        this.mensajeColor = "green";
      } catch (e) {
        this.mensaje = "Error al guardar: " + (e.response?.data || e.message);
        this.mensajeColor = "red";
      }
    },
    async actualizar() {
      try {
        if (!this.bodega.id) {
          this.mensaje = "Ingresa el ID para actualizar.";
          this.mensajeColor = "red";
          return;
        }
        const bodegaToBody = {
          codigo: this.bodega.codigo,
          nombre: this.bodega.nombre,
          ubicacion: this.bodega.ubicacion,
        };
        await actualizarFachada(this.bodega.id, bodegaToBody);
        this.mensaje = "Bodega actualizada correctamente.";
        this.mensajeColor = "green";
      } catch (e) {
        this.mensaje =
          "Error al actualizar: " + (e.response?.data || e.message);
        this.mensajeColor = "red";
      }
    },
    async borrar() {
      try {
        if (!this.bodega.id) {
          this.mensaje = "Ingresa el ID para borrar.";
          this.mensajeColor = "red";
          return;
        }
        await borrarPorIdFachada(this.bodega.id);
        this.mensaje = "Bodega borrada correctamente.";
        this.mensajeColor = "green";
      } catch (e) {
        this.mensaje = "Error al borrar: " + (e.response?.data || e.message);
        this.mensajeColor = "red";
      }
    },
  },
};
</script>

<style scoped>
.titulo {
  text-align: center;
  font-size: 2.2rem;
  font-weight: 700;
  color: #2c3e50;
  margin-bottom: 2rem;
  letter-spacing: 1px;
}
.form-bodega {
  max-width: 600px;
  margin: 0 auto 2rem auto;
  background: #fff;
  padding: 2rem 2.5rem 1.5rem 2.5rem;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(44, 62, 80, 0.09);
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
.btn-guardar,
.btn-actualizar,
.btn-borrar {
  flex: 1;
  padding: 0.7rem 0;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
  min-width: 250px;
  display: inline-block;
}
@media (max-width: 600px) {
  .form-bodega {
    padding: 1rem 0.5rem;
    max-width: 98vw;
    margin: 18px auto;
  }
  .input-form {
    max-width: 100%;
    font-size: 0.98rem;
    padding: 10px 8px;
  }
  .btn-guardar,
  .btn-actualizar,
  .btn-borrar {
    padding: 11px 0;
    font-size: 1rem;
    width: 100%;
    min-width: 0;
    margin: 10px 0 0 0;
  }
}
@media (max-width: 400px) {
  .form-bodega {
    padding: 8px 2px;
  }
  .input-form {
    font-size: 0.92rem;
    padding: 8px 4px;
  }
  .titulo {
    font-size: 1.1rem;
  }
}
</style>
