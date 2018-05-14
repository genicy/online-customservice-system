var ws = null
var wsServer = null
export function getConnect (userId) {
  wsServer = 'ws://localhost:8080/HFUT_Group1/chatServer/' + userId
  if (typeof (WebSocket) === 'undefined') {
    alert('不支持')
    return
  }
  ws = new WebSocket(wsServer)
  return ws
}
export function getAdminConnect (adminId) {
  wsServer = 'ws://localhost:8080/HFUT_Group1/adminServer/' + adminId
  if (typeof (WebSocket) === 'undefined') {
    alert('不支持')
    return
  }
  ws = new WebSocket(wsServer)
  return ws
}
