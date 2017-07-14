var xHRObject = new XMLHttpRequest();

function sendStatus () {
	var selectStatus = document.getElementById("selectStatus").value;
	var customStatus = document.getElementById("customStatus").value;
	console.log(customStatus);
	var data = new FormData();
	data.append('selectStatus', selectStatus);
	data.append('customStatus', customStatus);
	console.log(data);
	xHRObject.open("POST", "ChangeStatusServlet", true);
	xHRObject.send(data);
	xHRObject.onreadystatechange = changeStatus;
}

function changeStatus () {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var response = xHRObject.responseText;
			
			var statusSpan= document.getElementById("status");
		
			statusSpan.innerHTML = response;
		}
	}
}