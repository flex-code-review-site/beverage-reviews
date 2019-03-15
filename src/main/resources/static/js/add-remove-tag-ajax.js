

const submitTagButton = document.querySelector(".add-tag button");
const submitTagInput = document.querySelector(".add-tag input");
const tagsList = document.querySelector (".tags-list");

const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
  if(xhr.readyState === 4 && xhr.status === 200){
    const res = xhr.response;
    tagsList.innerHTML = res;
  }
}

submitTagButton.addEventListener("click",addTag);

function addTag() {  
  if(submitTagInput.value != "")
  {
    submitTag(submitTagInput.value);
    console.log(submitTagInput.value);
    submitTagInput.value = "";
  }
} 
 
  
function submitTag(name){
	xhr.open("POST","/tags/" + name, true);
	xhr.send();
}

tagsList.addEventListener("click", function(event){
  let id = event.target.previousElementSibling.previousElementSibling.value;
  submitRemoveTag(id);
  console.log(id);
})

function submitRemoveTag(id){
    xhr.open("POST","/tags/remove/" + id, true);
	xhr.send();
}