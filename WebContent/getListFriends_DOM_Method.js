var xHRObject = new XMLHttpRequest();

function getFriends () {
	xHRObject.open("GET", "GetFriendsServlet", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData () {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var i;
			var data = JSON.parse(xHRObject.responseText);
			
			var friendlistDiv = document.getElementById("friendlist");
			var friendlistTable = friendlistDiv.childNodes[0];
			if (friendlistTable == null) {
				console.log("help");
				friendlistTable = document.createElement("TABLE");
				friendlistTable.id = "friendlistTable";
				//create table..
			    for(var i = 0; i < data.length; i++){
			        var rowData = data[i];
			        var row = document.createElement("tr");
			        for(var j = 0; j < rowData.length; j++){
			            var cell = document.createElement("td");
			            cell.innerHTML = rowData[j];
			            row.appendChild(cell);
			        }
			        friendlistTable.appendChild(row);
			    }
			    //finally append the whole thing..
			   friendlistDiv.appendChild(friendlistTable);
			}
			else {
				friendlistTable = document.createElement("TABLE");
				//create table..
			    for(var i = 0; i < data.length; i++){
			        var rowData = data[i];
			        var row = document.createElement("tr");
			        for(var j = 0; j < rowData.length; j++){
			            var cell = document.createElement("td");
			            cell.innerHTML = rowData[j];
			            row.appendChild(cell);
			        }
			        friendlistTable.appendChild(row);
			    }
			    //finally append the whole thing..
			    quoteParagraph.removeChild(friendlistDiv.childNodes[0]);
			    friendlistDiv.appendChild(friendlistTable);
			}
		}
	}
}