<template>
    <main class="w-full h-full bg-slate-200 flex items-center justify-center">
        <div class="flex flex-col gap-2 min-w-80 max-w-96 border-4 rounded-lg border-green-500 bg-white">

            <!-- Header -->
            <h1 class="text-gray-800 h12 text-center w-full font-semibold text-lg pt-2">Customer Login</h1>
            <hr class="h-0.5 border-2 border-green-500">

            <!-- Username -->
            <div class="flex flex-col gap-1 px-2">
                <label class="text-gray-800 font-semibold">Username</label>
                <input class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="text" placeholder="Username"/>
            </div>

            <!-- Password -->
            <div class="flex flex-col gap-1 px-2">
                <label class="text-gray-800 font-semibold">Password</label>
                <input class="text-gray-800 placeholder:text-gray-400 w-full h-12 border-4 border-gray-300 rounded-lg p-2 focus:border-green-300 focus:outline-none" type="password" placeholder="Password"/>
            </div>
            <hr class="h-0.5 border-2 border-green-500 my-1">
            

            <!-- Buttons -->
            <div class="flex gap-4 items-stretch px-2 pb-2">
                <button class="text-white bg-green-500 rounded-lg p-2 text-lg basis-1/2 h-12 font-semibold border-green-500 border-4 hover:bg-green-600 hover:border-green-600 focus:outline-none focus:text-green-500 focus:bg-white" @click="loginProcess">Login</button>
                <button class="text-white bg-green-500 rounded-lg p-2 text-lg basis-1/2 h-12 font-semibold border-green-500 border-4 hover:bg-green-600 hover:border-green-600 focus:outline-none focus:text-green-500 focus:bg-white" @click="registerRedirect">Register</button>
            </div>

        </div>
    </main>
</template>

<script>
export default {
    name: "Login",
    methods: {
        loginProcess() {
            this.$emit("loginComplete");
        },
        registerRedirect() {
            this.$emit("register")
        }
    }
}
</script>