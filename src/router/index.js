import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import BodegaView from "../views/BodegaView.vue";
import ProductoView from "../views/ProductoView.vue";
import ClienteView from "../views/ClienteView.vue";
import FacturaView from "../views/FacturaView.vue";
import ReporteFacturaView from "../views/ReporteFactura.vue";

// import { obtenerPaginasPermitidas } from '../helpers/Autorizacion'

function estaAutenticado() {
  let result = localStorage.getItem("auth") === "true";
  console.log(result);
  return result;
}

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
  },
  {
    path: "/bodega",
    name: "bodega",
    component: BodegaView,
  },
  {
    path: "/producto",
    name: "producto",
    component: ProductoView,
  },
  {
    path: "/cliente",
    name: "cliente",
    component: ClienteView,
  },
  {
    path: "/factura",
    name: "factura",
    component: FacturaView,
    // meta: {
    //   requiereAutenticacion: true
    // },
  },
  {
    path: "/reporte",
    name: "reporte",
    component: ReporteFacturaView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// Guardian
router.beforeEach((to, from, next) => {
  console.log("antes");
  if (to.meta.requiereAutenticacion) {
    console.log("auth");
    if (!estaAutenticado()) {
      next("/");
    } else {
      // Aquí podrías validar autorización si tienes lógica de permisos
      next();
    }
  } else {
    next();
  }
});

export default router;
