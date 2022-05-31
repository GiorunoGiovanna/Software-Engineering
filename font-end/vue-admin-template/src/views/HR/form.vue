<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="头像上传">
            <el-upload class="avatar-uploader"
              action="?"
              :show-file-list="false"
              :on-change="beforeAvatarUpload"
              :auto-upload="false">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="I级机构">
            <el-input v-model="AHRForm.ILevel" :readonly="true"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="II级机构">
            <el-input v-model="AHRForm.IILevel" :readonly="true"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="III级机构">
            <el-input v-model="AHRForm.IIILevel" :readonly="true"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="职业分类">
            <el-input v-model="AHRForm.PositionType" :readonly="true"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="职位名称">
            <el-select v-model="JobTitle" @change="checkAHR" placeholder="选择职位名称,前面的内容自动补全">
              <el-option v-for="item in AHRFormList"
              :value="item.JobTitle"
              :label="item.JobTitle"
              :key="item.JobTitle">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="8">
          <el-form-item label="职称">
            <el-input v-model="form.Transportation" />
          </el-form-item>
        </el-col> -->
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="姓名">
            <el-input v-model="form.name" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别">
            <el-select width="100%" v-model="form.sex">
              <el-option value="男"></el-option>
              <el-option value="女"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Email">
            <el-input v-model="form.Email" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="电话">
            <el-input v-model="form.tel" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="QQ">
            <el-input v-model="form.QQ" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机">
            <el-input v-model="form.Phone" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="住址">
            <el-input v-model="form.Location" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮编">
            <el-input v-model="form.ZipCode" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="国籍">
            <el-input v-model="form.Nationality" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="出生地">
            <el-input v-model="form.Birthplace" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="生日">
            <el-input v-model="form.Birthday" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="民族">
            <el-select width="100%" v-model="form.Nation">
              <el-option value="汉族"></el-option>
              <el-option value="维吾尔族"></el-option>
              <el-option value="..."></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="宗教信仰">
            <el-input v-model="form.Belief" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="政治面貌">
            <el-input v-model="form.PoliticalOutlook" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="身份证号码">
            <el-input v-model="form.IDNumber" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="社会保障号码">
            <el-input v-model="form.SocialSecurityNumber" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="年龄">
            <el-input v-model="form.Age" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="学历">
            <el-select v-model="form.Education">
              <el-option value="本科"></el-option>
              <el-option value="高中"></el-option>
              <el-option value="..."></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="基本工资">
            <el-input v-model="form.Base" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="学历专业">
            <el-input v-model="form.Major" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="薪酬标准">
            <el-input v-model="form.SalaryStandard" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="开户行">
            <el-input v-model="form.AccountOpening" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="账号">
            <el-input v-model="form.AccountNumber" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="登记人">
            <el-input v-model="form.Registrant" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="建档时间">
            <el-input v-model="form.RegistrationTime" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="特长">
            <el-input v-model="form.Specialty" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="爱好">
            <el-input v-model="form.Hobbies" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="个人履历">
            <el-input type="textarea" :rows="3" v-model="form.PersonalResume" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="家庭关系信息">
            <el-input type="textarea" :rows="3" v-model="form.FamilyRelationshipInformation" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="备注">
            <el-input type="textarea" :rows="3" v-model="form.Note" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">关闭</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { getAP } from '@/api/adminAP'
  import { getAllAHR } from '@/api/adminAHR'
  import { addHR, updateHR } from '@/api/HR'
  import { parseTime } from '../../utils/index.js'

  export default {
    props: ['formVal', 'editId', 'name'],
    data() {
      return {
        form: {
          name: "小小怪",
          sex: "男",
          tel: "18566142330",
          Note: null,
          AHRid: 0,
          Email:'971615621@qq.com',
          Location:"ddd",
          Registrant: this.name,
          SalaryStandard: "1",
          SocialSecurityNumber: "1222232131231223",
          RegistrationTime: parseTime(new Date()),
          PoliticalOutlook: "群众",
          FamilyRelationshipInformation: "父母",
          AccountOpening: "中国银行",
          PersonalResume: "无",
          Nation: "汉",
          QQ: "971615621",
          Belief: "无",
          IDNumber: "445221200102171018",
          Age: "21",
          Birthday: "2001/02/17",
          Phone: "18566142330",
          Nationality: "中国",
          Birthplace: "广东揭阳",
          AccountNumber: "1232132132141",
          Hobbies: "唱跳rap",
          Major: "软件工程",
          Specialty: "打篮球",
          Education: "本科",
          Note:'dd',
          ZipCode:'455562',
          Image:'484',
          State:'1'
        },
        AHRFormList:[],
        JobTitle:'',
        AHRForm:{},
        imageUrl: ''
      }
    },
    watch: {
      // 监听目前是修改状态还是添加状态
      editId(val, newVal) {
        if (val === -1) {
          this.form = {
            name: "小小怪",
            sex: "男",
            tel: "18566142330",
            Note: null,
            AHRid: 0,
            Registrant: this.name,
            Email:'971615621@qq.com',
            Location:"ddd",
            SalaryStandard: "1",
            SocialSecurityNumber: "1222232131231223",
            RegistrationTime: parseTime(new Date()),
            PoliticalOutlook: "群众",
            FamilyRelationshipInformation: "父母",
            AccountOpening: "中国银行",
            PersonalResume: "无",
            Nation: "汉",
            QQ: "971615621",
            Belief: "无",
            IDNumber: "445221200102171018",
            Age: "21",
            Birthday: "2001/02/17",
            Phone: "18566142330",
            Nationality: "中国",
            Birthplace: "广东揭阳",
            AccountNumber: "1232132132141",
            Hobbies: "唱跳rap",
            Major: "软件工程",
            Specialty: "打篮球",
            Education: "本科",
            Note:'dd',
            ZipCode:'455562',
            Image:'484',
            State:'1'
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
      getAllAHR().then(response => {
        this.AHRFormList = response.adminHumanResourceList
        console.log(this.AHRFormList)
      })
      // getAllAP().then(response => {
      //   this.AHRFormList = response.adminHumanResourceList
      //   console.log(response)
      // })
    },
    methods: {
      onSubmit() {
        if (!this.form.id) {
          addHR(this.form).then(response => {
            this.$parent.$parent.showForm = false
            this.$parent.$parent.fetchData()
          })
        } else {
          updateHR(this.form).then(response => {
            this.$parent.$parent.showForm = false
            this.$parent.$parent.fetchData()
          })
        }
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      },
      beforeAvatarUpload(file) {
        console.log(file)
        this.imageUrl = URL.createObjectURL(file.raw);
        let imgFile = new FileReader();
        imgFile.readAsDataURL(file.raw);
        imgFile.onload = res => {
            this.form.Image = res.target.result
        };
        // let data = new FormData()
        // data.append("Image",file.raw)
        // data.append("data",'ddd')
        // upload(data).then(response => {
        //     console.log(response)
        // })
      },
      checkAHR(value){
        this.AHRFormList.map((m,i) => {
          if(m.JobTitle==value){
            this.AHRForm = this.AHRFormList[i]
          }
        })
        getAP(value,'keyword').then(response => {
            this.form.SalaryStandard = response.adminPaymentList[0].Tatol
            this.form.Base = response.adminPaymentList[0].Base
            this.$parent.$parent.fetchData()
        })
      }
    }
  }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
