<template>
  <div id="sch">
    <h1>查询数据库</h1>
    <button @click="action">点击查询id为1的数据（结果控制台输出）</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'inquire',

  methods:{
    action(){
      //注意代理服务的使用方式
      axios.get("http://localhost:8080/data?id=1").then(
        response => {
          console.log("Succeed:" , response.data)
        },

        err => {
          console.log("Err:" , err)
        }

      )
    }
  }
}
</script>


  
