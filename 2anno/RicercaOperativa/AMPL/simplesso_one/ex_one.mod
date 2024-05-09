var x1>=0;
var x2>=0;
var x3<=0;
var x4>=0;

minimize z: 7*x1+2*x2+5*x3-x4;

s.t. M1: 4*x1+3*x2+2*x4<=2;
	M2:  x1-3*x2-x3 <=1;

      