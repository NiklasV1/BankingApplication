<script setup>
    import { ref } from 'vue';
    import SidebarOption from './SidebarOption.vue';

    const selected = ref(1);

    function selectOption(x) {
        selected.value = x;
    }
</script>

<template>
    <main class="bg-slate-800 min-w-52">
        <div class="w-full text-left pl-5 py-2 font-semibold text-white">Menu</div>
        <div class="flex flex-col items-start">
            <SidebarOption @selected="selectOption(1)" text="Accounts" icon="account_balance" option="1" :selected="selected"/>
            <SidebarOption @selected="selectOption(2)" text="Transactions" icon="paid" option="2" :selected="selected"/>
            <SidebarOption @selected="selectOption(3)" text="Deposits" icon="savings" option="3" :selected="selected"/>
            <SidebarOption @selected="selectOption(4)" text="Withdrawals" icon="payments" option="4" :selected="selected"/>
        </div>
    </main>
</template>