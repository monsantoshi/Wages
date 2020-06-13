/* General PopUp Function */
function convertAbsoluteRootPath(current_path) 
{
  var result = "";

  if((current_path != null) && (current_path != "") && (current_path != "/")) 
  {
    result = "../";
    var currentChar;
    for(charItr = 0; charItr < current_path.length; charItr++) 
    {
      currentChar = current_path.charAt(charItr);
      if(currentChar == "/") 
      {
        result = result.concat("../");
      }
    } //for(charItr = 0; charItr < current_path.length; charItr++)
  }

  return result;
}

function simpleURLEncoding(url) 
{
  var result = "";

  var currentChar;
  for(charItr = 0; charItr < url.length; charItr++) 
  {
    currentChar = url.charAt(charItr);
    if(currentChar == "&") 
    {
      result = result.concat("%26");
    }
    else 
    {
      result = result.concat(currentChar);
    }
  } //for(charItr = 0; charItr < url.length; charItr++)

  return result;
}

function trimDialogMessage(message)
{
  var result = "";
  
  if(message.toString().length > 0)
  {
    var WHITE_SPACE = 32;
    
    result = message;
    var firstNonWhiteSpace = 0;
    
    while(firstNonWhiteSpace < result.length)
    {
      if(result.charCodeAt(firstNonWhiteSpace) > WHITE_SPACE)
      {
        result = result.substring(firstNonWhiteSpace, result.length);
        break;
      }
      
      ++firstNonWhiteSpace;
    } //while(firstNonWhiteSpace < result.length)
    
    firstNonWhiteSpace = result.length - 1;
    
    while(firstNonWhiteSpace > 0)
    {
      if(result.charCodeAt(firstNonWhiteSpace) > WHITE_SPACE)
      {
        result = result.substring(0, (firstNonWhiteSpace + 1));
        break;
      }
      
      --firstNonWhiteSpace;
    } //while(firstNonWhiteSpace > 0)
  }
  
  return result;
}

function isInputBoxCache(obj)
{
  var result = false;
  
  if((obj.type == "hidden") || (obj.type == "text") || (obj.type == "textarea"))
  {
    result = true;
  }
  
  return result;
}

function showConfirmDialog(message, currentPathFromRoot)
{
  var result = false;
  
  if(trimDialogMessage(message).length > 0)
  {
    var url_offset = convertAbsoluteRootPath(currentPathFromRoot);
    var arguments = new Array();
    arguments[0] = message;
    
    var windowStyle = " center:yes;dialogWidth:400px; dialogHeight:205px; status:no; scroll:no";
    
    result = window.showModalDialog(url_offset + "popup/confirm_dialog.jsp", arguments, windowStyle);
    if(result == null)
    {
      result = false;
    }
  }
  
  return result;
}

function showInfoDialog(message, currentPathFromRoot)
{
  var result = false;
  
  if(trimDialogMessage(message).length > 0)
  {
    var url_offset = convertAbsoluteRootPath(currentPathFromRoot);
    var arguments = new Array();
    arguments[0] = message;
    
    var windowStyle = " center:yes;dialogWidth:400px; dialogHeight:205px; status:no; scroll:no";
    
    result = window.showModalDialog(url_offset + "popup/info_dialog.jsp", arguments, windowStyle);
    if(result == null)
    {
      result = false;
    }
  }
  
  return result;
}

function showWarningDialog(message, currentPathFromRoot)
{
  var result = false;
  
  if(trimDialogMessage(message).length > 0)
  {
    var url_offset = convertAbsoluteRootPath(currentPathFromRoot);
    var arguments = new Array();
    arguments[0] = message;
    
    var windowStyle = " center:yes;dialogWidth:400px; dialogHeight:205px; status:no; scroll:no";
    
    result = window.showModalDialog(url_offset + "popup/warning_dialog.jsp", arguments, windowStyle);
    if(result == null)
    {
      result = false;
    }
  }
  
  return result;
}

function showErrorDialog(message, stacktrace, currentPathFromRoot)
{
  var result = false;
  
  if(trimDialogMessage(message).length > 0)
  {
    var url_offset = convertAbsoluteRootPath(currentPathFromRoot);
    var arguments = new Array();
    arguments[0] = message;
    arguments[1] = stacktrace;
    
    var windowStyle = " center:yes;dialogWidth:400px; dialogHeight:205px; status:no; scroll:no;";
    
    result = window.showModalDialog(url_offset + "popup/error_dialog.jsp", arguments, windowStyle);
    if(result == null)
    {
      result = false;
    }
  }
  
  return result;
}

function showCacheDialog(info_cache, warning_cache, error_cache, stacktrace_cache, currentPathFromRoot)
{
  var result = false;
  
  if(info_cache != null)
  {
    var info_message = "";
    if(isInputBoxCache(info_cache))
    {
      info_message = info_cache.value;
      info_cache.value = "";
    }
    else
    {
      info_message = info_cache.innerHTML;
      info_cache.innerHTML = "";
    }
    
    result = (result) || (showInfoDialog(info_message, currentPathFromRoot));
  }
  
  if(warning_cache != null)
  {
    var warning_message = "";
    if(isInputBoxCache(warning_cache))
    {
      warning_message = warning_cache.value;
      warning_cache.value = "";
    }
    else
    {
      warning_message = warning_cache.innerHTML;
      warning_cache.innerHTML = "";
    }
    
    result = (result) || (showWarningDialog(warning_message, currentPathFromRoot));
  }
  
  if(error_cache != null)
  {
    var error_message = "";
    if(isInputBoxCache(error_cache))
    {
      error_message = error_cache.value;
      error_cache.value = "";
    }
    else
    {
      error_message = error_cache.innerHTML;
      error_cache.innerHTML = "";
    }
    
    var error_stacktrace = "";
    if(stacktrace_cache != null)
    {
      if(isInputBoxCache(stacktrace_cache))
      {
        error_stacktrace = stacktrace_cache.value;
        stacktrace_cache.value = "";
      }
      else
      {
        error_stacktrace = stacktrace_cache.innerHTML;
        stacktrace_cache.innerHTML = "";
      }
    }
    
    result = (result) || (showErrorDialog(error_message, error_stacktrace, currentPathFromRoot));
  }
  
  return result;
}

function toggleSpanVisibility(spanObj)
{
  if(spanObj.style.display == "none")
  {
    spanObj.style.display = "inline";
  }
  else
  {
    spanObj.style.display = "none";
  }
}

function showFormDialog(urlFromRoot, currentPathFromRoot, dialogWidth, dialogHeight)
{
  var result = false;
  
  var url_offset = convertAbsoluteRootPath(currentPathFromRoot);
  
  var encoded_url = simpleURLEncoding(urlFromRoot);
  
  var windowStyle = " center:yes; status:no; scroll:no;";
  if((dialogWidth == null) || (dialogWidth <= 0) || (dialogWidth.toString().length <= 0))
  {
    windowStyle = windowStyle + " dialogWidth: 400px;";
  }
  else
  {
    windowStyle = windowStyle + " dialogWidth: " + dialogWidth + ";";
  }
  
  if((dialogHeight == null) || (dialogHeight <= 0) || (dialogHeight.toString().length <= 0))
  {
    windowStyle = windowStyle + " dialogHeight: 205px;";
  }
  else
  {
    windowStyle = windowStyle + " dialogHeight: " + dialogHeight + ";";
  }
  
  result = window.showModalDialog(url_offset + "popup/form_dialog.jsp?form_url=" + encoded_url, null, windowStyle);
  if(result == null)
  {
    result = false;
  }
  
  return result;
}
