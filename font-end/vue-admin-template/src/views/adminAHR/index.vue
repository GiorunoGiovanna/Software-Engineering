<template>
  <div class="app-container">
    <el-button type="success" style="margin-bottom: 20px;" @click="toAddAHR">+添加新的人事管理档案</el-button>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="一级机构">
        <template slot-scope="scope">
          {{ scope.row.ILevel }}
        </template>
      </el-table-column>
      <el-table-column label="二级机构" align="center">
        <template slot-scope="scope">
          {{ scope.row.IILevel }}
        </template>
      </el-table-column>
      <el-table-column label="三级机构" align="center">
        <template slot-scope="scope">
          {{ scope.row.IIILevel }}
        </template>
      </el-table-column>
      <el-table-column label="职务名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.JobTitle }}
        </template>
      </el-table-column>
      <el-table-column label="职务类型" align="center">
        <template slot-scope="scope">
          {{ scope.row.PositionType }}
        </template>
      </el-table-column>
      <el-table-column align="center"label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="infor" @click="toUpdateAHR(scope.row)">修改</el-button>
          <el-button type="danger" @click="deleteAHR(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm">
      <adminAHRForm :form-val="formVal" :edit-id="editId" />
    </el-dialog>
  </div>
</template>

<script>
import { getAllAHR, deleteAHR } from '@/api/adminAHR'
import adminAHRForm from './form.vue'
import { mapGetters } from 'vuex'

export default {
  components: {
    'adminAHRForm': adminAHRForm
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      showForm: false,
      formVal: {},
      editId: -1,
      title: ''
    }
  },
  created() {
    this.fetchData()
    console.log(this.token)
    if(this.role=='payCommissioner'||this.role=='payManager'){
      alert('您没有权限！请联系管理员')
      this.$router.push('/')
    }
  },
  computed: {
    ...mapGetters([
      'role',
      'token'
    ])
  },
  methods: {
    // 查询列表
    fetchData() {
      this.listLoading = true
      getAllAHR().then(response => {
        this.list = response.adminHumanResourceList
        this.listLoading = false
      })
    },
    // 删除
    deleteAHR(param) {
      deleteAHR(param).then(respone => {
        this.fetchData()
      })
    },
    // 增加
    toAddAHR() {
      this.editId = -1
      this.showForm = true
      this.title = '新增人事管理'
    },

    // 修改
    toUpdateAHR(param) {
      this.editId = param.id
      this.formVal = param
      this.showForm = true
      this.title = '修改人事管理'
    }
  }
}
</script>
