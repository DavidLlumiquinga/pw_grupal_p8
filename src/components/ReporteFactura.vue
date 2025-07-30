<template>
  <div class="container">
    <h2 class="titulo">Reporte de Facturas</h2>
    <div class="consulta-section">
      <button @click="consultar" class="btn-consultar">Consultar Facturas</button>
    </div>
    <table v-if="facturas.length" class="tabla-reporte">
      <thead>
        <tr>
          <th>Número de Documento</th>
          <th>Identificación del Cliente</th>
          <th>Nombre del Cliente</th>
          <th>Cantidad de Ítems</th>
          <th>Total Impuestos</th>
          <th>TOTAL</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="factura in facturas" :key="factura.numeroDocumento">
          <td>{{ factura.numeroDocumento }}</td>
          <td>{{ factura.identificacionCliente }}</td>
          <td>{{ factura.nombreCliente }}</td>
          <td>{{ factura.cantidadItems }}</td>
          <td>{{ factura.totalImpuestos }}</td>
          <td>{{ factura.total }}</td>
        </tr>
      </tbody>
    </table>
    <div v-if="mensaje" class="mensaje" :style="{color: mensajeColor}">{{ mensaje }}</div>
  </div>
</template>

<script>
// Importa aquí tu función para consultar facturas
import { consultarFacturas } from '../Clients/ReporteFacturaClient';

export default {
  data() {
    return {
      facturas: [],
      mensaje: '',
      mensajeColor: 'green',
    };
  },
  methods: {
    async consultar() {
      try {
        const data = await consultarFacturas();
        this.facturas = data;
        this.mensaje = '';
      } catch (e) {
        this.mensaje = 'Error al consultar facturas: ' + (e.response?.data || e.message);
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

.consulta-section {
  text-align: center;
  margin-bottom: 2rem;
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

.tabla-reporte {
  width: 100%;
  border-collapse: collapse;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 24px rgba(44,62,80,0.08);
  overflow: hidden;
  margin: 0 auto 2rem auto;
}

.tabla-reporte th, .tabla-reporte td {
  padding: 1rem 1.2rem;
  text-align: center;
  border: none;
}

.tabla-reporte th {
  background: #007bff;
  color: #fff;
  font-weight: 600;
  letter-spacing: 0.5px;
}

.tabla-reporte tr:nth-child(even) {
  background: #f2f6fc;
}

.tabla-reporte tr:hover {
  background: #eaf1fb;
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