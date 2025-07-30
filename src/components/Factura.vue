<template>
  <div class="factura-container">
    <h2>Nueva Factura</h2>

    <!-- Cabecera de la Factura -->
    <label>RUC Empresa: </label>
    <input
      v-model="factura.rucEmpresa"
      type="text"
      placeholder="1234567890001"
    />
    <label>Número de Documento: </label>
    <input
      v-model="factura.numeroDocumento"
      type="text"
      placeholder="001-001-000000001"
    />
    <label>Establecimiento: </label>
    <input v-model="factura.establecimiento" type="text" placeholder="001" />
    <label>Punto de Emisión: </label>
    <input v-model="factura.puntoEmision" type="text" placeholder="001" />
    <label>Fecha de Emisión: </label>
    <input v-model="factura.fechaEmision" type="date" readonly />

    <h2>--------------</h2>

    <div v-if="mensajeC" class="mensaje" :style="{ color: mensajeCColor }">
      {{ mensajeC }}
    </div>
    <label>Identificación: </label>
    <input
      v-model="factura.cedula"
      type="text"
      placeholder="Cédula o RUC"
      @input="limpiarDatosCliente"
    />

    <button @click="buscarClientePorCedula" style="margin-bottom: 18px">
      Buscar Cliente por Cédula
    </button>

    <h2>--------------</h2>

    <label>Nombre del Cliente: </label>
    <input v-model="factura.nombreCliente" type="text" />
    <label>Dirección: </label>
    <input v-model="factura.direccion" type="text" />
    <label>Correo electrónico: </label>
    <input v-model="factura.email" type="email" />

    <h2>Agregar Productos</h2>

    <!-- Inputs para agregar productos -->
    <div v-if="mensajeP" class="mensaje" :style="{ color: mensajePColor }">
      {{ mensajeP }}
    </div>
    <label>Código de Barras: </label>
    <input
      v-model="nuevoProducto.codigoBarras"
      type="text"
      style="margin-bottom: 10px; width: 100%"
    />
    <label>Cantidad: </label>
    <input
      v-model.number="nuevoProducto.cantidad"
      type="number"
      min="1"
      style="margin-bottom: 10px; width: 100%"
    />
    <button @click="agregarProducto" style="width: 100%">Agregar</button>

    <!-- Tabla de productos agregados -->
    <table
      v-if="productosAgregados.length"
      style="
        width: 100%;
        max-width: 370px;
        margin-bottom: 18px;
        border-collapse: collapse;
      "
    >
      <thead>
        <tr>
          <th style="border-bottom: 1px solid #b0bec5; text-align: left">
            Código de Barras
          </th>
          <th style="border-bottom: 1px solid #b0bec5; text-align: left">
            Nombre
          </th>
          <th style="border-bottom: 1px solid #b0bec5; text-align: left">
            Cantidad
          </th>
          <th style="border-bottom: 1px solid #b0bec5; text-align: left">
            Precio
          </th>
          <th style="border-bottom: 1px solid #b0bec5; text-align: left">
            Subtotal
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(prod, idx) in productosAgregados" :key="idx">
          <td>{{ prod.codigoBarras }}</td>
          <td>{{ prod.nombre || "No encontrado" }}</td>
          <td>{{ prod.cantidad }}</td>
          <td>
            {{
              typeof prod.precio === "number" && !isNaN(prod.precio)
                ? prod.precio.toFixed(2)
                : "N/A"
            }}
          </td>
          <td>
            {{
              typeof prod.precio === "number" && !isNaN(prod.precio)
                ? (prod.precio * prod.cantidad).toFixed(2)
                : "N/A"
            }}
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Tabla de pie de factura -->
    <table
      v-if="productosAgregados.length"
      style="
        width: 100%;
        max-width: 370px;
        margin-bottom: 18px;
        border-collapse: collapse;
        background: #f9fafb;
      "
    >
      <thead>
        <tr>
          <th
            colspan="2"
            style="border-bottom: 1px solid #b0bec5; text-align: left"
          >
            Pie de Factura
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td style="font-weight: bold">Subtotal</td>
          <td>{{ subtotalFactura.toFixed(2) }}</td>
        </tr>
        <tr>
          <td style="font-weight: bold">Total Impuestos</td>
          <td>{{ totalImpuestosFactura.toFixed(2) }}</td>
        </tr>
        <tr>
          <td style="font-weight: bold">TOTAL</td>
          <td>{{ totalFactura.toFixed(2) }}</td>
        </tr>
      </tbody>
    </table>

    <button @click="guardar">Guardar Factura</button>
  </div>
</template>

<script>
import { guardarFachada } from "../Clients/FacturaClient";
import { obtenerPorCedulaFachada } from "../Clients/ClienteClient";
import { obtenerPorCodigoBarrasFachada } from "../Clients/ProductoClient";
import { guardarFachada as guardarDetalleFachada } from "../Clients/DetalleFactura";

export default {
  data() {
    return {
      factura: {
        rucEmpresa: "",
        numeroDocumento: "",
        establecimiento: "",
        puntoEmision: "",
        fechaEmision: "", // Se asignará automáticamente
        cedula: "",
        nombreCliente: "",
        direccion: "",
        email: "",
      },
      nuevoProducto: {
        codigoBarras: "",
        cantidad: 1,
      },
      productosAgregados: [],
      mensajeC: "",
      mensajeCColor: "",
      mensajeP: "",
      mensajePColor: "",
    };
  },
  created() {
    // Asigna la fecha actual al cargar el componente
    this.factura.fechaEmision = new Date().toISOString().slice(0, 10);
  },
  methods: {
    async guardar() {
      try {
        // Validación de campos obligatorios (CORREGIDO: agregado rucEmpresa)
        if (
          !this.factura.rucEmpresa ||
          !this.factura.cedula ||
          !this.factura.numeroDocumento ||
          !this.factura.establecimiento ||
          !this.factura.puntoEmision ||
          !this.factura.nombreCliente ||
          !this.factura.direccion ||
          !this.factura.email ||
          !this.productosAgregados.length
        ) {
          this.mensajeC =
            "Completa todos los campos obligatorios y agrega al menos un producto.";
          this.mensajeCColor = "red";
          return;
        }

        // Construir el objeto de factura para enviar al backend (CORREGIDO: agregados totales)
        const facturaToBody = {
          rucEmpresa: this.factura.rucEmpresa,
          cedula: this.factura.cedula,
          numeroDocumento: this.factura.numeroDocumento,
          establecimiento: this.factura.establecimiento,
          puntoEmision: this.factura.puntoEmision,
          fechaEmision: this.factura.fechaEmision,
          nombreCliente: this.factura.nombreCliente,
          direccion: this.factura.direccion,
          email: this.factura.email,
          // CORREGIDO: Enviar totales calculados
          total: this.totalFactura,
          totalImpuestos: this.totalImpuestosFactura,
        };

        // Guardar la factura principal
        const facturaGuardada = await guardarFachada(facturaToBody);

        // Guardar cada detalle de producto usando el cliente de DetalleFactura
        for (const prod of this.productosAgregados) {
          const detalle = {
            facturaId: facturaGuardada.id,
            codigoBarras: prod.codigoBarras,
            nombre: prod.nombre,
            cantidad: prod.cantidad,
            precio: prod.precio,
            subtotal:
              typeof prod.precio === "number" ? prod.precio * prod.cantidad : 0,
            // CORREGIDO: Calcular impuestos reales del producto
            totalImpuestos: this.calcularImpuestosProducto(prod),
            total:
              typeof prod.precio === "number"
                ? prod.precio * prod.cantidad +
                  this.calcularImpuestosProducto(prod)
                : 0,
          };
          await guardarDetalleFachada(detalle);
        }

        this.mensajeC = "Factura y detalles guardados correctamente.";
        this.mensajeCColor = "green";

        // Limpiar formulario tras guardar (CORREGIDO: agregado rucEmpresa)
        this.factura = {
          rucEmpresa: "",
          cedula: "",
          numeroDocumento: "",
          establecimiento: "",
          puntoEmision: "",
          fechaEmision: new Date().toISOString().slice(0, 10),
          nombreCliente: "",
          direccion: "",
          email: "",
        };
        this.productosAgregados = [];
        this.nuevoProducto = { codigoBarras: "", cantidad: 1 };
      } catch (e) {
        this.mensajeC =
          "Error al guardar la factura: " + (e.response?.data || e.message);
        this.mensajeCColor = "red";
      }
    },
    limpiarDatosCliente() {
      // CORREGIDO: Limpiar los campos correctos
      this.factura.nombreCliente = "";
      this.factura.direccion = "";
      this.factura.email = "";
      this.mensajeC = "";
      this.mensajeCColor = "";
    },
    async buscarClientePorCedula() {
      if (!this.factura.cedula) {
        this.mensajeC = "Ingresa una Cédula para buscar el cliente.";
        this.mensajeCColor = "red";
        return;
      }
      try {
        const cliente = await obtenerPorCedulaFachada(this.factura.cedula);
        if (cliente) {
          // CORREGIDO: Mapear correctamente los campos del cliente
          this.factura.nombreCliente = this.construirNombreCompleto(cliente);
          this.factura.direccion = cliente.direccion || "";
          this.factura.email = cliente.email || "";
          this.mensajeC = "Cliente encontrado y datos cargados.";
          this.mensajeCColor = "green";
        } else {
          this.mensajeC = "Cliente no encontrado.";
          this.mensajeCColor = "red";
        }
      } catch (e) {
        this.mensajeC = "Cliente no encontrado.";
        this.mensajeCColor = "red";
      }
    },
    // CORREGIDO: Método para construir nombre completo
    construirNombreCompleto(cliente) {
      if (cliente.razonSocial) {
        return cliente.razonSocial;
      } else {
        const nombre = cliente.nombre || "";
        const apellido = cliente.apellido || "";
        return `${nombre} ${apellido}`.trim();
      }
    },
    async agregarProducto() {
      if (
        !this.nuevoProducto.codigoBarras ||
        !this.nuevoProducto.cantidad ||
        this.nuevoProducto.cantidad < 1
      ) {
        this.mensajeP = "Ingrese un código de barras y una cantidad válida.";
        this.mensajePColor = "red";
        return;
      }
      try {
        const producto = await obtenerPorCodigoBarrasFachada(
          this.nuevoProducto.codigoBarras
        );
        if (!producto || !producto.nombre) {
          this.mensajeP = "No se ha agregado el producto porque no existe.";
          this.mensajePColor = "red";
          return;
        }
        this.productosAgregados.push({
          codigoBarras: this.nuevoProducto.codigoBarras,
          nombre: producto.nombre,
          precio: Number(producto.precio) || 0,
          cantidad: this.nuevoProducto.cantidad,
          // CORREGIDO: Guardar información de impuestos del producto
          impuestos: producto.impuestos || [],
        });

        // Limpiar inputs
        this.nuevoProducto.codigoBarras = "";
        this.nuevoProducto.cantidad = 1;
        this.mensajeP = "";
      } catch (e) {
        this.mensajeP = "No se ha agregado el producto porque no existe.";
        this.mensajePColor = "red";
        this.nuevoProducto.codigoBarras = "";
        this.nuevoProducto.cantidad = 1;
      }
    },
    calcularImpuestosProducto(producto) {
      // Si no es un array, lo convertimos en uno vacío
      const impuestos = Array.isArray(producto.impuestos)
        ? producto.impuestos
        : [];
      if (impuestos.length === 0) {
        return 0;
      }

      const subtotalProducto = producto.precio * producto.cantidad;
      let totalImpuestos = 0;

      impuestos.forEach((impuesto) => {
        if (impuesto.activo) {
          const valorImpuesto = (subtotalProducto * impuesto.porcentaje) / 100;
          totalImpuestos += valorImpuesto;
        }
      });

      return totalImpuestos;
    },
  },
  computed: {
    subtotalFactura() {
      // Suma de todos los subtotales (precio * cantidad)
      return this.productosAgregados.reduce((acc, prod) => {
        if (
          typeof prod.precio === "number" &&
          typeof prod.cantidad === "number"
        ) {
          return acc + prod.precio * prod.cantidad;
        }
        return acc;
      }, 0);
    },
    totalImpuestosFactura() {
      // CORREGIDO: Calcular impuestos reales basados en la información del producto
      return this.productosAgregados.reduce((acc, prod) => {
        return acc + this.calcularImpuestosProducto(prod);
      }, 0);
    },
    totalFactura() {
      return this.subtotalFactura + this.totalImpuestosFactura;
    },
  },
};
</script>

<style scoped>
body {
  background: linear-gradient(120deg, #e3ffe6 0%, #f3f6fd 100%);
  min-height: 100vh;
}

.factura-container {
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 6px 32px rgba(13, 71, 161, 0.1);
  padding: 40px 48px;
  margin: 40px auto;
  max-width: 520px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 2px solid #42b98322;
  position: relative;
}

h2 {
  color: #0d47a1;
  margin-bottom: 28px;
  text-align: center;
  font-size: 2rem;
  letter-spacing: 1px;
  font-family: "Segoe UI", Arial, sans-serif;
}

input {
  display: block;
  width: 100%;
  max-width: 370px;
  margin-bottom: 18px;
  padding: 14px 16px;
  border: 1.5px solid #b0bec5;
  border-radius: 10px;
  font-size: 1.08rem;
  background: #f9fafb;
  transition: border 0.2s, box-shadow 0.2s;
  outline: none;
  box-shadow: 0 1px 4px rgba(66, 185, 131, 0.04);
}
input:focus {
  border: 2px solid #42b983;
  background: #e3f2fd;
  box-shadow: 0 2px 8px rgba(66, 185, 131, 0.1);
}

button {
  background: linear-gradient(90deg, #42b983 0%, #0d47a1 100%);
  color: #fff;
  border: none;
  border-radius: 10px;
  padding: 14px 32px;
  margin: 18px 0 0 0;
  font-size: 1.12rem;
  font-weight: 700;
  cursor: pointer;
  transition: background 0.2s, box-shadow 0.2s, transform 0.1s;
  box-shadow: 0 2px 12px rgba(66, 185, 131, 0.1);
  letter-spacing: 0.5px;
}
button:hover {
  background: linear-gradient(90deg, #0d47a1 0%, #42b983 100%);
  box-shadow: 0 6px 24px rgba(13, 71, 161, 0.13);
  transform: translateY(-2px) scale(1.03);
}

.mensaje {
  font-size: 1.13rem;
  font-weight: 600;
  text-align: center;
  color: #388e3c;
  background: #e8f5e9;
  border-radius: 8px;
  padding: 10px;
  margin-bottom: 18px;
  width: 100%;
  max-width: 370px;
}
.mensaje[style*="red"] {
  color: #c62828 !important;
  background: #ffebee;
}

@media (max-width: 700px) {
  .factura-container {
    padding: 24px 10px;
    max-width: 98vw;
  }
  input,
  .mensaje {
    max-width: 98vw;
    font-size: 1rem;
  }
  h2 {
    font-size: 1.3rem;
  }
}

@media (max-width: 480px) {
  .factura-container {
    padding: 10px 2vw;
    margin: 10px auto;
    border-radius: 10px;
  }
  input,
  .mensaje {
    font-size: 0.98rem;
  }
}
</style>
