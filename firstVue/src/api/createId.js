export function ID () {
  return uniqueNumber()
}

function uniqueNumber () {
  var date = Date.now()
  if (date <= uniqueNumber.previous) {
    date = ++uniqueNumber.privious
  } else {
    uniqueNumber.privious = date
  }
  return date.toString().substring(4, date.toString().length)
}
uniqueNumber.privious = 0
