function oninput (message) {
  var newmessage = message
  if (newmessage !== null || newmessage !== '') {
    return newmessage
  } else {
    return ''
  }
}
module.exports = oninput
