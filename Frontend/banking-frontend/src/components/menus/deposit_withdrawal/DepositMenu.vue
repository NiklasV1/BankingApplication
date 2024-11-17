<script setup>
import DepositRow from './DepositRow.vue';
import { defineProps } from 'vue';
import { ref } from 'vue';
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
            deposits: [],
            selectedAccount: "",
            newAmount: "",
            apiEndpointAccount: 'http://localhost:8080/api/account',
            apiEndpointDeposit: 'http://localhost:8080/api/deposit'
        }
    },
    mounted() {
        this.refreshAcounts()
        this.refreshDeposits()
    },
    methods: {
        refreshAcounts() {
            axios.post(`${this.apiEndpointAccount}/view`, {
                id: this.auth_id,
                username: this.auth_usr,
                plainPassword: this.auth_pw
            }).then(response => {
                let accountIds = []
                response.data.forEach(account => {
                    accountIds.push(account.accountId)
                });
                console.log(accountIds)
                this.accounts = accountIds
                console.log(this.accounts)
            })
        },
        refreshDeposits() {
            if (this.selectedAccount != "") {
                console.log(this.selectedAccount)
                axios.post(`${this.apiEndpointDeposit}/view`, {
                    id: this.auth_id,
                    username: this.auth_usr,
                    plainPassword: this.auth_pw,
                    accountId: this.selectedAccount
                }).then(response => {
                    this.deposits = response.data
                    console.log(this.deposits)
                })
            }
        },
        makeDeposit(amount) {
            if (this.selectedAccount != "" && this.newAmount != "") {
                console.log("Deposit: " + this.selectedAccount)
                axios.post(`${this.apiEndpointDeposit}/make`, {
                    id: this.auth_id,
                    username: this.auth_usr,
                    plainPassword: this.auth_pw,
                    accountId: this.selectedAccount,
                    amount: this.newAmount
                }).then(response => {
                    console.log(response.data)
                    this.refreshDeposits()
                })
            }
        }
    }
}
</script>

<template>
    <main class="h-full w-full flex flex-col items-center">
        <div class="mt-20 mb-4 flex gap-4">
            <h1 class="text-center text-2xl font-semibold text-slate-800">Deposits</h1>
            <select @change="refreshDeposits" v-model="selectedAccount" class="w-90 text-slate-800 font-semibold font-mono px-2">
                <option disabled value="" class="font-mono">Select an Account</option>
                <option v-for="acc in accounts" class="font-mono">{{ acc }}</option>
            </select>
        </div>
        <div class="shadow-lg rounded-lg overflow-hidden mx-4 md:mx-10">
            <table class="table-auto">
                <thead>
                    <tr class="bg-gray-100">
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">ID</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Amount</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Date</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase">Status</th>
                    </tr>
                </thead>
                <tbody class="bg-white">
                    <DepositRow v-for="dep in deposits" :depositId="dep.depositId" :amount="dep.amount" :timestamp="dep.timestamp"/>
                </tbody>
                <tfoot>
                    <tr>
                        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">
                            <div class="flex justify-end">
                                <label class="font-bold text-slate-900 pb-1 select-none">New Deposit</label>
                            </div>
                        </td>
                        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">
                            <input class="w-36 shadow-md hover:border-blue-400 focus:border-blue-400 focus:outline-none font-semibold text-slate-900 h-10 p-2 border-2 border-slate-300 placeholder:text-slate-400 rounded-lg" placeholder="Amount" v-model="newAmount" type="number"/>
                        </td>
                        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">
                            <button @click="makeDeposit" class="shadow-lg hover:bg-blue-600 hover:border-blue-600 focus:ring-2 focus:ring-blue-400 focus:outline-none bg-blue-500 border-blue-500 text-white font-semibold h-10 border-2 rounded-lg w-20">Create</button>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </main>
</template>