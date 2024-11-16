<template>
    <main class="w-full h-full bg-slate-200 flex items-center justify-center">
        <form class="flex flex-col gap-2 min-w-80 max-w-96 border-4 rounded-lg border-slate-800 bg-slate-800 p-2">

            <!-- Header -->
            <h1 class="text-white h12 text-center w-full font-semibold text-lg">Customer Login</h1>
            <hr class="h-0 border-t-2 border-white my-1">

            <!-- Username -->
            <div class="flex flex-col gap-1 px-2">
                <label class="text-white font-semibold">Username</label>
                <input class="bg-slate-800 text-white placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="text" placeholder="Username"/>
            </div>

            <!-- Password -->
            <div class="flex flex-col gap-1 px-2">
                <label class="text-white font-semibold">Password</label>
                <input class="bg-slate-800 text-white placeholder:text-gray-400 w-full h-12 border-2 border-white rounded-lg p-2 focus:border-blue-800 focus:outline-none" type="password" placeholder="Password"/>
            </div>
            <hr class="h-0 border-t-2 border-white my-2">
            

            <!-- Buttons -->
            <div class="flex gap-4 items-stretch px-2 pb-2">
                <button class="text-white bg-slate-800 rounded-lg p-2 text-lg basis-1/2 h-12 font-semibold border-white border-2 hover:bg-blue-800 hover:border-blue-800 focus:outline-none focus:border-blue-800 focus:bg-blue-800" @click="loginProcess">Login</button>
                <button class="text-white bg-slate-800 rounded-lg p-2 text-lg basis-1/2 h-12 font-semibold border-white border-2 hover:bg-blue-800 hover:border-blue-800 focus:outline-none focus:border-blue-800 focus:bg-blue-800" @click="registerRedirect">Register</button>
            </div>
        </form>
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