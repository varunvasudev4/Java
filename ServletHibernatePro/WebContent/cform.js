function vpsf() {
	document.getElementById("vps").style.display="inline-block";
	document.getElementById("twoC").style.display="inline-block";
	document.getElementById("oneC").style.display="none";
	document.getElementById("twoC").checked = true;
	document.getElementById("oneC").checked = true;
}
function vpsf2() {
	document.getElementById("vps").style.display="none";
	document.getElementById("twoC").style.display="none";
	document.getElementById("oneC").style.display="inline-block";
	document.getElementById("oneC").checked = false;
	document.getElementById("twoC").checked = false;
}

function del() {
	confirm('Delete This One');
}