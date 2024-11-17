<script setup>
import { defineProps } from 'vue';
import { defineEmits } from 'vue';
const props = defineProps(["name", "accountId", "balance", "frozen"])
const emit = defineEmits(["freeze", "unfreeze"])

function freeze(id, accName, frozen) {
    if (frozen) {
        emit('unfreeze', id, accName)
    } else {
        emit('freeze', id)
    }
}

</script>

<template>
    <tr>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">{{ name }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono">{{ accountId }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">{{ balance }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">
            <span :class="`${frozen ? 'bg-red-500' : 'bg-green-500'}`" class="bg-green-500 text-white py-1 px-2 rounded-full text-xs shadow-md">{{ frozen ? "Frozen" : "Active" }}</span>
        </td>
        <td class="py-2 px-6 border-b border-gray-200 text-slate-900">
            <button @click="freeze(accountId, name, frozen)" class="w-24 shadow-lg hover:bg-blue-600 hover:border-blue-600 focus:ring-2 focus:ring-blue-400 focus:outline-none bg-blue-500 border-blue-500 text-white font-semibold px-2 h-8 border-2 rounded-lg">{{ frozen ? "Unfreeze" : "Freeze" }}</button>
        </td>
    </tr>
</template>