<template>
    <main class="bg-white col-span-full row-start-2 row-end-13">
        <component @loginComplete="loginComplete" @register="registerCustomer" @logout="logout" v-bind:is="selectedComponent"></component>
    </main>
</template>

<script>
import Login from "./Login.vue";
import MainMenu from "./menus/MainMenu.vue";
import Register from "./menus/Register.vue";

export default {
    name: "Content",
    components: {
        Login, MainMenu, Register
    },
    data() {
        return {
            selectedComponent: "Login"
        }
    }, methods: {
        loginComplete(id, username, password) {
            this.selectedComponent = "MainMenu";
        },
        registerCustomer() {
            this.selectedComponent = "Register";
        },
        logout() {
            this.selectedComponent = "Login"
        }
    }, 
}
</script>
