function chkInputNum()		{		// check input data (number only)
		if (event.keyCode<48 || event.keyCode>57)	{
					event.returnValue = false;
		}
}