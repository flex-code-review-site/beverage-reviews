// window.onscroll = function() {scrollFunction()};

var navbar = document.getElementById("navbar");
navbar.innerHTML = ` <a class="active" href="http://localhost:8080/reviews">Reviews</a>
	                  <a href="http://localhost:8080/categories">Categories</a>
	                  <a href="http://localhost:8080/tags">Tags</a>`;

// var sticky = navbar.offsetTop;

// function scrollFunction() {
//   if (window.pageYOffset >= sticky) {
//     navbar.classList.add("sticky")
//   } else {
//     navbar.classList.remove("sticky");
//   }
// }