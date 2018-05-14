var ws = null
var wsServer = null
export function getConnect (userId) {
  wsServer = 'ws://localhost:8080/HFUT_Group1/chatServer/' + userId
  if (typeof (WebSocket) === 'undefined') {
    alert('不支持')
    return
  }
  console.log(wsServer)
  ws = new WebSocket(wsServer)
  return ws
}
