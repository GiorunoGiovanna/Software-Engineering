<template>
  <div class="app-container">
    <el-button type="success" style="margin-bottom: 20px" @click="toAddG"
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
          {{ scope.row.fId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="收支种类">
        <template slot-scope="scope">
          {{ scope.row.fInType }}
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.date }}
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center">
        <template slot-scope="scope">
          {{ scope.row.fIn }}
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          {{ scope.row.notes }}
        </template>
      </el-table-column>
      <!-- <el-table-column align="center" label="操作" width="300">
        <template slot-scope="scope">
          <el-button
            v-if="role"
            @click="toUpdateG(scope.row)"
            class="el-icon-edit"
          ></el-button>
          <el-button
            type="danger"
            class="el-icon-delete"
            @click="toDeleteG(scope.row.mId)"
          >
          </el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm" width="80%">
      <equipment :form-val="formVal" :edit-id="editId" />
    </el-dialog>
  </div>
</template>

<script>
import { searchF } from "@/api/financial";
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
      searchF().then((response) => {
        this.list = response.message;
        console.log(this.list);
        this.listLoading = false;
      });
    },
    // 删除
    toDeleteG(param) {
      // console.log(param);
      deleteM(param).then((respone) => {
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
