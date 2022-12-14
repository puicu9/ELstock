(function($) {
  'use strict';

  $(document).ready(function(){
        $('#searchButton').on('click', function(event){
          event.preventDefault(); /* 이벤트 전파 방지 효과 */
          gotoPage(0) ; /* 시작하자마자 1페이지로 이동하기 */
        });

        var now = new Date();
        var month = now.getMonth()+1;
        console.log("월 : ", month);
        var day = now.getDate();
        console.log("일 : ", day);

        $('.date').html(month + ' / ' + day);




  });

  function gotoPage(page){
    /* 해당 태그에는 field 이름을 속성 값으로 하는 id 속성이 자동으로 만들어 집니다. */
    var searchQuery = $("#searchQuery").val();

    location.href="/search/lists/" + page + "?searchQuery=" + searchQuery;
  }

})(jQuery);