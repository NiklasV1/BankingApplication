<script setup>
import { defineProps } from 'vue';
const props = defineProps(["depositId", "amount", "timestamp"])
</script>

<template>
    <tr>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono">{{ depositId }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono">{{ amount }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900 font-mono">{{ timestamp }}</td>
        <td class="py-4 px-6 border-b border-gray-200 text-slate-900">
            <span class="bg-green-500 text-white py-1 px-2 rounded-full text-xs shadow-md">Complete</span>
        </td>
    </tr>
</template>