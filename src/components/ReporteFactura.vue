<template>
  <div class="container">
    <h2 class="titulo">Reporte de Facturas</h2>
    <div class="consulta-section">
      <button @click="consultarBasico" class="btn-consultar">Reporte Básico</button>
      <button @click="consultarConClientes" class="btn-clientes">Reporte con Datos de Clientes</button>
    </div>

    <!-- Tabla básica de facturas -->
    <div v-if="!mostrarClientes && facturas.length === 0 && !cargando" class="no-data-message">
      📭 No hay facturas para mostrar. Haz clic en "Reporte Básico" para cargar los datos.
    </div>
    
    <table v-if="facturas.length && !mostrarClientes" class="tabla-reporte">
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

    <!-- Tabla con información completa de clientes -->
    <div v-if="mostrarClientes && facturasConClientes.length === 0 && !cargando" class="no-data-message">
      📭 No hay facturas con datos de clientes para mostrar. Haz clic en "Reporte con Datos de Clientes" para cargar los datos.
    </div>
    
    <table v-if="facturasConClientes.length && mostrarClientes" class="tabla-reporte tabla-expandida">
      <thead>
        <tr>
          <th>Número de Documento</th>
          <th>Cliente</th>
          <th>Género</th>
          <th>Teléfono</th>
          <th>Email</th>
          <th>Dirección</th>
          <th>Cantidad de Ítems</th>
          <th>TOTAL</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="factura in facturasConClientes" :key="factura.numeroDocumento">
          <td>{{ factura.numeroDocumento }}</td>
          <td>
            <div class="cliente-info">
              <strong>{{ factura.nombreCliente }}</strong>
              <small>{{ factura.identificacionCliente }}</small>
            </div>
          </td>
          <td>
            <span class="genero-badge" :class="factura.clienteCompleto?.genero">
              {{ formatearGenero(factura.clienteCompleto?.genero) }}
            </span>
          </td>
          <td>{{ factura.clienteCompleto?.telefono || 'N/A' }}</td>
          <td>{{ factura.clienteCompleto?.email || 'N/A' }}</td>
          <td>{{ factura.clienteCompleto?.direccion || 'N/A' }}</td>
          <td>{{ factura.cantidadItems }}</td>
          <td><strong>${{ factura.total?.toFixed(2) || '0.00' }}</strong></td>
        </tr>
      </tbody>
    </table>

    <div v-if="cargando" class="mensaje">Cargando información de clientes...</div>
    <div v-if="mensaje" class="mensaje" :style="{color: mensajeColor}">{{ mensaje }}</div>
  </div>
</template>

<script>
// Importa aquí tu función para consultar facturas
import { consultarFacturas, consultarFacturasConClientes, consultarClientes } from '../Clients/ReporteFacturaClient';

export default {
  data() {
    return {
      facturas: [],
      facturasConClientes: [],
      mostrarClientes: false,
      cargando: false,
      mensaje: '',
      mensajeColor: 'green',
    };
  },
  methods: {
    async consultarBasico() {
      try {
        console.log('🚀 Iniciando consulta básica de facturas...');
        this.cargando = true;
        this.mostrarClientes = false;
        this.mensaje = '⏳ Cargando facturas...';
        this.mensajeColor = 'blue';
        
        // Limpiar datos anteriores
        this.facturas = [];
        this.facturasConClientes = [];
        
        const data = await consultarFacturas();
        console.log('📊 Datos recibidos en consultarBasico:', data);
        
        if (!data) {
          console.warn('⚠️ No se recibieron datos');
          this.facturas = [];
          this.mensaje = '⚠️ No se recibieron datos del servidor';
          this.mensajeColor = 'orange';
          return;
        }
        
        if (!Array.isArray(data)) {
          console.warn('⚠️ Los datos no son un array:', data);
          this.facturas = [];
          this.mensaje = '⚠️ Formato de datos incorrecto';
          this.mensajeColor = 'orange';
          return;
        }
        
        this.facturas = data;
        
        if (this.facturas.length === 0) {
          this.mensaje = '📭 No se encontraron facturas en la base de datos';
          this.mensajeColor = 'orange';
        } else {
          this.mensaje = `✅ Se encontraron ${this.facturas.length} facturas`;
          this.mensajeColor = 'green';
        }
        
        console.log('✅ Consulta básica completada:', this.facturas.length, 'facturas');
        
      } catch (e) {
        console.error('❌ Error en consultarBasico:', e);
        this.facturas = [];
        this.mensaje = `❌ Error al consultar facturas: ${e.message || 'Error desconocido'}`;
        this.mensajeColor = 'red';
      } finally {
        this.cargando = false;
      }
    },

    async consultarConClientes() {
      try {
        console.log('🚀 Iniciando consulta con clientes...');
        this.cargando = true;
        this.mostrarClientes = true;
        this.mensaje = '⏳ Cargando información de clientes...';
        this.mensajeColor = 'blue';
        
        // Limpiar datos anteriores
        this.facturas = [];
        this.facturasConClientes = [];
        
        const data = await consultarFacturasConClientes();
        console.log('📊 Datos recibidos en consultarConClientes:', data);
        
        if (!data) {
          console.warn('⚠️ No se recibieron datos');
          this.facturasConClientes = [];
          this.mensaje = '⚠️ No se recibieron datos del servidor';
          this.mensajeColor = 'orange';
          this.mostrarClientes = false;
          return;
        }
        
        if (!Array.isArray(data)) {
          console.warn('⚠️ Los datos no son un array:', data);
          this.facturasConClientes = [];
          this.mensaje = '⚠️ Formato de datos incorrecto';
          this.mensajeColor = 'orange';
          this.mostrarClientes = false;
          return;
        }
        
        this.facturasConClientes = data;
        
        if (this.facturasConClientes.length === 0) {
          this.mensaje = '📭 No se encontraron facturas con datos de clientes';
          this.mensajeColor = 'orange';
        } else {
          const clientesEncontrados = this.facturasConClientes.filter(f => f.clienteCompleto).length;
          this.mensaje = `✅ Se encontraron ${this.facturasConClientes.length} facturas (${clientesEncontrados} con datos de cliente)`;
          this.mensajeColor = 'green';
        }
        
        console.log('✅ Consulta con clientes completada:', this.facturasConClientes.length, 'facturas');
        
      } catch (e) {
        console.error('❌ Error en consultarConClientes:', e);
        this.facturasConClientes = [];
        this.mensaje = `❌ Error al consultar facturas con clientes: ${e.message || 'Error desconocido'}`;
        this.mensajeColor = 'red';
        this.mostrarClientes = false;
      } finally {
        this.cargando = false;
      }
    },

    formatearGenero(genero) {
      const generos = {
        'masculino': 'Masculino',
        'femenino': 'Femenino',
        'otro': 'Otro',
        'no_especifica': 'No especifica'
      };
      return generos[genero] || 'N/A';
    },

    async debugearDatos() {
      console.log('=== 🐛 DEBUG DE DATOS ===');
      console.log('📊 Estado actual del componente:');
      console.log('- facturas:', this.facturas);
      console.log('- facturasConClientes:', this.facturasConClientes);
      console.log('- mostrarClientes:', this.mostrarClientes);
      console.log('- cargando:', this.cargando);
      
      this.mensaje = '🔍 Probando conexión al servidor...';
      this.mensajeColor = 'blue';
      
      try {
        console.log('🌐 Probando conexión directa con fetch...');
        
        // Probar conexión a facturas
        console.log('📋 Probando endpoint de facturas...');
        const facturaResponse = await fetch('http://localhost:8081/api/mercado/v1/facturas');
        console.log('📋 Status facturas:', facturaResponse.status, facturaResponse.statusText);
        
        if (facturaResponse.ok) {
          const facturaData = await facturaResponse.json();
          console.log('📋 Datos de facturas desde fetch:', facturaData);
          console.log('📋 Tipo de datos:', typeof facturaData, 'Es array:', Array.isArray(facturaData));
        } else {
          console.error('❌ Error en facturas:', facturaResponse.statusText);
        }
        
        // Probar conexión a clientes
        console.log('👥 Probando endpoint de clientes...');
        const clienteResponse = await fetch('http://localhost:8081/api/mercado/v1/clientes');
        console.log('👥 Status clientes:', clienteResponse.status, clienteResponse.statusText);
        
        if (clienteResponse.ok) {
          const clienteData = await clienteResponse.json();
          console.log('👥 Datos de clientes desde fetch:', clienteData);
          console.log('👥 Tipo de datos:', typeof clienteData, 'Es array:', Array.isArray(clienteData));
        } else {
          console.error('❌ Error en clientes:', clienteResponse.statusText);
        }
        
        // Probar con axios también
        console.log('🔧 Probando con funciones del cliente...');
        const { consultarFacturas, consultarClientes } = await import('../Clients/ReporteFacturaClient');
        
        try {
          const facturasAxios = await consultarFacturas();
          console.log('🔧 Facturas con axios:', facturasAxios);
        } catch (axiosError) {
          console.error('❌ Error con axios facturas:', axiosError);
        }
        
        try {
          const clientesAxios = await consultarClientes();
          console.log('🔧 Clientes con axios:', clientesAxios);
        } catch (axiosError) {
          console.error('❌ Error con axios clientes:', axiosError);
        }
        
        this.mensaje = '✅ Debug completado. Revisa la consola del navegador (F12) para ver todos los detalles';
        this.mensajeColor = 'green';
        
      } catch (error) {
        console.error('❌ Error general de conexión:', error);
        this.mensaje = '❌ Error de conexión. ¿Está el servidor ejecutándose en localhost:8081?';
        this.mensajeColor = 'red';
      }
    }
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
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.btn-consultar, .btn-clientes, .btn-debug {
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

.btn-clientes {
  background: linear-gradient(90deg, #28a745 0%, #218838 100%);
}

.btn-debug {
  background: linear-gradient(90deg, #dc3545 0%, #c82333 100%);
  font-size: 0.9rem;
}

.btn-consultar:hover, .btn-clientes:hover, .btn-debug:hover {
  transform: translateY(-2px) scale(1.03);
}

.btn-clientes:hover {
  background: linear-gradient(90deg, #218838 0%, #28a745 100%);
}

.btn-debug:hover {
  background: linear-gradient(90deg, #c82333 0%, #dc3545 100%);
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

/* Estilos para la tabla expandida */
.tabla-expandida {
  font-size: 0.85rem;
}

.tabla-expandida th {
  padding: 0.8rem 0.6rem;
  font-size: 0.8rem;
}

.tabla-expandida td {
  padding: 0.8rem 0.6rem;
}

.cliente-info {
  display: flex;
  flex-direction: column;
  gap: 0.2rem;
}

.cliente-info strong {
  font-size: 0.9rem;
  color: #2c3e50;
}

.cliente-info small {
  font-size: 0.75rem;
  color: #6c757d;
}

/* Badges para género */
.genero-badge {
  padding: 0.2rem 0.6rem;
  border-radius: 12px;
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  display: inline-block;
}

.genero-badge.masculino {
  background: #e3f2fd;
  color: #1976d2;
}

.genero-badge.femenino {
  background: #fce4ec;
  color: #c2185b;
}

.genero-badge.otro {
  background: #f3e5f5;
  color: #7b1fa2;
}

.genero-badge.no_especifica {
  background: #f5f5f5;
  color: #616161;
}

.genero-badge:not(.masculino):not(.femenino):not(.otro):not(.no_especifica) {
  background: #e0e0e0;
  color: #757575;
}

.no-data-message {
  text-align: center;
  padding: 3rem 2rem;
  font-size: 1.2rem;
  color: #6c757d;
  background: #f8f9fa;
  border: 2px dashed #dee2e6;
  border-radius: 12px;
  margin: 2rem 0;
}
</style>