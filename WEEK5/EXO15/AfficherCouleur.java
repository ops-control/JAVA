void f(int a) {
    if (a < 0) {
        throw new RuntimeException("Negative number");
    } else {
        System.out.println("The provided number is OK!");
    }
}

void g(int a) {
	try {
	    f(a);
	} catch (Exception e) {
	    e.printStackTrace();
	}
}