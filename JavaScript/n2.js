process.stdin.resume();
process.stdin.setEncoding('utf8');
// 自分の得意な言語で
// Let's チャレンジ！！

var lines = [];
var reader = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
});
reader.on('line', (line) => {
  lines.push(line);
});
reader.on('close', () => {
//   console.log(lines[0]);
    // console.log( lines[0]);
    var result = String(lines[0]).split(' ');
    // console.log( result );
    console.log( Number.parseInt(result[0])-Number.parseInt(result[1]));

});
