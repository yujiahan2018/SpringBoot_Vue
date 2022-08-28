<template>
  <div>
    <h1>登录</h1>
    <!-- <form  method="post" action="http://localhost:8081/data" > -->
      login:<input type="text" name="id" ><br>
      password:<input type="password" name="password">
      <button @click="action()"> 提交 </button>
    <!-- </form> -->
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'inquire',

  data() {
    return {
      id: '',
      password: '',
    }
  },

  methods:{
    action(){
      axios.post("http://localhost:8081/data",{
          data: {
            id: 1,
            password: 'aa',
          },
          headers: {
            'Content-Type': 'application/json'
          },
        }
      ).then(
        response => {
          
          console.log(response.data);

        }
      )
    }
  }

}
</script>


  
