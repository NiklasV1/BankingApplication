<script setup>
import { defineProps } from 'vue';

const props = defineProps(["text"])
</script>

<template>
    <main class="col-start-1 col-end-13 row-span-1">

        <div class="bg-blue-800 h-full flex items-center">
            <h1 class="text-center font-bold text-5xl text-slate-50 w-full">{{ text }}</h1>
        </div>
    </main>
</template>