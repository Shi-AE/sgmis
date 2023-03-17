<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()
    return {
      loading: computed(() => store.state.spin.loading),
      tip: computed(() => store.state.spin.tip)
    }
  }
}
</script>
<template>
  <a-spin class="spin" :loading=loading :tip=tip dot>
    <router-view></router-view>
  </a-spin>
</template>
<style scoped>
.spin {
  width: 100%;
}
</style>