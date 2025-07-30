<template>
  <div class="main-content">
    <h2>Reporte de Facturas</h2>
    <button @click="consultar">Consultar Facturas</button>
    <table v-if="facturas.length" border="1" style="margin-top:10px;">
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
    <div v-if="mensaje" :style="{color: mensajeColor, marginTop: '10px'}">{{ mensaje }}</div>
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
table {
  width: 90vw;
  max-width: 900px;
  border-collapse: collapse;
  margin: 32px auto 0 auto;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(13,71,161,0.08);
  overflow: hidden;
}
th, td {
  padding: 14px 16px;
  text-align: left;
  font-size: 1.05rem;
}
th {
  background: linear-gradient(90deg, #0d47a1 0%, #42b983 100%);
  color: #fff;
  font-weight: 700;
}
tr:nth-child(even) {
  background: #f3f6fd;
}
tr:hover {
  background: #e3f2fd;
}
h2 {
  color: #0d47a1;
  margin-bottom: 18px;
  text-align: center;
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
.mensaje {
  margin-top: 18px;
  font-size: 1.1rem;
  font-weight: 500;
  text-align: center;
}
</style>