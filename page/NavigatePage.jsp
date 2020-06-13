	function onNext(func){
		var page = DWRUtil.getValue("page")/1;
		var maxPage = DWRUtil.getValue("maxPage")/1;
		if (page < maxPage){
			page = page + 1;
			DWRUtil.setValue("page",page);
			func();
		}
		else{
			alert("Last Page");
		}
	}

	function onPrevious(func){
		var page = DWRUtil.getValue("page")/1;
		if (page > 0 ){
			page = page - 1;
			DWRUtil.setValue("page",page);
			func();
		}
		else{
			alert("First Page");
		}		
	}
	
	function onLast(func){
		var page = DWRUtil.getValue("page")/1;
		var maxPage = DWRUtil.getValue("maxPage")/1;
		if (page < maxPage){
			page = maxPage;
			DWRUtil.setValue("page",page);
			func();
		}
		else{
			alert("Last Page");
		}
	}

	function onFirst(func){
		var page = DWRUtil.getValue("page")/1;
		if (page > 0 ){
			page = 0;
			DWRUtil.setValue("page",page);
			func();
		}
		else{
			alert("First Page");
		}		
	}

	function onQuery(func){
			page = 0;
			DWRUtil.setValue("showMaxPage","");
			DWRUtil.setValue("page",page);
			func();
	}
	
	function onKeyGoPage(event,func){
		var page = DWRUtil.getValue("page")/1;
		var showPage = DWRUtil.getValue("showPage")/1;
		var showMaxPage = DWRUtil.getValue("showMaxPage")/1;
		if (event.keyCode ==13){
			if (DWRUtil.getValue("showPage").length == 0){
				DWRUtil.setValue("showPage",page+1);
			}
			if ((showPage > showMaxPage)||(showPage < 1 )){
				alert('Page not Found');
			}
			else{
				page = showPage-1;
				DWRUtil.setValue("page",page);
				func();
				
			}	
		}
	}

	function onChangeGoPage(func){
		var page = DWRUtil.getValue("page")/1;
		var showPage = DWRUtil.getValue("showPage")/1;
		var showMaxPage = DWRUtil.getValue("showMaxPage")/1;
			if (DWRUtil.getValue("showPage").length == 0){
				DWRUtil.setValue("showPage",page+1);
			}
			if ((showPage > showMaxPage)||(showPage < 1 )){
				alert('Page not Found');
			}
			else{
				page = showPage-1;
				DWRUtil.setValue("page",page);
				func();
				
			}	
	}

	function onCheckButt(frm){
		var page = DWRUtil.getValue("page")/1;
		var max   = Math.ceil((DWRUtil.getValue("countData")/1)/(DWRUtil.getValue("dataPerPage")/1));
		if (page == 0){
	    	document.forms[frm].previous.disabled = true;
	    	document.forms[frm].first.disabled = true;
	    }
	    else{
		    document.forms[frm].previous.disabled = false;
		    document.forms[frm].first.disabled    = false;
	    }
		if (page == max-1){
	    	document.forms[frm].next.disabled = true;
	    	document.forms[frm].last.disabled = true;
	    }
	    else{
		    document.forms[frm].next.disabled = false;
		    document.forms[frm].last.disabled = false;
	    }
	    DWRUtil.setValue("maxPage",max-1);
	    DWRUtil.setValue("showMaxPage",max);
	    DWRUtil.setValue("showPage",page+1);	
	}

	function onCheckPageNAN(value){
		var showPage = DWRUtil.getValue("showPage")/1;
		var page = DWRUtil.getValue("page")/1;
		if(isNaN(Number(value))) {
			value = value.substring(0,value.length - 1);
			if (value.length == 0){
				value = page + 1;
			}
		DWRUtil.setValue("showPage",value);
		}
		
	}	
	