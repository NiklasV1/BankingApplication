<script setup>
import { ref } from "vue";
import { defineProps } from "vue";
import { defineEmits } from "vue";
import axios from "axios";

const emit = defineEmits(["loginComplete"])
const props = defineProps(["auth_id", "auth_usr", "auth_pw"])

const registerEndpoint = 'http://localhost:8080/api/customer/register'


const username = ref("");
const password = ref("");
const firstName = ref("");
const lastName = ref("");
const address = ref("");

async function registerCustomer() {
    try {
        let firstn = firstName.value
        let lastn = lastName.value
        let addr = address.value
        let usr = username.value
        let pw = password.value
        const response = await axios.post(registerEndpoint, {
            firstName: firstn,
            lastName: lastn,
            address: addr,
            username: usr,
            plainPassword: pw
        })
        console.log("CustomerId:" + response.data)
        emit("loginComplete", response.data, usr, pw)
    } catch (error) {
        console.log(error);
    }
}
</script>

<template>
    <main class="w-full h-full bg-slate-200 flex items-center justify-center">
        <div class="flex flex-col gap-2 min-w-96 border-2 rounded-lg border-slate-800 p-2 bg-slate-800">

            <!-- Header -->
            <h1 class="text-white h12 text-center w-full font-semibold text-lg">Customer Registration</h1>
            <hr class="h-0 border-t-2 border-white">

            <div class="flex gap-4">
                <!-- Username -->
                <div class="flex flex-col gap-1">
                    <label class="text-white font-semibold">Username</label>
                    <input v-model="username" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Username"/>
                </div>
    
                <!-- Password -->
                <div class="flex flex-col gap-1">
                    <label class="text-white font-semibold">Password</label>
                    <input v-model="password" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="password" placeholder="Password"/>
                </div>
            </div>

            <div class="flex gap-4">
                <!-- First Name -->
                <div class="flex flex-col gap-1">
                    <label class="text-white font-semibold">First name</label>
                    <input v-model="firstName" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="First Name"/>
                </div>
    
                <!-- Last Name -->
                <div class="flex flex-col gap-1">
                    <label class="text-white font-semibold">Last name</label>
                    <input v-model="lastName" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Last Name"/>
                </div>
            </div>

            <!-- Address -->
            <div class="flex flex-col gap-1 ">
                <label class="text-white font-semibold">Address</label>
                <input v-model="address" class="text-white bg-slate-800 placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Address"/>
            </div>                
            <hr class="h-0 border-t-2 border-white my-1">
            
            <!-- Buttons -->
            <div class="flex gap-4 items-stretch px-2 pb-2">
                <button class="text-white bg-slate-800 rounded-lg p-2 text-lg w-full h-12 font-semibold border-white border-2 hover:bg-blue-800 hover:border-blue-800 focus:outline-none focus:border-blue-800 focus:bg-blue-800" @click="registerCustomer">Register</button>
            </div>
        </div>
    </main>
</template>