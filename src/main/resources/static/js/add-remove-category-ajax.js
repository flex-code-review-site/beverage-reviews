

const submitCategoryButton = document.querySelector(".add-category button");
const submitCategoryInput = document.querySelector(".add-category input");
const categoriesList = document.querySelector (".categories-list");

const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
  if(xhr.readyState === 4 && xhr.status === 200){
    const res = xhr.response;
    categoriesList.innerHTML = res;
  }
}

submitCategoryButton.addEventListener("click",addCategory);

function addCategory() {  
  if(submitCategoryInput.value != "")
  {
    submitCategory(submitCategoryInput.value);
    console.log(submitCategoryInput.value);
    submitTagInput.value = "";
  }
} 
 
  
function submitCategory(name){
	xhr.open("POST","/categories/" + name, true);
	xhr.send();
}

categoriesList.addEventListener("click", function(event){
  if(event.target.classList.contains("x")){
  let id = event.target.previousElementSibling.previousElementSibling.value;
  submitRemoveCategory(id);
  console.log(id);
  }
})

function submitRemoveCategory(id){
    xhr.open("POST","/categories/remove/" + id, true);
	xhr.send();
}