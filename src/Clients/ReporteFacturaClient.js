import axios from "axios";
const URL_API = 'http://localhost:8081/api/mercado/v1/facturas';
const URL_API_CLIENTES = 'http://localhost:8081/api/mercado/v1/clientes';

// Función básica para consultar facturas
export const consultarFacturas = async () => {
    try {
        console.log('🔍 Consultando facturas en:', URL_API);
        const response = await axios.get(URL_API);
        console.log('✅ Respuesta del servidor:', response);
        console.log('📊 Datos de facturas recibidos:', response.data);
        
        // Verificar si response.data es un array
        if (Array.isArray(response.data)) {
            console.log(`📈 Se encontraron ${response.data.length} facturas`);
            return response.data;
        } else {
            console.warn('⚠️ La respuesta no es un array:', response.data);
            return [];
        }
    } catch (error) {
        console.error('❌ Error consultando facturas:', error);
        console.error('❌ Detalles del error:', {
            message: error.message,
            status: error.response?.status,
            statusText: error.response?.statusText,
            data: error.response?.data,
            url: error.config?.url
        });
        throw error;
    }
}

// Función simplificada para consultar clientes
export const consultarClientes = async () => {
    try {
        console.log('🔍 Consultando todos los clientes en:', URL_API_CLIENTES);
        const response = await axios.get(URL_API_CLIENTES);
        console.log('✅ Clientes recibidos:', response.data);
        
        if (Array.isArray(response.data)) {
            console.log(`📈 Se encontraron ${response.data.length} clientes`);
            return response.data;
        } else {
            console.warn('⚠️ La respuesta de clientes no es un array:', response.data);
            return [];
        }
    } catch (error) {
        console.error('❌ Error consultando clientes:', error);
        return [];
    }
}

// Función para obtener información completa del cliente por cédula (simplificada)
export const obtenerClientePorCedula = async (cedula) => {
    try {
        console.log('🔍 Buscando cliente con cédula:', cedula);
        
        // Obtener todos los clientes y filtrar
        const clientes = await consultarClientes();
        const cliente = clientes.find(c => 
            c.cedula === cedula || 
            c.identificacion === cedula || 
            c.ci === cedula ||
            String(c.cedula) === String(cedula)
        );
        
        if (cliente) {
            console.log('✅ Cliente encontrado:', cliente);
        } else {
            console.warn('⚠️ No se encontró cliente con cédula:', cedula);
        }
        
        return cliente || null;
    } catch (error) {
        console.error('❌ Error obteniendo cliente:', error);
        return null;
    }
}

// Función para obtener facturas con información completa del cliente incluyendo género
export const consultarFacturasConClientes = async () => {
    try {
        console.log('🚀 Iniciando consulta de facturas con clientes...');
        
        // Primero obtener las facturas
        const facturas = await consultarFacturas();
        
        if (!facturas || facturas.length === 0) {
            console.warn('⚠️ No se encontraron facturas para procesar');
            return [];
        }
        
        console.log('📊 Procesando', facturas.length, 'facturas...');
        
        // Obtener todos los clientes de una vez para ser más eficiente
        const todosLosClientes = await consultarClientes();
        console.log('👥 Clientes disponibles:', todosLosClientes.length);
        
        // Para cada factura, encontrar su cliente correspondiente
        const facturasConClientes = facturas.map((factura, index) => {
            console.log(`🔄 Procesando factura ${index + 1}/${facturas.length}:`, factura);
            
            // Buscar la cédula en diferentes campos posibles
            const cedula = factura.cedula || 
                          factura.identificacionCliente || 
                          factura.cedulaCliente || 
                          factura.clienteCedula ||
                          factura.ruc ||
                          factura.ci;
            
            if (!cedula) {
                console.warn('⚠️ Factura sin identificación de cliente:', factura);
                return {
                    ...factura,
                    clienteCompleto: null
                };
            }
            
            // Buscar el cliente correspondiente
            const cliente = todosLosClientes.find(c => 
                c.cedula === cedula || 
                c.identificacion === cedula || 
                c.ci === cedula ||
                String(c.cedula) === String(cedula)
            );
            
            if (cliente) {
                console.log('✅ Cliente encontrado para factura:', cliente.nombre, cliente.apellido);
            } else {
                console.warn('⚠️ No se encontró cliente para cédula:', cedula);
            }
            
            return {
                ...factura,
                clienteCompleto: cliente || null
            };
        });
        
        const clientesEncontrados = facturasConClientes.filter(f => f.clienteCompleto).length;
        console.log(`🎉 Procesamiento completado: ${facturasConClientes.length} facturas, ${clientesEncontrados} con cliente`);
        
        return facturasConClientes;
    } catch (error) {
        console.error('❌ Error consultando facturas con clientes:', error);
        throw error;
    }
}
