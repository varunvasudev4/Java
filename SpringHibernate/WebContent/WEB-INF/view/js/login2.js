function show(status,th) {
	if(status){
		th.style.display = "none";
		document.getElementsByClassName("show")[0].style.display="inline-block";
		document.getElementById("pass").type = "text";
		document.getElementById("pass").style.width="90%";
	}else{
		th.style.display = "none";
		document.getElementsByClassName("hide")[0].style.display="inline-block";
		document.getElementById("pass").type = "password";
	}
}