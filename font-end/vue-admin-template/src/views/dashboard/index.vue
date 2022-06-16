<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎回来: {{ name }}</div>
    <div class="dashboard-text">
      您的身份是: {{ role ? "管理员" : "普通用户" }}
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Dashboard",
  computed: {
    ...mapGetters(["name", "role"]),
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
