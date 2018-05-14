<template>
  <span>{{ msg }}</span>
</template>
<script>
export default {
  data () {
    return {
      msg: 'hello world'
    }
  },
  mounted () {
    this.msg = 'bye bye'
  }
}
</script>
