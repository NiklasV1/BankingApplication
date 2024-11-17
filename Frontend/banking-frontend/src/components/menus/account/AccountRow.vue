<script setup>
import { defineProps } from 'vue';
import { defineEmits } from 'vue';
const props = defineProps(["name", "accountId", "balance", "frozen"])
const emit = defineEmits(["freeze", "unfreeze", "deleteAccount"])

function freeze(id, accName, frozen) {
    if (frozen) {
        emit('unfreeze', id, accName)
    } else {
        emit('freeze', id)
    }
}

function deleteAccount(id, balance) {
    if (balance > 0) {
        alert("Balance must be zero to delete!")
    } else {
        emit('deleteAccount', id)
    }
}

</script>

<template>
    <tr>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 border-r">{{ name }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ accountId }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 border-r">{{ balance }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 border-r flex items-center justify-center">
            <span :class="`${frozen ? 'bg-red-500' : 'bg-green-500'}`" class="bg-green-500 text-white py-1 px-2 rounded-full text-xs shadow-md">{{ frozen ? "Frozen" : "Active" }}</span>
        </td>
        <td class="py-2 px-6 border-b border-gray-200 text-slate-900">
            <button @click="freeze(accountId, name, frozen)" class="w-24 shadow-lg hover:bg-blue-600 hover:border-blue-600 focus:ring-2 focus:ring-blue-400 focus:outline-none bg-blue-500 border-blue-500 text-white font-semibold px-2 h-8 border-2 rounded-lg">{{ frozen ? "Unfreeze" : "Freeze" }}</button>
            <button @click="deleteAccount(accountId, balance)" class="ml-2 w-16 shadow-lg hover:bg-red-600 hover:border-red-600 focus:ring-2 focus:ring-red-400 focus:outline-none bg-red-500 border-red-500 text-white font-semibold px-2 h-8 border-2 rounded-lg">Delete</button>
        </td>
    </tr>
</template>