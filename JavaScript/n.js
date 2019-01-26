process.stdin.resume();
process.stdin.setEncoding('utf8');

var lines = [];
var reader = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
});
reader.on('line', (line) => {
  lines.push(line);
});
  reader.on('close', () => {
    if (lines[0] % 2 == 1) {
    //   reader.on('close', () => {
        // console.log(lines[0]%2);
        // console.log("a");
        console.log((lines[0]-1)/2);
    //   });
    } else {
        // console.log(lines[0]%2==1);
        // console.log("b");
        // console.log(lines[0]%2);
        console.log(lines[0]/2);
    }
  });
