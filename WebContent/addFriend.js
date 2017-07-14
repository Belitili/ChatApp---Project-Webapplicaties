var xHRObject = new XMLHttpRequest();

function addFriend () {
	var friendToAdd = document.getElementById("friendToAdd").value;
	console.log(customStatus);
	var data = new FormData();
	data.append('friendToAdd', friendToAdd);
	console.log(data);
	xHRObject.open("POST", "AddFriendServlet", true);
	xHRObject.send(data);
	xHRObject.onreadystatechange = changeStatus;
}

function changeStatus () {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var response = xHRObject.responseText;
			console.log(response);
			
			var responseSpan = document.getElementById("addFriendResponse");
			console.log(responseSpan);
			responseSpan.innerHTML = response;
		}
	}
}