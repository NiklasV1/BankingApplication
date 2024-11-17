<script setup>
import { ref } from 'vue';
import { defineEmits } from 'vue';
import { defineProps } from 'vue';
import axios from 'axios';

const props = defineProps(["auth_id", "auth_usr", "auth_pw"])
const emit = defineEmits(["loginComplete", "register"])

const customerEndpoint = 'http://localhost:8080/api/customer/login'

const username = ref("");
const password = ref("");

async function login() {
    try {
        let usr = username.value
        let pw = password.value
        const response = await axios.post(customerEndpoint, {
            username: usr,
            plainPassword: pw
        })
        console.log("CustomerId:" + response.data)
        emit("loginComplete", response.data, usr, pw)
    } catch (error) {
        console.log(error);
    }
}

function registerRedirect(){
    emit("register")
}
</script>



<template>
    <main class="w-full h-full bg-slate-200 flex items-center justify-center">
        <div class="flex flex-col gap-2 min-w-80 max-w-96 border-4 rounded-lg border-slate-800 bg-slate-800 p-2 shadow-lg">

            <!-- Header -->
            <h1 class="text-white h12 text-center w-full font-semibold text-lg">Customer Login</h1>
            <hr class="h-0 border-t-2 border-white my-1">

            <!-- Username -->
            <div class="flex flex-col gap-1 px-2">
                <label class="text-white font-semibold">Username</label>
                <input v-model="username" class="bg-slate-800 text-white placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Username"/>
            </div>

            <!-- Password -->
            <div class="flex flex-col gap-1 px-2">
                <label class="text-white font-semibold">Password</label>
                <input v-model="password" class="bg-slate-800 text-white placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="password" placeholder="Password"/>
            </div>
            <hr class="h-0 border-t-2 border-white my-2">
            

            <!-- Buttons -->
            <div class="flex gap-4 items-stretch px-2 pb-2">
                <button class="text-white bg-slate-800 rounded-lg p-2 text-lg basis-1/2 h-12 font-semibold border-white border-2 hover:bg-blue-800 hover:border-blue-800 focus:outline-none focus:border-blue-800 focus:bg-blue-800" @click="login">Login</button>
                <button class="text-white bg-slate-800 rounded-lg p-2 text-lg basis-1/2 h-12 font-semibold border-white border-2 hover:bg-blue-800 hover:border-blue-800 focus:outline-none focus:border-blue-800 focus:bg-blue-800" @click="registerRedirect">Register</button>
            </div>
        </div>
    </main>
</template>