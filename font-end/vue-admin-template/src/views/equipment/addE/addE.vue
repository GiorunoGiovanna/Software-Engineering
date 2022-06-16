<template>
  <div class="app-container">
    <el-button type="success" style="margin-bottom: 20px" @click="toAddAP"
      >+添加新的器材</el-button
    >
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          {{ scope.row.eId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="器材种类id" width="80">
        <template slot-scope="scope">
          {{ scope.row.eTypeId }}
        </template>
      </el-table-column>
      <el-table-column label="购买时间" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.buyDate }}
        </template>
      </el-table-column>
      <el-table-column label="购买金额" align="center">
        <template slot-scope="scope">
          {{ scope.row.buyCost }}
        </template>
      </el-table-column>
      <el-table-column label="购买数量" align="center">
        <template slot-scope="scope">
          {{ scope.row.buyNum }}
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm" width="80%">
      <equipment :form-val="formVal" :edit-id="editId" />
    </el-dialog>
  </div>
</template>

<script>
import { searchE } from "@/api/equipment";
import equipment from "./form.vue";
import { mapGetters } from "vuex";
import { parseTime } from "@/utils/index.js";

export default {
  components: {
    equipment: equipment,
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "gray",
        deleted: "danger",
      };
      return statusMap[status];
    },
  },
  data() {
    return {
      list: null,
      listLoading: true,
      showForm: false,
      formVal: {},
      editId: -1,
      title: "",
    };
  },
  computed: {
    ...mapGetters(["role", "token"]),
  },
  created() {
    this.fetchData();
    if (this.role == "payCommissioner" || this.role == "payManager") {
      alert("您没有权限！请联系管理员");
      this.$router.push("/");
    }
  },
  methods: {
    // 查询列表
    fetchData() {
      this.listLoading = true;
      searchE().then((response) => {
        this.list = response.message;
        console.log(this.list);
        this.listLoading = false;
      });
    },
    // 删除
    deleteET(param) {
      deleteET(param).then((respone) => {
        this.fetchData();
      });
    },
    // 增加
    toAddAP() {
      this.editId = -1;
      this.showForm = true;
      this.title = "新增器材";
    },

    // 修改
    toUpdateAHR(param) {
      this.editId = param.id;
      this.formVal = param;
      console.log(this.formVal);
      this.showForm = true;
      this.title = "修改器材";
    },
  },
};
</script>
