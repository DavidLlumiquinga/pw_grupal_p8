<template>
  <div class="home">
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "HomeView",
  components: {
    HelloWorld,
  },
};
</script>

<style scoped>
.home-container {
  max-width: 600px;
  margin: 48px auto 0 auto;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(44, 62, 80, 0.09);
  padding: 48px 32px 40px 32px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
}
h1 {
  color: #2c3e50;
  font-size: 2.1rem;
  font-weight: 700;
  margin-bottom: 2.2rem;
  letter-spacing: 1px;
  text-align: center;
}
.home-buttons {
  display: flex;
  gap: 1.5rem;
  margin-top: 1.5rem;
  width: 100%;
  justify-content: center;
}
button {
  flex: 1;
  background: linear-gradient(90deg, #28a745 0%, #218838 100%);
  color: #fff;
  border: none;
  border-radius: 10px;
  padding: 18px 0;
  font-size: 1.15rem;
  font-weight: 700;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  max-width: 220px;
}
button:hover {
  background: linear-gradient(90deg, #218838 0%, #28a745 100%);
  transform: translateY(-2px) scale(1.03);
}
@media (max-width: 600px) {
  .home-container {
    padding: 18px 6px;
    max-width: 98vw;
    margin: 18px auto;
  }
  h1 {
    font-size: 1.3rem;
  }
  .home-buttons {
    flex-direction: column;
    gap: 1rem;
  }
  button {
    font-size: 1rem;
    padding: 13px 0;
    max-width: 100%;
  }
}
</style>
