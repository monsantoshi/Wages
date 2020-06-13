function splitCombo(val){
	var tmp = val.split(' ');
	//alert( tmp );
	return tmp[0];
}

function checkNull(val, datatype){
	if( val == null && datatype == 'STRING' ){
		return '';
	}else if( val == null && datatype == 'NUMBER' ){
		return '0';
	}else
		return val;
}

function Trim(TRIM_VALUE){
	if(TRIM_VALUE.length < 1){
	  return"";
	}
	TRIM_VALUE = RTrim(TRIM_VALUE);
	TRIM_VALUE = LTrim(TRIM_VALUE);
	if(TRIM_VALUE==""){
	  return "";
	}
	else{
	  return TRIM_VALUE;
	}
}

function RTrim(VALUE){
	var w_space = String.fromCharCode(32);
	var v_length = VALUE.length;
	var strTemp = "";
	if(v_length < 0){
	  return"";
	}
	var iTemp = v_length -1;

	while(iTemp > -1){
  		if(VALUE.charAt(iTemp) == w_space)
  			;
  		else{
    		strTemp = VALUE.substring(0,iTemp +1);
    		break;
        }
  		iTemp = iTemp-1;

	} //End While
    return strTemp;

} //End Function

function LTrim(VALUE){
	var w_space = String.fromCharCode(32);
	if(v_length < 1){
		return"";
	}
	var v_length = VALUE.length;
	var strTemp = "";

	var iTemp = 0;

	while(iTemp < v_length){
  		if(VALUE.charAt(iTemp) == w_space)
  			;
  		else{
    		strTemp = VALUE.substring(iTemp,v_length);
    		break;
  		}
  		iTemp = iTemp + 1;
	} //End While
	return strTemp;
	
} //End Function
