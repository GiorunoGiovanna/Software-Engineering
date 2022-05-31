<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" size="mini">
      <el-form-item label="职位">
        <el-input v-model="form.AHRName" />
      </el-form-item>
      <el-form-item label="基本工资">
        <el-input v-model="form.Base" @change="calculation"/>
      </el-form-item>
      <el-form-item label="交通补贴">
        <el-input v-model="form.Transportation" @change="calculationAll"/>
      </el-form-item>
      <el-form-item label="午餐补贴">
        <el-input v-model="form.Lunch" @change="calculationAll"/>
      </el-form-item>
      <el-form-item label="通信补贴">
        <el-input v-model="form.Communication" @change="calculationAll"/>
      </el-form-item>
      <el-form-item label="养老保险(自动)">
        <el-input v-model="form.PensionInsurance" :readonly="true"/>
      </el-form-item>
      <el-form-item label="医疗保险(自动)">
        <el-input v-model="form.MedicalInsurance" :readonly="true"/>
      </el-form-item>
      <el-form-item label="失业保险(自动)">
        <el-input v-model="form.UnemploymentInsurance" :readonly="true"/>
      </el-form-item>
      <el-form-item label="住房公积金(自动)">
        <el-input v-model="form.HousingProvidentFund" :readonly="true"/>
      </el-form-item>
      <el-form-item label="总金额">
        <el-input v-model="form.Tatol" />
      </el-form-item>
      <el-form-item label="审核状态">
        <el-input v-model="form.State" />
      </el-form-item>
      <el-form-item label="登记人(自动)">
        <el-input v-model="form.Designee" />
      </el-form-item>
      <el-form-item label="制定人">
        <el-input v-model="form.Registrant" :readonly="true"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">关闭</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addAP, updateAP } from '@/api/adminAP'
import { mapGetters } from 'vuex'
import { parseTime } from '../../utils/index.js'

export default {
  props: ['formVal', 'editId'],
  data() {
    return {
      form: {
        AHRid: 1,
        AHRName: '软件工程英语',
        Designee: this.name,
        DesigneeTime:parseTime(new Date()),
        Registrant: 5,
        Base: 10000,
        Transportation: 300,
        Lunch: 500,
        Communication: 500,
        PensionInsurance: 800,
        MedicalInsurance: 203,
        UnemploymentInsurance: 50,
        HousingProvidentFund: 800,
        Tatol: 13153,
        State: 1,
        Suggestion:"dasd"
      },
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'token'
    ])
  },
  watch: {
    // 监听目前是修改状态还是添加状态
    editId(val, newVal) {
      console.log(val, newVal)
      if (val === -1) {
        this.form = {
          AHRid: 1,
          AHRName: '软件工程英语',
          Designee: this.name,
          DesigneeTime:parseTime(new Date()),
          Registrant: 5,
          Base: 10000,
          Transportation: 300,
          Lunch: 500,
          Communication: 500,
          PensionInsurance: 800,
          MedicalInsurance: 203,
          UnemploymentInsurance: 50,
          HousingProvidentFund: 800,
          Tatol: 13153,
          State: 1,
          Suggestion:"dasd"
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
      this.form.DesigneeTime = parseTime(new Date())
      console.log(this.form)
      // this.$message('submit!')
      if (!this.form.id) {
        addAP(this.form).then(response => {
          this.$parent.$parent.showForm = false
          this.$parent.$parent.fetchData()
        })
      } else {
        updateAP(this.form).then(response => {
          this.$parent.$parent.showForm = false
          this.$parent.$parent.fetchData()
          // this.form = {
          //   ILevel: '广东海洋大学 ',
          //   IILevel: '数学与计算机学院',
          //   IIILevel: '软件系',
          //   JobTitle: '软件工程英语',
          //   PositionType: ''
          // }
          // console.log(this.form)
        })
      }
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    },
    //自动计算三险一金
    calculation(value){
      this.form.PensionInsurance = value*0.08
      this.form.MedicalInsurance = value*0.02+3
      this.form.UnemploymentInsurance = value*0.005
      this.form.HousingProvidentFund = value*0.08
      this.calculationAll()
    },
    //自动计算总工资
    calculationAll(value){
      this.form.Tatol = this.form.Base*1+this.form.Transportation*1+this.form.Lunch*1+this.form.Communication*1+this.form.PensionInsurance*1+this.form.MedicalInsurance*1+this.form.UnemploymentInsurance*1+this.form.HousingProvidentFund*1
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>
