<template>
  <div class="app-container">
    <el-button type="success" style="margin-bottom: 20px" @click="toAddG"
      >+借用场地</el-button
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
          {{ scope.row.bId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="借出器材编号">
        <template slot-scope="scope">
          {{ scope.row.gId }}
        </template>
      </el-table-column>
      <el-table-column label="借用者" align="center">
        <template slot-scope="scope">
          {{ scope.row.uId }}
        </template>
      </el-table-column>
      <el-table-column label="借出时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.bStartTime }}
        </template>
      </el-table-column>
      <el-table-column label="预计归还时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.bEndTime }}
        </template>
      </el-table-column>
      <el-table-column label="租金" align="center">
        <template slot-scope="scope">
          {{ scope.row.bFee }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          {{
            scope.row.bStatus == 0
              ? "待审核"
              : scope.row.bStatus == 1
              ? "使用中"
              : "使用完毕"
          }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope" v-if="role">
          <el-button
            v-if="scope.row.bStatus == 0"
            type="primary"
            @click="toUpdateG(scope.row, 1)"
            >审核</el-button
          >
          <el-button
            v-if="scope.row.bStatus == 1"
            type="primary"
            @click="toUpdateG(scope.row, 3)"
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
import { searchGB, updateGB } from "@/api/ground";
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
      searchGB().then((response) => {
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
    toAddG() {
      this.editId = -1;
      this.showForm = true;
      this.title = "借用场地";
    },

    // 修改
    toUpdateG(param, status) {
      // this.editId = param.gId;
      // this.formVal = param;
      // // console.log(this.formVal);
      // this.showForm = true;
      // this.title = "修改场地";
      let data = {
        bStatus: status,
        bId: param.bId,
        bFee: param.bFee,
        date: new Date().getDate(),
      };
      // console.log(param);
      updateGB(data).then((respone) => {
        this.fetchData();
      });
    },
  },
};
</script>
