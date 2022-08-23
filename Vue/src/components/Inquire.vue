<template>
<div>
    <h1>查询数据库</h1>
    <button @click="action1('http://localhost:8081/data?id=1')">点击查询id为1的数据（结果控制台输出）</button>
    <hr>
    <button @click="action1('http://localhost:8081/Error')">点击显示错误页消息</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'inquire',

  methods:{
    action1(url){
      console.log(url)
      //注意代理服务的使用方式
      axios.get(url).then(
        response => {
          console.log("Succeed:" , response.data)
        },

        err => {
          console.log("Err:" , err)
        },

      )
    },


  }
}
</script>


  
