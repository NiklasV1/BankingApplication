<script setup>
import Sidebar from './Sidebar.vue';
import { defineEmits } from 'vue';
import { defineProps } from 'vue';
const props = defineProps(["auth_id", "auth_usr", "auth_pw"])


const emit = defineEmits(["logout"]);
</script>

<template>
    <main class="h-full w-full flex">

        <!-- Sidebar -->
        <Sidebar @logout="$emit('logout')"/>
    </main>
</template>