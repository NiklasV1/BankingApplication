<script>
export default {
    name: "Register",
    methods: {
        registerProcess() {


            this.$emit("loginComplete");
        }
    }
}
</script>

<script setup>
import { ref } from "vue";
import { defineProps } from "vue";

const props = defineProps(["auth_id", "auth_usr", "auth_pw"])


const username = ref("");
const password = ref("");
const firstName = ref("");
const lastName = ref("");
const address = ref("");
</script>

<template>
    <main class="w-full h-full bg-slate-200 flex items-center justify-center">
        <form class="flex flex-col gap-2 min-w-96 border-2 rounded-lg border-slate-800 p-2 bg-slate-800">

            <!-- Header -->
            <h1 class="text-white h12 text-center w-full font-semibold text-lg">Customer Registration</h1>
            <hr class="h-0 border-t-2 border-white">

            <div class="flex gap-4">
                <!-- Username -->
                <div class="flex flex-col gap-1">
                    <label for="username" class="text-white font-semibold">Username</label>
                    <input v-bind="username" id="username" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Username"/>
                </div>
    
                <!-- Password -->
                <div class="flex flex-col gap-1">
                    <label for="password" class="text-white font-semibold">Password</label>
                    <input v-bind="password" id="password" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="password" placeholder="Password"/>
                </div>
            </div>

            <div class="flex gap-4">
                <!-- First Name -->
                <div class="flex flex-col gap-1">
                    <label for="firstName" class="text-white font-semibold">First name</label>
                    <input v-bind="firstName" id="firstName" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="First Name"/>
                </div>
    
                <!-- Last Name -->
                <div class="flex flex-col gap-1">
                    <label for="lastName" class="text-white font-semibold">Last name</label>
                    <input v-bind="lastName" id="lastName" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Last Name"/>
                </div>
            </div>

            <!-- Address -->
            <div class="flex flex-col gap-1 ">
                <label for="address" class="text-white font-semibold">Address</label>
                <input v-bind="address" id="address" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Address"/>
            </div>                
            <hr class="h-0 border-t-2 border-white my-1">
            
            <!-- Buttons -->
            <div class="flex gap-4 items-stretch px-2 pb-2">
                <button class="text-white bg-slate-800 rounded-lg p-2 text-lg w-full h-12 font-semibold border-white border-2 hover:bg-blue-800 hover:border-blue-800 focus:outline-none focus:border-blue-800 focus:bg-blue-800" @click="registerProcess">Register</button>
            </div>
        </form>
    </main>
</template>