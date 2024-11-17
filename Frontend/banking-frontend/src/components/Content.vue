<template>
    <main class="bg-white col-span-full row-start-2 row-end-13">        
        <component @loginComplete="loginComplete" @register="registerCustomer" @logout="logout" v-bind:is="selectedComponent" :auth_id="auth_id" :auth_usr="auth_usr" :auth_pw="auth_pw"></component>
    </main>
</template>

<script setup>
import Login from "./menus/Login.vue";
import MainMenu from "./menus/MainMenu.vue";
import Register from "./menus/Register.vue";
import { ref } from "vue";

const selectedComponent = ref("Login")

const auth_id = ref("");
const auth_usr = ref("");
const auth_pw = ref("");

function loginComplete(id, username, password) {
    console.log(id + username + password)
    auth_id.value = id;
    auth_usr.value = username;
    auth_pw.value = password;
    selectedComponent.value = "MainMenu";
}

function registerCustomer() {
    selectedComponent.value = "Register";
}

function logout() {
    selectedComponent.value = "Login"
}


</script>

<script>
export default {
    components: {
        Login, MainMenu, Register
    }
}
</script>
