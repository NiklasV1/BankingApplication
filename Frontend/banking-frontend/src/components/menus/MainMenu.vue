<script setup>
import Sidebar from './Sidebar.vue';
import { defineEmits } from 'vue';
import { defineProps } from 'vue';
import { ref } from 'vue';
import AccountMenu from './account/AccountMenu.vue';
import TransactionMenu from './transaction/TransactionMenu.vue';
import DepositMenu from './deposit_withdrawal/DepositMenu.vue';
import WithdrawalMenu from './deposit_withdrawal/WithdrawalMenu.vue';

const props = defineProps(["auth_id", "auth_usr", "auth_pw"])

const selectedMenu = ref("AccountMenu");

const emit = defineEmits(["logout"]);

function selectMenu(x) {
    switch (x) {
        case 1:
            selectedMenu.value = "AccountMenu"
            break
        case 2:
            selectedMenu.value = "TransactionMenu"
            break
        case 3:
            selectedMenu.value = "DepositMenu"
            break
        case 4:
            selectedMenu.value = "WithdrawalMenu"
            break
    }
    console.log(selectedMenu.value)
}

</script>

<template>
    <main class="h-full w-full flex">

        <!-- Sidebar -->
        <Sidebar @logout="$emit('logout')" @select="selectMenu"/>

        <component v-bind:is="selectedMenu" :auth_id="auth_id" :auth_usr="auth_usr" :auth_pw="auth_pw"></component>
    </main>
</template>

<script>
export default {
    components: {
        AccountMenu, TransactionMenu, DepositMenu, WithdrawalMenu
    }
}
</script>