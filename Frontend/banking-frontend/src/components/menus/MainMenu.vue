<script setup>
import Sidebar from './Sidebar.vue';
import { defineEmits } from 'vue';

const emit = defineEmits(["logout"]);
</script>

<template>
    <main class="h-full w-full flex">

        <!-- Sidebar -->
        <Sidebar @logout="$emit('logout')"/>




    </main>
</template>