
var oninput = require('./api/chat.js')
var expect = require('chai').expect
var info = require('./components/changeInfo')

describe('info.vue', function () {
  it('聊天页面OnInput函数成功', function () {
    expect(oninput('he')).to.be.equal('he')
  })

  it('聊天页面OnInput函数成功', function () {
    const data = info.data()
    expect(data.activeName).toEqual('second')
  })
})
