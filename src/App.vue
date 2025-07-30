<template>
  <div>
    <nav>
      <router-link to="/bodega">Bodega</router-link> |
      <router-link to="/producto">Producto/Servicio</router-link> |
      <router-link to="/cliente">Cliente</router-link> |
      <router-link to="/factura">Generar Factura</router-link> |
      <router-link to="/reporte">Reporte de Facturas</router-link>
    </nav>
    <router-view />
  </div>
</template>

<style scoped>
.main-header {
  display: flex;
  gap: 24px;
  background: #23272f;
  color: #fff;
  padding: 18px 32px;
  font-size: 1.1rem;
  align-items: center;
  border-radius: 0 0 12px 12px;
  margin-bottom: 18px;
}
.main-header a {
  color: #fff;
  text-decoration: none;
  font-weight: 600;
  transition: color 0.2s;
}
.main-header a:hover {
  color: #7fa1c6;
}
nav {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  z-index: 100;
  background: linear-gradient(90deg, #23272f 0%, #1a2233 100%);
  padding: 20px 0 18px 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.07);
  text-align: center;
}
nav a {
  font-weight: 600;
  color: #fff;
  margin: 0 18px;
  text-decoration: none;
  transition: color 0.2s, background 0.2s;
  font-size: 1.1rem;
  padding: 8px 18px;
  border-radius: 8px;
}
nav a.router-link-exact-active {
  color: #fff;
  background: #3a4256;
  box-shadow: 0 2px 8px rgba(58, 66, 86, 0.12);
}
</style>
