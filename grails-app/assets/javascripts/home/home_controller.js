//= require third-party/jquery/dist/jquery.js

var HomeController = (function(){

  var selectors = {
    sidebar_toggle:'#sidebar-toggle',
    navbar_side:'.navbar-side',
    page_wrapper:'#page-wrapper'
  };

  var toggleDiv = function(){
    $(selectors.navbar_side).toggleClass("collapsed");
    $(selectors.page_wrapper).toggleClass("collapsed");
  };

  var bindEvents = function(){
    $(selectors.sidebar_toggle).on("click",toggleDiv);
  };

  var start = function(){
    bindEvents();
  };

  return{
    start:start
  };

}());

