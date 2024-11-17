<script setup>
import { defineProps } from 'vue';
const props = defineProps(["transactionId", "message", "amount", "partner", "timestamp"])
</script>

<template>
    <tr>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ transactionId }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ message }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ amount }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ partner }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ timestamp }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 flex justify-center items-center">
            <span class="bg-green-500 text-white py-1 px-2 rounded-full text-xs shadow-md">Complete</span>
        </td>
    </tr>
</template>