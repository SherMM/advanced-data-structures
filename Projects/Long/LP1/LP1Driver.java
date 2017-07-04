/** Driver program for LP1, level 1
 *  @author rbk
 *  Edit and replace "XYZ" by the name of your class
 */

public class LP1Driver {
    public static void main(String[] args) {
	XYZ a = new XYZ("1234567890123456789012345678901234567890");
	XYZ b = new XYZ(999);
	XYZ c = XYZ.add(a, b);
	XYZ d = XYZ.subtract(c, a);
	XYZ e = XYZ.product(c, a);
	XYZ zero = new XYZ(0);
	XYZ f = XYZ.product(a, zero);
	XYZ two = new XYZ(2);
	XYZ g = XYZ.power(two, 1025);
	System.out.println("a = " + a);
	System.out.println("b = " + b);
	System.out.println("c=a+b = " + c);
	System.out.println("a+b-a = " + d);
	System.out.println("a*c = " + e);
	System.out.println("a*0 = " + f);
	System.out.println("2^1025 = " + g);
	System.out.println("Internal representation:");
	g.printList();
    }
}
/*
Output:
a = 1234567890123456789012345678901234567890
b = 999
c=a+b = 1234567890123456789012345678901234568889
a+b-a = 999
a*c = 1524157875323883675049535156256668195733866777995869531010835238422208352374210
a*0 = 0
2^1025 = 359538626972463181545861038157804946723595395788461314546860162315465351611001926265416954644815072042240227759742786715317579537628833244985694861278948248755535786849730970552604439202492188238906165904170011537676301364684925762947826221081654474326701021369172596479894491876959432609670712659248448274432
Internal representation:
*****Depends on the base********
*/
