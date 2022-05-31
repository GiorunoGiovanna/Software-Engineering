<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="一级机构">
        <el-input v-model="form.ILevel" />
      </el-form-item>
      <el-form-item label="二级机构">
        <el-input v-model="form.IILevel" />
      </el-form-item>
      <el-form-item label="三级机构">
        <el-input v-model="form.IIILevel" />
      </el-form-item>
      <el-form-item label="职务名称">
        <el-input v-model="form.JobTitle" />
      </el-form-item>
      <el-form-item label="职务类型">
        <el-input v-model="form.PositionType" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">关闭</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addAHR, updateAHR } from '@/api/adminAHR'

export default {
  props: ['formVal', 'editId'],
  data() {
    return {
      form: {
        ILevel: '广东海洋大学',
        IILevel: '数学与计算机学院',
        IIILevel: '软件系',
        JobTitle: '软件工程英语',
        PositionType: ''
      }
    }
  },
  watch: {
    // 监听目前是修改状态还是添加状态
    editId(val, newVal) {
      console.log(val, newVal)
      if (val === -1) {
        this.form = {
          ILevel: '广东海洋大学',
          IILevel: '数学与计算机学院',
          IIILevel: '软件系',
          JobTitle: '软件工程英语',
          PositionType: ''
        }
      } else {
        this.form = this.formVal
      }
    }
  },
  created() {
    if (this.formVal.id) {
      this.form = this.formVal
    }
  },
  methods: {
    onSubmit() {
      // console.log(this.form)
      // this.$message('submit!')
      if (!this.form.id) {
        addAHR(this.form).then(response => {
          this.$parent.$parent.showForm = false
          this.$parent.$parent.fetchData()
        })
      } else {
        updateAHR(this.form).then(response => {
          this.$parent.$parent.showForm = false
          this.$parent.$parent.fetchData()
          // this.form = {
          //   ILevel: '广东海洋大学 ',
          //   IILevel: '数学与计算机学院',
          //   IIILevel: '软件系',
          //   JobTitle: '软件工程英语',
          //   PositionType: ''
          // }
          console.log(this.form)
        })
      }
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>
