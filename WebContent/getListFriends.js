var xHRObject = new XMLHttpRequest();

function getFriends () {
	xHRObject.open("GET", "GetFriendsServlet", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

/**
 * Rechtstreeks DOM gebruiken is beter, maar dit is veel leesbaarder.
 * Zie andere js voor DOM oplossing
 */
function getData () {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var data = JSON.parse(xHRObject.responseText);
			
			var friendlistDiv = document.getElementById("friendlist");
			
			var HTML = "<table>";
			for(var i = 0; i < data.length; i++)
			{
				var friend = data[i]
			    HTML += "<tr><td>"+friend.username+"</td><td>"+friend.status+"</td></tr>";
			}
			HTML += "</table>";
		
			friendlistDiv.innerHTML = HTML;
			setTimeout("getFriends()", 100);
		}
	}
}