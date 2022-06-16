<template>
  <div class="app-container">
    <el-button
      type="success"
      style="margin-bottom: 20px"
      @click="toAddG"
      v-if="role"
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
          {{ scope.row.gId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称">
        <template slot-scope="scope">
          {{ scope.row.gName }}
        </template>
      </el-table-column>
      <el-table-column label="收费标准/元" align="center">
        <template slot-scope="scope">
          {{ scope.row.gFee }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="300">
        <template slot-scope="scope">
          <el-button
            v-if="role"
            @click="toUpdateG(scope.row)"
            class="el-icon-edit"
          ></el-button>
          <router-link :to="'addgb?gId=' + scope.row.gId">
            <el-button type="infor" class="el-icon-date">借出</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm" width="80%">
      <equipment :form-val="formVal" :edit-id="editId" />
    </el-dialog>
  </div>
</template>

<script>
import { searchG } from "@/api/ground";
import equipment from "./form.vue";
import { mapGetters } from "vuex";
import { parseTime } from "../../utils/index.js";

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
      searchG().then((response) => {
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
      this.title = "新增场地";
    },

    // 修改
    toUpdateG(param) {
      this.editId = param.gId;
      this.formVal = param;
      // console.log(this.formVal);
      this.showForm = true;
      this.title = "修改场地";
    },
  },
};
</script>
