

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
    submitTag(submitTagInput.value);
    console.log(submitTagInput.value);
    submitTagInput.value = "";
} 
 
  
function submitTag(name){
	xhr.open("POST","/tags/" + name, true);
	xhr.send();
}