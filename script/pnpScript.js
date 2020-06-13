	function doSubmitSearch(frm){
	var type = frm.elements["reqType"].value;	
		if(type=='submit'){
			frm.elements["pageGroup"].value=0;
			frm.elements["startRow"].value=0;
			frm.elements["reqCode"].value="doSearch";
		}
	return true; 
	}
	
	function goPage(frm, rowNo){
		frm.elements["startRow"].value=rowNo;
		frm.elements["reqCode"].value="doSearch";
		frm.elements["reqType"].value="link";	
		frm.submit();
	}
	
	function goNextGroup(frm, groupNo, startRow){
		frm.elements["pageGroup"].value=groupNo;
		goPage(frm, startRow);
	}
	
	function doSubmit(frm, iReqCode, iTarget){
		frm.elements["reqCode"].value=iReqCode;
		if(iTarget!=null)frm.target=iTarget;
		frm.submit();
	}
/*	
	function preEdit(pk){
 	var frm=document.forms[0];
 			frm.elements["orgId"].value=pk;
 			frm.elements["reqType"].value="link";
 			frm.elements["reqCode"].value="preEdit";
 			frm.submit();
	}
	*/
	function setFocus(){
		var item;
		var frms = document.forms[0];
		if (frms!=null){
			for(i=0;i<frms.elements.length;i++){
				item = document.forms[0].elements[i];
				if(item.type=='text'&&!item.readOnly&&!item.disabled){
					try{
					item.focus();
					break;
					}catch(error){}
				}
			}
		}
	}
/**
* Script for date input
*/
	function redirect(frm, m, y){
    d=frm.value
    var temp = frm;
    for (x=temp.options.length-1;x>0;x--)
    temp.options[x]=null
    amount = (m == 1?y%4==0?y>1581?y%100==0&&y%400!=0?0:1:1:0:m==3||m==5||m==8||m==10?2:3) + 28;

    for (i=1;i<=amount;i++){
        if  (i == 1) {
                temp.options[0]=new Option("", 0)
        }
        temp.options[i]=new Option(i,i)
    }
	if(d>0){
		if (d <= amount) {
			temp.options[d].selected=true;
		}
		else {
			alert('ไม่มีวันที่ ' + d + ' ในเดือนนี้');
		}
	}
}