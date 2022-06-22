<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" size="mini">
      <el-form-item label="器材种类ID">
        <el-input v-model="form.eTypeId" />
      </el-form-item>
      <el-form-item label="购买时间">
        <el-date-picker
          v-model="form.buyDate"
          type="datetime"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="购买金额">
        <el-input v-model="form.buyCost" />
      </el-form-item>
      <el-form-item label="购买数量">
        <el-input v-model="form.buyNum" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">关闭</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addE } from "@/api/equipment";
import { mapGetters } from "vuex";
import { parseTime } from "@/utils/index.js";

export default {
  props: ["formVal", "editId"],
  data() {
    return {
      form: {
        buyCost: "",
        buyDate: "",
        buyNum: "",
        eTypeId: "",
      },
    };
  },
  computed: {
    ...mapGetters(["name", "token"]),
  },
  watch: {
    // 监听目前是修改状态还是添加状态
    editId(val, newVal) {
      console.log(val, newVal);
      if (val === -1) {
        this.form = {
          buyCost: "",
          buyDate: "",
          buyNum: "",
          eTypeId: "",
        };
      } else {
        this.form = this.formVal;
      }
    },
  },
  created() {
    if (this.formVal.id) {
      this.form = this.formVal;
    }
    console.log(this.$route);
    if (this.$route.query.eTypeId) {
      this.form.eTypeId = this.$route.query.eTypeId;
    }
  },
  methods: {
    onSubmit() {
      // this.form.DesigneeTime = parseTime(new Date());
      console.log(this.form);
      // this.$message('submit!')
      if (!this.form.id) {
        addE(this.form).then((response) => {
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
