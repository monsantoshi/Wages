var pressEnter = false;
var clickCalendar = false;
var isBetweenDate = false;
var currentField = 0;
// Define for convert month from 'MMM' to 'MM'
var	monthArr = new Array ;
monthArr['JAN']='01';	monthArr['FEB']='02';	monthArr['MAR']='03';	monthArr['APR']='04';
monthArr['MAY']='05';	monthArr['JUN']='06';	monthArr['JUL']='07';	monthArr['AUG']='08';
monthArr['SEP']='09';	monthArr['OCT']='10';	monthArr['NOV']='11';	monthArr['DEC']='12';

function checkKeyDate(obj, curField) {
	var returnValue = false;
	clickCalendar = false;
	currentField = ((curField == undefined || curField == "") ? 0  : curField);
// Only Common-key, "-", ".", "/" Backspace and 0-9 Key-in Textfield
	if (isCommonKey()) {
		returnValue =  true;
	} else {
		if (event.keyCode==8 || event.keyCode==37 || event.keyCode==39 || (event.keyCode<=57 && event.keyCode>=48) || (event.keyCode<=105 && event.keyCode>=96) || (event.keyCode<=111 && event.keyCode>=109) || (event.keyCode<=191 && event.keyCode>=189)) {
			returnValue =  true;
		}
	}
	if (event.keyCode==13) {
//alert(event.srcElement.tagName);
		pressEnter = true;
		return validateDate(obj);
	}
	return returnValue;
}

function checkValidDate(obj, betweenDate) {
	isBetweenDate = ((betweenDate == undefined) ? true  : betweenDate);
	if (!(pressEnter || clickCalendar)) {
		return validateDate(obj)
	} else {
		return false;
	}
}

function validateDate(obj) {
	srcValue = obj.value;
	var returnValue = false;
	dateString = "";
	dateStringArray = new Array();
	title = "Please check date value";
	msg = "";
	okBtnLabel = "OK";
	century = "25";
	minYear = 0;
	maxYear = 9999;
	minMonth = 1;
	maxMonth = 12;
	minDay = 1;
	maxDay = 31;

	if (srcValue == "") {
		returnValue = true;
	}

	if (srcValue.indexOf("/") == -1 && srcValue.indexOf("-") == -1 && srcValue.indexOf(".") == -1) {
		if (srcValue.length == 6 || srcValue.length == 8) {
			dateStringArray[0] = srcValue.substring(0, 2);
			dateStringArray[1] = srcValue.substring(2, 4);
			dateStringArray[2] = (srcValue.length == 6 ? century+srcValue.substring(4, 6) : srcValue.substring(4, 8));			
		} else {
			msg = "Not valid date format";
		}
	}

	if (srcValue.split("/").length == 3) {
		dateStringArray = srcValue.split("/");
	} else if (srcValue.split("-").length == 3) {
		dateStringArray = srcValue.split("-");
	} else if (srcValue.split(".").length == 3) {
		dateStringArray = srcValue.split(".");
	}
					
	if (dateStringArray.length == 3) {
		dateStringArray[2] = (dateStringArray[2].length == 2 ? century+dateStringArray[2] : dateStringArray[2]);
		if (parseInt(parseFloat(dateStringArray[0])) != dateStringArray[0]) {
			msg = "วันที่ไม่ถูกต้อง";
		} else if (parseInt(parseFloat(dateStringArray[1])) != dateStringArray[1]) {
			msg = "เดือนไม่ถูกต้อง";
		} else if (parseInt(parseFloat(dateStringArray[2])) != dateStringArray[2]) {
			msg = "ปีไม่ถูกต้อง";
		} else {
			if (!(parseInt(parseFloat(dateStringArray[2])) >= minYear && parseInt(parseFloat(dateStringArray[2])) <= maxYear)) {
				msg = "ปีควรอยู่ระหว่าง "+minYear+" และ "+maxYear;
			} else if (!(parseInt(parseFloat(dateStringArray[1])) >= minMonth && parseInt(parseFloat(dateStringArray[1])) <= maxMonth)) {
				msg = "เดือนควรอยู่ระหว่าง "+minMonth+" และ "+maxMonth;
			} else {
				maxDay = findDayInMonthYear(parseInt(parseFloat(dateStringArray[1])), parseInt(parseFloat(dateStringArray[2])));
				if (!(parseInt(parseFloat(dateStringArray[0])) >= minDay && parseInt(parseFloat(dateStringArray[0])) <= maxDay)) {
					msg = "วันที่ควรอยู่ระหว่าง "+minDay+" และ "+maxDay;
				} else {
					dateStringArray[0] = (dateStringArray[0].length == 1 ? "0"+dateStringArray[0] : dateStringArray[0]);
					dateStringArray[1] = (dateStringArray[1].length == 1 ? "0"+dateStringArray[1] : dateStringArray[1]);
					dateStringArray[2] = (dateStringArray[2].length == 1 ? "000"+dateStringArray[2] : (dateStringArray[2].length == 3 ? "0"+dateStringArray[2] : dateStringArray[2]));
					obj.value = dateStringArray[0]+"/"+dateStringArray[1]+"/"+dateStringArray[2];
					returnValue = true;
				}
			}
		}
	} else {
		msg = "รูปแบบวันที่ไม่ถูกต้อง";
	}
	if (!returnValue) {
		
		obj.focus();
		alert(msg);

		//showOWarningDialog(title, msg, okBtnLabel);
		//window.showModalDialog(dialogPage, arguments, windowStyle);
		
	} else if (isBetweenDate) {
		if (obj.parentElement.name == undefined) {
			returnValue = checkBetweenDate(eval("document.all."+obj.name));
		} else {
			returnValue = checkBetweenDate(eval(obj.parentElement.name+"."+obj.name));
		}
	}
	pressEnter = false;
	clickCalendar = false;
	
	//alert('Inscript   '+returnValue);
	
	return returnValue;
}

function findDayInMonthYear(month, year) {
	var returnValue = 31;
	dayInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (year%4 == 3 && month == 2) {
		dayInMonth[1] = 29;
	}
	returnValue = dayInMonth[month-1];
	return returnValue;
}

function showCalendar(returnToObj) {
	dateLayer.style.visibility = 'visible';
}

function hideLayer(layer) {
	layer.style.visibility = 'hidden';
}

//function returnDate(objName,objLayer,objID) {
	function returnDate(objLayer,objID) {
	if (event.srcElement.className.substr(0,6)=="Day_ms" || event.srcElement.className.substr(0,6)=="DaySel") {
		hideLayer(objLayer);
		if (eval(currentField).value == undefined) {
//			functionStr = "assignValue("+objName+"["+currentField+"],"+objID.id+")";
			functionStr = "assignValues("+currentField+","+objID.id+")";
			setTimeout(functionStr,50);
			setTimeout("checkBetweenDateFromCalendar("+currentField+")",100);
		} else {
			functionStr = "assignValues("+currentField+","+objID.id+")";
			setTimeout(functionStr,50);
		}
	}
}

function assignValues(obj,objID) {
	var cDay = ""+objID.day;
	var cMonth = ""+objID.month;
	var cYear = ""+objID.year;
	if (cDay.length == 1) cDay = "0"+cDay;
	if (cMonth.length == 1) cMonth = "0"+cMonth;
	//obj.value = cDay+'/'+cMonth+'/'+cYear;
	obj.value = formatDate(new Date(getDateFromFormat(cDay+'/'+cMonth+'/'+cYear,'dd/MM/yyyy')),dateformatInput);
	obj.focus();
}

function assignValue(obj,objID,IsName) {
	var cDay = ""+parseInt(objID.day);
	var cMonth = ""+objID.month;
	var cYear = ""+objID.year;
	if (cDay.length == 1) cDay = "0"+cDay;
	if (cMonth.length == 1) cMonth = "0"+cMonth;
	obj.value = formatDate(new Date(getDateFromFormat(cDay+'/'+cMonth+'/'+cYear,'dd/MM/yyyy')),dateformatInput);
	//obj.value = cDay+'/'+cMonth+'/'+cYear;
	obj.focus();
}

function checkBetweenDateFromCalendar(objName) {
	clickCalendar = true;
//	checkBetweenDate(objName);
	clickCalendar = checkBetweenDate(objName);
}

function checkBetweenDate(objName) {
	var returnValue = true;
//alert("event.srcElement.value = "+event.srcElement.value);
//alert("objName[0].value = "+objName[0].value);
//alert("currentField = "+currentField);
	if (objName[0].value != "" && objName[1].value != "") {
		if (compareValue("date",objName[0], objName[1], "<=", "<B>From date</B>", "<B>To date</B>", currentField+1)) {
//				dateLayer.style.visibility='hidden';
		} else {
			if (currentField == 0) {
				objName[0].value = objName[1].value;
			} else {
				objName[1].value = objName[0].value;
			}
			returnValue = false;
		}
	}
	hideLayer(objName);
//alert("returnValue = "+returnValue);
	return returnValue;
}

function showLayer(numField,objName) {
//	alert('document.all.'+numField.name+"------"+objName.id);
	currentField = 'document.all.'+numField.name;
	posX = 0;
	posY = 0;
	posX = ((document.body.clientWidth - window.event.x) > objName.clientWidth) ? window.event.x : window.event.x - objName.clientWidth ;
	posX = (document.body.clientWidth < objName.clientWidth) ? window.event.x : posX;
	posY = ((document.body.clientHeight - window.event.y) > objName.clientHeight) ? window.event.y : window.event.y - objName.clientHeight ;
	posY = (document.body.clientHeight < objName.clientHeight) ? window.event.y : posY;
/*
alert("window width = "+document.body.clientWidth);
alert("window height = "+document.body.clientHeight);
alert("objName.style.width = "+objName.clientWidth);
alert("objName.style.height = "+objName.clientHeight);
*/
	objName.style.left = document.body.scrollLeft+posX;
	objName.style.top =document.body.scrollTop+ posY;
	objName.style.visibility = 'visible';
}

function focusField(numField) {
	currentField = numField;
}

////////////-------------- check min and max year between textbox and calendar when change date---------------\\\\\\\\\\\\
//			checkValidYear(c,obj).
//			c is calender Layer name
//			obj is return object(textbox name)
	function checkValidYear(c,obj)
	{
		var calendar = eval('calendar'+c);					
		// ------- 'calendar'+c is calendar componet ID.
		// ------- if you want to change calendar component ID please 
		// ------- change calendar component ID in calendar generator at method getTagGenerator() too.
		var objLength = obj.value.length;
		var splitValue	= "/";
		var dateArray = obj.value.split(splitValue);
		var year = dateArray[2];
		if( obj.value!="" &&( Number(year) < calendar.minYear || Number(year) > calendar.maxYear) )
		{
			showOWarningDialog("Data not complete", "Year must between "+calendar.minYear+" and "+calendar.maxYear+".", "OK") ;
			if(Number(year) < calendar.minYear)
			{
				obj.value = dateArray[0]+splitValue+dateArray[1]+splitValue+ calendar.minYear;
			}else{
				obj.value =  dateArray[0]+splitValue+dateArray[1]+splitValue+ calendar.maxYear;
			}
			obj.focus();
		}
	}
////////////-------------- set calendar selectiing when change date ---------------\\\\\\\\\\\\
	function setDateToCalendar(obj,objID) 
	{
		var splitValue	= "/";
		var dateArray = obj.value.split(splitValue);
		if( obj.value!="" )
		{
			objID.day	= Number( dateArray[0] ) ;
			objID.month = Number( dateArray[1] ) ;
			dateArray[2]  = Number( dateArray[2] ) > 2500 ? Number( dateArray[2] ) - 543 : Number( dateArray[2] ) ;
			objID.year = Number( dateArray[2] ) ;

		}
	}
	
	// Heavenly
	// return string with format DD/MM/YYYY
	function getCurrentDate() {
		d = new Date();
		dd = "0" + d.getDate();
		mm = "0" + ( d.getMonth() + 1);
		dn = dd.substring( dd.length, dd.length - 2) + "/" + mm.substring( mm.length, mm.length - 2) + "/" + d.getFullYear();
		return dn;
	}
	
	// return true is d1 >= current date
	// return false id d1 < current date
	// d1 is string only
	function getCompareNow( d1,d)
	{
		if (d1.length>10)
			d1 = changeToMM(d1) ;
		if (d.length>10)
			d  = changeToMM(d) ;
		// Convert dash to slash
		d1 = dashToSlash(d1) ;
		d  = dashToSlash(d) ;
		// Parse the string in DD/MM/YYYY format
		re = /(\d{1,2})\/(\d{1,2})\/(\d{4})/
		var arr = re.exec( d1);
		//var d = getCurrentDate();
		var arr2 = re.exec( d);
		//alert('getCompareNow');
		var dt = new Date( parseInt(arr[3]), parseInt(arr[2], 10) - 1, parseInt(arr[1], 10));
		var dn = new Date( parseInt(arr2[3]), parseInt(arr2[2], 10) - 1, parseInt(arr2[1], 10));
	
		return dt >= dn;
	}

/*The format string consists of the following abbreviations:

Field        | Full Form          | Short Form
-------------|--------------------|-----------------------
Year         | yyyy (4 digits)    | yy (2 digits), y (2 or 4 digits)
Month        | MMMM (name or abbr.)| MM (2 digits), M (1 or 2 digits)
             | MMM (abbr.)        |
Day of Month | dd (2 digits)      | d (1 or 2 digits)
Day of Week  | EE (name)          | E (abbr)
Hour (1-12)  | hh (2 digits)      | h (1 or 2 digits)
Hour (0-23)  | HH (2 digits)      | H (1 or 2 digits)
Hour (0-11)  | KK (2 digits)      | K (1 or 2 digits)
Hour (1-24)  | kk (2 digits)      | k (1 or 2 digits)
Minute       | mm (2 digits)      | m (1 or 2 digits)
Second       | ss (2 digits)      | s (1 or 2 digits)
AM/PM        | a                  |

NOTE THE DIFFERENCE BETWEEN MM and mm! Month=MM, not mm!

Examples:
 "MMM d, y" matches: January 01, 2000
                     Dec 1, 1900
                     Nov 20, 00
 "M/d/yy"   matches: 01/20/00
                     9/2/00
 "MMM dd, yyyy hh:mm:ssa" matches: "January 01, 2000 12:30:45AM"
*/

var MONTH_NAMES=new Array('January','February','March','April','May','June','July','August','September','October','November','December','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');var DAY_NAMES=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sun','Mon','Tue','Wed','Thu','Fri','Sat');
function LZ(x){return(x<0||x>9?"":"0")+x}
function isDate(val,format){var date=getDateFromFormat(val,format);if(date==0){return false;}return true;}
function compareDates(date1,dateformat1,date2,dateformat2){var d1=getDateFromFormat(date1,dateformat1);var d2=getDateFromFormat(date2,dateformat2);if(d1==0 || d2==0){return -1;}else if(d1 > d2){return 1;}return 0;}
function formatDate(date,format){
	format=format+"";
	var result="";
	var i_format=0;
	var c="";
	var token="";
	var y=date.getYear()+543+"";//chang year Eng to year Thai
	var M=date.getMonth()+1;
	var d=date.getDate();
	var E=date.getDay();
	var H=date.getHours();
	var m=date.getMinutes();
	var s=date.getSeconds();
	var yyyy,yy,MMMM,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k;
	var value=new Object();
	if(y.length < 4){y=""+(y-0+1900);}
	value["y"]=""+y;
	value["yyyy"]=y;
	value["yy"]=y.substring(2,4);
	value["M"]=M;
	value["MM"]=LZ(M);
	value["MMMM"]=MONTH_NAMES[M-1];
	value["MMM"]=MONTH_NAMES[M+11];
	value["d"]=d;
	value["dd"]=LZ(d);
	value["E"]=DAY_NAMES[E+7];
	value["EE"]=DAY_NAMES[E];
	value["H"]=H;
	value["HH"]=LZ(H);
	if(H==0){value["h"]=12;}
	else if(H>12){value["h"]=H-12;}
	else{value["h"]=H;}value["hh"]=LZ(value["h"]);

	if(H>11){value["K"]=H-12;}
	else{value["K"]=H;}
	value["k"]=H+1;
	value["KK"]=LZ(value["K"]);
	value["kk"]=LZ(value["k"]);
	if(H > 11){value["a"]="PM";}
	else{value["a"]="AM";}
	value["m"]=m;
	value["mm"]=LZ(m);
	value["s"]=s;
	value["ss"]=LZ(s);
	while(i_format < format.length){
		c=format.charAt(i_format);
		token="";
		while((format.charAt(i_format)==c) &&(i_format < format.length)){
			token += format.charAt(i_format++);
		}
		if(value[token] != null){result=result + value[token];}
		else{result=result + token;}
	}
	return result;
}
function _isInteger(val){var digits="1234567890";for(var i=0;i < val.length;i++){if(digits.indexOf(val.charAt(i))==-1){return false;}}return true;}
function _getInt(str,i,minlength,maxlength){for(var x=maxlength;x>=minlength;x--){var token=str.substring(i,i+x);if(token.length < minlength){return null;}if(_isInteger(token)){return token;}}return null;}
function getDateFromFormat(val,format){
	val=val+"";
	format=format+"";
	var i_val=0;
	var i_format=0;
	var c="";
	var token="";
	var token2="";
	var x,y;
	var now=new Date();
	var year=now.getYear();
	var month=now.getMonth()+1;
	var date=1;
	var hh=now.getHours();
	var mm=now.getMinutes();
	var ss=now.getSeconds();
	var ampm="";
	while(i_format < format.length){
		c=format.charAt(i_format);
		token="";
		while((format.charAt(i_format)==c) &&(i_format < format.length)){
			token += format.charAt(i_format++);
		}
		if(token=="yyyy" || token=="yy" || token=="y"){
			if(token=="yyyy"){x=4;y=4;}
			if(token=="yy"){x=2;y=2;}
			if(token=="y"){x=2;y=4;}year=_getInt(val,i_val,x,y);
			if(year==null){return 0;}i_val += year.length;
			if(year.length==2){
				if(year > 70){year=1900+(year-0);}
				else{year=2000+(year-0);}
			}
			year = year - 543;//chang year to eng because input year in thai
			//alert(year);
		}
		else if(token=="MMMM"||token=="MMM"){
			month=0;
			for(var i=0;i<MONTH_NAMES.length;i++){
				var month_name=MONTH_NAMES[i];
				if(val.substring(i_val,i_val+month_name.length).toLowerCase()==month_name.toLowerCase()){
					if(token=="MMMM"||(token=="MMM"&&i>11)){
						month=i+1;if(month>12){month -= 12;}i_val += month_name.length;break;}}}
						if((month < 1)||(month>12)){return 0;}}
						else if(token=="EE"||token=="E"){
							for(var i=0;i<DAY_NAMES.length;i++){
								var day_name=DAY_NAMES[i];
								if(val.substring(i_val,i_val+day_name.length).toLowerCase()==day_name.toLowerCase()){
									i_val += day_name.length;break;}}}
									else if(token=="MM"||token=="M"){
										month=_getInt(val,i_val,token.length,2);
										if(month==null||(month<1)||(month>12)){return 0;}i_val+=month.length;}
										else if(token=="dd"||token=="d"){date=_getInt(val,i_val,token.length,2);
										if(date==null||(date<1)||(date>31)){return 0;}i_val+=date.length;}
										else if(token=="hh"||token=="h"){hh=_getInt(val,i_val,token.length,2);
										if(hh==null||(hh<1)||(hh>12)){return 0;}i_val+=hh.length;}
										else if(token=="HH"||token=="H"){hh=_getInt(val,i_val,token.length,2);
										if(hh==null||(hh<0)||(hh>23)){return 0;}i_val+=hh.length;}
										else if(token=="KK"||token=="K"){hh=_getInt(val,i_val,token.length,2);
										if(hh==null||(hh<0)||(hh>11)){return 0;}i_val+=hh.length;}
										else if(token=="kk"||token=="k"){hh=_getInt(val,i_val,token.length,2);
										if(hh==null||(hh<1)||(hh>24)){return 0;}i_val+=hh.length;hh--;}
										else if(token=="mm"||token=="m"){mm=_getInt(val,i_val,token.length,2);
										if(mm==null||(mm<0)||(mm>59)){return 0;}i_val+=mm.length;}
										else if(token=="ss"||token=="s"){ss=_getInt(val,i_val,token.length,2);
										if(ss==null||(ss<0)||(ss>59)){return 0;}i_val+=ss.length;}
										else if(token=="a"){if(val.substring(i_val,i_val+2).toLowerCase()=="am"){ampm="AM";}
										else if(val.substring(i_val,i_val+2).toLowerCase()=="pm"){ampm="PM";}
										else{return 0;}i_val+=2;}else{if(val.substring(i_val,i_val+token.length)!=token){return 0;}
										else{i_val+=token.length;}}}if(i_val != val.length){return 0;}
										if(month==2){if( ((year%4==0)&&(year%100 != 0) ) ||(year%400==0) ){
											if(date > 29){return 0;}}else{if(date > 28){return 0;}}}
											if((month==4)||(month==6)||(month==9)||(month==11)){
												if(date > 30){return 0;}}if(hh<12 && ampm=="PM"){hh=hh-0+12;}
												else if(hh>11 && ampm=="AM"){hh-=12;}
			var newdate=new Date(year,month-1,date,hh,mm,ss);
			return newdate.getTime();}
function parseDate(val){var preferEuro=(arguments.length==2)?arguments[1]:false;generalFormats=new Array('y-M-d','MMM d, y','MMM d,y','y-MMM-d','d-MMM-y','MMM d');monthFirst=new Array('M/d/y','M-d-y','M.d.y','MMM-d','M/d','M-d');dateFirst =new Array('d/M/y','d-M-y','d.M.y','d-MMM','d/M','d-M');var checkList=new Array('generalFormats',preferEuro?'dateFirst':'monthFirst',preferEuro?'monthFirst':'dateFirst');var d=null;for(var i=0;i<checkList.length;i++){var l=window[checkList[i]];for(var j=0;j<l.length;j++){d=getDateFromFormat(val,l[j]);if(d!=0){return new Date(d);}}}return null;}

// function getCompareDate create by max on 11/05/2004
	function getCompareDate( d1, d2)
	{
		if (d1.length>10)
			d1 = changeToMM(d1) ;
		if (d2.length>10)
			d2 = changeToMM(d2) ;
		// Convert dash to slash
		d1 = dashToSlash(d1) ;
		d2 = dashToSlash(d2) ;
		// Parse the string in DD/MM/YYYY format
		re = /(\d{1,2})\/(\d{1,2})\/(\d{4})/
		var arr = re.exec( d1);
		var arr2 = re.exec( d2);

		var dt = new Date( parseInt(arr[3]), parseInt(arr[2], 10) - 1, parseInt(arr[1], 10));
		var dn = new Date( parseInt(arr2[3]), parseInt(arr2[2], 10) - 1, parseInt(arr2[1], 10));
	
		return dt >= dn;
	}
	
	// function getCompareDateEqual create by prince on 20/09/2004
	function getCompareDateEqual( d1, d2)
	{
		if (d1.length>10)
			d1 = changeToMM(d1) ;
		if (d2.length>10)
			d2 = changeToMM(d2) ;
		// Convert dash to slash
		d1 = dashToSlash(d1) ;
		d2 = dashToSlash(d2) ;
		// Parse the string in DD/MM/YYYY format
		re = /(\d{1,2})\/(\d{1,2})\/(\d{4})/
		var arr = re.exec( d1);
		var arr2 = re.exec( d2);

		var dt = new Date( parseInt(arr[3]), parseInt(arr[2], 10) - 1, parseInt(arr[1], 10));
		var dn = new Date( parseInt(arr2[3]), parseInt(arr2[2], 10) - 1, parseInt(arr2[1], 10));
	
		return dt > dn;
	}
// For change month format 'MMM' to 'MM'
	function changeToMM(src)
	{
		var mName = src.substring(3,6) ;
		return src.replace(mName, monthArr[mName.toUpperCase()]) ;
	}
// For change all '-' in src to '/'
	function dashToSlash(src)
	{
		if (src.indexOf('-')>-1)
			return dashToSlash(src.replace('-','/')) ;
		else
			return src ;
	}
	

