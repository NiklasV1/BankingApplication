<script setup>
import { defineProps } from 'vue';
const props = defineProps(["depositId", "amount", "timestamp"])
</script>

<template>
    <tr>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ depositId }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ amount }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono border-r">{{ timestamp }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 flex justify-center items-center">
            <span class="bg-green-500 text-white py-1 px-2 rounded-full text-xs shadow-md">Complete</span>
        </td>
    </tr>
</template>