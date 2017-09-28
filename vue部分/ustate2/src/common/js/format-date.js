Date.prototype.format = function(format) {
       var date = {
              'M+': this.getMonth() + 1,
              'd+': this.getDate(),
              'h+': this.getHours(),
              'm+': this.getMinutes(),
              's+': this.getSeconds(),
              'q+': Math.floor((this.getMonth() + 3) / 3),
              'S+': this.getMilliseconds()
       }
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length))
       }
       for (var k in date) {
              if (new RegExp('(' + k + ')').test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length === 1
                            ? date[k] : ('00' + date[k]).substr(('' + date[k]).length))
              }
       }
       return format
}

var second = 1000
var minute = 1000 * 60
var hour = minute * 60
var day = hour * 24

export function getDateDiff(dateTimeStamp) {
	var now = new Date()
	var diffValue = now.getTime() - dateTimeStamp
	var dayCount = diffValue / day
	var hourCount = diffValue / hour
	var minuteCount = diffValue / minute
	var secondCount = diffValue / second

	var date = new Date()
	date.setTime(dateTimeStamp)
	var diffDate = now.getDate() - date.getDate()
	if (diffDate < 0) {
		diffDate = diffDate + getDay(now)
	}
	if (date.getFullYear() < now.getFullYear()) {
		return date.format('yyyy-MM-dd')
	} else if (dayCount >= 5) {
		return date.format('MM-dd')
	} else if (diffDate > 2) {
		return parseInt(dayCount) + '天前'
	} else if (diffDate === 2) {
		return '前天' + date.format('hh:mm')
	} else if (diffDate === 1) {
		return '昨天' + date.format('hh:mm')
	} else if (hourCount > 5 && now.getDate() === date.getDate()) {
		return '今天' + date.format('hh:mm')
	} else if (hourCount >= 1 && hourCount <= 5) {
		return parseInt(hourCount) + '小时前'
	} else if (minuteCount >= 1) {
		return parseInt(minuteCount) + '分钟前'
	} else if (secondCount >= 1) {
		return parseInt(secondCount) + '秒前'
	} else {
		return '刚刚'
	}
}

export function getDate(dateTimeStamp) {
	var date = new Date()
	date.setTime(dateTimeStamp)
	return date.format('MM-dd hh:mm')
}
function getDay(now) {
	if (now.getFullYear % 4 === 0 && now.getFullYear % 100 !== 0) {
		array[2] += 1
	}
	let array = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
	var index = now.getMonth()
	index = index - 1 < 0 ? index + 11 : index - 1
	return array[index]
}
