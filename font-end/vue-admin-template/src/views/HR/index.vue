<template>
  <div class="app-container">
    <el-button type="success" style="margin-bottom: 20px;" @click="toAddAP">+添加新的薪酬标准管理档案</el-button>
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
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="姓名" width="80">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center">
        <template slot-scope="scope">
          {{ scope.row.Phone }}
        </template>
      </el-table-column>
      <el-table-column label="身份证号码" align="center">
        <template slot-scope="scope">
          {{ scope.row.IDNumber }}
        </template>
      </el-table-column>
      <el-table-column label="社会保障号码" align="center">
        <template slot-scope="scope">
          {{ scope.row.SocialSecurityNumber }}
        </template>
      </el-table-column>
      <el-table-column label="薪酬标准" align="center">
        <template slot-scope="scope">
          {{ scope.row.SalaryStandard }}
        </template>
      </el-table-column>
      <el-table-column label="开户行" align="center">
        <template slot-scope="scope">
          {{ scope.row.AccountOpening }}
        </template>
      </el-table-column>
      <el-table-column label="开户账号" align="center">
        <template slot-scope="scope">
          {{ scope.row.AccountNumber }}
        </template>
      </el-table-column>
      <el-table-column label="建档时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.RegistrationTime }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" width="80">
        <template slot-scope="scope">
          <i class="el-icon-s-promotion" v-if="scope.row.State==1">待复核</i>
          <i class="el-icon-s-claim" v-if="scope.row.State==0">复核通过</i>
          <i class="el-icon-delete" v-if="scope.row.State==2">已删除</i>
        </template>
      </el-table-column>
      <el-table-column label="登记人" align="center">
        <template slot-scope="scope">
          {{ scope.row.Registrant }}
        </template>
      </el-table-column>
      <el-table-column align="center"label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="infor" @click="toUpdateAHR(scope.row)" class="el-icon-edit"></el-button>
          <el-button type="danger" @click="deleteHR(scope.row.id)" class="el-icon-delete-solid"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm" width="80%">
      <adminAHRForm :form-val="formVal" :edit-id="editId" :name='name' />
    </el-dialog>
  </div>
</template>

<script>
import { getAllHR, deleteHR } from '@/api/HR'
import { getAllAP } from '@/api/adminAP'
import adminAHRForm from './form.vue'
import { mapGetters } from 'vuex'
import { parseTime } from '../../utils/index.js'

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
  computed: {
    ...mapGetters([
      'role',
      'token'
    ])
  },
  created() {
    this.fetchData()
    if(this.role=='payCommissioner'||this.role=='payManager'){
      alert('您没有权限！请联系管理员')
      this.$router.push('/')
    }
  },
  methods: {
    // 查询列表
    fetchData() {
      this.listLoading = true
      getAllHR().then(response => {
        this.list = response.humanResourceList
        console.log(this.list)
        this.listLoading = false
      })
    },
    // 删除
    deleteHR(param) {
      deleteHR(param).then(respone => {
        this.fetchData()
      })
    },
    // 增加
    toAddAP() {
      this.editId = -1
      this.showForm = true
      this.title = '新增人事管理'
    },

    // 修改
    toUpdateAHR(param) {
      this.editId = param.id
      this.formVal = param
      console.log(this.formVal)
      this.showForm = true
      this.title = '修改人事管理'
    }
  }
}
</script>
