<script setup>
    import { defineEmits } from 'vue';
    import { defineProps } from 'vue';

    const props = defineProps(["text", "icon", "option", "selected"]);
    const emit = defineEmits(["selected"]);

    function emitSelected() {
        emit("selected");
    }
</script>

<template>
    <div @click="emitSelected" :class="`${selected == option ? 'bg-slate-700' : 'bg-slate-800'}`" class="w-full min-h-14 text-left pl-5 hover:bg-slate-700 flex gap-2 items-center">
        <span :class="`${selected == option ? 'text-blue-200' : 'text-white'}`" class="material-icons text-4xl select-none">{{ icon }}</span>
        <div :class="`${selected == option ? 'border-r-8 border-blue-300' : ''}`" class="h-14 w-full flex flex-col justify-center">
            <span :class="`${selected == option ? 'text-blue-200' : 'text-white'}`" class="text-lg font-semibold select-none">{{ text }}</span>
        </div>
    </div>
</template>