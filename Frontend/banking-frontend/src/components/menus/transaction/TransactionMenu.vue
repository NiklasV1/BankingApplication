<script setup>
import TransactionRow from './TransactionRow.vue';
import { defineProps } from 'vue';
import axios from 'axios';

const props = defineProps(["auth_id", "auth_usr", "auth_pw"])

</script>

<script>
export default {
    data() {
        return {
            accounts: [],
            transactions: [],
            selectedAccount: "",
            receiver: "",
            newAmount: "",
            message: "",
            mode: "",
            apiEndpointAccount: 'http://localhost:8080/api/account',
            apiEndpointTransaction: 'http://localhost:8080/api/transaction'
        }
    },
    mounted() {
        this.refreshAcounts()
        this.refreshTransactions()
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
                    accountIds.push({
                        id: account.accountId,
                        balance: account.balance
                    })
                });
                console.log(accountIds)
                this.accounts = accountIds
                console.log(this.accounts)
            })
        },
        refreshTransactions() {
            if (this.selectedAccount != "" && this.mode != "") {
                console.log(this.selectedAccount)
                let url = this.apiEndpointTransaction + "/view"
                switch (this.mode) {
                    case "Received":
                        url += "/received"
                        break
                    case "Sent":
                        url += "/sent"
                        break
                }
                axios.post(url, {
                    id: this.auth_id,
                    username: this.auth_usr,
                    plainPassword: this.auth_pw,
                    accountId: this.selectedAccount
                }).then(response => {
                    this.transactions = response.data
                    console.log(this.transactions)
                })
            }
        },
        makeTransaction() {
            if (this.selectedAccount != "" && this.newAmount != "" && this.receiver != "" && this.message != "") {
                console.log("Transaction: " + this.selectedAccount)
                axios.post(`${this.apiEndpointTransaction}/make`, {
                    id: this.auth_id,
                    username: this.auth_usr,
                    plainPassword: this.auth_pw,
                    accountId: this.selectedAccount,
                    receiverId: this.receiver,
                    message: this.message,
                    amount: this.newAmount
                }).then(response => {
                    console.log(response.data)
                    this.refreshTransactions()
                })
            }
        }
    }
}
</script>

<template>
    <main class="h-full w-full flex flex-col items-center">
        <div class="mt-20 mb-4 flex gap-4">
            <h1 class="text-center text-2xl font-semibold text-slate-800 select-none">Transactions</h1>
            <select @change="refreshTransactions" v-model="selectedAccount" class="w-[90] text-slate-800 font-semibold font-mono px-2">
                <option disabled value="" class="font-mono">Select an Account</option>
                <option v-for="acc in accounts" class="font-mono">{{ acc.id }}</option>
            </select>
            <select @change="refreshTransactions" v-model="mode" class="w-44 text-slate-800 font-semibold font-mono px-2">
                <option disabled value="" class="font-mono">Select Type</option>
                <option value="Sent">Sent</option>
                <option value="Received">Received</option>
            </select>
        </div>
        <div class="shadow-lg rounded-lg overflow-hidden mx-4 md:mx-10">
            <table class="table-auto">
                <thead>
                    <tr class="bg-gray-100">
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase select-none">Transaction-ID</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase select-none">Message</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase select-none">Amount</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase select-none">{{ mode === 'Received' ? 'Sender-ID' : 'Receiver-ID' }}</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase select-none">Date</th>
                        <th class="py-4 px-6 text-left text-gray-600 font-bold uppercase select-none">Status</th>
                    </tr>
                </thead>
                <tbody class="bg-white">
                    <TransactionRow v-for="trans in transactions" :transactionId="trans.transactionId" :message="trans.message" :amount="trans.amount" :partner="mode === 'Received' ? trans.senderId : trans.receiverId" :timestamp="trans.timestamp"/>
                </tbody>
                <tfoot>
                    <tr>
                        <td class="py-4 px-6 border-b border-gray-200 text-slate-900" colspan="6">
                            <div class="flex justify-center items-center gap-4">
                                <label class="font-bold text-slate-900 pb-1 select-none">New Transaction</label>
                                <input class="w-96 shadow-md hover:border-blue-400 focus:border-blue-400 focus:outline-none font-semibold text-slate-900 h-10 p-2 border-2 border-slate-300 placeholder:text-slate-400 rounded-lg font-mono" placeholder="Receiver-ID" v-model="receiver" type="text"/>
                                <input class="w-56 shadow-md hover:border-blue-400 focus:border-blue-400 focus:outline-none font-semibold text-slate-900 h-10 p-2 border-2 border-slate-300 placeholder:text-slate-400 rounded-lg font-mono" placeholder="Message" v-model="message" type="text"/>
                                <input class="w-36 shadow-md hover:border-blue-400 focus:border-blue-400 focus:outline-none font-semibold text-slate-900 h-10 p-2 border-2 border-slate-300 placeholder:text-slate-400 rounded-lg" placeholder="Amount" v-model="newAmount" type="number"/>
                                <button @click="makeTransaction" class="shadow-lg hover:bg-blue-600 hover:border-blue-600 focus:ring-2 focus:ring-blue-400 focus:outline-none bg-blue-500 border-blue-500 text-white font-semibold h-10 border-2 rounded-lg w-20">Create</button>
                            </div>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </main>
</template>