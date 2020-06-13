	<!-- grid -->
	var checkFlag = false;
	function doSelectAll(f){	
//		if(f==null)frm=document.forms[0];		
		if(f==null)frm=document.forms["deleteForm"];
		else frm =f;
		if ( frm.elements["chk"] != undefined){
			var chkLength = frm.elements["chk"].length;
			var chk = frm.elements["chk"];
			if(chkLength==undefined){
				if(!checkFlag){
					chk.checked = true;
					checkFlag = true;
				}else{
					chk.checked = false;
					checkFlag = false;
				}
			}else{
				if(!checkFlag){
					for(i=0;i<chkLength;i++){
						chk[i].checked = true;
					}
					checkFlag = true;
				}else{
					for(i=0;i<chkLength;i++){
						chk[i].checked = false;
					}
					checkFlag = false;
				}
			}
		}	
	}
	function doCheck(f){
//		if(f==null)frm=document.forms[0];
		if(f==null)frm=document.forms["deleteForm"];
		else frm =f;
		var chkLength = frm.elements["chk"].length;
		var selectAll = frm.elements["selectAll"];
		var chk = frm.elements["chk"];
		var cFlag = true;
		if(chkLength==undefined){
			if(!chk.checked){
				selectAll.checked = false;
				checkFlag = false;
			}else{
				selectAll.checked =true;
				checkFlag = true;
			}
		}else{
			for(i=0;i<chkLength;i++){
				if(!chk[i].checked){
					selectAll.checked = false;
					checkFlag = false;
					cFlag = false;
					break;
				}
			}
			if(cFlag){
				selectAll.checked = true;
				checkFlag = true;
			}
		}				
	}
	
	function checkBefore(f){
	if(confirm("คุณต้องการลบข้อมูลที่เลือกใช่หรือไม่ ?")){
//		if(f==null)frm=document.forms[0];
		if(f==null)frm=document.forms["deleteForm"];
		else frm =f;
		var chk = frm.elements["chk"];
		var ret = false;
		if(chk!=null){
			if(chk.length==undefined){
				ret = chk.checked;
			}else{
				for(i=0;i<chk.length;i++){
					if(chk[i].checked){
						ret=chk[i].checked;
						break;
					}
				}
			}
		}
		if(!ret)alert("ต้องเลือกข้อมูลอย่างน้อยหนึ่งเร็คคอร์ด");
	}
	return ret;
	}	
	
	<!-- navigator -->
	function doPrevious(){
		var pg = parseInt(document.forms[0].txtPage.value) - 1;
		validate(pg);
	}
	
	function doNext(){
		var pg = parseInt(document.forms[0].txtPage.value) + 1;
		validate(pg);
	}	
	
	function doPage(event){
		var pg = parseInt(document.forms[0].txtPage.value);
		if(event.keyCode == 13){
			validate(pg);
		}
	}
		
	function validate(pg){
		var vd = true;
		var pgMax = parseInt(document.forms[0].txtMaxPage.value);
		
		vd = validateNaN(pg);
		if(vd){
			vd = validateMin(pg);
			if(vd){
				vd = validateMax(pg,pgMax);
				if(vd){
					goPage(pg);
				}
			}
		}		
	}
	
	function validateNaN(pg){
		if(isNaN(pg)){
			alert('Invalid page number');
			return false;
		}else{
			return true;
		}
	}
	
	function validateMin(pg){
		if(pg < 0){
			alert('Page must be bigger than 1');
			return false;
		}else if(pg == 0){
			//alert('อยู่ที่หน้า 1 อยู่แล้ว กดไม่ได้');
			return false;
		}else{
			return true;
		}
	}
	
	function validateMax(pg,maxpg){
		if(pg > maxpg){
			//alert('Invalid page number');
			return false;
		}else{
			return true;
		}
	}
		
	function goPage(pg){
//	var frm = document.forms[0];
	var frm = document.forms["searchForm"];
	var txtPage = frm.elements["txtPage"];
	var maxRowPerPage = frm.elements["maxRowPerPage"].value;
		if(txtPage!=null) txtPage.value = pg;
		goToPage((pg-1)*maxRowPerPage);
	}
	
	function goToPage(rowNo){
	var frm = document.forms[0];
	var stRow = frm.elements["startRow"];
		if(stRow!=null) stRow.value=rowNo;
		frm.elements["reqCode"].value="doSearch";
		frm.elements["reqType"].value="link";	
		frm.submit();
	}