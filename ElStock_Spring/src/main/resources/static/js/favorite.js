(function($) {
  'use strict';
  $(function() {


    //클릭 시, minimize
    $('[data-toggle="minimize"]').on("click", function() {
      if ((body.hasClass('sidebar-toggle-display')) || (body.hasClass('sidebar-absolute'))) {
        body.toggleClass('sidebar-hidden');
      } else {
        body.toggleClass('sidebar-icon-only');
      }
    });

    function addActiveClass(element) {
          if (current === "") {
            //for root url
            if (element.attr('href').indexOf("main.html") !== -1) {
              element.parents('.nav-item').last().addClass('active');
              if (element.parents('.sub-menu').length) {
                element.closest('.collapse').addClass('show');
                element.addClass('active');
              }
            }
          } else {
            //for other url
            if (element.attr('href').indexOf(current) !== -1) {
              element.parents('.nav-item').last().addClass('active');
              if (element.parents('.sub-menu').length) {
                element.closest('.collapse').addClass('show');
                element.addClass('active');
              }
              if (element.parents('.submenu-item').length) {
                element.addClass('active');
              }
            }
          }
        }







  });
})(jQuery);