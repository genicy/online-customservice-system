
var oninput = require('./api/chat.js')
var expect = require('chai').expect

describe('聊天页面OnInput函数的测试', function () {
  it('聊天页面OnInput函数成功', function () {
    expect(oninput('he')).to.be.equal('he')
  })
})
