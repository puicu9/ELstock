!function(){var a={},b=function(b){for(var c=a[b],e=c.deps,f=c.defn,g=e.length,h=new Array(g),i=0;i<g;++i)h[i]=d(e[i]);var j=f.apply(null,h);if(void 0===j)throw"module ["+b+"] returned undefined";c.instance=j},c=function(b,c,d){if("string"!=typeof b)throw"module id must be a string";if(void 0===c)throw"no dependencies for "+b;if(void 0===d)throw"no definition function for "+b;a[b]={deps:c,defn:d,instance:void 0}},d=function(c){var d=a[c];if(void 0===d)throw"module ["+c+"] was undefined";return void 0===d.instance&&b(c),d.instance},e=function(a,b){for(var c=a.length,e=new Array(c),f=0;f<c;++f)e.push(d(a[f]));b.apply(null,b)},f={};f.bolt={module:{api:{define:c,require:e,demand:d}}};var g=c,h=function(a,b){g(a,[],function(){return b})};h("5",tinymce.util.Tools.resolve),g("1",["5"],function(a){return a("tinymce.dom.DOMUtils")}),g("2",["5"],function(a){return a("tinymce.Env")}),g("3",["5"],function(a){return a("tinymce.PluginManager")}),g("4",["5"],function(a){return a("tinymce.util.Delay")}),g("0",["1","2","3","4"],function(a,b,c,d){var e=a.DOM;return c.add("autoresize",function(a){function c(){return a.plugins.fullscreen&&a.plugins.fullscreen.isFullscreen()}function f(d){var g,j,k,l,m,n,o,p,q,r,s,t;if(j=a.getDoc()){if(k=j.body,l=j.documentElement,m=h.autoresize_min_height,!k||d&&"setcontent"===d.type&&d.initial||c())return void(k&&l&&(k.style.overflowY="auto",l.style.overflowY="auto"));o=a.dom.getStyle(k,"margin-top",!0),p=a.dom.getStyle(k,"margin-bottom",!0),q=a.dom.getStyle(k,"padding-top",!0),r=a.dom.getStyle(k,"padding-bottom",!0),s=a.dom.getStyle(k,"border-top-width",!0),t=a.dom.getStyle(k,"border-bottom-width",!0),n=k.offsetHeight+parseInt(o,10)+parseInt(p,10)+parseInt(q,10)+parseInt(r,10)+parseInt(s,10)+parseInt(t,10),(isNaN(n)||n<=0)&&(n=b.ie?k.scrollHeight:b.webkit&&0===k.clientHeight?0:k.offsetHeight),n>h.autoresize_min_height&&(m=n),h.autoresize_max_height&&n>h.autoresize_max_height?(m=h.autoresize_max_height,k.style.overflowY="auto",l.style.overflowY="auto"):(k.style.overflowY="hidden",l.style.overflowY="hidden",k.scrollTop=0),m!==i&&(g=m-i,e.setStyle(a.iframeElement,"height",m+"px"),i=m,b.webKit&&g<0&&f(d))}}function g(b,c,e){d.setEditorTimeout(a,function(){f({}),b--?g(b,c,e):e&&e()},c)}var h=a.settings,i=0;a.settings.inline||(h.autoresize_min_height=parseInt(a.getParam("autoresize_min_height",a.getElement().offsetHeight),10),h.autoresize_max_height=parseInt(a.getParam("autoresize_max_height",0),10),a.on("init",function(){var b,c;b=a.getParam("autoresize_overflow_padding",1),c=a.getParam("autoresize_bottom_margin",50),b!==!1&&a.dom.setStyles(a.getBody(),{paddingLeft:b,paddingRight:b}),c!==!1&&a.dom.setStyles(a.getBody(),{paddingBottom:c})}),a.on("nodechange setcontent keyup FullscreenStateChanged",f),a.getParam("autoresize_on_init",!0)&&a.on("init",function(){g(20,100,function(){g(5,1e3)})}),a.addCommand("mceAutoResize",f))}),function(){}}),d("0")()}();