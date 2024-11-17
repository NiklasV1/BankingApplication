<script setup>
    import { ref } from 'vue';
    import SidebarOption from './SidebarOption.vue';
    import { defineEmits } from 'vue';

    const emit = defineEmits(["logout", "select"]);
    const selected = ref(1);

    function selectOption(x) {
        selected.value = x;
        emit("select", x)
    }
</script>

<template>
    <main class="bg-slate-800 min-w-64 flex flex-col">
        <div class="w-full text-left pl-5 py-2 font-semibold text-white">Menu</div>
        <div class="flex flex-col items-start">
            <SidebarOption @selected="selectOption(1)" text="Accounts" icon="account_balance" option="1" :selected="selected"/>
            <SidebarOption @selected="selectOption(2)" text="Transactions" icon="paid" option="2" :selected="selected"/>
            <SidebarOption @selected="selectOption(3)" text="Deposits" icon="savings" option="3" :selected="selected"/>
            <SidebarOption @selected="selectOption(4)" text="Withdrawals" icon="payments" option="4" :selected="selected"/>
        </div>
        <div class="w-full h-full flex flex-col justify-end">
            <SidebarOption @selected="$emit('logout')" text="Log Out" icon="logout" option="5" :selected="selected"/>
        </div>
    </main>
</template>