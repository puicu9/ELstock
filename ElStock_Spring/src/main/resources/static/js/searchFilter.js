(function($) {
  'use strict';
  function searchFilter(){
       let search = document.getElementById("search").value.toLowerCase();
       let listInner = document.getElementsByClassName("listInner");

       for (let i = 0; i < listInner.length; i++) {
         ticker_code = listInner[i].getElementsByClassName("ticker_code");
         ticker_name = listInner[i].getElementsByClassName("ticker_name");
         if (job[0].innerHTML.toLowerCase().indexOf(search) != -1 ||
           company[0].innerHTML.toLowerCase().indexOf(search) != -1
         ) {
           listInner[i].style.display = "block"
         } else {
           listInner[i].style.display = "none"
         }
       }
     };
})(jQuery);