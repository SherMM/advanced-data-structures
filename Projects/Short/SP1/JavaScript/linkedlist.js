function Node(item, next) {
  this.item = item;
  this.next = null;
}

function LList() {
  this.head = new Node(null, null);
  this.tail = this.head.next;
  this.add = add;
  this.print = print;
  this.reverse = reverse;
  this.reverseRec = reverseRec;
  this.size = 0;
}

function add(item) {
  if (this.tail === null) {
    this.head.next = new Node(item, this.head.next);
    this.tail = this.head.next;
  } else {
    this.tail.next = new Node(item, null);
    this.tail = this.tail.next;
  }
  this.size++;
}

function print() {
  var numString = "";
  var x = this.head.next;
  while (x !== null) {
    numString += x.item + " ";
    x = x.next;
  }
  console.log(numString);
}

function reverse() {
  if (this.head.next !== null && this.size > 1) {
    var temp1 = this.head.next;
    var temp2 = temp1.next;
    var temp3 = temp2.next;

    temp1.next = null;
    this.tail = temp1;
    temp2.next = temp1;
    temp1 = temp3;

    while (temp1 !== null) {
      var nextNode = temp1.next;
      temp1.next = temp2;
      temp2 = temp1;
      temp1 = nextNode;
    }
    this.head.next = temp2;
  }
}

function reverseRec(list, node) {
  if (node === null) {
    return;
  }

  if (node.next === null) {
    list.tail = list.head.next;
    list.head.next = node;
    return;
  }

  reverseRec(list, node.next);
  node.next.next = node;
  node.next = null;
}

function testReverse(min, max, n) {
  var list = new LList();
  for (var i = 0; i < n; i += 1) {
    list.add(Math.floor(Math.random() * (max - min)) + min);
  }
  list.print();
  console.time("reverse-LL-runtime");
  list.reverse();
  console.timeEnd("reverse-LL-runtime");
  list.print();
}

function doubleReverse(min, max, n) {
  var list = new LList();
  for (var i = 0; i < n; i += 1) {
    list.add(Math.floor(Math.random() * (max - min)) + min);
  }
  list.print();
  list.reverse();
  list.print();
  list.reverse();
  list.print();
}

function testRecursiveReverse(min, max, n) {
  var list = new LList();
  for (var i = 0; i < n; i += 1) {
    list.add(Math.floor(Math.random() * (max - min)) + min);
  }
  list.print();
  console.time("reverseRec-LL-runtime");
  reverseRec(list, list.head.next);
  console.timeEnd("reverseRec-LL-runtime");
  list.print();
}

function main() {
  var i = 0;
  var n = 25;
  for (i = 0; i < n; i += 1) {
    testReverse(0, 51, i);
    console.log();
  }

  for (i = 0; i < n; i += 1) {
    doubleReverse(0, 51, i);
    console.log();
  }

  //testReverse(0, 51, 10000);
  for (i = 0; i < n; i += 1) {
    testRecursiveReverse(0, 51, i);
    console.log();
  }
}

main();
