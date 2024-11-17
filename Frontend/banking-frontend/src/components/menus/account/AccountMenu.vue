<script setup>
import { defineProps } from 'vue';
import { ref } from 'vue';
import AccountRow from './AccountRow.vue';
import axios from 'axios';

const props = defineProps(["auth_id", "auth_usr", "auth_pw"])

const newAccountName = ref("");
</script>

<script>
import { sha3_256 } from 'js-sha3';

export default {
    data() {
        return {
            accounts: [],
            apiEndpoint: 'http://localhost:8080/api/account'
        }
    },
    mounted() {
        axios.get(`${this.apiEndpoint}/all`).then(response => {
            this.accounts = response.data
            console.log(this.accounts)
        })
    },
    methods: {
        refreshAcounts() {
            axios.get(`${this.apiEndpoint}/all`).then(response => {
                this.accounts = response.data
                console.log(this.accounts)
            })
        },
        freeze(accountId) {
            axios.post(`${this.apiEndpoint}/freeze`, {
                id: this.auth_id,
                username: this.auth_usr,
                plainPassword: this.auth_pw,
                accountId: accountId
            }).then(response => {
                console.log(accountId)
                alert(response.data)
                this.refreshAcounts()
            })
        },
        unfreeze(accountId, accountName) {
            let unfreezeCode = sha3_256(accountId+this.auth_pw+accountName)
            console.log(unfreezeCode)
            axios.post(`${this.apiEndpoint}/unfreeze`, {
                id: this.auth_id,
                username: this.auth_usr,
                plainPassword: this.auth_pw,
                accountId: accountId,
                unfreezeCode: unfreezeCode
            }).then(response => {
                console.log(accountId)
                this.refreshAcounts()
            })
        }
    }
}
</script>

<template>
    <main class="h-full w-full flex flex-col items-center">
        <div class="my-5">
            <h1 class="text-center text-2xl font-semibold text-slate-800">Accounts</h1>
        </div>
        <div class="shadow-lg rounded-lg overflow-hidden mx-4 md:mx-10">
            <table class="table-auto">
                <thead>
                    <tr class="bg-gray-100">
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Name</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Number</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Balance</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Status</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Manage</th>
                    </tr>
                </thead>
                <tbody class="bg-white">
                    <AccountRow @freeze="freeze" @unfreeze="unfreeze" v-for="acc in accounts" :name="acc.name" :accountId="acc.accountId" :balance="acc.balance" :frozen="acc.frozen"/>
                </tbody>
            </table>
            
        </div>
        <div class="flex gap-2 mt-6 items-end">
            <div class="flex flex-col">
                <label class="font-bold text-sm text-slate-900 pb-1">Create Account</label>
                <input class="shadow-md hover:border-blue-400 focus:border-blue-400 focus:outline-none font-semibold text-slate-900 h-10 p-2 border-2 border-slate-300 placeholder:text-slate-400 rounded-lg" placeholder="Name" v-model="newAccountName" type="text"/>
            </div>
            <button @click="refreshAcounts" class="shadow-lg hover:bg-blue-600 hover:border-blue-600 focus:ring-2 focus:ring-blue-400 focus:outline-none bg-blue-500 border-blue-500 text-white font-semibold px-2 h-10 border-2 rounded-lg">Create</button>
        </div>
    </main>
</template>