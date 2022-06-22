<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" size="mini">
      <el-form-item label="器材种类ID">
        <el-input v-model="form.eTypeId" />
      </el-form-item>
      <el-form-item label="借用者">
        <el-input v-model="form.uId" :disabled="role === 0" />
      </el-form-item>
      <el-form-item label="借用开始时间">
        <el-date-picker
          v-model="form.borrowBegin"
          type="datetime"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="借用结束时间">
        <el-date-picker
          v-model="form.borrowEnd"
          type="datetime"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="购买数量">
        <el-input v-model="form.borrowNum" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">关闭</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addER } from "@/api/equipment";
import { mapGetters } from "vuex";
import { parseTime } from "@/utils/index.js";

export default {
  props: ["formVal", "editId"],
  data() {
    return {
      form: {
        eTypeId: "",
        uId: "",
        borrowBegin: "",
        borrowEnd: "",
        borrowNum: "",
      },
    };
  },
  computed: {
    ...mapGetters(["name", "role", "token"]),
  },
  created() {
    if (this.$route.query.eTypeId) {
      this.form.eTypeId = this.$route.query.eTypeId;
      this.form.uId = this.name;
    }
  },
  methods: {
    onSubmit() {
      // this.form.DesigneeTime = parseTime(new Date());
      // console.log(this.form);
      // this.$message('submit!')
      if (!this.form.id) {
        addER(this.form).then((response) => {
          this.$parent.$parent.showForm = false;
          this.$parent.$parent.fetchData();
        });
      } else {
        updateAP(this.form).then((response) => {
          this.$parent.$parent.showForm = false;
          this.$parent.$parent.fetchData();
          // this.form = {
          //   ILevel: '广东海洋大学 ',
          //   IILevel: '数学与计算机学院',
          //   IIILevel: '软件系',
          //   JobTitle: '软件工程英语',
          //   PositionType: ''
          // }
          // console.log(this.form)
        });
      }
    },
    onCancel() {
      this.$message({
        message: "cancel!",
        type: "warning",
      });
    },
    // //自动计算三险一金
    // calculation(value) {
    //   this.form.PensionInsurance = value * 0.08;
    //   this.form.MedicalInsurance = value * 0.02 + 3;
    //   this.form.UnemploymentInsurance = value * 0.005;
    //   this.form.HousingProvidentFund = value * 0.08;
    //   this.calculationAll();
    // },
    // //自动计算总工资
    // calculationAll(value) {
    //   this.form.Tatol =
    //     this.form.Base * 1 +
    //     this.form.Transportation * 1 +
    //     this.form.Lunch * 1 +
    //     this.form.Communication * 1 +
    //     this.form.PensionInsurance * 1 +
    //     this.form.MedicalInsurance * 1 +
    //     this.form.UnemploymentInsurance * 1 +
    //     this.form.HousingProvidentFund * 1;
    // },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>
