<template>
  <div class="app-container">
    <!--      搜索条-->
    <div style="display: flex;justify-content: space-between; margin-bottom: 5px">
      <div>
        <el-input
        v-if="type!=='time'"
        placeholder="选择对应条件并输入关键字进行查询..." prefix-icon="el-icon-search" clearable @keydown.enter.native="getAP"
          style="width: 700px;margin-right: 10px" v-model="keyword"></el-input>
        <el-date-picker
        v-if="type==='time'"
        v-model="keyword" type="datetimerange" range-separator="至" start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
        <el-select v-model="type" :placeholder="type.label" style="width: 150px;margin-right: 5px;height: 100%">
          <el-option value="keyword" label="根据关键词查询">根据关键词查询</el-option>
          <el-option value="id" label="根据id查询">根据id查询</el-option>
          <el-option value="time" label="根据时间查询">根据时间查询</el-option>
        </el-select>
        <el-button icon="el-icon-search" type="primary" @click="getAP" :disabled="keyword.length === 0">搜索
        </el-button>
        <el-button type="primary" @click="fetchData">查看所有薪酬档案</el-button>
        <el-button icon="el-icon-circle-plus" type="success" @click="toAddAP">添加新的薪酬标准管理档案</el-button>
      </div>
    </div>
    <!-- <el-button type="success" style="margin-bottom: 20px;" @click="toAddAP">+添加新的薪酬标准管理档案</el-button> -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row stripe>
      <el-table-column align="center" label="ID">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="职位">
        <template slot-scope="scope">
          {{ scope.row.AHRName }}
        </template>
      </el-table-column>
      <el-table-column label="基本工资" align="center">
        <template slot-scope="scope">
          {{ scope.row.Base }}
        </template>
      </el-table-column>
      <el-table-column label="交通补贴" align="center">
        <template slot-scope="scope">
          {{ scope.row.Transportation }}
        </template>
      </el-table-column>
      <el-table-column label="午餐补贴" align="center">
        <template slot-scope="scope">
          {{ scope.row.Lunch }}
        </template>
      </el-table-column>
      <el-table-column label="通信补贴" align="center">
        <template slot-scope="scope">
          {{ scope.row.Communication }}
        </template>
      </el-table-column>
      <el-table-column label="养老保险" align="center">
        <template slot-scope="scope">
          {{ scope.row.PensionInsurance }}
        </template>
      </el-table-column>
      <el-table-column label="医疗保险" align="center">
        <template slot-scope="scope">
          {{ scope.row.MedicalInsurance }}
        </template>
      </el-table-column>
      <el-table-column label="失业保险" align="center">
        <template slot-scope="scope">
          {{ scope.row.UnemploymentInsurance }}
        </template>
      </el-table-column>
      <el-table-column label="住房公积金" align="center">
        <template slot-scope="scope">
          {{ scope.row.HousingProvidentFund }}
        </template>
      </el-table-column>
      <el-table-column label="总金额" align="center">
        <template slot-scope="scope">
          ￥{{ scope.row.Tatol }}
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center">
        <template slot-scope="scope">
          <i class="el-icon-s-promotion" v-if="scope.row.State==1">待复核</i>
          <i class="el-icon-s-claim" v-if="scope.row.State==0">复核通过</i>
        </template>
      </el-table-column>
      <el-table-column label="制定人" align="center">
        <template slot-scope="scope">
          {{ scope.row.Registrant }}
        </template>
      </el-table-column>
      <el-table-column label="登记人" align="center">
        <template slot-scope="scope">
          {{ scope.row.Designee }}
        </template>
      </el-table-column>
      <el-table-column label="登记时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.DesigneeTime }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="infor" size="mini" @click="toUpdateAP(scope.row)" class="el-icon-edit"></el-button>
          <el-button type="danger" size="mini" @click="deleteAP(scope.row.id)" class="el-icon-delete-solid"></el-button>
          <el-button v-if="scope.row.State==1" type="success" size="mini" @click="toReviewAP(scope.row)">复核</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="showForm">
      <adminAHRForm :form-val="formVal" :edit-id="editId" />
    </el-dialog>
    <el-dialog :title="title" :visible.sync="showTable">
      <adminAHRTable :form-val="formVal" :edit-id="editId" />
    </el-dialog>
  </div>
</template>

<script>
  import {
    getAllAP,
    getAP,
    deleteAP,
    getAPByTime
  } from '@/api/adminAP'
  import adminAHRForm from './form.vue'
  import adminAHRTable from './table.vue'
  import { mapGetters } from 'vuex'
  import { parseTime } from '../../utils/index.js'

  export default {
    components: {
      'adminAHRForm': adminAHRForm,
      'adminAHRTable': adminAHRTable
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
        showTable:false,
        formVal: {},
        editId: -1,
        title: '',
        keyword: '',
        type: '',
      }
    },
    created() {
      this.fetchData()
      if(this.role=="manager"||this.role=="commissioner"){
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
        getAllAP().then(response => {
          this.list = response.adminPaymentList
          this.listLoading = false
        })
      },
      //模糊查询
      getAP() {
        this.listLoading = true
        if(Array.isArray(this.keyword)){
          const start = parseTime(this.keyword[0])
          const end = parseTime(this.keyword[1])
          this.keyword = start + '/' + end
          console.log(this.keyword)
          // const obj = {
          //   start,
          //   end
          // }
          // getAPByTime(obj).then(response => {
          //   console.log(response)
          //   this.list = response.adminPaymentList
          //   this.listLoading = false
          // })
        }
        if (this.keyword.length === 0) {
          this.$message({
            type: 'info',
            message: '关键词不能为空！'
          });
        }else {
          getAP(this.keyword, this.type).then(response => {
            console.log(response)
            this.list = response.adminPaymentList
            this.listLoading = false
          })
        }

      },
      // 删除
      deleteAP(param) {
        deleteAP(param).then(respone => {
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
      toUpdateAP(param) {
        this.editId = param.id
        this.formVal = param
        this.showForm = true
        this.title = '修改人事管理'
      },
      //复核
      toReviewAP(param) {
        this.editId = param.id
        this.formVal = param
        console.log(this.formVal)
        this.showTable = true
        this.title = '复核'
      }
    }
  }
</script>
