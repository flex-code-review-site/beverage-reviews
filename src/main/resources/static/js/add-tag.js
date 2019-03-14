var tagSubmit = document.getElementsByClassName('tag-submit');
for (let div of tagSubmit) {
    div.style.display = 'none';
}

var tagButton = document.getElementById('tag-button');
tagButton.onclick = function() {
	tagButton.style.display = 'none';
	for (let div of tagSubmit) {
	    div.style.display = 'inline-block';
	}
}
