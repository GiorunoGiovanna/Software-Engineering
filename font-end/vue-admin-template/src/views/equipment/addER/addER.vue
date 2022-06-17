<template>
  <div class="app-container">
    <el-button type="success" style="margin-bottom: 20px" @click="toAddAP"
      >+新增借出</el-button
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
          {{ scope.row.eRentId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="器材种类id" width="80">
        <template slot-scope="scope">
          {{ scope.row.eTypeId }}
        </template>
      </el-table-column>
      <el-table-column label="借用者（一卡通号码）" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.uId }}
        </template>
      </el-table-column>
      <el-table-column label="借用开始时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.borrowBegin }}
        </template>
      </el-table-column>
      <el-table-column label="借用结束时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.borrowEnd }}
        </template>
      </el-table-column>
      <el-table-column label="借用状态" align="center">
        <template slot-scope="scope">
          {{
            scope.row.status == 0
              ? "待审核"
              : scope.row.status == 1
              ? "未归还"
              : "已归还"
          }}
        </template>
      </el-table-column>
      <el-table-column label="预计金额" align="center">
        <template slot-scope="scope">
          {{ scope.row.borrowFee }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope" v-if="role">
          <el-button
            v-if="scope.row.status == 0"
            type="primary"
            @click="checkER(scope.row)"
            >审核</el-button
          >
          <el-button
            v-if="scope.row.status == 1"
            type="primary"
            @click="returnER(scope.row)"
            >归还</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm" width="80%">
      <equipment :form-val="formVal" :edit-id="editId" />
    </el-dialog>
  </div>
</template>

<script>
import { searchER, returnER } from "@/api/equipment";
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
      searchER().then((response) => {
        this.list = response.message;
        console.log(this.list);
        this.listLoading = false;
      });
    },
    // 归还器材
    returnER(param) {
      param.status = 2;
      returnER(param).then((respone) => {
        this.fetchData();
      });
    },
    // 审核
    checkER(param) {
      param.status = 1;
      returnER(param).then((respone) => {
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
