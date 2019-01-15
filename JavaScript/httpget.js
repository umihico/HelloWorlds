// const Http = new XMLHttpRequest();
// const url='https://api.github.com/users/umihico/repos?per_page=100&page=1';
// Http.open("GET", url);
// Http.send();
// Http.onreadystatechange=(e)=>{
// console.log(Http.responseText)
// }
var url; // リクエスト先URL
var request;
request = new ActiveXObject("MSXML2.XMLHTTP");
// request = new XMLHttpRequest();
// request = new ActiveXObject("Microsoft.XMLHTTP");
// if( window.XMLHttpRequest){
// }else if(window.ActiveXObject){
//   try {
//   } catch (e) {
//   }
// }
request.open('GET', 'https://api.github.com/users/umihico/repos?per_page=100&page=1');
request.onreadystatechange = function () {
    if (request.readyState != 4) {
      console.log('a')
        // リクエスト中
    } else if (request.status != 200) {
      console.log('a')
        // 失敗
    } else {
      console.log(Http.responseText)
        // 取得成功
        // var result = request.responseText;
    }
};
request.send(null);


var price=document.getElementById('priceof{href}').value;
var xhr = new XMLHttpRequest();
xhr.open('GET', 'http://localhost:5555/bid/?aucid={href}&price='+price, true);
xhr.send();
