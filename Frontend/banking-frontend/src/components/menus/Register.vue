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

const username = ref("");
const password = ref("");
const firstName = ref("");
const lastName = ref("");
const address = ref("");
</script>

<template>
    <main class="w-full h-full bg-slate-200 flex items-center justify-center">
        <form class="flex flex-col gap-2 min-w-96 border-4 rounded-lg border-green-500 bg-white">
            <!-- Header -->
            <h1 class="text-gray-800 h12 text-center w-full font-semibold text-lg pt-2">Customer Registration</h1>
            <hr class="h-0.5 border-2 border-green-500">

            <div class="flex">
                <!-- Username -->
                <div class="flex flex-col gap-1 px-2">
                    <label for="username" class="text-gray-800 font-semibold">Username</label>
                    <input v-bind="username" id="username" class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="text" placeholder="Username"/>
                </div>
    
                <!-- Password -->
                <div class="flex flex-col gap-1 px-2">
                    <label for="password" class="text-gray-800 font-semibold">Password</label>
                    <input v-bind="password" id="password" class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="password" placeholder="Password"/>
                </div>
            </div>

            <div class="flex">
                <!-- First Name -->
                <div class="flex flex-col gap-1 px-2">
                    <label for="firstName" class="text-gray-800 font-semibold">First name</label>
                    <input v-bind="firstName" id="firstName" class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="text" placeholder="First Name"/>
                </div>
    
                <!-- Last Name -->
                <div class="flex flex-col gap-1 px-2">
                    <label for="lastName" class="text-gray-800 font-semibold">Last name</label>
                    <input v-bind="lastName" id="lastName" class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="text" placeholder="Last Name"/>
                </div>
            </div>

            <!-- Address -->
            <div class="flex flex-col gap-1 px-2">
                <label for="address" class="text-gray-800 font-semibold">Address</label>
                <input v-bind="address" id="address" class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="text" placeholder="Address"/>
            </div>                
            <hr class="h-0.5 border-2 border-green-500 my-1">
            
            <!-- Buttons -->
            <div class="flex gap-4 items-stretch px-2 pb-2">
                <button class="text-white bg-green-500 rounded-lg p-2 text-lg basis-full h-12 font-semibold border-green-500 border-4 hover:bg-green-600 hover:border-green-600 focus:outline-none focus:bg-green-600 focus:border-green-600" @click="registerProcess">Register</button>
            </div>
        </form>
    </main>
</template>